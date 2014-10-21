/*
 * EarthComm
 *
 * Description:
 *
 */

package hardware;

import controller.RoverController;

public class EarthComm extends Comm {
  private RoverController context;

  private EarthComm() {}

  public EarthComm(RoverController inContext) {
    context = inContext;
  }

  protected void receive(String message) {
    context.setReceiving(message);
  }
}
