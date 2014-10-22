package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hardware.MockComm;
import hardware.MockDriver;

public class TestRoverState {
  private RoverController context;
  private MockComm comm;
  private MockDriver driver;

  @Before
  public void setUp() {
    context = new RoverController();
    comm = new MockComm(context);
    driver = new MockDriver(context);

    context.setDriver(driver);
  }

  @Test
  public void testRoverStartsIdle() {
    assertTrue(context.getState() instanceof RoverIdle);
  }

  @Test
  public void testReceiveMessageWhenIdle() {
    final String testMessage = "L1 {M 90\nT 15}";

    // If we receive a message while idle, then we should change state
    // to "Receiving Message"
    comm.testReceive(testMessage);
    assertTrue(context.getState() instanceof RoverReceiving);
  }
}
