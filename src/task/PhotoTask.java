/*
 * PhotoTask
 *
 * Description
 *  The task that will take a photo with the camera.
 */

package task;

import controller.RoverController;
import hardware.Camera;

public class PhotoTask extends Task {
  public PhotoTask(RoverController inContext) {
    super(inContext);
  }

  public void execute() {
    controller.getCamera().takePhoto();
  }
}
