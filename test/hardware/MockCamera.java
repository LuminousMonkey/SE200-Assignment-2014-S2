/*
 * MockCamera
 *
 * Description
 *  Test class for the camera, with this should be able to check that
 *  photos have been requested, and to generate photoReady requests.
 */

package hardware;

import controller.RoverController;

public class MockCamera extends RoverCamera {
  private int noPhotoRequest = 0;

  public MockCamera(RoverController inContext) {
    super(inContext);
  }

  @Override
  public void takePhoto() {
    System.out.println("Got task to take photo.");
    noPhotoRequest++;
  }

  /*
   * testPhotoReady
   *
   * Since the photoReady method is protected, create a method.
   */
  public void testPhotoReady(char[] photoData) {
    this.photoReady(photoData);
  }

  /*
   * getTakePhotoRequest
   *
   * Returns the number of times a photo has been requested since the
   * list time this was called.
   * WARNING: Calling this resets it back to 0.
   */
  public int getTakePhotoRequest() {
    int result = noPhotoRequest;
    noPhotoRequest = 0;
    return result;
  }
}
