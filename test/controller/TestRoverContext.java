/*
 * TestRoverContext
 *
 * Description
 *
 *  Tests the rover context, the rover context is the linchpin of the
 *  system.
 */

package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hardware.MockDriver;
import hardware.MockComm;

public class TestRoverContext {
  private RoverController context;
  private MockDriver driver;
  private MockComm comm;

  /*
   * testListExecuteList
   *
   * Tests that if we add two lists, that one list can execute the
   * other.
   */
  @Test
  public void testListExecuteList() {
    newContext();

    // Add out two lists.
    context.addTaskList("L1 {M 100\nT 50}");
    context.addTaskList("L2 {M 50\nT 25\nL 1}");

    // Execute list two, which should execute list 1 last.
    context.execute(2);
    context.execute();

    // Should be the distance and angle from the first list.
    assertEquals(100.0, driver.getDistanceReceived(), 0.1);
    assertEquals(50.0, driver.getAngleReceived(), 0.1);
  }

  /*
   * testPendingExecuted
   *
   * Description
   *  Test that if we add two lists, one that executes the other, that
   *  the pending lists get execute.
   */
  @Test
  public void testPendingExecuted() {
    newContext();

    // Add out two lists.
    context.addTaskList("L1 {M 100\nT 50}");
    context.addTaskList("L2 {M 50\nT 25\nL 1}");

    // Execute list two, which should execute list 1 last.
    context.executePending();

    // Should be the distance and angle from the first list.
    assertEquals(100.0, driver.getDistanceReceived(), 0.1);
    assertEquals(50.0, driver.getAngleReceived(), 0.1);
  }

  /*
   * testBasicTaskFlow
   *
   * Description
   *  Test receving a message, getting a response, then sending the
   *  response back.
   */
  @Test
  public void testBasicTaskFlow() {
    newContext();

    assertTrue(context.getState() instanceof RoverIdle);

    System.out.println(context.getState().toString());

    comm.testReceive("L1 {M 100\n}");

    System.out.println(context.getState().toString());

    assertTrue(context.getState() instanceof RoverRunning);
  }

  private void newContext() {
    context = new RoverController();
    driver = new MockDriver(context);
    comm = new MockComm(context);

    context.setDriver(driver);
    context.setComm(comm);
  }
}
