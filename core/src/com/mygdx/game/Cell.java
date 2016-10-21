package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by jfabiano on 10/14/2016.
 */
public class Cell {
    int x, y;
    int cellSize = 10;
    String type;
    ArrayList<Coordinates> breadCrumbsList = new ArrayList<Coordinates>();
    int length = 0;//food collected + head
    ArrayList<Cell> body = new ArrayList<Cell>();

    public Cell()
    {

    }
    public Cell(String type)
    {
        this.type = type;
    }

    public Cell(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Coordinates> getBreadCrumbsList() {
        return breadCrumbsList;
    }

    public void setBreadCrumbsList(ArrayList<Coordinates> breadCrumbsList) {
        this.breadCrumbsList = breadCrumbsList;
    }
    //called from snake game in the move method every time the head is going to move
    public void addCoordinateToList(Coordinates latestBreadCrumb)
    {
        //logic for adding the crumb to the last index(most recent)
        if(!breadCrumbsList.isEmpty() && (breadCrumbsList.size() - 1 == body.size()))
        {
            System.out.println("in the if statement in the addcoordinatetolistmethod.");
            breadCrumbsList.remove(0);
        }
        breadCrumbsList.add(breadCrumbsList.size(), latestBreadCrumb);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<Cell> getBody() {
        return body;
    }

    public void setBody(ArrayList<Cell> body) {
        this.body = body;
    }
    //called from the screen grid class if the cell run over by the head is food before length of the head is set.
    public void addBodyCellToList(Cell bodySegment)
    {
            //logic for adding the crumb to the last index(most recent)
            bodySegment.setX(breadCrumbsList.get(breadCrumbsList.size() - 1).getX());
            bodySegment.setY(breadCrumbsList.get(breadCrumbsList.size() - 1).getY());
            bodySegment.setType("body");
            body.add(bodySegment);
    }
    public void moveTailToBackOfHead()
    {
        for(int index = 0; index < body.size(); index++)
        {
            Cell cell = body.get(index);
            Coordinates myCoordinate = breadCrumbsList.get(index);
            cell.setX(myCoordinate.getX());
            cell.setY(myCoordinate.getY());
        }
    }
}
