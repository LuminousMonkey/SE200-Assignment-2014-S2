/*
 * TestComm
 *
 * Description:
 *  Test class for comms, since the method we want to trigger for
 *  testing is protected, we're making this subclass so we can send
 *  our own messages for testing.
 */

package hardware;

public class TestComm extends EarthComm {
  public RoverComm() {
    super();
  }

  /*
   * testReceive
   *
   * Since the receive method is protected, it can't be accessed
   * outside its subclasses. So we add a public method here so we can
   * test.
   */
  public void testReceive(String message) {
    this.receive(message);
  }
}
