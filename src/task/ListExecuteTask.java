/*
 * ListExecuteTask
 *
 * Description
 * A task can execute another task list. This is the task that allows
 * that.
 */

package task;

public class ListExecuteTask implements Task {
  private TaskListManager taskListManager;
  private int listId;

  public ListExecuteTask(TaskListManager inTaskListManager, int inListId) {
    taskListManager = inTaskListManager;
    listId = inListId;
  }

  public void execute() {
    taskListManager.executeSubList(listId);
  }
}
