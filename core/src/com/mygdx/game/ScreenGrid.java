package com.mygdx.game;

import java.lang.reflect.Array;

/**
 * Created by jfabiano on 10/12/2016.
 */
//This is to be used to segment the game movement and interaction into a grid in which the snake will move, and interact with the game environment
public class ScreenGrid {
    //The original size by pixel of the play area
    //Array[][] gridArray = new Array[800][600];
    //The size of the grid by units(10 pixels)
    //Array[][] gridArray = new Array[160][120];
    int screenWidth;
    int screenHeight;

    int dimensionOfCell = 10;
    boolean[][] coordinateGridBool;
    Cell[][] coordinateGrid;

    public ScreenGrid()
    {

    }
    public ScreenGrid(int screenWidth, int screenHeight)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        coordinateGrid = new Cell[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];
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

    public Cell[][] getCoordinateGrid() {
        return coordinateGrid;
    }

    public void setCoordinateGrid(Cell[][] coordinateGrid) {
        this.coordinateGrid = coordinateGrid;

    }
    public void setCoordinateGrid() {
        coordinateGrid = new Cell[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];

    }
    public void setTrueCellLocation(Coordinates myCoordinates)
    {
        System.out.println("x length of grid = " + coordinateGrid.length);
        for(int index = 0; index < coordinateGrid.length; index++) {
            System.out.println("y length of grid = " + coordinateGrid[index].length);
        }
        System.out.println("x = " + myCoordinates.getX() + " y = " + myCoordinates.getY());
        coordinateGridBool[myCoordinates.getX()][myCoordinates.getY()] = true;
    }
    public void setFalseCellLocation(Coordinates myCoordinates)
    {
        coordinateGridBool[myCoordinates.getX()][myCoordinates.getY()] = false;
    }

