/*
 * RoverState
 *
 * Description:
 *   The Rover doesn't have a flow control like a normal program, it's
 *   all event based. As such, the RoverState class is where the flow
 *   of event responses resides.
 */

package controller;

import task.TaskList;

public abstract class RoverState {
  /*
   * Rover is idle, nothing to do until we receive a message from
   * Earth.
   */
  public abstract void setIdle(RoverController inContext);

  /*
   * Rover is currently running a Task from a Task list, nothing else
   * can happen until it completes.
   */
  public abstract void setRunning(RoverController inContext);
}
