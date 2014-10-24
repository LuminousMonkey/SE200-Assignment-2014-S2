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

  public RoverController() {
    taskParser = new TaskParser(this);

    camera = new RoverCamera(this);
    comm = new EarthComm(this);
    driver = new RoverDriver(this);
    soilAnalyser = new RoverSoilAnalyser(this);

    taskParser = new TaskParser(this);
    taskLists = new HashMap<Integer,TaskList>();
    pendingLists = new LinkedHashSet<Integer>();
    parentLists = new Stack<Integer>();
  }

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

  public void addTaskList(String message) {
    TaskList taskListToAdd = new TaskList(taskParser, message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());
  }

  public void setResult(String inResult) {
    // Send the result back.
    comm.send(inResult);
    execute();
  }

  /*
   * setError
   *
   * If an error occurs, stop executing anymore tasks in the current
   * tasklist. Send a message back to Earth saying there was an error,
   * and start any pending task lists.
   */
  public void setError(String inError) {
    // Send the error back to earth.
    comm.send(inError);

    // Move onto the next task list.
    executeError();
  }

  public void execute() {
    if (currentTaskList.hasNext()) {
      currentTaskList.execute();
    } else {
      executeError();
    }
  }

  public void execute(int taskListId) {
    currentTaskList = taskLists.get(taskListId);
    currentTaskList.resetListCursor();
    currentTaskList.execute();
  }

  public void executeSubList(int taskListId) {
    parentLists.add(currentTaskList.getListId());
    execute(taskListId);
  }

  // Function just here for refactoring.
  public void executeError() {
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

  /*
   * executePending
   *
   * Get the next list that is pending to be executed.
   */
  public void executePending() {
    // Just get the first one.
    Iterator<Integer> i = pendingLists.iterator();
    if (i.hasNext()) {
      Integer element = i.next();
      parentLists.clear();
      execute(element);
      i.remove();
    }
  }
}
