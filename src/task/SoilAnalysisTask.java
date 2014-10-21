/*
 * SoilAnalysisTask
 *
 * Description
 *  The task that will take a soil analysis.
 */

package task;

import controller.RoverController;
import hardware.SoilAnalyser;

public class SoilAnalysisTask extends Task {
  public SoilAnalysisTask(RoverController inContext) {
    super(inContext);
  }

  public void execute() {
    controller.getSoilAnalyser().analyse();
  }
}
