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

  @Override
  public void receive(String message) {
    System.out.println("Receving message from Earth.");
    controller.addTaskList(message);
  }

  @Override
  public void send(String message) {
    System.out.println("Message: \"" + message + "\" sent to Earth");
  }
}
