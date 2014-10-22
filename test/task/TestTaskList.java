/*
 * TestTaskList
 *
 * Description
 *  Tests the TaskList, which should be able to take a message
 *  received from Earth and parse it into a TaskList.
 */

package task;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import controller.RoverController;
import hardware.MockDriver;

public class TestTaskList {
  private RoverController context;
  private MockDriver driver;

  @Before
  public void setUp() {
    context = new RoverController();
    driver = new MockDriver(context);
    context.setDriver(driver);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNoInstructions() {
    TaskList testTaskList = new TaskList(context, "ASD ASDF asd");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testEmptyList() {
    TaskList testTaskList = new TaskList(context, "L1 {}");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidListHeader() {
    TaskList testTaskList = new TaskList(context, "B1 {G 3\nH\nB 11\n}");
  }

  @Test(expected=TaskParseException.class)
  public void testInvalidListContents() {
    TaskList testTaskList = new TaskList(context, "L1 {G 3\nH\nB 11\n}");
  }

  @Test
  public void testValidList() {
    TaskList testTaskList = new TaskList(context, "L100 {M 100\nT 90\nP\nS}");
    assertEquals(100, testTaskList.getListId());
    assertEquals(4, testTaskList.getSize());
  }

  @Test
  public void testListExecute() {
    TaskList testTaskList = new TaskList(context, "L1 {M 100\n}");
    testTaskList.execute();

    assertEquals(100.0, driver.getDistanceReceived(), 0.1);

    testTaskList = new TaskList(context, "L2 {T -15\n}");
    testTaskList.execute();

    assertEquals(-15.0, driver.getAngleReceived(), 0.1);
  }

  @Test
  public void testListMultipleExecute() {
    TaskList testTaskList = new TaskList(context, "L4 {M 54.4\nT 55.3}");
    testTaskList.execute();

    assertEquals(54.4, driver.getDistanceReceived(), 0.1);
    assertEquals(55.3, driver.getAngleReceived(), 0.1);
  }
}
