// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Intersection
// *****************************************************************************
// *****************************************************************************

import java.util.*;
import java.math.*;

public class Intersection{

  private Segment [] myInboundSeg;
  private Segment [] myOutboundSeg;
  private final int S = 0;
  private final int E = 1;
  private final int N = 2;
  private final int W = 3;


  public Intersection(){
    myInboundSeg = new Segment[4];
    myOutboundSeg = new Segment[4];
  } // end of Intersection Constructor  

  public void setInbound(Segment sg, int i){
    myInboundSeg[i] = sg;
  } // end of setInbound

  public void setOutbound(Segment sg, int i){
    myOutboundSeg[i] = sg;
  } // end of setOutbound

  public Segment getInbound(int i){
    return myInboundSeg[i];
  } // end of getInbound

  public Segment getOutbound(int i){
    return myOutboundSeg[i];
  } // end of getOutbound

  public void putCarIntoSegment(Car car, int direction){
    myInboundSeg[direction].putCar(car);
  } // end of putCarIntoSegment

  public void advance(){
    Car c0, c1, c2, c3;
    c0 = myInboundSeg[0].getHeadCar();
    c1 = myInboundSeg[1].getHeadCar();
    c2 = myInboundSeg[2].getHeadCar();
    c3 = myInboundSeg[3].getHeadCar();

    for(int index = 0; index < 4; ++index){
      myInboundSeg[index].advanceCarTimeOnGrid();
    } // end of for(int index = 0; index < 4; index++ )

    ArrayList<Integer> carsToMove = carsToMove(c0, c1, c2, c3);
    int directionToMove;  // the direction of the segment where the car
                          // will be removed from

    if(carsToMove.size() >= 1){
      directionToMove = carsToMove.get(0);
    }

    else{
      directionToMove = -1;
    } //end of if(carsToMove.size() >= 1) else...

    for(int index = 0; index < 4; ++index){
      printInformationInbound(index);

      if(index == directionToMove){
        Car headCar = myInboundSeg[index].removeHeadCar();
        int outboundSegment = segmentToPut(headCar,index);
        myOutboundSeg[outboundSegment].putCar(headCar);
        headCar.passOneBlock();
        System.out.println("    car#" + headCar.getID()
                           + " is removed and placed "
                           + "into outgoing lane having direction " 
                           + convertToSegmentDirection(outboundSegment));

        if(myOutboundSeg[outboundSegment].getIsEdge()){
          System.out.println("    car#" + headCar.getID() + " leaves the grid");
          myOutboundSeg[outboundSegment].removeHeadCar();
        } // end of if(myOutboundSeg[outboundSegment].getIsEdge())

        if(headCar == null){
          System.out.println();
          continue;
        } // end of if(headCar == null)

      } // end of if(index == directionToMove)

    } // end of for(int index = 0; index < 4; ++index)

    printInformationOutbound();
    return;
  } // end of advance

  public ArrayList<Integer> carsToMove(Car c0, Car c1, Car c2, Car c3){
    // based on the traffic rule, 
    // return the order of the directions of the streets on which the car moves
    Car[] inputCars = { c0, c1, c2, c3 };

    // priority of turns
    int[] turns = { 0, 1, -1 };

    ArrayList<Integer> resultToMove = new ArrayList<Integer>();
    for (int t : turns) {
      for(int carDir = 0; carDir < 4; ++carDir){
        if(inputCars[carDir] != null){
          if(inputCars[carDir].getTurnSignal() == t) {
            resultToMove.add(carDir);
          } // end of if(inputCars[carDir].getTurnSignal()...)

        } // end of if(inputCars[carDir] != null){

      } // end of for(int carDir = 0; carDir < 4; ++carDir)
        
    } // end of for(int t : turns)

    // find which cars have potential to move
    /* FOR FUTURE USE
    int numPotential = 0;
    boolean[] potentialToMove = new boolean[4];
    for(int carDir = 0; carDir < 4; ++carDir){
      potentialToMove[carDir] = 
          !(myOutboundSeg[segmentToPut(inputCars[carDir], carDir)].isFull());
    
      if(potentialToMove[carDir])
        ++numPotential;
    } // end of for(int carDir = 0; carDir < 4; ++carDir)

    if(numPotential <= 1){
      int potential = -1;
      for(int carDir = 0; carDir < 4; ++carDir)
        if(potentialToMove[carDir])
          potential = carDir;

      // assuming NO cars in intersection
      if(potential != -1)
        resultToMove.add(potential);

    } // end of if(numPotential <= 1)
    else{
    */

    return resultToMove;

  }// carsToMove

  public int segmentToPut(Car car, int direction){
    // oppositeSegment is equal to direction (opposite faces same direction)
    // Based on the turn signal and current direction, return the direction
    // of the outbound segment that the car will be put onto as an int
    // 1: right; -1: left; 0: straight
    int turnSignal = car.getTurnSignal();
    int diff = (direction - turnSignal);
    if(diff < 0){
      diff += 4;
    } // end of if(diff < 0)
    int segmentToPut = diff % 4;

    return segmentToPut;
  } // end of segmentToPut

  private void printInformationInbound(int direction){
    String empty;
    if(myInboundSeg[direction].isEmpty()){
      empty = "empty";
    }
    else{
      empty = "nonempty";
    } // end of if(myInboundSeg[index].isEmpty())

    System.out.println("  incoming lane having direction "
                       + convertToSegmentDirection(direction)
                       + " is " + empty);
  } // end of printInformationInbound

  private void printInformationOutbound(){
    String empty;
    for(int index = 0; index < 4; ++index){

      if(myOutboundSeg[index].isEmpty()){
        empty = "empty";
      }
      else{
        empty = "nonempty";
      } // end of if(myOutboundSeg[index].isEmpty())

      System.out.println("  outgoing lane having direction "
                         + convertToSegmentDirection(index)
                         + " is " + empty);
    } // end of for(int index = 0; index < 4; ++index)
  } // end of printInformationOutbound

  private static String convertToSegmentDirection(int segmentDirectionCode){
    if (segmentDirectionCode == 2)      return "NORTHWARD";
    if (segmentDirectionCode == 3)      return "WESTWARD";
    if (segmentDirectionCode == 0)      return "SOUTHWARD";
    if (segmentDirectionCode == 1)      return "EASTWARD";
    return "ILLEGAL segmentDirectionCode!!!" + segmentDirectionCode;
  } // end of convertToSegmentDirection

} // end of Intersection Class
