/*
 * ListExecuteTask
 *
 * Description
 * A task can execute another task list. This is the task that allows
 * that.
 */

package task;

import controller.RoverController;

public class ListExecuteTask implements Task {
  private RoverController context;
  private int listId;

  public ListExecuteTask(RoverController inContext, int inListId) {
    context = inContext;
    listId = inListId;
  }

  public void execute() {
    context.execute(listId);
  }
}
