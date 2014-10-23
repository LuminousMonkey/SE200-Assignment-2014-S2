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
import task.TaskListManager;
import task.TaskParser;

public class RoverController {
  private TaskListManager taskListManager;
  private TaskParser taskParser;

  private Camera camera;
  private Comm comm;
  private Driver driver;
  private SoilAnalyser soilAnalyser;

  public RoverController() {
    taskListManager = new TaskListManager(this);
    taskParser = new TaskParser(this);

    camera = new RoverCamera(this);
    comm = new EarthComm(this);
    driver = new RoverDriver(this);
    soilAnalyser = new RoverSoilAnalyser(this);
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

  public void setTaskListManager(TaskListManager inTaskListManager) {
    taskListManager = inTaskListManager;
  }

  public TaskListManager getTaskListManager() {
    return taskListManager;
  }

  public TaskParser getTaskParser() {
    return taskParser;
  }

  public void addTaskList(String message) {
    taskListManager.addTaskList(message);
  }

  public void setResult(String inResult) {
    // Send the result back.
    comm.send(inResult);
    taskListManager.execute();
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
    taskListManager.executePending();
  }
}
