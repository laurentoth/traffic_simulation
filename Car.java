// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Car
// *****************************************************************************
// *****************************************************************************

public class Car{
  private int myID;
  private int myRow;
  private int myCol;
  private int mySegDirection;    // 0: S; 1: E; 2: N; 3: W
  private int myNumBlocksBeforeTurning;
  private int myTurnSignal;      // 1: right; -1: left; 0: straight
  private int myTimeOnGrid;      // total time an instance has stayed in Grid
  private int myRemainingTimeOnSeg;   // time left on each segment
  private int myTimeTraverseSeg;      // time need to traverse segment

  public Car(int carID,
             int r,
             int c,
             int direction,
             int blocks,
             int turn,
             int traverseTm){
    myID = carID;
    myRow = r;
    myCol = c;
    mySegDirection = direction;
    myNumBlocksBeforeTurning = blocks;
    myTurnSignal = turn;
    myTimeOnGrid = 0;
    myTimeTraverseSeg = traverseTm;
  } // end of constructor of Car class

  public void resetRemainingTimeOnSeg(){
    myRemainingTimeOnSeg = myTimeTraverseSeg;
  } // end of resetRemainingTimeOnSeg()

  public boolean canLeaveSegment(){
    return (myRemainingTimeOnSeg <= 0);
  }  // end of canLeaveSegment()

  public int getTurnSignal(){
    if(myNumBlocksBeforeTurning == 0){
      return myTurnSignal;
    } // end of if(myNumBlocksBeforeTurning == 0) 

    return 0;
  }  // end of getTurnSignal()

  public int getID(){
    return myID;
  } // end of getID

  public int getCurrentRow(){
    return myRow;
  } // end of getCurrentRow

  public int getCurrentCol(){
    return myCol;
  } // end of getCurrentCol

  public int getCurrentDirection(){
    return mySegDirection;
  } // end of getCurrentDirection

  public void advanceOneTimeUnit(){
    myTimeOnGrid++;
    myRemainingTimeOnSeg--;
  }  // end of advanceOneTimeUnit()

  public void passOneBlock(){
    myNumBlocksBeforeTurning--;
  }  // end of passOneBlock()

  public int getTimeOnGrid(){
    return myTimeOnGrid;
  } // end of getTimeOnGrid
}
