/*
 * RoverCamera
 *
 * Description:
 *  Basic camera class, inherits from the assignment class.
 */

package hardware;

import controller.RoverController;

public class RoverCamera extends Camera {
  private RoverController context;

  // Be sure we're provided with a context.
  private RoverCamera() {}

  public RoverCamera(RoverController inContext) {
    super();
    context = inContext;
  }

  protected void photoReady(char[] photoData) {
  }
}
