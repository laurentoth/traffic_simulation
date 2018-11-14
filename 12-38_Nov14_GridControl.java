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
  private final int SEGMENT_CAPACITY = 100;

  // integer representing each direction
  private final int S = 0;
  private final int E = 1;
  private final int N = 2;
  private final int W = 3;

  public GridControl(int numIntersectionsInOneDirection){
    this.numIntersections = numIntersectionsInOneDirection + 1;
    intersection = new Intersection[numIntersections][numIntersections];
    segment = new Segment[numIntersections * 4 * numIntersections];

    int direction = 0;

    for (int index = 0; index < segment.length; index++){
      segment[index] = new Segment(SEGMENT_CAPACITY, direction);
      direction++;
      if(direction == 4)
        direction = 0;
    } // end of for (int index = 0; index < segment.length; index++)


    for (int row = 1; row < numIntersections; row++){
      for (int col = 1; col < numIntersections; col++){
        intersection[row][col] = new Intersection(
            isInterNorthEdge(row), isInterSouthEdge(row),
            isInterWestEdge(col),  isInterEastEdge(row));
      } // end of for(int col = 0; col < numIntersections; col++)
    } // end of for(int row = 0; row < numIntersections; row++) 

    setInboundSegment();
    setOutboundSegment();
  } // end of GridControl constructor

  private void setInboundSegment(){
    for (int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        int index = 4 * ((row - 1) + (col - 1) * numIntersections);
        intersection[row][col].setInbound(segment[index], S);
        intersection[row][col].setInbound(segment[index + 1], E);
        intersection[row][col].setInbound(segment[index + 2], N);
        intersection[row][col].setInbound(segment[index + 3], W);
      } //end of for(int col = 0; col < numIntersections; col++)
    } // end of for(int row = 0; row < numIntersections; row++)
  } // end of setInboundSegment

  private void setOutboundSegment(){  
    // connect outbound segments to intersections
    for(int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        if(row + 1 < numIntersections){
          intersection[row][col].setOutbound(
            intersection[row + 1][col].getInbound(N), N);
        }
        else{
          Segment sg = new Segment(SEGMENT_CAPACITY, N);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, N);
        } // end of if(row + 1 < numIntersections) else ...

        if(col - 1 > 0){
          intersection[row][col].setOutbound(
            intersection[row][col - 1].getInbound(W), W);
        }
        else{
          Segment sg = new Segment(SEGMENT_CAPACITY, W);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, W);
        } // end of if(col - 1 > 0) else ...

        if(row - 1 > 0){
          intersection[row][col].setOutbound(
            intersection[row - 1][col].getInbound(S), S);
        }
        else {
          Segment sg = new Segment(SEGMENT_CAPACITY, S);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, S);
        } // end of if(row - 1 > 0) else ...

        if(col + 1 < numIntersections){
          intersection[row][col].setOutbound(
            intersection[row][col + 1].getInbound(E), E);
        }
        else {
          Segment sg = new Segment(SEGMENT_CAPACITY, E);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, E);
        } // end of if(col + 1 < numIntersections) else... 
      } // end of for (int col = 0; col < numIntersections; col++)
    } // end of for (int row = 0; row < numIntersections; row++)
  } // end of setOutboundSegment


  public Intersection[][] getIntersections(){
    return intersection;
  } // end of getIntersections

} //end of GridControl class
