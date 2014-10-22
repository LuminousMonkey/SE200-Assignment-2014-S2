package task;

import java.util.HashMap;

public class TaskListCollection {
  private HashMap<Integer, TaskList> taskList;

  public TaskListCollection() {
    taskList = new HashMap<Integer, TaskList>();
  }

  public void add(TaskList inTaskList) {
    taskList.put(inTaskList.getListId(), inTaskList);
  }
}
