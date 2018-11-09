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

  public TrafficModel(int numIntersections, int numCarsToBegin){
    this.numIntersectionsInOneDirection = numIntersections;
    this.numInitalCars = numCarsToBegin;
    carArray = new Car [numCarsToBegin];
    gc = new GridControl(numIntersectionsInOneDirection);
    intersection = gc.getIntersections();
    simulationTime = 2; //for now
  }

  public void addCar(int carID,
                     int row,
                     int col,
                     int segmentDirectionCode,
                     int numBlocksBeforeTurning,
                     int turnDirectionCode){

    carArray[carID-1] = new Car(carID,
                                row,
                                col,
                                segmentDirectionCode,
                                numBlocksBeforeTurning,
                                turnDirectionCode);
    return;
  }
  // 0:N; 1:W; 2:S; 3:E
  public void startSimulation(){
    for(int i = 0; i < simulationTime; i++){
      for (int row = 1; row < numIntersectionsInOneDirection + 1; row++){
        for(int col = 1; col < numIntersectionsInOneDirection + 1; col++){
          intersection[row][col].advance();
        }
      }
    }
    return;
  }
}
