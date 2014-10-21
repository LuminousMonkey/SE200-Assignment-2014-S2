/*
 * TaskParseException
 *
 * Description
 *  When we get a bad task, this exception should be thrown.
 */

package task;

public class TaskParseException extends RuntimeException {
  public TaskParseException() { super(); }
  public TaskParseException(String message) { super(message); }
}
