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
  
  public Intersection(){

    myInboundSeg = new Segment[4];
    myOutboundSeg = new Segment[4];
  }// end of Intersection Constructor  

  public void setInbound(Segment sg, int i){
    myInboundSeg[i] = sg;
  } // end of setInbound

  public void setOutbound(Segment sg, int i){
    myOutboundSeg[i] = sg;
  } // end of setOutbound

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
    for(int index = 0; index < 4; index++ ){
      myInboundSeg[index].advanceCarTimeOnGrid();
    }


    /* returns the order of directions from which car will go
    ArrayList<Integer> carsToMove = carsToMove(c0, c1, c2, c3);
    for(int i : carsToMove){
      Car headCar = myInboundSeg[i].removeHeadCar();

      // need to worry about the turns
      int outboundSegment = segmentToPut(headCar, i);
      myOutboundSeg[outboundSegment].putCar(headCar);
    }// end of for (int i : carsToMove)
    */
    printInformation();
    return;

  } // advance


  public void carsToMove(Car c0, Car c1, Car c2, Car c3){
    // based on the traffic rule, 
    // return the order of the directions of the streets on which the car moves
    Car[] inputCars = { c0, c1, c2, c3 };

    Queue<Car> resultToMove = new Queue<Car>;

    // find which cars have potential to move
    int numPotential = 0;
    boolean[] potentialToMove = new boolean[4];
    for(int CarDir = 0; CarDir < 4; ++CarDir){
      potentialToMove[CarDir] = 
          !(myOutboundSeg[segmentToPut(inputCars[CarDir], CarDir)].isFull());
    
      if(potentialToMove[CarDir])
        ++numPotential;
    } // end of for(int CarDir = 0; CarDir < 4; ++CarDir)

    if(numPotential <= 1){
      Car potential;
      for(int CarDir = 0; CarDir < 4; ++CarDir)
        if(potentialToMove[CarDir])
          potential = inputCars[CarDir];

      // assuming NO cars in intersection
      resultToMove.add(potential);
    } // end of if(numPotential <= 1)
    else{
      // Add cars going S, then R, then L; prioritizing S E N W
      // this deals with contention implicitly
      for(int turnSignal = 1; turnSignal < 3; ++turnSignal){
        for(int CarDir = 0; CarDir < 4; ++CarDir){
          if(potentialToMove[CarDir])
            if(inputCars[CarDir].getTurnSignal() == ((turnSignal % 2) - 1))
              resultToMove.add(inputCars[CarDir]);
        } // end of for(int CarDir = 0; CarDir < 4; ++CarDir)
      } // end of for(int turnSignal = 0; turnSignal < 3; ++turnSignal)
    } // end of else

    return resultToMove;
      
  }// carsToMove

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
  }// segmentToPut

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


}//Intersection
  
