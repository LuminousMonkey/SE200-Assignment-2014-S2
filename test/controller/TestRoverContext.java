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
import hardware.MockCamera;

public class TestRoverContext {
  private MockRoverController context;
  private MockDriver driver;
  private MockComm comm;
  private MockCamera camera;

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
    context.addTaskList("L3 {P\nL 2\nM 15}");

    context.execute(3);
    assertEquals(1, camera.getTakePhotoRequest());

    // Execute list two, which should execute list 1 last.
    context.execute();
    assertEquals(50.0, driver.getDistanceReceived(), 0.1);

    context.execute();
    assertEquals(25.0, driver.getAngleReceived(), 0.1);

    // Told to start executing the new list, will run the next task in
    // the new list straight away.
    context.execute();
    assertEquals(100.0, driver.getDistanceReceived(), 0.1);

    context.execute();
    assertEquals(50.0, driver.getAngleReceived(), 0.1);

    context.execute();
    assertEquals(15.0, driver.getDistanceReceived(), 0.1);
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

    // Add our two lists.
    context.addTaskList("L1 {M 100\nT 50}");
    context.addTaskList("L2 {M 50\nT 25\nL 1}");

    // Execute the lists.
    // Will run the first task in the first pending list.
    context.executePending();
    assertEquals(100.0, driver.getDistanceReceived(), 0.1);

    // Should be the distance and angle from the first list.
    context.execute();
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

    comm.testReceive("L1 {M 100\nT 50}");

    // It's received, but it won't execute until told.
    context.executePending();
    assertEquals(100.00, driver.getDistanceReceived(), 0.1);

    // It has issued the move, and will not proceed until it gets a
    // result.
    driver.testMoveFinished();

    // It should have send the result back, and execute the next task.
    assertEquals(50.0, driver.getAngleReceived(), 0.1);
  }

  /*
   * testFlowWithErrors
   *
   * Test that when we have an error on a task, it will stop the
   * current list and move onto the next pending task list.
   */
  @Test
  public void testFlowWithErrors() {
    newContext();

    comm.testReceive("L1 {M 45.4\nT 15\nP\nM 14.6\nS}");
    comm.testReceive("L2 {P\nP\nP\nS}");

    // We get it to execute the pending tasks, this will get it to run
    // the first task in L1
    context.executePending();
    assertEquals(45.4, driver.getDistanceReceived(), 0.1);

    driver.testMoveFinished();

    // Since the move has finished, it should have moved onto the next
    // command.
    assertEquals(15.0, driver.getAngleReceived(), 0.1);

    driver.testMechanicalError();

    // An error occured, should be taking a photo now.
    assertEquals(1, camera.getTakePhotoRequest());
  }

  /*
   * testReceiveMessageWhenRunning
   *
   * Test that new task lists will get added to the pending list when
   * the Rover is waiting for a result from moving, soil analysis,
   * etc.
   */
  @Test
  public void testReceiveMessageWhenRunning() {
    newContext();

    comm.testReceive("L1 {M 22\n}");

    context.executePending();
    assertEquals(22.0, driver.getDistanceReceived(), 0.1);

    // The rover is now waiting for the "moveFinished" event.
    // Send another message from Earth.
    comm.testReceive("L2 {M 88.5}");

    // Now, if we send a testMoveFinished event, it should execute the
    // new list.
    driver.testMoveFinished();

    assertEquals(88.5, driver.getDistanceReceived(), 0.1);
  }

  /*
   * testMultipleListExecTasks
   *
   * Test that we can call multiple chained TaskLists.
   */
  @Test
  public void testMultipleListExecTasks() {
    newContext();

    comm.testReceive("L1 {M 1}");
    context.executePending();
    assertEquals(1.0, driver.getDistanceReceived(), 0.1);
    driver.testMoveFinished();

    comm.testReceive("L2 {M 2\nL 1}");
    context.executePending();
    assertEquals(2.0, driver.getDistanceReceived(), 0.1);
    driver.testMoveFinished();

    // L2 calls list 1
    assertEquals(1.0, driver.getDistanceReceived(), 0.1);
  }

  private void newContext() {
    context = new MockRoverController();
    driver = new MockDriver(context);
    comm = new MockComm(context);
    camera = new MockCamera(context);

    context.setDriver(driver);
    context.setComm(comm);
    context.setCamera(camera);
  }
}
