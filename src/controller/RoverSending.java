/*
 * RoverSending
 *
 * Description:
 *  When the Rover is sending back responses to Earth.
 */

package controller;

import task.TaskList;

public class RoverSending extends RoverState {
  // We shouldn't be going from sending to idle, since we don't know
  // if there's more tasks to run.
  public void setIdle(RoverController inContext) {
  }

  // We shoudn't be going from sending to receiving.
  public void setReceiving(RoverController inContext,
                           String message) {
  }

  // We're already sending.
  public void setSending(RoverController inContext,
                         String message) {
  }

  // Once it finishes sending the results to Earth, it should try
  // running the next task in the list.
  public void setRunning(RoverController inContext,
                         int taskListId) {
  }

  // We're already sending a result.
  public void setResultReady(RoverController inContext,
                             String result) {
  }

  public void setErrorOccured(RoverController inContext,
                              String error) {
  }
}
