/*
 * Task
 *
 * Description:
 */

package task;

import controller.RoverController;

public abstract class Task {
  protected RoverController context;

  private Task() {};

  public Task(RoverController inContext) {
    context = inContext;
  }

  public abstract void execute();
}
