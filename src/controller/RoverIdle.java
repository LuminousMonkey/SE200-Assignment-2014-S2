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
    throw new IllegalStateException("Trying to switch to idle when idle.");
  }

  // My tallest, message from... EAR..TH.
  public void setReceiving(RoverController inContext,
                           String message) {
    inContext.setState(new RoverReceiving());
    inContext.addTaskList(message);

    // Since we're idle, execute any pending lists.
    inContext.executePending();
  }

  // We shouldn't be going from idle to sending.
  public void setSending(RoverController inContext,
                         String message) {
    throw new IllegalStateException("Going from idle to sending.");
  }

  // We shouldn't be going from idle to running without receiving
  // something first.
  public void setRunning(RoverController inContext,
                         int taskListId) {
    throw new IllegalStateException("Trying to run directly from idle.");
  }

  // Results can't be ready until a task has finished, and since we
  // can't move on until the current task has finished we can't go
  // from idle to result ready.
  public void setResultReady(RoverController inContext,
                             String result) {
    throw new IllegalStateException("Result ready from idle.");

  }

  // Shouldn't have any errors when idle.
  public void setErrorOccured(RoverController inContext,
                              String error) {
    throw new IllegalStateException("Error while idle.");
  }
}
