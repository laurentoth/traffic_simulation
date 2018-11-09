// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Intersection
// *****************************************************************************
// *****************************************************************************

import java.util.*;

public class Intersection{
  private Car NorthEast, NorthWest, SouthEast, SouthWest;
  private Segment [] myInboundSeg;
  private Segment [] myOutboundSeg;
  
  public Intersection(){
    NorthEast = new Car;
    NorthWest = new Car;
    SouthEast = new Car;
    SouthWest = new Car;

    myInboundSeg = new Segment[4];
    myOutboundSeg = new Segment[4];
  }//Intersection Constructor  

  public void setInbound(Segment sg, int i){
    myInboundSeg[i] = sg;
  }//setInbound

  public void setOutbound(Segment sg, int i){
    myOutboundSeg[i] = sg;
  }//setOutbound

  public Segment getInbound(int i){
    return myInboundSeg[i];
  }//getInbound

  public Segment getOutbound(int i){
    return myOutboundSeg[i];
  }//getOutbound

  public void putCarIntoSegment(Car car, int direction){
    myInboundSeg[direction].putCar(car);
  } // end of putCarIntoSegment

  public void advance(){
    Car c0, c1, c2, c3;
    c0 = myInboundSeg[0].getHeadCar();
    c1 = myInboundSeg[1].getHeadCar();
    c2 = myInboundSeg[2].getHeadCar();
    c3 = myInboundSeg[3].getHeadCar();

    // returns the order of directions from which car will go
    ArrayList<Integer> carsToMove = carsToMove(c0, c1, c2, c3);
    for(int i : carsToMove) {
      Car headCar = myInboundSeg[i].removeHeadCar();

      // need to worry about the turns
      int outboundSegment = segmentToPut(c, i);
      myOutboundSeg[outboundSegment].putCar(headCar);
    }// end of for (int i : carsToMove)
    printInformation();
  }// advance


  public ArrayList<Integer> carsToMove(Car c0, Car c1, Car c2, Car c3){
    // based on the traffic rule, 
    // return the order of the directions of the streets on which the car moves
    return;
  }// carsToMove

  public int segmentToPut(Car c, int i){
    // based on the turn signal, current direction (and maybe other related
    // information) of the car, return the direction of the outbound segment 
    // that the car will be put onto as an int
    return 0;
  }// segmentToPut

  private void printInformation(){
    for(int index = 0; index < 4; index++ ){
      System.out.println("incoming lane having direction "
                         + convertToSegmentDirection(index)
                         + " is " + myInboundSeg[index].isEmpty());
    }// end of for(int index = 0; index < 4; index++ )

    for(int index = 0; index < 4; index++ ){
      System.out.println("outgoing lane having direction "
                         + convertToSegmentDirection(index)
                         + " is " + myOutboundSeg[index].isEmpty());
    }// end of for(int index = 0; index < 4; index++ )
  }

  private static String convertToSegmentDirection(int segmentDirectionCode) {
    if (segmentDirectionCode == 2)      return "NORTHWARD";
    if (segmentDirectionCode == 3)      return "WESTWARD";
    if (segmentDirectionCode == 0)      return "SOUTHWARD";
    if (segmentDirectionCode == 1)      return "EASTWARD";
    return "ILLEGAL segmentDirectionCode!!!";
  } // convertToSegmentDirection


}//Intersection
  
