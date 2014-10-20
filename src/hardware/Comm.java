/*
 * Comm
 *
 * Description:
 *  Basic class as provided in the assignment details.
 */

package hardware;

public abstract class Comm
{
  public Comm() {}
  public void send(String message) {}
  protected abstract void receive(String message);
}
