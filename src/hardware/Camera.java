/*
 * Camera
 *
 * Description:
 *  Base class as provided in the assignment details.
 */

package hardware;

public abstract class Camera
{
  public Camera() {}
  public void takePhoto() {}
  protected abstract void photoReady(char [] photoData);
}
