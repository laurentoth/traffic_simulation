// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** GridControl
// *****************************************************************************
// *****************************************************************************

public class GridControl{
  // 0:S 1:E 2:N 3:W (aiming in that direction)
    private Intersection[][] intersection;
    private Segment[] segment;
    private int numIntersections;
    private final int SEGMENT_CAPACITY = 100;

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
    }// end of for (int x = 0; x < segment.length; x++)

    for (int row = 1; row < numIntersections; row++){
        for (int col = 1; col < numIntersections; col++){
             intersection[row][col] = new Intersection();
         } // end of for (int c = 0; c < numIntersections; c++)
    }//end of for (int r =0; r < numIntersections; r++) 

    setInboundSegment();
    setOutboundSegment();
  }//GridControl

  private void setInboundSegment(){
    for (int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        int index = 4 * ((row - 1) + (col - 1) * numIntersections);
        intersection[row][col].setInbound(segment[index], 0);
        intersection[row][col].setInbound(segment[index + 1], 1);
        intersection[row][col].setInbound(segment[index + 2], 2);
        intersection[row][col].setInbound(segment[index + 3], 3);
        }//end of for(int col = 0; col < numIntersections; col++)
    } // end of for (int row = 0; row < numIntersections; row++)
  }//setInboundSegment

  private void setOutboundSegment(){  
    // 0:S 1:E 2:N 3:W (aiming in that direction)
    // connect outbound segments to intersections
    for(int row = 1; row < numIntersections; row++){
      for(int col = 1; col < numIntersections; col++){
        if(row + 1 < numIntersections){
          intersection[row][col].setOutbound(
                                   intersection[row + 1][col].getInbound(2), 2);
        }
        else{
          Segment sg = new Segment(SEGMENT_CAPACITY, 2);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, 2);
        }

        if(col - 1 > 0){
          intersection[row][col].setOutbound(
                                   intersection[row][col - 1].getInbound(3), 3);
        }
        else{
          Segment sg = new Segment(SEGMENT_CAPACITY, 3);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, 3);
        }

        if(row - 1 > 0){
          intersection[row][col].setOutbound(
                                   intersection[row - 1][col].getInbound(0), 0);
        }
        else {
          Segment sg = new Segment(SEGMENT_CAPACITY, 0);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, 0);
        }

        if(col + 1 < numIntersections){
          intersection[row][col].setOutbound(
                                   intersection[row][col + 1].getInbound(1), 1);
        }
        else {
          Segment sg = new Segment(SEGMENT_CAPACITY, 1);
          sg.setIsEdge(true);
          intersection[row][col].setOutbound(sg, 1);
        }
      }//end of for (int j = 0; j < numIntersections; j++)
    }//end of for (int i = 0; i < numIntersections; i++)
  }//setOutboundSegment

  public Intersection[][] getIntersections(){
    return intersection;
  }//getIntersections

}//GridControl
