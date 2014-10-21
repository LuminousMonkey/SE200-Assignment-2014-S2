/*
 * EarthComm
 *
 * Description:
 *
 */

package hardware;

public class EarthComm extends Comm {
  protected void receive(String message) {
    System.out.println("Received message" + message);
  }
}
