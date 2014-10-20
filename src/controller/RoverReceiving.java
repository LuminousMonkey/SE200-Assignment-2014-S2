/*
 * RoverReceiving
 *
 * Description:
 *  The Rover enters this state when receiving a message from Earth.
 */

package controller;

public class RoverReceiving extends RoverState {
  /*
   * The Rover can only go into the idle state from receiving if there
   * was an error with the message from Earth.
   */
  public void setIdle() {
  }

  /*
   * This shouldn't happen, we're already receiving!
   */
  public void setReceiving() {
  }

  /*
   * Shouldn't happen, however, maybe a future feature is to send back
   * an error message if list receiving was corrupted.
   */
  public void setSending() {
  }

  /*
   * This is the next state, once a list has been received, it's to be
   * executed.
   */
  public void setRunning() {
  }

  /*
   * This shoudn't happen.
   */
  public void setResultReady() {}
}
