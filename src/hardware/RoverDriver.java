/*
 * RoverDriver
 *
 * Description:
 *  Basic driver class, inherits from the assignment class so we can
 *  call out state object when the Driver has finished moving, or if
 *  there's an error.
 */

package hardware;

public class RoverDriver extends Driver {
  private RoverController controller;

  // This should not be able to be created without a RoverState of some kind.
  private RoverDriver();

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

  public void moveFinished() {
    state.setResultReady("RoverDriver: Move finished.");
  }

  public void mechanicalError() {
    state.setError("RoverDriver: Mechanical Error.");
  }
}
