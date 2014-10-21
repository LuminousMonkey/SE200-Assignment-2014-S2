/*
 * RoverIdle
 *
 * Description:
 * State when the Rover has finished all tasks and is waiting for more
 * tasks from Earth.
 */

package controller;

import task.TaskList;

public class RoverIdle extends RoverState {
  // We're already idle.
  public void setIdle(RoverController inContext) {
  }

  // My tallest, message from... EAR..TH.
  public void setReceiving(RoverController inContext,
                           String message) {
  }

  // We shouldn't be going from idle to sending.
  public void setSending(RoverController inContext,
                         String message) {
  }

  // We shouldn't be going from idle to running without receiving
  // something first.
  public void setRunning(RoverController inContext,
                         TaskList inTaskList) {
  }

  // Can't have any results yet, no task is running.
  public void setResultReady(RoverController inContext,
                             String result) {
  }

  // Shouldn't have any errors when idle.
  public void setErrorOccured(RoverController inContext,
                              String error) {
  }
}
