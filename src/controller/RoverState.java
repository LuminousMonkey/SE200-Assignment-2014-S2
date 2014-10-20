/*
 * RoverState
 *
 * Description:
 *   The Rover doesn't have a flow control like a normal program, it's
 *   all event based. As such, the RoverState class is where the flow
 *   of event responses resides.
 */

package controller;

public abstract class RoverState {

  /*
   * We have either errors, or results.
   */
  private String resultString;
  private String errorString;

  /*
   * Rover is idle, nothing to do until we receive a message from
   * Earth.
   */
  public abstract void setIdle();

  /*
   * Rover is receiving a message from Earth and parsing it into a
   * list.
   */
  public abstract void setReceiving();

  /*
   * Rover is sending a response back to Earth.
   */
  public abstract void setSending();

  /*
   * Rover is currently running a Task from a Task list, nothing else
   * can happen until it completes.
   */
  public abstract void setRunning();

  /*
   * Task has finished and Rover needs to send the message back to Earth.
   */
  public abstract void setResultReady();
}
