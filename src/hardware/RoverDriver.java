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
  // Need the context to call when an event occurs.
  private RoverController context;

  // This should not be able to be created without a RoverState of some kind.
  private RoverDriver() {};

  /*
   * Create the Rover driver for moving the Rover.
   *
   * state - RoverState which to use for updates to the status. i.e.
   * moveFinshed, mechanicalError
   */
  public RoverDriver(RoverController inContext) {
    super();
    context = inContext;
  }

  public void moveFinished() {
    context.result("Move finished.");
  }

  public void mechanicalError() {
    context.error("Mechanical Error");
  }
}
