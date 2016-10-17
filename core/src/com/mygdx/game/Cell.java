package com.mygdx.game;

/**
 * Created by jfabiano on 10/14/2016.
 */
public class Cell {
    int x, y;
    int cellSize = 10;
    String type;

    public Cell()
    {

    }
    public Cell(String type)
    {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
