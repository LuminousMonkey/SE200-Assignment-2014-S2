/*
 * RoverDriver
 *
 * Description:
 *  Basic driver class, inherits from the assignment class so we can
 *  call out state object when the Driver has finished moving, or if
 *  there's an error.
 */

package hardware;

import controller.RoverController;

public class RoverDriver extends Driver {
  // Need the controller to call when an event occurs.
  private RoverController controller;

  // This should not be able to be created without a RoverState of some kind.
  private RoverDriver() {};

  /*
   * Create the Rover driver for moving the Rover.
   *
   * state - RoverState which to use for updates to the status. i.e.
   * moveFinshed, mechanicalError
   */
  public RoverDriver(RoverController inController) {
    super();
    controller = inController;
  }

  /*
   * drive
   *
   * This is the method that gets called when we want the rover to
   * drive a certain distance. This is here to override the base class
   * so we can actually get some output and a response from the driver
   * module.
   *
   * When this function is called, it will start up another thread
   * which will wait an amount of time (based on the distance), when
   * the time is up, it will trigger the moveFinished, or
   * mechanicalError methods.
   */
  @Override
  public void drive(double distance) {
    System.out.println("Request to move: " + distance);
    DriveThread responseThread = new DriveThread(distance);
    Thread t = new Thread(responseThread);
    t.start();
  }

  @Override
  public void turn(double inAngle) {
    System.out.println("Request to turn: " + inAngle);
    TurnThread responseThread = new TurnThread(inAngle);
    Thread t = new Thread(responseThread);
    t.start();
  }

  protected void moveFinished() {
    controller.setResult("Move finished.");
  }

  protected void mechanicalError() {
    controller.setError("Mechanical Error");
  }

  /*
   * Our private class so we can spawn a thread that will generate
   * events after turn or drive requests have been issued.
   *
   * This is not in the UML because it's just for running the code for
   * the simulation. It's not really part of the assignment design.
   */
  private class DriveThread implements Runnable {
    // Scale the time taken by distance (10M/sec) fast for a Rover.
    private final static int TIME_SCALING = 100;

    private double distance;

    public DriveThread(double inDistance) {
      distance = inDistance;
    }

    public void run() {
      System.out.println("Drive thread running");
      try {
        Thread.sleep((int) Math.abs(distance) * TIME_SCALING);

        moveFinished();
      } catch (InterruptedException e) {
        System.out.println("Drive thread interrupted.");
      }
    }
  }

  private class TurnThread implements Runnable {
    // Scan the time taken by angle (approx 360 deg in 2 sec.)
    private final static int TIME_SCALING = 6;

    private double angle;

    public TurnThread(double inAngle) {
      angle = inAngle;
    }

    public void run() {
      System.out.println("Turn thread running.");
      try {
        Thread.sleep((int) Math.abs(angle) * TIME_SCALING);
        moveFinished();
      } catch (InterruptedException e) {
        System.out.println("Turn thread interrupted");
      }
    }
  }
}
