/*
 * RoverWaiting
 *
 * Description
 *  The state for when the Rover is waiting on a device to finish.
 */

package controller;

import task.TaskList;

public class RoverWaiting extends RoverState {
  public void setIdle(RoverController inContext) {
  }

  public void setReceiving(RoverController inContext,
                           String message) {
  }

  public void setSending(RoverController inContext,
                         String message) {
  }

  public void setRunning(RoverController inContext,
                         int taskListId) {
  }

  public void setWaitingForResult(RoverController inContext) {
  }

  public void setResultReady(RoverController inContext,
                             String message) {
    // Received a ready event from what we're waiting on.
    inContext.setState(new RoverSending());
    inContext.setSending(message);
  }

  public void setErrorOccured(RoverController inContext, String error) {
    inContext.setState(new RoverSending());
    inContext.setSending(error);
  }
}
