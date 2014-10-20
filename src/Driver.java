public abstract class Driver
{
  public Driver() {}
  public void drive(double distance) {}
  public void turn(double angle) {}
  protected abstract void moveFinished();
  protected abstract void mechanicalError();
}
