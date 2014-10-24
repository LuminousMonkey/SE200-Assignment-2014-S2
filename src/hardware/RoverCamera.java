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

  @Override
  public void takePhoto() {
    System.out.println("Request for photo.");
    PhotoThread responseThread = new PhotoThread();
    Thread t = new Thread(responseThread);
    t.start();
  }

  @Override
  protected void photoReady(char[] inPhotoData) {
    String photoMessage = new String(inPhotoData);
    controller.receiveResult(photoMessage);
  }

  private class PhotoThread implements Runnable {
    // Photos take the same amount of time to take.
    private final static int PHOTO_DELAY = 500;

    public void run() {
      System.out.println("Photo thread running.");

      try {
        Thread.sleep(PHOTO_DELAY);
        photoReady("Photo Ready".toCharArray());
      } catch (InterruptedException e) {
        System.out.println("Photo thread interrupted");
      }
    }
  }
}
