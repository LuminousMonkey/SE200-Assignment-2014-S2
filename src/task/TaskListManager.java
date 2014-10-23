/*
 * TaskListManager
 *
 * Description
 *  Handles the task lists and their execution.
 */

package task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;

import controller.RoverController;

public class TaskListManager {
  private HashMap<Integer,TaskList> taskLists;
  private RoverController controller;

  // Current running task list.
  private TaskList currentTaskList;

  // Lists that are pending to be executed.
  private HashSet<Integer> pendingLists;

  // Lists can recurse, which means we have to keep track of which
  // lists we have come from when we execute a sublist.
  // This is what this array is for.
  private Stack<Integer> parentLists;

  public TaskListManager(RoverController inController) {
    controller = inController;
    taskLists = new HashMap<Integer,TaskList>();
    pendingLists = new LinkedHashSet<Integer>();
    parentLists = new Stack<Integer>();
  }

  public void addTaskList(String message) {
    TaskList taskListToAdd = new TaskList(controller.getTaskParser(), message);
    taskLists.put(taskListToAdd.getListId(), taskListToAdd);
    pendingLists.add(taskListToAdd.getListId());

    if (currentTaskList == null) {
      executePending();
    }
  }

  public void execute(int taskListId) {
    parentLists.clear();
    currentTaskList = taskLists.get(taskListId);
    currentTaskList.resetListCursor();
    currentTaskList.execute();
  }

  public void execute() {
    if (currentTaskList.hasNext()) {
      currentTaskList.execute();
    } else if (parentLists.isEmpty()) {
      // Only run pending lists if we've finished the current list and
      // are not executing sublists.
      executePending();
    } else {
      // We're back from our sub list call here, put everything back.
      currentTaskList = taskLists.get(parentLists.pop());

      execute();
    }
  }

  public void executeSubList(int listId) {
    parentLists.add(currentTaskList.getListId());
    currentTaskList = taskLists.get(listId);
    currentTaskList.resetListCursor();
    execute();
  }

  /*
   * executePending
   *
   * Get the next list that is pending to be executed.
   */
  public void executePending() {
    // Just get the first one.
    Iterator<Integer> i = pendingLists.iterator();
    if (i.hasNext()) {
      Integer element = i.next();
      execute(element);
      i.remove();
    }
  }
}
