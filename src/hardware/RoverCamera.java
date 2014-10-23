/*
 * RoverCamera
 *
 * Description:
 *  Basic camera class, inherits from the assignment class.
 */

package hardware;

import controller.RoverController;

public class RoverCamera extends Camera {
  private RoverController controller;

  // Be sure we're provided with a controller.
  private RoverCamera() {}

  public RoverCamera(RoverController inController) {
    super();
    controller = inController;
  }

  protected void photoReady(char[] photoData) {
  }
}
