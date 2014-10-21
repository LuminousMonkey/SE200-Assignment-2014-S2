package controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hardware.MockComm;

public class TestRoverState {
  private RoverController context;
  private MockComm comm;

  @Before
  public void setUp() {
    context = new RoverController();
    comm = new MockComm(context);
  }

  @Test
  public void testRoverStartsIdle() {
    assertTrue(context.getState() instanceof RoverIdle);
  }

  @Test
  public void testReceiveMessageWhenIdle() {
    // If we receive a message while idle, then we should change state
    // to "Receiving Message"
    comm.testReceive("Test Message");
    assertTrue(context.getState() instanceof RoverReceiving);
  }
}
