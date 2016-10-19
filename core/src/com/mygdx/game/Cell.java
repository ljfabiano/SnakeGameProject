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
        if(!breadCrumbsList.isEmpty() && (breadCrumbsList.size() - 1 == body.size()))
        {
            System.out.println("in the if statement in the addcoordinatetolistmethod.");
            breadCrumbsList.remove(0);
        }
        breadCrumbsList.add(breadCrumbsList.size(), latestBreadCrumb);

        //breadCrumbsList.remove(breadCrumbsList.indexOf(latestBreadCrumb) - length);
//        if (length > 1)//may need to change to include length =1 as well so there is not a leaked segment???
//        {
//            breadCrumbsList.remove(length - (length - 1));
//        }
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

//        if(breadCrumbsList.size() == 0)
//        {
//            bodySegment.setX(x);
//            //System.out.println("");
//            bodySegment.setY(y);
//            bodySegment.setType("body");
//            body.add(length, bodySegment);//changed from length to length -1
//        }
//        else {
            //logic for adding the crumb to the last index(most recent)
            bodySegment.setX(breadCrumbsList.get(breadCrumbsList.size() - 1).getX());
            //System.out.println("");
            bodySegment.setY(breadCrumbsList.get(breadCrumbsList.size() - 1).getY());

            bodySegment.setType("body");
            body.add(bodySegment);//changed from length to length -1

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
        //}

    }
    public void moveTailToBackOfHead()
    {
//        if(length > 0)
//        {
//            int lastIndex = length-1;
//            Cell tail = body.get(lastIndex);//was 0
////            tail.setX(body.get(length - 1).getX());
////            tail.setY(body.get(length - 1).getY());
//            tail.setX(breadCrumbsList.get(lastIndex).getX());
//            tail.setY(breadCrumbsList.get(lastIndex).getY());
//            body.add(lastIndex, tail);
//
//        }
        //int lastIndex = length-1;
        for(int index = 0; index < body.size(); index++)
        {
            Cell cell = body.get(index);
            Coordinates myCoordinate = breadCrumbsList.get(index);
            cell.setX(myCoordinate.getX());
            cell.setY(myCoordinate.getY());


        }
    }
}
