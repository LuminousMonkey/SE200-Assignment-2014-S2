/*
 * SoildAnalyser
 *
 * Description:
 *  Base assignment class.
 */

package hardware;

public abstract class SoilAnalyser
{
  public SoilAnalyser() {}
  public void analyse() {}
  protected abstract void analysisReady(String soilAnalysis);
}
