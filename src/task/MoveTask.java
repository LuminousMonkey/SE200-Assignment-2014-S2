/*
 * MoveTask
 *
 * Description:
 *  This is the task that will make the Rover move forward.
 */

package task;

import controller.RoverController;
import hardware.Driver;

public class MoveTask implements Task {
  public final static double MAX_DISTANCE_FWD = 100.0;
  public final static double MAX_DISTANCE_REV = -100.0;

  private Driver driver;
  private double distance;

  public MoveTask(Driver inDriver, double inDistance)
      throws IllegalArgumentException  {
    driver = inDriver;
    if (inDistance <= MAX_DISTANCE_FWD && inDistance >= MAX_DISTANCE_REV) {
      distance = inDistance;
    } else {
      throw new IllegalArgumentException("Distance: " + inDistance +
                                         " outside limits.");
    }
  }

  public void execute() {
    // Start the move.
    driver.drive(distance);
  }

  public double getDistance() {
    return distance;
  }
}
