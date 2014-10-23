/*
 * SE 200 Assignment
 *
 * Author: Mike Aldred
 */

import java.util.Random;

import hardware.Driver;
import hardware.Camera;
import hardware.SoilAnalyser;
import hardware.Comm;

import hardware.RoverDriver;
import hardware.RoverCamera;
import hardware.RoverSoilAnalyser;
import hardware.EarthComm;

import controller.RoverController;

import task.TaskListManager;

public class Assignment {
  private static RoverController controller;
  private static EarthComm comm;

  private static int listCount;

  private static Random randGen = new Random();

  /*
   * This is just a test main for now, it should be removed into the unit tests.
   */
  public static void main(String[] args) {
    // Init all the classes.
    controller = new RoverController();

    comm = new EarthComm(controller);

    for (int i = 0; i < 10; i++) {
      comm.receive(listGenerator());
      controller.getTaskListManager().executePending();

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.out.println("Main thread interrupted.");
      }
    }
  }

  /*
   * listGenerator
   *
   * Generates random lists to be passed through to the Rover, as if
   * they're coming from Earth.
   */
  private static String listGenerator() {
    String result = "L" + listCount++ + "{";

    int numberOfCommands = randGen.nextInt(5) + 1;

    for (int i = 0; i < numberOfCommands; i++) {
      result += randomCommand() + "\n";
    }

    result += "}";
    return result;
  }

  private static String randomCommand() {
    int selection = randGen.nextInt(4);

    String result;

    switch (selection) {
      case 0:
        result = randomDriverCommand('M', 100.0, 0.2);
        break;
      case 1:
        result = randomDriverCommand('T', 180.0, 0.5);
        break;
      case 2:
        result = "P";
        break;
      case 3:
        result = "S";
        break;
      case 4:
        if (listCount > 1) {
          result = "L " + randGen.nextInt(listCount) + 1;
        } else {
          result = "P";
        }
        break;
      default:
        result = "P";
    }

    return result;
  }

   /*
   * randomDriverCommand
   *
   * Generates a random turn command and returns the result as a
   * string. Will be valid.
   */
  private static String randomDriverCommand(char driveTurn, double maxValue,
                                            double negProb) {
    double randomDistance = maxValue * randGen.nextDouble();

    if (randGen.nextDouble() < negProb) {
      randomDistance = -randomDistance;
    }

    return driveTurn + " " + randomDistance;
  }
}
