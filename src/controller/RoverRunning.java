/*
 * RoverWaiting
 *
 * Description
 *  The state for when the Rover is waiting on a device to finish.
 */

package controller;

import task.TaskList;

public class RoverRunning extends RoverState {
  public void setIdle(RoverController inContext) {
  }

  public void setRunning(RoverController inContext) {
  }

  @Override
  public String toString() {
    return "State: Running";
  }
}
