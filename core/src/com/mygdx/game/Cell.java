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
    public void addCoordinateToList(Coordinates latestBreadCrumb)
    {
        //logic for adding the crumb to the last index(most recent)
        breadCrumbsList.add(length, latestBreadCrumb);
        //breadCrumbsList.remove(breadCrumbsList.indexOf(latestBreadCrumb) - length);
        if (length > 1)//may need to change to include length =1 as well so there is not a leaked segment???
        {
            breadCrumbsList.remove(length - (length - 1));
        }
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
    public void addBodyCellToList(Cell bodySegment)
    {
        //logic for adding the crumb to the last index(most recent)
        bodySegment.setX(breadCrumbsList.get(length).getX());
        System.out.println("");
        bodySegment.setY(breadCrumbsList.get(length).getY());
        bodySegment.setType("body");
        body.add(length, bodySegment);//changed from length to length -1
        //breadCrumbsList.remove(breadCrumbsList.indexOf(latestBreadCrumb) - length);
        //body.remove(length - length);
//        if (length > 1)//may need to change to include length =1 as well so there is not a leaked segment???
//        {
//            body.remove(length - (length - 1));
//        }
//        else
//        {
//            body.remove(length - length);
//        }
    }
    public void moveTailToBackOfHead()
    {
        Cell tail = body.get(length - length);
        tail.setX(breadCrumbsList.get(length).getX());
        tail.setY(breadCrumbsList.get(length).getY());
    }
}
