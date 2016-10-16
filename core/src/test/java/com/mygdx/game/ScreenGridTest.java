package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/14/2016.
 */
public class ScreenGridTest {
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