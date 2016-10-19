package com.mygdx.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/14/2016.
 */
public class ScreenGridTest {
    @Test
    public void isCellFood() throws Exception {
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        boolean isCellFood = testGrid.isCellFood("food");
        assertNotNull(isCellFood);
        assertEquals(true, isCellFood);
    }

    @Test
    public void isCellFoodNegative() throws Exception {
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        boolean isCellFood = testGrid.isCellFood("head");
        assertNotNull(isCellFood);
        assertEquals(false, isCellFood);
    }

    @Test
    public void addFoodCellToGrid() throws Exception {
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        Cell testHeadCell = new Cell("head");
        testHeadCell.setX(0);
        testHeadCell.setY(0);
        testGrid.addCellToGrid(testHeadCell);
        Cell testCell = new Cell("food");
        testGrid.addFoodCellToGrid(testCell);
        assertNotNull(testCell);
        assertNotEquals(0, testCell.getX());
        assertNotEquals(0, testCell.getY());

    }

    //food is there, so true is expected response
    @Test
    public void checkForCell() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();

        Cell testFood = new Cell("food");
        testFood.setX(0);
        testFood.setY(1);
        testGrid.addCellToGrid(testFood);
        //the boolean should return true if there is food in the coordinate, and false if there is not, so make a test for each of these possibilities
        boolean response = testGrid.checkForCell(0, 1);
        assertNotNull(response);
        assertEquals(true, response);
    }
    //a head type cell is there, so false is expected
    @Test
    public void checkForCellNegative() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();

        Cell testHead = new Cell("head");
        testHead.setX(0);
        testHead.setY(1);
        testGrid.addCellToGrid(testHead);
        //the boolean should return true if there is food in the coordinate, and false if there is not, so make a test for each of these possibilities
        boolean response = testGrid.checkForCell(0, 1);
        assertNotNull(response);
        assertEquals(true, response);
    }
    //null is there, so false is expected
    @Test
    public void checkForCellNull() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //the boolean should return true if there is food in the coordinate, and false if there is not, so make a test for each of these possibilities
        boolean response = testGrid.checkForCell(0, 1);
        assertNotNull(response);
        assertEquals(false, response);

    }

    @Test
    public void moveGridCellUp() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(0);
        testCell.setY(0);
        testGrid.addCellToGrid(testCell);
        //move the cell up(+1) in the Y axis(method)
        testGrid.moveGridCellUp(testCell);
        //Remove the cell from the old coordinate in the grid(method)
        //change the y axis value in the cell object to reflect the new coordinate in the grid(method)
        assertNotNull(testGrid.coordinateGrid[0][1]);
        assertNull(testGrid.coordinateGrid[0][0]);
        assertEquals(0, testCell.getX());
        assertEquals(1, testCell.getY());
    }
    @Test
    public void moveGridCellUpWithTail() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testCell.setLength(4);
        testGrid.addCellToGrid(testCell);

        //create coordinates and add to the coordinatelist in the head
        ArrayList<Coordinates> testCoordinateList = new ArrayList<Coordinates>();
        Coordinates testCoordinate1 = new Coordinates(1, 2);
        Coordinates testCoordinate2 = new Coordinates(2, 2);
        Coordinates testCoordinate3 = new Coordinates(3, 2);
        Coordinates testCoordinate4 = new Coordinates(3, 3);
        testCoordinateList.add(testCoordinate1);
        testCoordinateList.add(testCoordinate2);
        testCoordinateList.add(testCoordinate3);
        testCoordinateList.add(testCoordinate4);
        testCell.setBreadCrumbsList(testCoordinateList);

        //create a tail of 1, 2, and 3 for the test
        Cell testBodyCell3 = new Cell("body");
        testBodyCell3.setX(3);
        testBodyCell3.setY(2);
        testGrid.addCellToGrid(testBodyCell3);

        Cell testBodyCell2 = new Cell("body");
        testBodyCell2.setX(2);
        testBodyCell2.setY(2);
        testGrid.addCellToGrid(testBodyCell2);

        Cell testBodyCell1 = new Cell("body");
        testBodyCell1.setX(1);
        testBodyCell1.setY(2);
        testGrid.addCellToGrid(testBodyCell1);

        //create an array of cells as body to add to the head body list
        ArrayList<Cell> testBodyList = new ArrayList<Cell>();
        testBodyList.add(testBodyCell1);
        testBodyList.add(testBodyCell2);
        testBodyList.add(testBodyCell3);
        testCell.setBody(testBodyList);

        System.out.println("before the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        testGrid.moveGridCellUp(testCell);

        //make sure the cell tail being gotten is the last or furthest from the head in the list
        System.out.println("after the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        assertNotNull(testGrid.coordinateGrid[3][4]);
        assertNull(testGrid.coordinateGrid[3][3]);
        assertEquals(3, testCell.getX());
        assertEquals(4, testCell.getY());

    }
    @Test
    public void moveGridCellUpCollision() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testGrid.addCellToGrid(testCell);
        //create a cell in the grid to collide with for the test
        Cell testCollisionCell = new Cell("body");
        testCollisionCell.setX(3);
        testCollisionCell.setY(4);
        testGrid.addCellToGrid(testCollisionCell);

        //move the cell up(+1) in the Y axis(method)
        try {
            testGrid.moveGridCellUp(testCell);
        }
        catch(Exception e)
        {
            assertEquals("java.lang.Exception: You hit a snake body segment. This is not a food cell!", e.toString());
        }

        assertNotNull(testGrid.coordinateGrid[3][3]);
        assertNotNull(testGrid.coordinateGrid[3][4]);
        assertEquals(3, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void moveGridCellDown() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(0);
        testCell.setY(1);
        testGrid.addCellToGrid(testCell);
        //move the cell up(+1) in the Y axis(method)
        testGrid.moveGridCellDown(testCell);
        //Remove the cell from the old coordinate in the grid(method)
        //change the y axis value in the cell object to reflect the new coordinate in the grid(method)
        assertNotNull(testGrid.coordinateGrid[0][0]);
        assertNull(testGrid.coordinateGrid[0][1]);
        assertEquals(0, testCell.getX());
        assertEquals(0, testCell.getY());
    }
    @Test
    public void moveGridCellDownWithTail() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testCell.setLength(4);
        testGrid.addCellToGrid(testCell);

        //create coordinates and add to the coordinatelist in the head
        ArrayList<Coordinates> testCoordinateList = new ArrayList<Coordinates>();
        Coordinates testCoordinate1 = new Coordinates(4, 5);
        Coordinates testCoordinate2 = new Coordinates(4, 4);
        Coordinates testCoordinate3 = new Coordinates(3, 4);
        Coordinates testCoordinate4 = new Coordinates(3, 3);
        testCoordinateList.add(testCoordinate1);
        testCoordinateList.add(testCoordinate2);
        testCoordinateList.add(testCoordinate3);
        testCoordinateList.add(testCoordinate4);
        testCell.setBreadCrumbsList(testCoordinateList);

        //create a tail of 1, 2, and 3 for the test
        Cell testBodyCell3 = new Cell("body");
        testBodyCell3.setX(3);
        testBodyCell3.setY(4);
        testGrid.addCellToGrid(testBodyCell3);

        Cell testBodyCell2 = new Cell("body");
        testBodyCell2.setX(4);
        testBodyCell2.setY(4);
        testGrid.addCellToGrid(testBodyCell2);

        Cell testBodyCell1 = new Cell("body");
        testBodyCell1.setX(4);
        testBodyCell1.setY(5);
        testGrid.addCellToGrid(testBodyCell1);

        //create an array of cells as body to add to the head body list
        ArrayList<Cell> testBodyList = new ArrayList<Cell>();
        testBodyList.add(testBodyCell1);
        testBodyList.add(testBodyCell2);
        testBodyList.add(testBodyCell3);
        testCell.setBody(testBodyList);

        System.out.println("before the method call:");
        //System.out.println("testcell = " + testCell.getBody().indexOf(testCell));
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));

        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        testGrid.moveGridCellDown(testCell);

        //make sure the cell tail being gotten is the last or furthest from the head in the list
        System.out.println("after the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        assertNotNull(testGrid.coordinateGrid[3][2]);
        assertNull(testGrid.coordinateGrid[3][3]);
        assertEquals(3, testCell.getX());
        assertEquals(2, testCell.getY());
    }
    @Test
    public void moveGridCellDownCollision() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testGrid.addCellToGrid(testCell);
        //create a cell in the grid to collide with for the test
        Cell testCollisionCell = new Cell("body");
        testCollisionCell.setX(3);
        testCollisionCell.setY(2);
        testGrid.addCellToGrid(testCollisionCell);

        //move the cell up(+1) in the Y axis(method)
        try {
            testGrid.moveGridCellDown(testCell);
        }
        catch(Exception e)
        {
            assertEquals("java.lang.Exception: You hit a snake body segment. This is not a food cell!", e.toString());
        }

        assertNotNull(testGrid.coordinateGrid[3][3]);
        assertNotNull(testGrid.coordinateGrid[3][2]);
        assertEquals(3, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void moveGridCellLeft() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(1);
        testCell.setY(0);
        testGrid.addCellToGrid(testCell);
        //move the cell up(+1) in the Y axis(method)
        testGrid.moveGridCellLeft(testCell);
        //Remove the cell from the old coordinate in the grid(method)
        //change the y axis value in the cell object to reflect the new coordinate in the grid(method)
        assertNotNull(testGrid.coordinateGrid[0][0]);
        assertNull(testGrid.coordinateGrid[1][0]);
        assertEquals(0, testCell.getX());
        assertEquals(0, testCell.getY());
    }
    @Test
    public void moveGridCellLeftWithTail() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testCell.setLength(4);
        testGrid.addCellToGrid(testCell);

        //create coordinates and add to the coordinatelist in the head
        ArrayList<Coordinates> testCoordinateList = new ArrayList<Coordinates>();
        Coordinates testCoordinate1 = new Coordinates(1, 2);
        Coordinates testCoordinate2 = new Coordinates(2, 2);
        Coordinates testCoordinate3 = new Coordinates(3, 2);
        Coordinates testCoordinate4 = new Coordinates(3, 3);
        testCoordinateList.add(testCoordinate1);
        testCoordinateList.add(testCoordinate2);
        testCoordinateList.add(testCoordinate3);
        testCoordinateList.add(testCoordinate4);
        testCell.setBreadCrumbsList(testCoordinateList);

        //create a tail of 1, 2, and 3 for the test
        Cell testBodyCell3 = new Cell("body");
        testBodyCell3.setX(3);
        testBodyCell3.setY(2);
        testGrid.addCellToGrid(testBodyCell3);

        Cell testBodyCell2 = new Cell("body");
        testBodyCell2.setX(2);
        testBodyCell2.setY(2);
        testGrid.addCellToGrid(testBodyCell2);

        Cell testBodyCell1 = new Cell("body");
        testBodyCell1.setX(1);
        testBodyCell1.setY(2);
        testGrid.addCellToGrid(testBodyCell1);

        //create an array of cells as body to add to the head body list
        ArrayList<Cell> testBodyList = new ArrayList<Cell>();
        testBodyList.add(testBodyCell1);
        testBodyList.add(testBodyCell2);
        testBodyList.add(testBodyCell3);
        testCell.setBody(testBodyList);

        System.out.println("before the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        testGrid.moveGridCellLeft(testCell);

        //make sure the cell tail being gotten is the last or furthest from the head in the list
        System.out.println("after the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        assertNotNull(testGrid.coordinateGrid[2][3]);
        assertNull(testGrid.coordinateGrid[3][3]);
        assertEquals(2, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void moveGridCellLeftCollision() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testGrid.addCellToGrid(testCell);
        //create a cell in the grid to collide with for the test
        Cell testCollisionCell = new Cell("body");
        testCollisionCell.setX(2);
        testCollisionCell.setY(3);
        testGrid.addCellToGrid(testCollisionCell);

        //move the cell up(+1) in the Y axis(method)
        try {
            testGrid.moveGridCellLeft(testCell);
        }
        catch(Exception e)
        {
            assertEquals("java.lang.Exception: You hit a snake body segment. This is not a food cell!", e.toString());
        }

        assertNotNull(testGrid.coordinateGrid[3][3]);
        assertNotNull(testGrid.coordinateGrid[2][3]);
        assertEquals(3, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void moveGridCellRight() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(0);
        testCell.setY(0);
        testGrid.addCellToGrid(testCell);
        //move the cell up(+1) in the Y axis(method)
        testGrid.moveGridCellRight(testCell);
        //Remove the cell from the old coordinate in the grid(method)
        //change the y axis value in the cell object to reflect the new coordinate in the grid(method)
        assertNotNull(testGrid.coordinateGrid[1][0]);
        assertNull(testGrid.coordinateGrid[0][0]);
        assertEquals(1, testCell.getX());
        assertEquals(0, testCell.getY());
    }
    @Test
    public void moveGridCellRightWithTail() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testCell.setLength(4);
        testGrid.addCellToGrid(testCell);

        //create coordinates and add to the coordinatelist in the head
        ArrayList<Coordinates> testCoordinateList = new ArrayList<Coordinates>();
        Coordinates testCoordinate1 = new Coordinates(1, 2);
        Coordinates testCoordinate2 = new Coordinates(2, 2);
        Coordinates testCoordinate3 = new Coordinates(3, 2);
        Coordinates testCoordinate4 = new Coordinates(3, 3);
        testCoordinateList.add(testCoordinate1);
        testCoordinateList.add(testCoordinate2);
        testCoordinateList.add(testCoordinate3);
        testCoordinateList.add(testCoordinate4);
        testCell.setBreadCrumbsList(testCoordinateList);

        //create a tail of 1, 2, and 3 for the test
        Cell testBodyCell3 = new Cell("body");
        testBodyCell3.setX(3);
        testBodyCell3.setY(2);
        testGrid.addCellToGrid(testBodyCell3);

        Cell testBodyCell2 = new Cell("body");
        testBodyCell2.setX(2);
        testBodyCell2.setY(2);
        testGrid.addCellToGrid(testBodyCell2);

        Cell testBodyCell1 = new Cell("body");
        testBodyCell1.setX(1);
        testBodyCell1.setY(2);
        testGrid.addCellToGrid(testBodyCell1);

        //create an array of cells as body to add to the head body list
        ArrayList<Cell> testBodyList = new ArrayList<Cell>();
        testBodyList.add(testBodyCell1);
        testBodyList.add(testBodyCell2);
        testBodyList.add(testBodyCell3);
        testCell.setBody(testBodyList);

        System.out.println("before the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        testGrid.moveGridCellRight(testCell);

        //make sure the cell tail being gotten is the last or furthest from the head in the list
        System.out.println("after the method call:");
        System.out.println("index of testbodycell1 = " + testCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testCell.getBody().indexOf(testBodyCell3));
        System.out.println("x of testcell = " + testCell.getX());
        System.out.println("y of testcell = " + testCell.getY());
        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        assertNotNull(testGrid.coordinateGrid[4][3]);
        assertNull(testGrid.coordinateGrid[3][3]);
        assertEquals(4, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void moveGridCellRightCollision() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell in the grid(method)
        Cell testCell = new Cell();
        testCell.setX(3);
        testCell.setY(3);
        testGrid.addCellToGrid(testCell);
        //create a cell in the grid to collide with for the test
        Cell testCollisionCell = new Cell("body");
        testCollisionCell.setX(4);
        testCollisionCell.setY(3);
        testGrid.addCellToGrid(testCollisionCell);

        //move the cell up(+1) in the Y axis(method)
        try {
            testGrid.moveGridCellRight(testCell);
        }
        catch(Exception e)
        {
            assertEquals("java.lang.Exception: You hit a snake body segment. This is not a food cell!", e.toString());
        }

        assertNotNull(testGrid.coordinateGrid[3][3]);
        assertNotNull(testGrid.coordinateGrid[4][3]);
        assertEquals(3, testCell.getX());
        assertEquals(3, testCell.getY());
    }
    @Test
    public void createNextGridCell() throws Exception
    {
        ScreenGrid testGrid = new ScreenGrid();
        //Cell testCell = testGrid.createNextGridCell();
    }

}