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
  public final static double MAX_DISTANCE_FWD = 100.0;
  public final static double MAX_DISTANCE_REV = -100.0;

  private double distance;

  public MoveTask(RoverController inContext, double inDistance)
      throws IllegalArgumentException  {
    super(inContext);
    if (inDistance <= MAX_DISTANCE_FWD && inDistance >= MAX_DISTANCE_REV) {
      distance = inDistance;
    } else {
      throw new IllegalArgumentException("Distance: " + inDistance +
                                         " outside limits.");
    }
  }

  public void execute() {
    // Start the move.
    context.getDriver().drive(distance);
  }

  public double getDistance() {
    return distance;
  }
}
