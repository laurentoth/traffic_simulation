// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** GridControl
// *****************************************************************************
// *****************************************************************************

public class GridControl{
  // n: num intersections per row/col, so 
  // numIntersections = n * n
    private Intersection[][] intersection;
    private Segment[] segment;
    private int numIntersections;

  public GridControl(int numIntersectionsInOneDirection){
    int segmentCapacity = 10;
    this.numIntersections = numIntersectionsInOneDirection;
    intersection = new Intersection[numIntersections][numIntersections];
    segment = new Segment[numIntersections * 4 * numIntersections];
    int direction = 0;

    for (int x = 0; x < segment.length; x++) {
      segment[x] = new Segment(segmentCapacity, direction);
      direction++;
      if(direction == 4){direction = 0;}
    }


    for (int r =0; r < numIntersections; r++){
        for (int c = 0; c < numIntersections; c++) {
             intersection[r][c] = new Intersection();
         }
    }


    for (int index = 0; index < numIntersections * numIntersections; index++) {
        for (int i =0; i <4; i++){
        intersection[index / numIntersections][index % numIntersections].setInbound(segment[4 * index + i], i);                                  // i represents the direction
      }
  }
    


    // 0:N; 1:W; 2:S; 3:E
    // connect them up
    for (int i = 0; i < numIntersections; i++) {
      for (int j = 0; j < numIntersections; j++) {
        if (i + 1 < numIntersections) {
          intersection[i][j].setOutbound(intersection[i + 1][j].getInbound(0),
            0);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 0);
          sg.setIsEdge(true);
          intersection[i][j].setOutbound(sg, 0);
        }

        if (j - 1 > -1) {
          intersection[i][j].setOutbound(intersection[i][j - 1].getInbound(1),
            1);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 1);
          sg.setIsEdge(true);
          intersection[i][j].setOutbound(sg, 1);
        }

        if (i - 1 > -1) {
          intersection[i][j].setOutbound(intersection[i - 1][j].getInbound(2),
            2);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 2);
          sg.setIsEdge(true);
          intersection[i][j].setOutbound(sg, 2);
        }

        if (j + 1 < numIntersections) {
          intersection[i][j].setOutbound(intersection[i][j + 1].getInbound(3),
            3);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 3);
          sg.setIsEdge(true);
          intersection[i][j].setOutbound(sg, 3);
        }
      }
    }
  }

  public Intersection[][] getIntersections() {
    return intersection;
  }

}
