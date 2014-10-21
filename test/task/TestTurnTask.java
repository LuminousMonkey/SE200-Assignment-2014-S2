/*
 * TestTurnTask
 *
 * Description
 *  Tests that relate specificially to the TurnTask.
 */

package task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.RoverController;

public class TestTurnTask {
  private final static double EPSILON = 0.01;
  private RoverController context;

  @Before
  public void setUp() {
    context = new RoverController();
  }

  @Test
  public void testTurnLimits() {
    TurnTask result = new TurnTask(context, -100.0);
    assertEquals(-100, result.getAngle(), EPSILON);

    result = new TurnTask(context, 100.0);
    assertEquals(100, result.getAngle(), EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMaxAngle() {
    TurnTask result = new TurnTask(context, TurnTask.MAX_ANGLE + EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMinAngle() {
    TurnTask result = new TurnTask(context, TurnTask.MIN_ANGLE - EPSILON);
  }
}
