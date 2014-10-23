package task;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import controller.RoverController;

public class TaskList {
  private final static int HEADER_INVALID = -1;
  private int listId;
  private List<Task> tasks;
  private RoverController roverContext;
  private Iterator<Task> listIterator;

  private TaskList() {}

  /*
   * Given a message from Earth, parse it into a task list.
   *
   * Assumes that the message is not empty, empty messages should be
   * checked before being passed in here.
   */
  public TaskList(RoverController context, String message) {
    roverContext = context;
    tasks = new ArrayList<Task>();

    if (!message.isEmpty()) {
      // Given the message, find the listId, then step through the
      // tasks.
      String[] tokens = message.split("[{}\n]");

      // Must have more than one token, otherwise there's no list
      // data.
      if (tokens.length > 1) {
        listId = parseHeader(tokens[0]);

        if (listId == HEADER_INVALID) {
          throw new IllegalArgumentException("Invalid header");
        } else {
          // Go through the token list and add the tasks.
          TaskParser taskParser = new TaskParser(context);

          for (int i = 1; i < tokens.length; i++) {
            tasks.add(taskParser.parseTask(tokens[i]));
          }
        }
      } else {
        throw new IllegalArgumentException("No instructions found");
      }

      listIterator = tasks.iterator();
    }
  }

  /*
   * getLength
   *
   * Return the number of tasks in the list.
   */
  public int getSize() {
    return tasks.size();
  }

  /*
   * getListId
   *
   * Return the id of the list.
   */
  public int getListId() {
    return listId;
  }

  /*
   * parseHeader
   *
   * Takes a token that should be the message header of the list. (L1,
   * L99, etc) and returns the list number. If the format is invalid,
   * it will return HEADER_INVALID.
   */
  private int parseHeader(String header) {
    int result = HEADER_INVALID;

    if (header.charAt(0) == 'L') {
      try {
        result = Integer.parseInt(header.substring(1).trim());
      } catch(NumberFormatException e) {
        result = HEADER_INVALID;
      }
    }

    return result;
  }

  public void resetListCursor() {
    listIterator = tasks.iterator();
  }

  public boolean hasNext() {
    return listIterator.hasNext();
  }

  public void execute() {
    if (listIterator.hasNext()) {
      Task currentTask = listIterator.next();
      currentTask.execute();
    }
  }
}