    public Coordinates getTrueCellLocation()
    {
        for(int xIndex = 0; xIndex < coordinateGrid.length; xIndex++)
        {
            for(int yIndex = 0; yIndex < coordinateGrid[xIndex].length; yIndex++)
            {
                if(coordinateGridBool[xIndex][yIndex] == true)
                {
                    Coordinates trueCoordinates = new Coordinates(xIndex, yIndex);
                    return trueCoordinates;
                }
            }
        }
        return null;
    }
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
    //The method is used to move an object in the grid(object/cell) to another location in the grid
    void moveGridCellUp(Cell myCell)throws Exception
    {
        //if the cell to move to is not null then check for cell will be true as in there is a cell already there in the grid
        boolean checkForCell = checkForCell(myCell.getX(), myCell.getY() + 1);
        if(checkForCell == true)
        {
            //if the cell to move to has the type = food the return true, else false
            boolean isCellFood = isCellFood(coordinateGrid[myCell.getX()][myCell.getY() + 1].getType());
            if(isCellFood == true)
            {
                //Create another piece of food in the grid
                addFoodCellToGrid(coordinateGrid[myCell.getX()][myCell.getY() + 1]);
                coordinateGrid[myCell.getX()][myCell.getY() + 1] = myCell;
                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);
                //add the tail to the back of the snake
                myCell.setY(myCell.getY() + 1);
            }
            else if(isCellHead(coordinateGrid[myCell.getX()][myCell.getY() + 1].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake head. This is not a food cell!");
                throw ex;
            }
            else if(isCellBody(coordinateGrid[myCell.getX()][myCell.getY() + 1].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake body segment. This is not a food cell!");
                throw ex;
            }
            else
            {
                Exception ex = new Exception("This is not food, a head, or body... something else is wrong!");
                throw ex;
            }
        }
        //the cell to move the head to is empty
        else
        {
            coordinateGrid[myCell.getX()][myCell.getY() + 1] = myCell;
            if(myCell.getBody().isEmpty())
            {
                coordinateGrid[myCell.getX()][myCell.getY()] = null;
            }
            myCell.setY(myCell.getY() + 1);
            //if the head cell has a tail
            if(!myCell.getBody().isEmpty())
            {
                myCell.moveTailToBackOfHead();
                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                //the tail of the snake's old location is set to null
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }
        }
    }
    void moveGridCellDown(Cell myCell)throws Exception
    {
        boolean checkForCell = checkForCell(myCell.getX(), myCell.getY() - 1);
        if(checkForCell == true)
        {
            boolean isCellFood = isCellFood(coordinateGrid[myCell.getX()][myCell.getY() - 1].getType());
            if(isCellFood == true)
            {
                //Create another piece of food in the grid
                addFoodCellToGrid(coordinateGrid[myCell.getX()][myCell.getY() - 1]);
                coordinateGrid[myCell.getX()][myCell.getY() - 1] = myCell;
                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);
                myCell.setY(myCell.getY() - 1);
            }
            else if(isCellHead(coordinateGrid[myCell.getX()][myCell.getY() - 1].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake head. This is not a food cell!");
                throw ex;
            }
            else if(isCellBody(coordinateGrid[myCell.getX()][myCell.getY() - 1].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake body segment. This is not a food cell!");
                throw ex;
            }
            else
            {
                Exception ex = new Exception("This is not food, a head, or body... something else is wrong!");
                throw ex;
            }
        }
        else
        {
            coordinateGrid[myCell.getX()][myCell.getY() - 1] = myCell;
            if(myCell.getBody().isEmpty())
            {
                coordinateGrid[myCell.getX()][myCell.getY()] = null;
            }
            myCell.setY(myCell.getY() - 1);
            if(!myCell.getBody().isEmpty())
            {
                myCell.moveTailToBackOfHead();
                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }
        }
    }
    void moveGridCellLeft(Cell myCell)throws Exception
    {
        boolean checkForCell = checkForCell(myCell.getX() - 1, myCell.getY());
        if(checkForCell == true)
        {
            boolean isCellFood = isCellFood(coordinateGrid[myCell.getX() - 1][myCell.getY()].getType());
            if(isCellFood == true)
            {
                //Create another piece of food in the grid
                addFoodCellToGrid(coordinateGrid[myCell.getX() - 1][myCell.getY()]);
                coordinateGrid[myCell.getX() - 1][myCell.getY()] = myCell;
                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);
                myCell.setX(myCell.getX() - 1);
            }
            else if(isCellHead(coordinateGrid[myCell.getX() - 1][myCell.getY()].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake head. This is not a food cell!");
                throw ex;
            }
            else if(isCellBody(coordinateGrid[myCell.getX() - 1][myCell.getY()].getType()) == true)
            {
                Exception ex = new Exception("You hit a snake body segment. This is not a food cell!");
                throw ex;
            }
            else
            {
                Exception ex = new Exception("This is not food, a head, or body... something else is wrong!");
                throw ex;
            }
        }
        else
        {
            coordinateGrid[myCell.getX() - 1][myCell.getY()] = myCell;
            if(myCell.getBody().isEmpty())
            {
                coordinateGrid[myCell.getX()][myCell.getY()] = null;
            }
            myCell.setX(myCell.getX() - 1);
            if(!myCell.getBody().isEmpty())//
            {
                myCell.moveTailToBackOfHead();
                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }
        }
    }
    void moveGridCellRight(Cell myCell)throws Exception
     {
         boolean checkForCell = checkForCell(myCell.getX() + 1, myCell.getY());
         if(checkForCell == true)
         {
             boolean isCellFood = isCellFood(coordinateGrid[myCell.getX() + 1][myCell.getY()].getType());
             if(isCellFood == true)
             {
                 //Create another piece of food in the grid
                 addFoodCellToGrid(coordinateGrid[myCell.getX() + 1][myCell.getY()]);
                 coordinateGrid[myCell.getX() + 1][myCell.getY()] = myCell;
                 Cell newTail = new Cell();
                 myCell.addBodyCellToList(newTail);
                 myCell.setLength(myCell.getLength() + 1);
                 myCell.setX(myCell.getX() + 1);
             }
             else if(isCellHead(coordinateGrid[myCell.getX() + 1][myCell.getY()].getType()) == true)
             {
                 Exception ex = new Exception("You hit a snake head. This is not a food cell!");
                 throw ex;
             }
             else if(isCellBody(coordinateGrid[myCell.getX() + 1][myCell.getY()].getType()) == true)
             {
                 Exception ex = new Exception("You hit a snake body segment. This is not a food cell!");
                 throw ex;
             }
             else
             {
                 Exception ex = new Exception("This is not food, a head, or body... something else is wrong!");
                 throw ex;
             }
         }
         else
         {
             coordinateGrid[myCell.getX() + 1][myCell.getY()] = myCell;
             if(myCell.getBody().isEmpty())
             {
                 coordinateGrid[myCell.getX()][myCell.getY()] = null;
             }
             myCell.setX(myCell.getX() + 1);
             if(!myCell.getBody().isEmpty())
             {
                 myCell.moveTailToBackOfHead();
                 coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                 coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
             }
         }
    }

    public void addCellToGrid(Cell myCell)
    {
        coordinateGrid[myCell.getX()][myCell.getY()] = myCell;
    }

    public boolean checkForCell(int x, int y)
    {
        boolean coordinateisOccupied;
        if(coordinateGrid[x][y] != null)
        {
            coordinateisOccupied = true;
        }
        else
        {
            coordinateisOccupied = false;
        }
        return coordinateisOccupied;
    }

    public boolean isCellFood(String type)
    {
        boolean coordinateIsFood;
        if(type.equals("food"))
        {
            coordinateIsFood = true;
        }
        else
        {
            coordinateIsFood = false;
        }
        return coordinateIsFood;
    }

    public boolean isCellHead(String type)
    {
        boolean coordinateIsHead;
        if(type.equals("head"))
        {
            coordinateIsHead = true;
        }
        else
        {
            coordinateIsHead = false;
        }
        return coordinateIsHead;
    }

    public boolean isCellBody(String type)
    {
        boolean coordinateIsBody;
        if(type.equals("body"))
        {
            coordinateIsBody = true;
        }
        else
        {
            coordinateIsBody = false;
        }
        return coordinateIsBody;
    }

    public void addFoodCellToGrid(Cell foodCell)
    {
        double x, y;
        do {
            x = Math.random() * coordinateGrid.length;
            y = Math.random() * coordinateGrid[0].length;
        }
        while(coordinateGrid[(int)x][(int)y] != null);
        foodCell.setX((int)x);
        foodCell.setY((int)y);
        coordinateGrid[foodCell.getX()][foodCell.getY()] = foodCell;
        //a test to determine if the food was going to appear on top of the head of the snake
//        double x, y;
//                if(coordinateGrid[0][0] != null) {
//                    System.out.println("origin cell is occupied");
//                    do {
//                        x = Math.random() * coordinateGrid.length;
//                        y = Math.random() * coordinateGrid[0].length;
//                    }
//                    while(coordinateGrid[(int)x][(int)y] != null);
//                    foodCell.setX((int)x);
//                    foodCell.setY((int)y);
//                    coordinateGrid[foodCell.getX()][foodCell.getY()] = foodCell;
//                }
//                else
//                {
//                    System.out.println("origin cell is open");
//                        foodCell.setX(0);
//                        foodCell.setY(0);
//                    coordinateGrid[0][0] = foodCell;
//
//                }
    }
}
