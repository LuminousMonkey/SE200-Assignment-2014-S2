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

public class TestTaskList {
  private RoverController context;

  @Before
  public void setUp() {
    context = new RoverController();
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
}
