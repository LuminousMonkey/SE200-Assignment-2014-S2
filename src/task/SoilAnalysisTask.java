/*
 * SoilAnalysisTask
 *
 * Description
 *  The task that will take a soil analysis.
 */

package task;

import hardware.SoilAnalyser;

public class SoilAnalysisTask implements Task {
  private SoilAnalyser soilAnalyser;

  public SoilAnalysisTask(SoilAnalyser inSoilAnalyser) {
    soilAnalyser = inSoilAnalyser;
  }

  public void execute() {
    soilAnalyser.analyse();
  }
}
