
// Programmer: Arthur Charlesworth  (c) Copyright 2018
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

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    console.nextLine();
    int numIntersectionsInOneDirection = console.nextInt();
    System.out.print("The number of intersections in one direction ");
    System.out.println("is: " + numIntersectionsInOneDirection);
    console.nextLine();
    console.nextLine();
    int numberOfCars = console.nextInt();
    System.out.println("The number of cars is: " + numberOfCars);
    model = new TrafficModel(numIntersectionsInOneDirection, numberOfCars);
    int carID;
    int row;
    int col;
    int segmentDirectionCode;
    int numBlocksBeforeTurning;
    int turnDirectionCode;
    for (int i = 1; i <= numberOfCars; i++) {
      console.nextLine();
      console.nextLine();
      carID = console.nextInt();
      System.out.println("Car #" + carID);
      console.nextLine();
      console.nextLine();
      row = console.nextInt();
      console.nextLine();
      console.nextLine();
      col = console.nextInt();
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
      model.addCar(carID, row, col, segmentDirectionCode,
                   numBlocksBeforeTurning, turnDirectionCode);
    } // end for

  } // main


  public static String convertToSegmentDirection(int segmentDirectionCode) {
    if (segmentDirectionCode == NORTHWARD)      return "NORTHWARD";
    if (segmentDirectionCode == WESTWARD)       return "WESTWARD";
    if (segmentDirectionCode == SOUTHWARD)      return "SOUTHWARD";
    if (segmentDirectionCode == EASTWARD)       return "EASTWARD";
    return "ILLEGAL segmentDirectionCode!!!";
  } // convertToSegmentDirection

 
  public static String convertToTurnDirection(int turnDirectionCode) {
    if (turnDirectionCode == NEVER_TURN)     return "NEVER_TURN";
    if (turnDirectionCode == TURN_RIGHTWARD) return "TURN_RIGHTWARD";
    if (turnDirectionCode == TURN_LEFTWARD)  return "TURN_LEFTWARD";
    return "ILLEGAL turnDirectionCode!!!";
  } // convertToTurnDirection


} // TrafficTesterView

 

////////////////// Example of sample use of the above program //////////////////
// except for the fact that all the following lines have been made comments,  //
// and also the actual phrase "additional blocks to travel" in the data       //
// shown below has been shortened to the phrase "additional blocks" to fit    //
// the lines below into the required 80-columns per line.                     //
////////////////////////////////////////////////////////////////////////////////
//numIntersectionsInOneDirection:
//1
//number of cars created for the test:
//2
//car number:
//1
//born in the segment that is positioned at col:
//1
//born in the segment that is positioned at row:
//1
//born in the segment that is oriented in direction:
//0
//additional blocks prior to turning (-1 means the car will never turn):
//0
//direction the car will turn, when the car turns:
//1
//car number:
//2
//born in the segment that is positioned at col:
//1
//born in the segment that is positioned at row:
//1
//born in the segment that is oriented in direction:
//3
//additional blocks prior to turning (-1 means the car will never turn):
//-1
//direction the car will turn, when the car turns:
//0
//hopper2{acharles}2484: java TrafficTesterView < data1_for_TrafficTesterView
//The number of intersections in one direction is: 1
//The number of cars is: 2
//Car #1
//  is born in the segment located at col 1 and row 1, that aims SOUTHWARD,
//  and has 0 block(s) to go before turning
//  and plans to TURN_RIGHTWARD
//Car #2
//  is born in the segment located at col 1 and row 1, that aims WESTWARD,
//  and has -1 block(s) to go before turning
//  and plans to NEVER_TURN
