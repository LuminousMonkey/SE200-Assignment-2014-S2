/*
 * TestTaskParser
 *
 * Description
 *  Unit tests to check that the correct tasks are returned for given
 *  strings.
 */
package task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.RoverController;

public class TestTaskParser {
  private final static double EPSILON = 0.01;

  private RoverController context;
  private TaskParser taskParser;

  @Before
  public void setUp() {
    context = new RoverController();
    taskParser = new TaskParser(context);
  }

  @Test
  public void testMoveTask() {
    Task result = taskParser.parseTask("M 50");

    assertTrue(result instanceof MoveTask);

    // We've already asserted it's a MoveTask.
    assertEquals(50, ((MoveTask) result).getDistance(), EPSILON);
  }

  @Test
  public void testTurnTask() {
    Task result = taskParser.parseTask("T 93.7");

    assertTrue(result instanceof TurnTask);

    assertEquals(93.7, ((TurnTask) result).getAngle(), EPSILON);
  }

  @Test
  public void testPhotoTask() {
    Task result = taskParser.parseTask("P");

    assertTrue(result instanceof PhotoTask);
  }

  @Test
  public void testSoilAnalysisTask() {
    Task result = taskParser.parseTask("S");

    assertTrue(result instanceof SoilAnalysisTask);
  }

  @Test(expected=TaskParseException.class)
  public void testInvalidTask() {
    Task result = taskParser.parseTask("UNKNOWN");
  }
}
