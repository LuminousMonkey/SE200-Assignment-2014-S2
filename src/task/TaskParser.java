/*
 * TaskParser
 *
 * Description:
 *  Given a string that represents a task for the Rover, parse the
 *  task and return a task.
 */

package task;

import controller.RoverController;

/*
 * We're using the command pattern and because these tasks need to
 * call the instances of the different hardware devices the rover has
 * they need to be encapsulated when the Task is created.
 *
 * To reduce the number of parameters that need to be passed in, we
 * just pass in the controller, since we can query that directly for
 * the hardware instances we need.
 */
public class TaskParser {
  private RoverController controller;

  /*
   * TaskParser()
   *
   * Default constructor, made private so it can't be used. We enforce
   * creation through the alternative constructor so we don't have to
   * worry about a parser being created without a controller set.
   */
  private TaskParser() {}

  /*
   * TaskParser()
   *
   * See default constructor comments.
   */
  public TaskParser(RoverController inController) {
    controller = inController;
  }

  /*
   * parseTask
   *
   * Given a message string from Earth, return a Task instance that
   * represents that task. It is assumed that the input is correct.
   */
  public Task parseTask(String inTask)
      throws TaskParseException {
    String[] tokens = inTask.split("[ ]+");

    Task result;

    switch (tokens[0].charAt(0)) {
      case 'M':
        result = new MoveTask(controller.getDriver(),
                              Double.parseDouble(tokens[1]));
        break;
      case 'T':
        result = new TurnTask(controller.getDriver(),
                              Double.parseDouble(tokens[1]));
        break;
      case 'S':
        result = new SoilAnalysisTask(controller.getSoilAnalyser());
        break;
      case 'P':
        result = new PhotoTask(controller.getCamera());
        break;
      case 'L':
        result = new ListExecuteTask(controller,
                                     Integer.parseInt(tokens[1]));
        break;
      default:
        throw new TaskParseException("Unknown Task given.");
    }

    return result;
  }
}
