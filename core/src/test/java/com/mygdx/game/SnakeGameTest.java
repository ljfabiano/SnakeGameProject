package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/12/2016.
 */
public class SnakeGameTest {
    @Test
    public void movePerGridCell() throws Exception {


    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void render() throws Exception {

    }

    @Test
    public void createTestCell() throws Exception
    {
        SnakeGame testGame = new SnakeGame();
        ScreenGrid testGrid = new ScreenGrid();
        Cell testCell = testGrid.createTestCell(20, 30);
        assertNotNull(testCell);
        assertEquals(20, testCell.getX());
        assertEquals(30, testCell.getY());
    }
    @Test
    public void createNextGridCell() throws Exception
    {
        SnakeGame testGame = new SnakeGame();
        ScreenGrid testGrid = new ScreenGrid();
        Cell testCell = new Cell();
        testGrid.setScreenWidth(480);
        testGrid.setScreenHeight(640);
        testGrid.setCoordinateGrid();
        Coordinates testCoordinatesInitial = new Coordinates(20, 30);
        testGrid.setTrueCellLocation(testCoordinatesInitial);
        Coordinates testCoordinatesReturn = testGrid.getTrueCellLocation();
        testCell.setX(testCoordinatesReturn.getX());
        testCell.setY(testCoordinatesReturn.getY());

        assertNotNull(testCell);
        assertEquals(20, testCell.getX());
        assertEquals(30, testCell.getY());
    }



}