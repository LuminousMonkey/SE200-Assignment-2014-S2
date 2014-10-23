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

import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import hardware.Camera;
import hardware.Driver;
import hardware.SoilAnalyser;
import hardware.Comm;

import task.TaskList;

public class RoverController {
  private HashMap<Integer,TaskList> taskLists;
  private Driver driver;
  private Camera camera;
  private SoilAnalyser soilAnalyser;
  private Comm comm;

  // Current running task list.
  private TaskList currentTaskList;

  // Lists that are pending to be executed.
  private HashSet<Integer> pendingLists;

  public RoverController() {
    taskLists = new HashMap<Integer,TaskList>();
    pendingLists = new LinkedHashSet<Integer>();
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

  public Comm getComm() {
    return comm;
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
    TaskList taskListToAdd = new TaskList(this, message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());
  }

  public void execute(int taskListId) {
    currentTaskList = taskLists.get(taskListId);
    currentTaskList.resetListCursor();
    currentTaskList.execute();
  }

  public void execute() {
    if (currentTaskList.hasNext()) {
      currentTaskList.execute();
    } else {
      // No more tasks in the current list, execute anything pending.
      executePending();
    }
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
    executePending();
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
      execute(element);
      i.remove();
    }
  }
}
