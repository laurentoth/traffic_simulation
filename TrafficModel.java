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
  // 0:N; 1:W; 2:S; 3:E
public void startSimulation(){
    gc = new GridControl(numIntersectionsInOneDirection);
    intersection = gc.getIntersections();

    for(int x = 0; x < numIntersectionsInOneDirection; x++){
        for(int y = 0; y < numIntersectionsInOneDirection; y++){
            for(int n =0; n < 4; n++){
              System.out.println("Int: " + x +" " + y + " " + "Seg: " + n +
                                 " " + 
                                 intersection[x][y].getOutbound(n).getIsEdge());
            }
        }
    }
    return;
}

}