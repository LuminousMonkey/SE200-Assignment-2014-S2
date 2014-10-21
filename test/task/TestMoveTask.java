/*
 * TestMoveTask
 *
 * Description
 *  Tests that relate specificially to the MoveTask.
 */

package task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.RoverController;

public class TestMoveTask {
  private final static double EPSILON = 0.01;
  private RoverController context;

  @Before
  public void setUp() {
    context = new RoverController();
  }

  @Test
  public void testMoveLimits() {
    MoveTask result = new MoveTask(context, -100.0);
    assertEquals(-100, result.getDistance(), EPSILON);

    result = new MoveTask(context, 100.0);
    assertEquals(100, result.getDistance(), EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMaxDistance() {
    MoveTask result = new MoveTask(context, MoveTask.MAX_DISTANCE_FWD + EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMinDistance() {
    MoveTask result = new MoveTask(context, MoveTask.MAX_DISTANCE_REV - EPSILON);
  }
}
