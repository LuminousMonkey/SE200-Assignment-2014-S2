/*
 * RoverIdle
 *
 * Description:
 * State when the Rover has finished all tasks and is waiting for more
 * tasks from Earth.
 */

package controller;

public class RoverIdle extends RoverState {
  // We're already idle.
  public void setIdle() {
  }

  // My tallest, message from... EAR..TH.
  public void setReceiving() {
  }

  // We shouldn't be going from idle to sending.
  public void setSending() {
  }

  // We shouldn't be going from idle to running without receiving
  // something first.
  public void setRunning() {
  }

  // Can't have any results yet, no task is running.
  public void setResultReady() {}
}
