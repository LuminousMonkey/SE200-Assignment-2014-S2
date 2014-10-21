/*
 * RoverReceiving
 *
 * Description:
 *  The Rover enters this state when receiving a message from Earth.
 */

package controller;

import task.TaskList;

public class RoverReceiving extends RoverState {
  /*
   * The Rover can only go into the idle state from receiving if there
   * was an error with the message from Earth.
   */
  public void setIdle(RoverController inContext) {
  }

  /*
   * This shouldn't happen, we're already receiving!
   */
  public void setReceiving(RoverController inContext,
                           String message) {
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
                         TaskList inTaskList) {
  }

  /*
   * This shoudn't happen.
   */
  public void setResultReady(RoverController inContext,
                             String result) {
  }

  public void setErrorOccured(RoverController inContext,
                              String error) {
  }
}
