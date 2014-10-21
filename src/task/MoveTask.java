/*
 * MoveTask
 *
 * Description:
 *  This is the task that will make the Rover move forward.
 */

package task;

import controller.RoverController;
import hardware.Driver;

public class MoveTask extends Task {
  private double distance;

  public MoveTask(RoverController inController, double inDistance) {
    super(inController);
    distance = inDistance;
  }

  public void execute() {
    // Start the move.
    Driver currentDriver = controller.getDriver();
    currentDriver.drive(distance);
  }
}
