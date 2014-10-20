/*
 * RoverSending
 *
 * Description:
 *  When the Rover is sending back responses to Earth.
 */

package controller;

public class RoverSending extends RoverState {
  // We shouldn't be going from sending to idle, since we don't know
  // if there's more tasks to run.
  public void setIdle() {
  }

  // We shoudn't be going from sending to receiving.
  public void setReceiving() {
  }

  // We're already sending.
  public void setSending() {
  }

  // Once it finishes sending the results to Earth, it should try
  // running the next task in the list.
  public void setRunning() {
  }

  // We're already sending a result.
  public void setResultReady() {}
}
