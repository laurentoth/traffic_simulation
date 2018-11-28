
// Programmer: Arthur Charlesworth  (c) Copyright 2018
// Modified by: FOXES (Greg, Lauren, Maxine)
// *****************************************************************************
// *****************************************************************************
// **** TrafficTesterView
// *****************************************************************************
// *****************************************************************************
 
// NOTE: A segment has same coordinates as intersection the segment aims at.
//   Throughout the rest of the semester, make sure that
//   intersections of a grid are processed in exactly the following order:
//     row 1 intersections, from left to right
//     row 2 intersections, from left to right, etc, for all rows.
//   In an actual simulation, that process should be repeated (in that specific
//   order) as many times as necessary for the desired length of the simulation.
//   But, for simplicity, the initial tester software students build should
//   only show the first iteration of the above process, that is, only one
//   processing of each of the intersections.
//
//   The format of the data to be used with the program in this file
//   is shown, via an example, at the end of this file.
 
import java.util.*;

public class TrafficTesterView {

// NOTE: Just to keep the current tester program really simple,
// the following constants are not encapsulated within two classes, Direction
// and Turn, according to appropriate Information Hiding:
  static final int SOUTHWARD = 0;
  static final int EASTWARD = 1;
  static final int NORTHWARD = 2;
  static final int WESTWARD = 3;
  static final int NEVER_TURN = 0;
  static final int TURN_RIGHTWARD = 1;
  static final int TURN_LEFTWARD = -1;
  private static TrafficModel model;
  private static boolean isError = false;

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    console.nextLine();
    int numTimeUnits = console.nextInt();
    System.out.print("The number of time units requested ");
    System.out.println("is: " + numTimeUnits);
    console.nextLine();
    console.nextLine();
    int maxSegmentCapacity = console.nextInt();
    System.out.print("The requested maximum segment capacity (in cars) ");
    System.out.println("is: " + maxSegmentCapacity);
    console.nextLine();
    console.nextLine();
    int timeTraverseSeg = console.nextInt();
    System.out.print("The requested time to traverse segment ");
    System.out.println("is: " + timeTraverseSeg);
    console.nextLine();
    console.nextLine();
    int numIntersectionsInOneDirection = console.nextInt();
    System.out.print("The number of intersections in one direction ");
    System.out.println("is: " + numIntersectionsInOneDirection);
    console.nextLine();
    console.nextLine();
    int numberOfCars = console.nextInt();
    System.out.println("The number of cars is: " + numberOfCars);
    model = new TrafficModel(numIntersectionsInOneDirection,
                             numberOfCars,
                             numTimeUnits,
                             maxSegmentCapacity);
    int carID;
    int row;
    int col;
    int segmentDirectionCode;
    int numBlocksBeforeTurning;
    int turnDirectionCode;
    for(int i = 1; i <= numberOfCars; i++){
      console.nextLine();
      console.nextLine();
      carID = console.nextInt();
      System.out.println("Car #" + carID);
      console.nextLine();
      console.nextLine();
      col = console.nextInt();
      console.nextLine();
      console.nextLine();
      row = console.nextInt();
      console.nextLine();
      console.nextLine();
      segmentDirectionCode = console.nextInt();
      console.nextLine();
      console.nextLine();
      numBlocksBeforeTurning = console.nextInt();
      console.nextLine();
      console.nextLine();
      turnDirectionCode = console.nextInt();
      System.out.println("  is born in the segment located at col " + col +
                         " and row " + row + ", that aims " +
                         convertToSegmentDirection(segmentDirectionCode) + ",");
      System.out.println("  and has " + numBlocksBeforeTurning +
                         " block(s) to go before turning");
      System.out.println("  and plans to " +
                         convertToTurnDirection(turnDirectionCode));
      System.out.println();

      if(!checkForCarError(numberOfCars,
                          numIntersectionsInOneDirection,
                          carID,
                          col,
                          row,
                          turnDirectionCode,
                          segmentDirectionCode)){

        model.addCar(carID, row, col, segmentDirectionCode,
                     numBlocksBeforeTurning, turnDirectionCode,
                     timeTraverseSeg);
        } 

      else{
        isError = true;
      } // end of if(!checkForCarError(numberOfCars....

    } // end of for (int i = 1; i <= numberOfCars; i++)

    System.out.println();
    model.placeCar();

    if(!isError){

      model.placeCar();
      if(numTimeUnits > 0 && maxSegmentCapacity >= 0 && timeTraverseSeg >= 0){
        model.startSimulation();
      }

      else {
        System.out.println("ERROR: ");
        System.out.println("Simulation cannot start input time units must be" 
                         + " positive, it is currently " + numTimeUnits + "\n");
      } // end of if (numTimeUnits > 0 && maxSegmentCapacity > 0 && ...
    } // end of if(!isError)

    else{
      System.out.println("ERROR: Simulation cannot start due to invalid"
                         + " input\n");
    }
  } // end of main


  public static String convertToSegmentDirection(int segmentDirectionCode) {
    if(segmentDirectionCode == NORTHWARD)      return "NORTHWARD";
    if(segmentDirectionCode == WESTWARD)       return "WESTWARD";
    if(segmentDirectionCode == SOUTHWARD)      return "SOUTHWARD";
    if(segmentDirectionCode == EASTWARD)       return "EASTWARD";
    return "ILLEGAL segmentDirectionCode!!!";
  } // end of convertToSegmentDirection

 
  public static String convertToTurnDirection(int turnDirectionCode) {
    if(turnDirectionCode == NEVER_TURN)     return "NEVER_TURN";
    if(turnDirectionCode == TURN_RIGHTWARD) return "TURN_RIGHTWARD";
    if(turnDirectionCode == TURN_LEFTWARD)  return "TURN_LEFTWARD";
    return "ILLEGAL turnDirectionCode!!!";
  } // end of convertToTurnDirection

  private static boolean checkForCarError(int simNumCars,
                                          int simNumInt,
                                          int carNum,
                                          int carCol,
                                          int carRow,
                                          int turnDirectionCode,
                                          int segDirection){
    boolean isThereError = false;

    if(carNum > simNumCars){
      System.out.println("ERROR: Input has more cars than indicated\n");
      isThereError = true;
    }

    if(carCol > simNumInt || carRow > simNumInt || carRow < 1 || carCol < 1){
      System.out.println("ERROR: Car #" + carNum + " is trying to be born in "
                         + "non-existent segment\n");
      isThereError = true;
    }

    if(turnDirectionCode < -1 || turnDirectionCode > 1){
      isThereError = true;
    }

    if(segDirection < 0 || segDirection > 3){
      isThereError = true;
    }

    return isThereError;
  } // end of checkForCarError

} // end of TrafficTesterView
