package com.averagedistance001.averagedistance001;

/**
 * Created by Andreas on 21-04-14.
 */
//Create the nodes for the list, needs to store, x,y,time & nextNode
public class tListNode{

    private int xCoordinate;
    private int yCoordinate;
    private long time;
    private tListNode next;
    // Create a new empty node
    public tListNode(){
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.time = 0;
        this.next  = null;
    }
    // Create a new node with information
    public tListNode(int inputX, int inputY, long inputTime){
        xCoordinate = inputX;
        yCoordinate = inputY;
        time = inputTime;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() { return yCoordinate; }

    public long getTime() {
        return time;
    }

    public tListNode getNext() {
        return next;
    }

    public void setNext(tListNode next) {
        this.next = next;
    }
}
