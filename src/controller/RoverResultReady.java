/*
 * RoverResultReady
 */

package controller;

import task.TaskList;

public class RoverResultReady extends RoverState {
  // We should be going to sending.
  public void setIdle(RoverController inContext) {
  }

  // Should be going to sending.
  public void setReceiving(RoverController inContext,
                           String message) {
  }

  // This is where we expect to go.
  public void setSending(RoverController inContext,
                         String message) {
  }

  // Try to send the task first.
  public void setRunning(RoverController inContext,
                         int taskListId) {
  }

  // We already have results.
  public void setResultReady(RoverController inContext,
                             String result) {
  }

  public void setErrorOccured(RoverController inContext,
                              String error) {
  }
}
