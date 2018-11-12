// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Segment
// *****************************************************************************
// *****************************************************************************
import java.util.*;
public class Segment{

  private Queue<Car> myCarQueue;

  private int myCapacity;
  private int myDirection;

  private boolean isEdge;

  public Segment(int capacity, int direction){
    myCapacity = capacity;
    myDirection = direction;
    isEdge = false;

    myCarQueue = new LinkedList<Car>();
  } // end of Segment Constructor

  public void setIsEdge(boolean b){
    isEdge = b;
    return;
  }// end of setIsEdge

  public boolean getIsEdge(){
    return isEdge;
  }// end of getIsEdge

  public boolean putCar(Car car){
    if(myCarQueue.size() < myCapacity){
        myCarQueue.add(car);
        return true;
    } // end of if(myCarQueue.size() < myCapacity)
    return false;
  } // end of putCar

  public void advanceCarTimeOnGrid(){
    Iterator<Car> queueIterator = myCarQueue.iterator();
    while(queueIterator.hasNext()){
      queueIterator.next().advanceOneTimeUnit();
    } // end of while(queueIterator.hasNext())
  } // end of advanceCarTimeOnGrid

  public Car getHeadCar(){
    return myCarQueue.peek();
  } // end of getHeadCar

  public Car removeHeadCar(){
    return myCarQueue.poll();
  } // end of removeHeadCar

  public boolean isFull(){
    if (myCarQueue.size() == myCapacity){
      return true;
    } // end of if (the capacity of the Segment instance is reached)
    return false;
  } // end of isFull

  public int capacity(){
      return myCapacity;
  } // end of capacity

  public boolean isEmpty(){
    if(myCarQueue.size() > 0){
      return false;
    } // end of if(myCarQueue.size() > 0)

      return true;
  } // end of isEmpty

  public String getCarsInfo(){
    return "";
  } // end of getCarsInfo()

} // end of Segment
