// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** GridControl
// *****************************************************************************
// *****************************************************************************

public class GridControl{
  private Intersection[][] intersection;
  private Segment[] segment;
  private int numIntersections;
  private int segmentCapcity;

  // integer representing each direction
  private final int SOUTHWARD = 0;
  private final int EASTWARD = 1;
  private final int NORTHWARD = 2;
  private final int WESTWARD = 3;

  public GridControl(int numIntersectionsInOneDirection, int segCapcity){
    this.segmentCapcity = segCapcity;
    this.numIntersections = numIntersectionsInOneDirection + 1;
    intersection = new Intersection[numIntersections][numIntersections];
    segment = new Segment[numIntersections * 4 * numIntersections];

    int direction = 0;

    for (int index = 0; index < segment.length; index++){
      segment[index] = new Segment(direction, segmentCapcity);
      direction++;
      if(direction == 4)
        direction = 0;
    } // end of for (int index = 0; index < segment.length; index++)


    for (int row = 1; row < numIntersections; row++){
      for (int col = 1; col < numIntersections; col++){
        intersection[row][col] = new Intersection();
      } // end of for(int col = 1; col < numIntersections; col++)
    } // end of for(int row = 1; row < numIntersections; row++) 

    setInboundSegment();
    setOutboundSegment();
  } // end of GridControl constructor

  private void setInboundSegment(){
    for (int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        int index = 4 * ((row - 1) + (col - 1) * numIntersections);
        intersection[row][col].setInbound(segment[index], SOUTHWARD);
        intersection[row][col].setInbound(segment[index + 1], EASTWARD);
        intersection[row][col].setInbound(segment[index + 2], NORTHWARD);
        intersection[row][col].setInbound(segment[index + 3], WESTWARD);
      } //end of for(int col = 1; col < numIntersections; col++)
    } // end of for(int row = 1; row < numIntersections; row++)
  } // end of setInboundSegment

  private void setOutboundSegment(){  
    // connect outbound segments to intersections
    for(int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        if(row + 1 < numIntersections){
          intersection[row][col].setOutbound(
            intersection[row + 1][col].getInbound(NORTHWARD), NORTHWARD);
        }
        else{
          Segment sg = new Segment(NORTHWARD, segmentCapcity);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, NORTHWARD);
        } // end of if(row + 1 < numIntersections) else ...

        if(col - 1 > 0){
          intersection[row][col].setOutbound(
            intersection[row][col - 1].getInbound(WESTWARD), WESTWARD);
        }
        else{
          Segment sg = new Segment(WESTWARD, segmentCapcity);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, WESTWARD);
        } // end of if(col - 1 > 0) else ...

        if(row - 1 > 0){
          intersection[row][col].setOutbound(
            intersection[row - 1][col].getInbound(SOUTHWARD), SOUTHWARD);
        }
        else {
          Segment sg = new Segment(SOUTHWARD, segmentCapcity);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, SOUTHWARD);
        } // end of if(row - 1 > 0) else ...

        if(col + 1 < numIntersections){
          intersection[row][col].setOutbound(
            intersection[row][col + 1].getInbound(EASTWARD), EASTWARD);
        }
        else {
          Segment sg = new Segment(EASTWARD, segmentCapcity);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, EASTWARD);
        } // end of if(col + 1 < numIntersections) else... 
      } // end of for (int col = 0; col < numIntersections; col++)
    } // end of for (int row = 0; row < numIntersections; row++)
  } // end of setOutboundSegment

  public Intersection[][] getIntersections(){
    return intersection;
  } // end of getIntersections

} //end of GridControl class
