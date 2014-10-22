/*
 * MockSoilAnalyser
 *
 * Description
 *  Test class for the soil analyser.
 */

package hardware;

import controller.RoverController;

public class MockSoilAnalyser extends RoverSoilAnalyser {
  private int noSoilAnalyserRequests = 0;

  public MockSoilAnalyser(RoverController inContext) {
    super(inContext);
  }

  @Override
  public void analyse() {
    System.out.println("Got task to analyse.");
    noSoilAnalyserRequests++;
  }

  public int getAnalyseRequest() {
    int result = noSoilAnalyserRequests;
    noSoilAnalyserRequests = 0;
    return result;
  }
}
