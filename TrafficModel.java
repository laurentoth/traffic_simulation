// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
// *****************************************************************************
// *****************************************************************************
 
import java.util.*;

public class TrafficModel {
  private int numIntersectionsInOneDirection, numInitalCars;
  private Car [] carArray;
  private static GridControl gc;
  private Intersection intersection [][];
  private int simulationTime;

  public TrafficModel(int numIntersections,
                      int numCarsToBegin,
                      int numTimeUnits,
                      int maxSegmentCapacity){
    this.numIntersectionsInOneDirection = numIntersections;
    this.numInitalCars = numCarsToBegin;
    this.simulationTime = numTimeUnits;
    carArray = new Car [numCarsToBegin];
    gc = new GridControl(numIntersectionsInOneDirection, maxSegmentCapacity);
    intersection = gc.getIntersections();
  } //end of TrafficModel Constructor

  public void addCar(int carID,
                     int row,
                     int col,
                     int segmentDirectionCode,
                     int numBlocksBeforeTurning,
                     int turnDirectionCode,
                     int timeToTraverse){

    carArray[carID-1] = new Car(carID,
                                row,
                                col,
                                segmentDirectionCode,
                                numBlocksBeforeTurning,
                                turnDirectionCode,
                                timeToTraverse);
    return;
  } // end of addCar

  public void placeCar(){
    for(int index = 0; index < carArray.length; index++){
      int tempRow = carArray[index].getCurrentRow();
      int tempCol = carArray[index].getCurrentCol();
      int direction = carArray[index].getCurrentDirection();
      intersection[tempRow][tempCol].putCarIntoSegment(carArray[index],
                                                       direction);
    } // end of for(int index = 0; index < carArray.length; index++)
    return;
  } // end of placeCar

  public void startSimulation(){
    for(int time = 0; time < simulationTime; time++){
      System.out.println("TIME UNIT " + (time + 1) + " BEGINS");
      System.out.println();
      
      for(int row = 1; row < (numIntersectionsInOneDirection + 1); row++){
        for(int col = 1; col < (numIntersectionsInOneDirection + 1); col++){
          System.out.println("At the intersection located at col "
                             + col + " and row " + row);
          intersection[row][col].advance();
          System.out.println();
        } // end for(int col = 1; col < (numIntersectionsInOneDirection + 1)...)
      } // end for(int row = 1; row < (numIntersectionsInOneDirection + 1)...)
      resetTimeUnit();
    } // end for(int time = 0; time < simulationTime; time++)

    return;
  } // end of startSimulation

  private void resetTimeUnit(){
    for(int row = 1; row < (numIntersectionsInOneDirection + 1); row++){
      for(int col = 1; col < (numIntersectionsInOneDirection + 1); col++){
         intersection[row][col].callResetCarsThatMoved();
      } // end of for(int row = 1...
    } // end of for(int col = 1 ...
  } // end of resetTimeUnit

  public String getAvrgTime(){
    String info = "";
    int numCarsLeft = gc.getNumCarsLeftGrid();
    double sumTime = gc.getSumTimeOnGrid();
    if(numCarsLeft == 0){
      info += "  No cars left the grid";
    }
    else{
      double avrg = sumTime / numCarsLeft;
      info += ("  The average time in the grid for cars that left the grid is: "
           + avrg);
    } // end of if(numCarsLeft == 0)
    return info;
  } // end of getAvrgTime

} // end of TrafficModel
