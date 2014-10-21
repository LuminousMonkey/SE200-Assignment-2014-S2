/*
 * SE 200 Assignment
 *
 * Author: Mike Aldred
 */

import hardware.Driver;
import hardware.Camera;
import hardware.SoilAnalyser;
import hardware.Comm;

import hardware.RoverDriver;
import hardware.RoverCamera;
import hardware.RoverSoilAnalyser;
import hardware.EarthComm;

import controller.RoverController;

public class Assignment {
  private static RoverController controller;
  private static Driver currentDriver;
  private static Camera currentCamera;
  private static SoilAnalyser currentSoilAnalyser;
  private static Comm currentComms;
  /*
   * This is just a test main for now, it should be removed into the unit tests.
   */
  public static void main(String[] args) {
    // Init all the classes.

    controller = new RoverController();

    currentDriver = new RoverDriver(controller);
    currentCamera = new RoverCamera(controller);
    currentSoilAnalyser = new RoverSoilAnalyser(controller);


  }

  private static void testReceive() {
  }
}
