/*
 * RoverRunning
 *
 * Description:
 *  The Rover enters this state when receiving a message from Earth.
 */

package controller;

import task.TaskList;

public class RoverRunning extends RoverState {
  /*
   * The Rover can only go into the idle state from receiving if there
   * was an error with the message from Earth.
   */
  public void setIdle(RoverController inContext) {
  }

  /*
   * If we get any lists from Earth while we're running, then add
   * them, but delay execution until we've finished the current list.
   */
  public void setReceiving(RoverController inContext,
                           String message) {
    // We don't change state here (probably remove the receving state.)
    inContext.addTaskList(message);
  }

  /*
   * Shouldn't happen, however, maybe a future feature is to send back
   * an error message if list receiving was corrupted.
   */
  public void setSending(RoverController inContext,
                         String message) {
  }

  /*
   * This is the next state, once a list has been received, it's to be
   * executed.
   */
  public void setRunning(RoverController inContext,
                         int taskListId) {
    throw new IllegalStateException("Already running a task.");
  }

  // When a task has been executed, then we're waiting for a result.
  public void setWaitingForResult(RoverController inContext) {
    inContext.setState(new RoverWaiting());
  }

  /*
   * This shoudn't happen.
   */
  public void setResultReady(RoverController inContext,
                             String result) {
    // There's a result ready.

  }

  public void setErrorOccured(RoverController inContext,
                              String error) {
  }
}
