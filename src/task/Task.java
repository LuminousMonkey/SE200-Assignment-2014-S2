/*
 * Task
 *
 * Description:
 */

package task;

import controller.RoverController;

public abstract class Task {
  protected RoverController controller;

  private Task() {};

  public Task(RoverController inController) {
    controller = inController;
  }

  public abstract void execute();
}
