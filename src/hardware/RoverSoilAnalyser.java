/*
 */

package hardware;

import controller.RoverController;

public class RoverSoilAnalyser extends SoilAnalyser {
  private RoverController controller;

  private RoverSoilAnalyser() {};

  public RoverSoilAnalyser(RoverController inController) {
    super();
    controller = inController;
  }

  public void analysisReady(String soilAnalysis) {
    // Set result ready.
    controller.setResultReady(soilAnalysis);
  }
}
