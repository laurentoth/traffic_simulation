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

  public Car(int carID,
             int r,
             int c,
             int direction,
             int blocks,
             int turn){
    myID = carID;
    myRow = r;
    myCol = c;
    mySegDirection = direction;
    myNumBlocksBeforeTurning = blocks;
    myTurnSignal = turn;
    myTimeOnGrid = 0;
  } // end of constructor of Car class


  public int getTurnSignal(){
    if (myNumBlocksBeforeTurning == 0) {
      return myTurnSignal;
    }
       // end of if (the number of blocks until turning for the instance 
       // reaches 0, meaning the car will turn based on its turn signal at the
       // current intersection)
    return 0;    // if myNumBlocksBeforeTurning != 0, go straight
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
  }  // end of advanceOneTimeUnit()

  public void passOneBlock(){
    myNumBlocksBeforeTurning--;
  }  // end of passOneBlock()

  public int getTimeOnGrid(){
    return myTimeOnGrid;
  } // end of getTimeOnGrid
}
