package com.mygdx.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/18/2016.
 */
public class CellTest {
    @Test
    public void addBodyCellToList() throws Exception
    {

    }

    @Test
    public void moveTailToBackOfHead() throws Exception
    {
        //create grid
        ScreenGrid testGrid = new ScreenGrid();
        testGrid.setScreenWidth(640);
        testGrid.setScreenHeight(480);
        testGrid.setCoordinateGrid();

        //create head cell
        Cell testHeadCell = new Cell("head");
        testHeadCell.setX(3);
        testHeadCell.setY(4);
        testHeadCell.setLength(4);
        testGrid.addCellToGrid(testHeadCell);

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
        testHeadCell.setBreadCrumbsList(testCoordinateList);

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
        testHeadCell.setBody(testBodyList);

        System.out.println("before the tail to head method call:");
        System.out.println("index of testbodycell1 = " + testHeadCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testHeadCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testHeadCell.getBody().indexOf(testBodyCell3));

        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        assertEquals(1, testBodyCell1.getX());
        assertEquals(2, testBodyCell1.getY());

        //call method
        testHeadCell.moveTailToBackOfHead();

        //make sure the cell tail being gotten is the last or furthest from the head in the list
        System.out.println("after the tail to head method call:");
        System.out.println("index of testbodycell1 = " + testHeadCell.getBody().indexOf(testBodyCell1));
        System.out.println("index of testbodycell2 = " + testHeadCell.getBody().indexOf(testBodyCell2));
        System.out.println("index of testbodycell3 = " + testHeadCell.getBody().indexOf(testBodyCell3));

        System.out.println("x of testbodycell1 = " + testBodyCell1.getX());
        System.out.println("y of testbodycell1 = " + testBodyCell1.getY());
        System.out.println("x of testbodycell2 = " + testBodyCell2.getX());
        System.out.println("y of testbodycell2 = " + testBodyCell2.getY());
        System.out.println("x of testbodycell3 = " + testBodyCell3.getX());
        System.out.println("y of testbodycell3 = " + testBodyCell3.getY());

        //make sure the location the cell tail is being moved to the location closest to the head in the list
        assertEquals(3, testBodyCell1.getX());
        assertEquals(3, testBodyCell1.getY());

        //make sure the old cell location is null
        //assertNull(testGrid.coordinateGrid[1][2]);
        assertNotNull(testGrid.coordinateGrid[2][2]);
        assertNotNull(testGrid.coordinateGrid[3][2]);
        //assertNotNull(testGrid.coordinateGrid[3][3]);
        assertNotNull(testGrid.coordinateGrid[3][4]);
    }

}