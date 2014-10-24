/*
 * RoverController
 *
 * Description
 *
 * This is the class that binds everything together. It is what holds
 * the current context of the Rover, including task lists and also
 * contains the different devices, such as.
 */

package controller;

import hardware.Camera;
import hardware.Comm;
import hardware.Driver;
import hardware.SoilAnalyser;

import hardware.RoverCamera;
import hardware.EarthComm;
import hardware.RoverDriver;
import hardware.RoverSoilAnalyser;

import task.TaskList;
import task.TaskParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;

public class RoverController {
  private Camera camera;
  private Comm comm;
  private Driver driver;
  private SoilAnalyser soilAnalyser;

  private HashMap<Integer,TaskList> taskLists;
  private TaskParser taskParser;

  // Current running task list.
  private TaskList currentTaskList;

  // Lists that are pending to be executed.
  private HashSet<Integer> pendingLists;

  // Lists can recurse, which means we have to keep track of which
  // lists we have come from when we execute a sublist.
  // This is what this array is for.
  private Stack<Integer> parentLists;

  /*
   * RoverController
   *
   * Since the controller and the rover devices are linked, this
   * constructor will create the instances of the devices by default.
   *
   * Because we're using a "one to one only" Observer type pattern,
   * creating the devices involves sending the instances the
   * controller instance itself.
   *
   * This results in a bit of verbose code, so it's been moved here by
   * default. However, setters exist so different instances can be
   * passed in. For example, if testing is necessary. (Check the unit
   * test code for examples.)
   */
  public RoverController() {
    taskParser = new TaskParser(this);

    camera = new RoverCamera(this);
    comm = new EarthComm(this);
    driver = new RoverDriver(this);
    soilAnalyser = new RoverSoilAnalyser(this);

    taskLists = new HashMap<Integer,TaskList>();
    pendingLists = new LinkedHashSet<Integer>();
    parentLists = new Stack<Integer>();
  }

  /*
   * Getters and setters for the hardware devices. Since the
   * controller is the central hub it pretty much touches everything.
   */
  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver inDriver) {
    driver = inDriver;
  }

  public Camera getCamera() {
    return camera;
  }

  public void setCamera(Camera inCamera) {
    camera = inCamera;
  }

  public void setComm(Comm inComm) {
    comm = inComm;
  }

  public SoilAnalyser getSoilAnalyser() {
    return soilAnalyser;
  }

  public void setSoilAnalyser(SoilAnalyser inAnalyser) {
    soilAnalyser = inAnalyser;
  }

  /*
   * Controller methods
   *
   * These are the methods that actually do the work, such as adding
   * new task lists, executing, and responding to results from the
   * devices.
   */

  /*
   * addTaskList
   *
   * Given a message string from Earth, adds the necessary task list.
   * The list gets added to the pending queue so it will be executed
   * when any other lists have finished running.
   *
   * Adding a list will not cause it to be executed, it has to be
   * running already, or executePending() has to be called after.
   */
  public void addTaskList(String message) {
    TaskList taskListToAdd = new TaskList(taskParser, message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());
  }

  /*
   * receiveResult
   *
   * Receive a result from the hardware, expects a String, so the
   * hardware needs to convert it to a string for transmission first.
   */
  public void receiveResult(String inResult) {
    comm.send(inResult);
    execute();
  }

  /*
   * receiveError
   *
   * Receive an error from the hardware, expects a string of the
   * error. The error will be reported back to Earth, and the Rover
   * will continue onto the next list. If the Rover was executing a
   * sub list, it will back to the parent list and move onto the
   * next command there.
   */
  public void receiveError(String inError) {
    // Send the error back to earth.
    comm.send(inError);
    executeWhenError();
  }

  /*
   * execute
   *
   * Given an id of a list to execute, run that list directly.
   */
  public void execute(int taskListId) {
    currentTaskList = taskLists.get(taskListId);
    currentTaskList.resetListCursor();
    currentTaskList.execute();
  }

  /*
   * executeSubList
   *
   * When we have a list task execute command, we need to save the
   * current list we're on (so we can backtrack) then execute the
   * requested list number.
   */
  public void executeSubList(int taskListId) {
    parentLists.add(currentTaskList.getListId());
    execute(taskListId);
  }

  /*
   * executePending
   *
   * Get the next list that is pending to be executed. Pending lists
   * are any lists we've received but not yet executed.
   */
  public void executePending() {
    // Just get the first one.
    Iterator<Integer> i = pendingLists.iterator();
    if (i.hasNext()) {
      Integer element = i.next();
       execute(element);
      i.remove();
    }
  }

  /*
   * execute
   *
   * Will either execute the next command in the current list, or, if
   * we've finished the current list, execute any lists that aren't
   * pending.
   *
   * This is protected instead of private so it can be tested in the
   * unit tests.
   */
  protected void execute() {
    if (currentTaskList.hasNext()) {
      currentTaskList.execute();
    } else {
      executeWhenError();
    }
  }

  /*
   * executeWhenError()
   *
   * Execution when we have an error it a little tricky. If we have an
   * error while we are recursed into a another list (via the execute
   * list command from Earth), we need to quit that list, but keep
   * trying to execute through the parent list.
   */
  private void executeWhenError() {
    // If we have an error, and have something on our stack.
    // Pop off the stack, and continue running
    if (parentLists.isEmpty()) {
      executePending();
    } else {
      int parentList = parentLists.pop();
      currentTaskList = taskLists.get(parentList);
      execute();
    }
  }
}
