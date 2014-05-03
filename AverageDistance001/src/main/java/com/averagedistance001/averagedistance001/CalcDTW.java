package com.averagedistance001.averagedistance001;

/**
 * Created by Andreas on 4/22/14.
 */
public class CalcDTW {

    // Should later be changed to accept two arrays instead
    private int[] SequenceA;
    private int[] SequenceB;
    // YCoordinates
    private int[] SequenceAy;
    private int[] SequenceBy;

    private int sequenceALength;
    private int sequenceBLength;
    private int finalDTWDistance;

    // Constructor
    // Handle Input both Input sequences, fill Distance matrix and call distance calculator
    public CalcDTW(int[] refX, int[]refY, int[]seqX, int[] seqY){

        SequenceA = refX;
        SequenceAy = refY;
        SequenceB = seqX;
        SequenceBy = seqY;

        // Length of input
        sequenceALength = SequenceA.length;
        sequenceBLength = SequenceB.length;

        //Instantiate an array to hold all possible distances
        int[][]  d = new int[sequenceALength][sequenceBLength];

        //Calculate all the distances
        for(int i = 0; i < sequenceALength; i++){
            for(int j = 0; j < sequenceBLength; j++){
              //d[i][j] = distanceBetweenPoints(SequenceA[i], SequenceB[j]);
                d[i][j] = distanceBetweenPoints2D(SequenceA[i], SequenceAy[i], SequenceB[j], SequenceBy[j]);
            }
        }
        finalDTWDistance = distance(d,sequenceALength-1,sequenceBLength-1);
    }

    public int getFinalDTWDistance() {
        return finalDTWDistance;
    }

    // Calculate Dynamic Time Warping Distance
    private int distance(int[][] disArray, int i, int j){
        int currentDistance;
        //Both i and j is zero, so we have reached the end
        if(i+j == 0){
            currentDistance = disArray[0][0];
        }
        // j has reached zero so we continue along i
        else if(j == 0){
            currentDistance = disArray[i][j] + distance(disArray,i-1,j);
        }
        // i has reached zero so we continue along j
        else if(i == 0){
            currentDistance = disArray[i][j] + distance(disArray, i, j-1);
        }
        // None has reached zero so we calculate normally
        else{
            currentDistance = disArray[i][j] + minVal(distance(disArray, i - 1, j), distance(disArray, i - 1, j - 1), distance(disArray, i, j - 1));
        }
        return currentDistance;
    }

    //Function to calculate the minimum value
    private int minVal(int a, int b, int c){
        int temp;
        if(a <= b && a <= c){
            temp = a;
        }
        else if (b <= a && b <= c){
            temp = b;
        }
        else{
            temp = c;
        }
        return temp;
    }
    //For 1D Distance Calculation
    private int distanceBetweenPoints(int first, int second){
        int temp = (first - second) * (first - second);
        return temp;
    }
    //For 2D Distance Calculation
    private int distanceBetweenPoints2D(int firstX,int firstY, int secondX, int secondY){
        int tempX = (firstX-secondX) * (firstX - secondX);
        int tempY = (firstY-secondY) * (firstY - secondY);
        double result = Math.sqrt((double)tempX+(double)tempY);
        return (int)result;
    }
}
