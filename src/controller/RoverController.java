package controller;

import java.util.ArrayList;
import hardware.Camera;
import hardware.Driver;
import hardware.SoilAnalyser;
import task.TaskList;

public class RoverController {
  private ArrayList<TaskList> taskLists;
  private RoverState currentState;
  private Driver driver;
  private Camera camera;
  private SoilAnalyser soilAnalyser;

  public RoverController() {
    currentState = new RoverIdle();
  }

  public Driver getDriver() {
    return driver;
  }

  public Camera getCamera() {
    return camera;
  }

  public SoilAnalyser getSoilAnalyser() {
    return soilAnalyser;
  }

  public void setState(RoverState newState) {
    currentState = newState;
  }

  public RoverState getState() {
    return currentState;
  }

  public void execute(int taskListId) {
  }

  public void setIdle() {
    currentState.setIdle(this);
  }

  public void setReceiving(String message) {
    currentState.setReceiving(this, message);
  }

  public void setSending(String message) {
    currentState.setSending(this, message);
  }

  public void setRunning(TaskList inTaskList) {
    currentState.setRunning(this, inTaskList);
  }

  public void setResultReady(String result) {
    currentState.setResultReady(this, result);
  }

  public void setErrorOccured(String error) {
    currentState.setErrorOccured(this, error);
  }
}
