/*
 */

package hardware;

import controller.RoverController;

public class RoverSoilAnalyser extends SoilAnalyser {
  private RoverController context;

  private RoverSoilAnalyser() {};

  public RoverSoilAnalyser(RoverController inContext) {
    super();
    context = inContext;
  }

  public void analysisReady(String soilAnalysis) {
    // Set result ready.
    context.setResult(soilAnalysis);
  }
}
