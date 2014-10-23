/*
 * TaskParser
 *
 * Description:
 *  Given a string that represents a task for the Rover, parse the
 *  task and return a task.
 */

package task;

import controller.RoverController;

public class TaskParser {
  private RoverController context;

  private TaskParser() {}

  public TaskParser(RoverController inContext) {
    context = inContext;
  }

  public Task parseTask(String inTask)
      throws TaskParseException {
    String[] tokens = inTask.split("[ ]+");

    Task result = null;

    switch (tokens[0].charAt(0)) {
      case 'M':
        result = new MoveTask(context.getDriver(), Double.parseDouble(tokens[1]));
        break;
      case 'T':
        result = new TurnTask(context.getDriver(), Double.parseDouble(tokens[1]));
        break;
      case 'S':
        result = new SoilAnalysisTask(context.getSoilAnalyser());
        break;
      case 'P':
        result = new PhotoTask(context.getCamera());
        break;
      case 'L':
        result = new ListExecuteTask(context, Integer.parseInt(tokens[1]));
        break;
      default:
        throw new TaskParseException("Unknown Task given.");
    }

    return result;
  }
}
