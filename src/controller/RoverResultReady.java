/*
 * RoverResultReady
 */

package controller;

public class RoverResultReady extends RoverState {
  // We should be going to sending.
  public void setIdle() {
  }

  // Should be going to sending.
  public void setReceiving() {
  }

  // This is where we expect to go.
  public void setSending() {
  }

  // Try to send the task first.
  public void setRunning() {
  }

  // We already have results.
  public void setResultReady() {}
}
