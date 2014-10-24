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
  private RoverController controller;
  private int listId;

  public ListExecuteTask(RoverController inController, int inListId) {
    controller = inController;
    listId = inListId;
  }

  public void execute() {
    controller.executeSubList(listId);
  }
}
