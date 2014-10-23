/*
 * TurnTask
 *
 * Description
 *  This is the task for when the Rover has been instructed to turn.
 */

package task;

import hardware.Driver;

public class TurnTask implements Task {
  public final static double MAX_ANGLE = 180.0;
  public final static double MIN_ANGLE = -180.0;

  private double angle;
  private Driver driver;

  public TurnTask(Driver inDriver, double inAngle)
      throws IllegalArgumentException {
    driver = inDriver;
    if (inAngle <= MAX_ANGLE && inAngle >= MIN_ANGLE) {
      angle = inAngle;
    } else {
      throw new IllegalArgumentException("Angle: " + inAngle +
                                         " outside limits");
    }
  }

  @Override
  public void execute() {
    // Start the turn.
    driver.turn(angle);
  }

  public double getAngle() {
    return angle;
  }
}
