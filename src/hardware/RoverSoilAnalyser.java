/*
 */

package hardware;

public class RoverSoilAnalyser extends SoiolAnalyser {
  private RoverController controller;

  private RoverSoilAnalyser();

  public RoverSoilAnalyser(RoverController inController) {
    controller = inController;
  }

  public void analysisReady(String soilAnalysis) {
    // Set result ready.
    controller.setResultReady(soildAnalysis);
  }
}
