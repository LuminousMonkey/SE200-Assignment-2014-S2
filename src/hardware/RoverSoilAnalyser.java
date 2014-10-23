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

  @Override
  public void analyse() {
    System.out.println("Request for photo.");
    SoilAnalyserThread responseThread = new SoilAnalyserThread();
    Thread t = new Thread(responseThread);
    t.start();
  }

  public void analysisReady(String soilAnalysis) {
    // Set result ready.
    controller.setResult(soilAnalysis);
  }

  private class SoilAnalyserThread implements Runnable {
    // SoilAnalysers take the same amount of time to take.
    private final static int ANALYSER_DELAY = 2000;

    public void run() {
      System.out.println("SoilAnalyser thread running.");

      try {
        Thread.sleep(ANALYSER_DELAY);
        analysisReady("SoilAnalyser Ready");
      } catch (InterruptedException e) {
        System.out.println("SoilAnalyser thread interrupted");
      }
    }
  }
}
