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
import hardware.MockDriver;

public class TestMoveTask {
  private final static double EPSILON = 0.01;
  private RoverController controller;
  private MockDriver driver;

  @Before
  public void setUp() {
    controller = new RoverController();
    driver = new MockDriver(controller);
  }

  @Test
  public void testMoveLimits() {
    MoveTask result = new MoveTask(driver, -100.0);
    assertEquals(-100, result.getDistance(), EPSILON);

    result = new MoveTask(driver, 100.0);
    assertEquals(100, result.getDistance(), EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMaxDistance() {
    MoveTask result = new MoveTask(driver, MoveTask.MAX_DISTANCE_FWD + EPSILON);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidMinDistance() {
    MoveTask result = new MoveTask(driver, MoveTask.MAX_DISTANCE_REV - EPSILON);
  }
}
