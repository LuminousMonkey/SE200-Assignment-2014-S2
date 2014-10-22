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
  private RoverState currentState;
  private Driver driver;
  private Camera camera;
  private SoilAnalyser soilAnalyser;
  private Comm comm;

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

  public Comm getComm() {
    return comm;
  }

  public void setComm(Comm inComm) {
    comm = inComm;
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

  public void setRunning() {
    currentState.setRunning(this);
  }

  public void addTaskList(String message) {
    TaskList taskListToAdd = new TaskList(this, message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());
  }

  public void execute(int taskListId) {
    currentTaskList = taskLists.get(taskListId);

    currentTaskList.execute();
  }

  public void execute() {
    currentTaskList.execute();
  }

  public void result(String inResult) {
  }

  public void error(String inResult) {
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
