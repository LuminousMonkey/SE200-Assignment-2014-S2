/*
 * MockRoverController
 *
 * For testing, we need finegrained control over the execution steps.
 * This mock object just gives us a method so we can call it.
 */

package controller;

public class MockRoverController extends RoverController {
  public void testExecute() {
    this.execute();
  }
}
