// Programmer: Team FOXES (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** GridControl
// *****************************************************************************
// *****************************************************************************
public class GridControl{
  // n: num intersections per row/col, so 
  // numIntersections = n * n
    private Intersection[][] intersections;
    private Segment[] segments;
    private int numIntersections;

  public GridControl(int numIntersectionsInOneDirection){
    int segmentCapacity = 10;
    this.numIntersections = numIntersectionsInOneDirection;
    intersections = new Intersection[numIntersections][numIntersections];
    segments = new Segment[numIntersections * 4];
    int direction = 0;

    for (int x = 0; x < segments.length; x++) {
      segments[x] = new Segment(segmentCapacity, direction);
      direction++;
      if(direction == 4){direction = 0;}
    }


    for (int r =0; r < numIntersections; r++){
        for (int c = 0; c < numIntersections; c++) {
             intersections[r][c] = new Intersection();
             System.out.println("r: " + r + " c: " + c);
         }
    }


    for (int index = 0; index < numIntersections * numIntersections; index++) {
        for (int i =0; i <4; i++){
        System.out.println("DEBUG Intersection: " + index/numIntersections + " " + index % numIntersections + " INDEX: " + index);
        intersections[index / numIntersections][index % numIntersections].setInbound(segments[4 * index + i], i);                                  // i represents the direction
      }
  }
    


    // 0:N; 1:W; 2:S; 3:E
    // connect them up
    for (int i = 0; i < numIntersections; i++) {
      for (int j = 0; j < numIntersections; j++) {
        if (i + 1 < numIntersections) {
          intersections[i][j].setOutbound(intersections[i + 1][j].getInbound(0),
            0);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 0);
          sg.setIsEdge(true);
          intersections[i][j].setOutbound(sg, 0);
        }

        if (j - 1 > -1) {
          intersections[i][j].setOutbound(intersections[i][j - 1].getInbound(1),
            1);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 1);
          sg.setIsEdge(true);
          intersections[i][j].setOutbound(sg, 1);
        }

        if (i - 1 > -1) {
          intersections[i][j].setOutbound(intersections[i - 1][j].getInbound(2),
            2);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 2);
          sg.setIsEdge(true);
          intersections[i][j].setOutbound(sg, 2);
        }

        if (j + 1 < numIntersections) {
          intersections[i][j].setOutbound(intersections[i][j + 1].getInbound(3),
            3);
        }
        else {
          Segment sg = new Segment(segmentCapacity, 3);
          sg.setIsEdge(true);
          intersections[i][j].setOutbound(sg, 3);
        }
      }
    }
  }

  public Intersection[][] getIntersections() {
    return intersections;
  }

}
