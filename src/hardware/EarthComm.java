/*
 * EarthComm
 *
 * Description:
 *
 */

package hardware;

import controller.RoverController;

public class EarthComm extends Comm {
  private RoverController controller;

  private EarthComm() {}

  public EarthComm(RoverController inController) {
    controller = inController;
  }

  protected void receive(String message) {
    controller.addTaskList(message);
  }
}
