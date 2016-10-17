package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/14/2016.
 */
public class ScreenGridTest {
    //food is there, so true is expected response
    @Test
    public void checkForCell() throws Exception
    {
        //create a grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();
        //create a cell for the head
//        Cell testHead = new Cell();
//        testHead.setX(0);
//        testHead.setY(0);
//        testGrid.addCellToGrid(testHead);
        //create a cell for the food
        Cell testFood = new Cell("food");
        testFood.setX(0);
        testFood.setY(1);
        testGrid.addCellToGrid(testFood);
        //call a move method to move the head to the food
//        testGrid.moveGridCellUp(testHead);
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
        //create a cell for the head
//        Cell testHead = new Cell();
//        testHead.setX(0);
//        testHead.setY(0);
//        testGrid.addCellToGrid(testHead);
        //create a cell for the food
        Cell testHead = new Cell("head");
        testHead.setX(0);
        testHead.setY(1);
        testGrid.addCellToGrid(testHead);
        //call a move method to move the head to the food
//        testGrid.moveGridCellUp(testHead);
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
        //create a cell for the head
//        Cell testHead = new Cell();
//        testHead.setX(0);
//        testHead.setY(0);
//        testGrid.addCellToGrid(testHead);
        //create a cell for the food
//        Cell testHead = new Cell("head");
//        testHead.setX(0);
//        testHead.setY(1);
//        testGrid.addCellToGrid(testHead);
        //call a move method to move the head to the food
//        testGrid.moveGridCellUp(testHead);
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
    public void createNextGridCell() throws Exception
    {
        ScreenGrid testGrid = new ScreenGrid();
        //Cell testCell = testGrid.createNextGridCell();
    }

}