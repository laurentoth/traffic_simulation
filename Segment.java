// Programmer: Team FOXES (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Segment
// *****************************************************************************
// *****************************************************************************
public class Segment {
  private int myCapacity;
  private int myDirection;
  private boolean isEdge;

  public Segment(int capacity, int direction) {
    myCapacity = capacity;
    myDirection = direction;
    isEdge = false;
  }

  public void setIsEdge(boolean b){
    isEdge = b;
    return;
  }

}