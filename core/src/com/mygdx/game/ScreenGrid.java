package com.mygdx.game;

import java.lang.reflect.Array;

/**
 * Created by jfabiano on 10/12/2016.
 */
//This is to be used to segment the game movement and interaction into a grid in which the snake will move, and interact with the game environment
public class ScreenGrid {
    //The original size by pixel of the play area
    //Array[][] gridArray = new Array[800][600];
    //The size of the grid by units(5 pixels)
    //Array[][] gridArray = new Array[160][120];
    int screenWidth;
    int screenHeight;

    int dimensionOfCell = 10;
    boolean[][] coordinateGrid;
//    Array[] xCoordinate = new Array[screenWidth/dimensionOfCell];
//    Array[] yCoordinate = new Array[screenHeight/dimensionOfCell];
//    Array[] xCoordinate;
//    Array[] yCoordinate;
    //boolean[][] coordinateGrid = new boolean[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];

    public ScreenGrid()
    {

    }

    public ScreenGrid(int screenWidth, int screenHeight)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        coordinateGrid = new boolean[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

//    public Array[] getxCoordinate() {
//        return xCoordinate;
//    }
//
//    public void setxCoordinate(Array[] xCoordinate) {
//        this.xCoordinate = xCoordinate;
//    }
//
//    public Array[] getyCoordinate() {
//        return yCoordinate;
//    }
//
//    public void setyCoordinate(Array[] yCoordinate) {
//        this.yCoordinate = yCoordinate;
//    }

    public boolean[][] getCoordinateGrid() {
        return coordinateGrid;
    }

    public void setCoordinateGrid(boolean[][] coordinateGrid) {
        this.coordinateGrid = coordinateGrid;

    }
    public void setCoordinateGrid() {
        coordinateGrid = new boolean[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];

    }
//    public void setCoordinateGrid() {
//
//        for(int index; index < coordinateGrid.length; index++)
//        {
//            for()
//            {
//
//            }
//        }
//    }

    public void setTrueCellLocation(Coordinates myCoordinates)
    {
        System.out.println("x length of grid = " + coordinateGrid.length);
        for(int index = 0; index < coordinateGrid.length; index++) {
            System.out.println("y length of grid = " + coordinateGrid[index].length);
        }
        System.out.println("x = " + myCoordinates.getX() + " y = " + myCoordinates.getY());
        coordinateGrid[myCoordinates.getX()][myCoordinates.getY()] = true;
    }

    public void setFalseCellLocation(Coordinates myCoordinates)
    {
        coordinateGrid[myCoordinates.getX()][myCoordinates.getY()] = false;
    }

    public Coordinates getTrueCellLocation()
    {
        for(int xIndex = 0; xIndex < coordinateGrid.length; xIndex++)
        {
            for(int yIndex = 0; yIndex < coordinateGrid[xIndex].length; yIndex++)
            {
                if(coordinateGrid[xIndex][yIndex] == true)
                {
                    Coordinates trueCoordinates = new Coordinates(xIndex, yIndex);
                    return trueCoordinates;
                }
            }
        }
        return null;
    }

//    public int getTrueCellYLocation()
//    {
//
//        return 0;
//    }

    Cell createNextGridCell(int x, int y)
    {
        Cell myCell = new Cell();
        return myCell;
    }

    Cell createTestCell(int x, int y)
    {
        Cell gridCell = new Cell();
        gridCell.setX(x);
        gridCell.setY(y);
        return gridCell;
    }

    void createGridCell()
    {

    }

}
