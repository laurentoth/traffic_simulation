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

  public void setInbound(Segment sg, int i) {
    myInboundSeg[i] = sg;
  }//setInbound

  public void setOutbound(Segment sg, int i) {
    myOutboundSeg[i] = sg;
  }//setOutbound

  public Segment getInbound(int i) {
    return myInboundSeg[i];
  }//getInbound

  public Segment getOutbound(int i) {
    return myOutboundSeg[i];
  }//getOutbound
}//Intersection
  