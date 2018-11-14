// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Intersection
// *****************************************************************************
// *****************************************************************************

import java.util.*;

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

    for(int index = 0; index < 4; ++index){
      myInboundSeg[index].advanceCarTimeOnGrid();
    } // end of for(int index = 0; index < 4; index++ )


     for(int index = 0; index < 4; ++index){
      Car headCar = myInboundSeg[index].removeHeadCar();

      if(headCar == null){
        continue;
      }// end of if(headCar == null)

      // need to worry about the turns
      int outboundSegment = segmentToPut(headCar, index);
      myOutboundSeg[outboundSegment].putCar(headCar);
      System.out.print("car#" + headCar.getID() + " is removed and placed " +
        "into outgoing lane having direction " 
        + convertToSegmentDirection(outboundSegment));
    }// end of for(int index = 0; index < 4; ++index)

    printInformation();
    return;

  } //end of advance


  public int segmentToPut(Car car, int inboundDirection){
    // based on the turn signal, current direction (and maybe other related
    // information) of the car, return the direction of the outbound segment 
    // that the car will be put onto as an int
    int oppositeSegment = (inboundDirection + 2) % 4;

    // 1: right; -1: left; 0: straight
    int turnSignal = car.getTurnSignal();
    int segmentToPut = (oppositeSegment - turnSignal);

    // 0: S; 1: E; 2: N; 3: W
    return segmentToPut;
  } //end of segmentToPut

  private void printInformation(){
    String empty;

    for(int index = 0; index < 4; index++ ){
      if(myInboundSeg[index].isEmpty()){
        empty = "empty";
      }
      else{
        empty = "nonempty";
      }// end of if(myInboundSeg[index].isEmpty())

      System.out.println("incoming lane having direction "
                         + convertToSegmentDirection(index)
                         + " is " + empty);
    }// end of for(int index = 0; index < 4; index++ )

    
    for(int index = 0; index < 4; index++ ){

      if(myOutboundSeg[index].isEmpty()){
        empty = "empty";
      }
      else{
        empty = "nonempty";
      }// end of if(myOutboundSeg[index].isEmpty())

      System.out.println("outgoing lane having direction "
                         + convertToSegmentDirection(index)
                         + " is " + empty);
    }// end of for(int index = 0; index < 4; index++ )
  }// end of printInformation()

  private static String convertToSegmentDirection(int segmentDirectionCode){
    if (segmentDirectionCode == 2)      return "NORTHWARD";
    if (segmentDirectionCode == 3)      return "WESTWARD";
    if (segmentDirectionCode == 0)      return "SOUTHWARD";
    if (segmentDirectionCode == 1)      return "EASTWARD";
    return "ILLEGAL segmentDirectionCode!!!";
  } // end of convertToSegmentDirection


} // end of Intersection Class
  
