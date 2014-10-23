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
import hardware.MockCamera;
import hardware.MockSoilAnalyser;

public class TestTaskList {
  private RoverController context;
  private MockDriver driver;
  private MockCamera camera;
  private MockSoilAnalyser soilAnal;

  @Before
  public void setUp() {
    context = new RoverController();
    driver = new MockDriver(context);
    camera = new MockCamera(context);
    soilAnal = new MockSoilAnalyser(context);

    context.setDriver(driver);
    context.setCamera(camera);
    context.setSoilAnalyser(soilAnal);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNoInstructions() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "ASD ASDF asd");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testEmptyList() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "L1 {}");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidListHeader() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "B1 {G 3\nH\nB 11\n}");
  }

  @Test(expected=TaskParseException.class)
  public void testInvalidListContents() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "L1 {G 3\nH\nB 11\n}");
  }

  @Test
  public void testValidList() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "L100 {M 100\nT 90\nP\nS}");
    assertEquals(100, testTaskList.getListId());
    assertEquals(4, testTaskList.getSize());
  }

  @Test
  public void testListExecute() {
    TaskList testTaskList = new TaskList(context.getTaskParser(), "L1 {M 100\n}");
    testTaskList.execute();
    assertEquals(100.0, driver.getDistanceReceived(), 0.1);

    testTaskList = new TaskList(context.getTaskParser(), "L2 {T -15\n}");
    testTaskList.execute();
    assertEquals(-15.0, driver.getAngleReceived(), 0.1);
  }

  /*
   * testAllDevices
   *
   * Add a list that includes all the devices.
   */
  @Test
  public void testAllDevices() {
    TaskList testTaskList = new TaskList(context.getTaskParser(),
                                         "L1 {M 100\nT 10\nS\nP\n}");
    for (int i = 0; i < 4; i++) {
      testTaskList.execute();
    }

    assertEquals(100.0, driver.getDistanceReceived(), 0.1);
    assertEquals(10.0, driver.getAngleReceived(), 0.1);
    assertEquals(1, camera.getTakePhotoRequest());
    assertEquals(1, soilAnal.getAnalyseRequest());
  }
}
