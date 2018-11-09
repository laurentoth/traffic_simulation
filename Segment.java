// Programmer: FOXES (Greg, Lauren, Maxine) (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Segment
// *****************************************************************************
// *****************************************************************************

public class Segment{
  private Queue<Car> myCarQueue;

  private int myCapacity;
  private int myDirection;

  private boolean isEdge;

  public Segment(int capacity, int direction) {
    myCapacity = capacity;
    myDirection = direction;
    isEdge = false;

    myCarQueue = new Queue<Car>();
  }//Segment Constructor

  public void setIsEdge(boolean b){
    isEdge = b;
    return;
  }//setIsEdge

  public boolean getIsEdge(){
    return isEdge;
  }//getIsEdge

  public boolean putCar(Car car)
  {
    if(myCarQueue.size() < myCapacity)
    {
        myCarQueue.add(car);
        return true;
    }
    return false;
  }

  public boolean putNewCar()
  {
    Car car = new Car();
    return putCar(car);
  }

  public Car getHeadCar()
  {
    return myCarQueue.peek();
  }

  public Car removeHeadCar()
  {
    return myCarQueue.poll();
  }

  public int capacity()
  {
      return myCapacity;
  }

  public void getCarsInfo()
  {

  }

}//Segment