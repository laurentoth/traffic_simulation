// Programmer: Team FOXES (c) Copyright 2018
// *****************************************************************************
// *****************************************************************************
// **** Car
// *****************************************************************************
// *****************************************************************************

public class Car{
    private int carID;
    private int row;
    private int col;
    private int segmentDirectionCode;
    private int numBlocksBeforeTurning;
    private int turnDirectionCode;
    private int timeOnGrid;

    public Car (int carID,
                int r,
                int c,
                int direction,
                int blocks,
                int turn){
        this.carID = carID;
        this.row = r;
        this.col = c;
        this.turnDirectionCode = turn;
        this.segmentDirectionCode = direction;
        this.numBlocksBeforeTurning = blocks;
        this.timeOnGrid = 0;
    }
}
