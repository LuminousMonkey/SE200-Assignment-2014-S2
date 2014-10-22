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

  public void setRunning(RoverController inContext) {
    inContext.executePending();
  }

  @Override
  public String toString() {
    return "State: Idle";
  }
}
