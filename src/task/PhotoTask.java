/*
 * PhotoTask
 *
 * Description
 *  The task that will take a photo with the camera.
 */

package task;

import hardware.Camera;

public class PhotoTask implements Task {
  private Camera camera;

  public PhotoTask(Camera inCamera) {
    camera = inCamera;
  }

  public void execute() {
    camera.takePhoto();
  }
}
