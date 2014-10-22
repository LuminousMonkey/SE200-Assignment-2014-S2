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
import task.TaskList;

public class RoverController {
  private HashMap<Integer,TaskList> taskLists;
  private RoverState currentState;
  private Driver driver;
  private Camera camera;
  private SoilAnalyser soilAnalyser;

  // Current running task list.
  private TaskList currentTaskList;

  private String receivedMessage;
  private String errorMessage;
  private String result;

  // Lists that are pending to be executed.
  private HashSet<Integer> pendingLists;

  public RoverController() {
    currentState = new RoverIdle();
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

  public SoilAnalyser getSoilAnalyser() {
    return soilAnalyser;
  }

  public void setMessage(String inMessage) {
    receivedMessage = inMessage;
  }

  public String getMessage() {
    return receivedMessage;
  }

  public void setSoilAnalyser(SoilAnalyser inAnalyser) {
    soilAnalyser = inAnalyser;
  }

  public void setState(RoverState newState) {
    currentState = newState;
  }

  public RoverState getState() {
    return currentState;
  }

  public void setIdle() {
    currentState.setIdle(this);
  }

  public void setReceiving(String message) {
    // If this state is invalid, then it will throw an exception. So
    // we can proceed with adding and parsing the message to the
    // TaskList.
    currentState.setReceiving(this, message);
  }

  public void setSending(String message) {
    currentState.setSending(this, message);
  }

  public void setRunning(int taskListId) {
    currentState.setRunning(this, taskListId);
  }

  public void setWaitingForResult() {
    currentState.setWaitingForResult(this);
  }

  public void setResultReady(String result) {
    currentState.setResultReady(this, result);
  }

  public void setErrorOccured(String error) {
    currentState.setErrorOccured(this, error);
  }

  public void addTaskList(String message) {
    TaskList taskListToAdd = new TaskList(this, message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());
  }

  public void execute(int taskListId) {
    TaskList taskListToAdd = taskLists.get(taskListId);

    taskListToAdd.execute();
  }

  /*
   * executePending
   *
   * Execute any pending tasks lists that have been added while we've
   * been running.
   */
  public void executePending() {
    for (Iterator<Integer> i = pendingLists.iterator(); i.hasNext();) {
      Integer element = i.next();

      execute(element);
      i.remove();
    }
  }
}
