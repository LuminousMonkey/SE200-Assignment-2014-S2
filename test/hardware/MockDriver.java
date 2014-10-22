package hardware;

import controller.RoverController;

public class MockDriver extends RoverDriver {
  private double distanceReceived = 0.0;
  private double angleReceived = 0.0;

  public MockDriver(RoverController context) {
    super(context);
  }

  @Override
  public void drive(double inDistance) {
    distanceReceived = inDistance;
    System.out.println("Got task to drive: " + inDistance);
  }

  @Override
  public void turn(double inAngle) {
    angleReceived = inAngle;
    System.out.println("Got task to turn: " + inAngle);
  }

  public void testMoveFinished() {
    moveFinished();
  }

  public void testMechanicalError() {
    mechanicalError();
  }

  /*
   * getDistanceReceived
   *
   * Read in the distance that would have been received.
   * WARNING: Will reset the distance back to 0.0 after being read.
   */
  public double getDistanceReceived() {
    double result = distanceReceived;
    distanceReceived = 0.0;
    return result;
  }

  public double getAngleReceived() {
    double result = angleReceived;
    angleReceived = 0.0;
    return result;
  }
}
