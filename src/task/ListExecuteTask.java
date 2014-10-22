/*
 * ListExecuteTask
 *
 * Description
 * A task can execute another task list. This is the task that allows
 * that.
 */

package task;

import controller.RoverController;

public class ListExecuteTask extends Task {
  private int listId;

  public ListExecuteTask(RoverController inContext, int inListId) {
    super(inContext);
    listId = inListId;
  }

  public void execute() {
    System.out.println("ListExecuteTask: " + listId);
    context.execute(listId);
  }
}
