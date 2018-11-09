// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Intersection
// *****************************************************************************
// *****************************************************************************

public class Intersection{
  private Segment [] myInboundSeg;
  private Segment [] myOutboundSeg;
  
  public Intersection(){
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

  public boolean putCarIntoSegment(Car car, int direction){
    myInboundSeg[direction].putCar(car);
  }

  public void advance(){
    Car c0, c1, c2, c3;
    c0 = myInboundSeg[0].getHeadCar();
    c1 = myInboundSeg[1].getHeadCar();
    c2 = myInboundSeg[2].getHeadCar();
    c3 = myInboundSeg[3].getHeadCar();

    // returns the order of directions from which car will go
    ArrayList<Integer> carsToMove = carsToMove(c0, c1, c2, c3);
    for (int i : carsToMove) {
      Car c = myInboundSeg[i].removeHeadCar();

      // need to worry about the turns
      int j = segmentToPut(c, i);

      myOutbound[j].putCar(c);
    }
  }

  public ArrayList<Interger> carsToMove(Car c0, Car c1, Car c2, Car c3){
    // based on the traffic rule, 
    // return the order of the directions of the streets on which the car moves
    
  }

  public int segmentToPut(Car c, int i){
    // based on the turn signal, current direction(and potentially other related
    // information) of the car, return the direction of the outbound segment 
    // that the car will be put onto as an int
  }
}
}//Intersection
  