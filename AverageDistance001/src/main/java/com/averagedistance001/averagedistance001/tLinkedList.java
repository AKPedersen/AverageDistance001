package com.averagedistance001.averagedistance001;

/**
 * Created by Andreas on 21-04-14.
 */
public class tLinkedList {

    private tListNode firstNode;
    private tListNode lastNode;
    private int size = 0;

    // Constructor
    // Instantiate the list
    public tLinkedList(){}
    // Add a node holding coordinates and time to the list
    public void add(int x, int y, long time){
        // If the list is empty
        if(firstNode == null){
            firstNode = new tListNode(x,y,time);
            firstNode.setNext(lastNode);
            lastNode = firstNode;
        }
        else{
            lastNode.setNext(new tListNode(x,y,time));
            lastNode = lastNode.getNext();
        }
        // Keep track of list size
        size++;
    }
    // Return list size
    public int getSize() {
        return size;
    }

    // Get Nodes based on list position
    public tListNode get(int index){
        if(index < 0 && index <= getSize()){
            return null;
        }
        tListNode temp = firstNode;

        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        return temp;
    }
    // Get Nodes based on time
    public tListNode findTime(long time){
        if(time < 0){
            return null;
        }
        tListNode temp = firstNode;
        for(int i = 0; i < getSize(); i++){
            if(temp.getTime() >= time){
                return temp;
            }
            else{
                temp = temp.getNext();
            }
        }
        return null;
    }
    // Create an return array based on time
    public tListNode[] percentageTimeReturn(){
        long endTime = lastNode.getTime()-firstNode.getTime();
        long percentageTime = endTime/10;
        tListNode[] timeArray = new tListNode[10];
        for(int i = 1; i <= 10;i++){
            timeArray[i-1] = findTime((i*percentageTime)+firstNode.getTime());
        }
        return timeArray;
    }

    // Create an return array based on node position
    public tListNode[] percentageReturn(){
        int arraySize = 10;
        if(getSize() < 10){
            arraySize = getSize();
        }
        double number = (double)arraySize / 10.0;
        int percentage = (int)Math.floor(number);
        tListNode[] returnList = new tListNode[arraySize];
        for(int i = 1; i <= arraySize; i++){
            returnList[i-1] = get(i*percentage);
        }
        return returnList;
    }
}
