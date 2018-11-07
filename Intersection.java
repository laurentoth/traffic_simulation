// Programmer: Team FOXES (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** GridControl
// *****************************************************************************
// *****************************************************************************

public class Intersection{
  private Segment [] myInboundSeg;
  private Segment [] myOutboundSeg;
  
  public Intersection(){
    myInboundSeg = new Segment[4];
    myOutboundSeg = new Segment[4];
  }  

  public void setInbound(Segment sg, int i) {
    myInboundSeg[i] = sg;
  }

  public void setOutbound(Segment sg, int i) {
    myOutboundSeg[i] = sg;
  }

  public Segment getInbound(int i) {
    return myInboundSeg[i];
  }

  public Segment getOutbound(int i) {
    return myOutboundSeg[i];
  }
}
  