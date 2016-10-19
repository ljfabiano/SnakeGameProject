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
    boolean[][] coordinateGridBool;
    Cell[][] coordinateGrid;
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
        //coordinateGrid = new boolean[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];
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

    public Cell[][] getCoordinateGrid() {
        return coordinateGrid;
    }

    public void setCoordinateGrid(Cell[][] coordinateGrid) {
        this.coordinateGrid = coordinateGrid;

    }
    public void setCoordinateGrid() {
        coordinateGrid = new Cell[screenWidth/dimensionOfCell][screenHeight/dimensionOfCell];

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
    //The method is used to move an object in the grid(object/cell) to another location in the grid
    void moveGridCellUp(Cell myCell)throws Exception//ArrayIndexOutOfBoundsException
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
                //addFoodCellToGrid(coordinateGrid[myCell.getX()][myCell.getY()]);
                coordinateGrid[myCell.getX()][myCell.getY() + 1] = myCell;
                //myCell = coordinateGrid[myCell.getX()][myCell.getY() + 1];


                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);

//                Cell newTail = new Cell("body");
//                coordinateGrid[myCell.getX()][myCell.getY()] = newTail;
//                //coordinateGrid[myCell.getX()][myCell.getY()] = null;
//                newTail.setX(myCell.getX());
//                newTail.setY(myCell.getY());
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
            coordinateGrid[myCell.getX()][myCell.getY()] = null;
            //coordinateGrid[myCell.getX()][myCell.getY()] = tail;
            //tail = myCell.getY()
            myCell.setY(myCell.getY() + 1);
            //if the head cell has a tail
            if(!myCell.getBody().isEmpty())
            {
                myCell.moveTailToBackOfHead();
                //the body cell at head cell's length property - 1 in the body cell list in the head cell is being assigned to the head cell's coordinate grid location in this screengrid class

                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);//myCell.getBody().size()//actual original working line
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.length - 1);//1st change is currently on all the move methods
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.length);//works for unit test
                //the tail of the snake's old location is set to null
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }

        }
    }
    void moveGridCellDown(Cell myCell)throws Exception//ArrayIndexOutOfBoundsException
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
                //myCell = coordinateGrid[myCell.getX()][myCell.getY() - 1];


                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);

//                Cell newTail = new Cell("body");
//                coordinateGrid[myCell.getX()][myCell.getY()] = newTail;
//                newTail.setX(myCell.getX());
//                newTail.setY(myCell.getY());
                //coordinateGrid[myCell.getX()][myCell.getY()] = null;
                myCell.setY(myCell.getY() - 1);
                //add the tail to the back of the snake


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
            coordinateGrid[myCell.getX()][myCell.getY()] = null;
            myCell.setY(myCell.getY() - 1);
            if(!myCell.getBody().isEmpty())
            {
                myCell.moveTailToBackOfHead();
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size());
                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.length - 1);
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }

        }
    }
    void moveGridCellLeft(Cell myCell)throws Exception//ArrayIndexOutOfBoundsException
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
                //myCell = coordinateGrid[myCell.getX() - 1][myCell.getY()];


                Cell newTail = new Cell();
                myCell.addBodyCellToList(newTail);
                myCell.setLength(myCell.getLength() + 1);


                //these need to be tied to the breadcrumbs because as of now they are floating in the grid, staying where they are... they are collided with eventually but are not visible...
//                Cell newTail = new Cell("body");
//                coordinateGrid[myCell.getX()][myCell.getY()] = newTail;
//                newTail.setX(myCell.getX());
//                newTail.setY(myCell.getY());
                //coordinateGrid[myCell.getX()][myCell.getY()] = null;
                myCell.setX(myCell.getX() - 1);
                //add the tail to the back of the snake


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
            coordinateGrid[myCell.getX()][myCell.getY()] = null;
            myCell.setX(myCell.getX() - 1);
            if(!myCell.getBody().isEmpty())//
            {
                myCell.moveTailToBackOfHead();
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size());
                coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.length - 1);
                coordinateGrid[myCell.getBody().get(0).getX()][myCell.getBody().get(0).getY()] = null;
            }

        }
    }
    void moveGridCellRight(Cell myCell)throws Exception//ArrayIndexOutOfBoundsException
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
                 //myCell = coordinateGrid[myCell.getX() + 1][myCell.getY()];



                 Cell newTail = new Cell();
                 myCell.addBodyCellToList(newTail);
                 myCell.setLength(myCell.getLength() + 1);

//                 Cell newTail = new Cell("body");
//                 coordinateGrid[myCell.getX()][myCell.getY()] = newTail;
//                 newTail.setX(myCell.getX());
//                 newTail.setY(myCell.getY());
                 //coordinateGrid[myCell.getX()][myCell.getY()] = null;
                 myCell.setX(myCell.getX() + 1);
                 //add the tail to the back of the snake
                //myCell.moveTailToBackOfHead();


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
             coordinateGrid[myCell.getX()][myCell.getY()] = null;
             myCell.setX(myCell.getX() + 1);
             if(!myCell.getBody().isEmpty())
             {
                 myCell.moveTailToBackOfHead();
                 //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size());
                 coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.getBody().size() - 1);
                 //coordinateGrid[myCell.getX()][myCell.getY()] = myCell.getBody().get(myCell.length - 1);
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
//        x = Math.random() * coordinateGrid.length;
//        y = Math.random() * coordinateGrid[0].length;
        foodCell.setX((int)x);
        foodCell.setY((int)y);
        coordinateGrid[foodCell.getX()][foodCell.getY()] = foodCell;
    }

}
