// Programmer: Team FOXES (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
// *****************************************************************************
// *****************************************************************************
 
import java.util.*;

public class TrafficModel {
    private int numIntersectionsInOneDirection, numInitalCars;
    private Car [] carArray;


public TrafficModel(int numIntersections, int numCarsToBegin){
    this.numIntersectionsInOneDirection = numIntersections;
    this.numInitalCars = numCarsToBegin;
    carArray = new Car [numCarsToBegin];
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

}