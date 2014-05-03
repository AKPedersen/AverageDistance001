package com.averagedistance001.averagedistance001;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity {

    private long time1;
    private long time2;
    private tLinkedList tList;
    boolean listCreated = false;
    int fail = 0;
    int[] CoordinatesX;
    int[] CoordinatesY;
    int[] CoordinatesX2;
    int[] CoordinatesY2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time1 = System.currentTimeMillis();
        time2 = System.currentTimeMillis();
        tList = new tLinkedList();

        //Ensure that Coordinate arrays are initialized if user press button before inputting trajectory
        CoordinatesX = new int[]{0,0,0,0,0,0,0,0,0,0};
        CoordinatesY = new int[]{0,0,0,0,0,0,0,0,0,0};
        CoordinatesX2 = new int[]{0,0,0,0,0,0,0,0,0,0};
        CoordinatesY2 = new int[]{0,0,0,0,0,0,0,0,0,0};
        RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.RelativeLayout);
        myLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener(){
                    public boolean onTouch(View v, MotionEvent m){
                        handleTouch(m);
                        return true;
                    }
                }
        );
    }


    void handleTouch(MotionEvent m){
        TextView logView=(TextView)findViewById(R.id.textView2);
        TextView listView = (TextView)findViewById(R.id.textView5);
        TextView listYView = (TextView)findViewById(R.id.textView6);

        int pointerCount = m.getPointerCount();
        for(int i = 0; i < pointerCount; i++){
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            //int id = m.getPointerId(i);
            int action = m.getActionMasked();
            //int actionIndex =  m.getActionIndex();
            String actionString;
            switch(action){
                // First Finger down
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                // First Finger Up
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                // All following fingers down
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                // All following fingers up
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }

            if(System.currentTimeMillis()-time1 > 10){
                //Debug to screen
                long passedTime = time1 - time2;
                String touchStatus = "x " + x + " y " + y +" time " + passedTime + " " + actionString;
                logView.setText(touchStatus);
                time1 = System.currentTimeMillis();

                // Finger has gone down
                if(actionString == "DOWN"){
                    // The up message has failed so we need to handle it here
                    if(listCreated == true){
                        listCreated = false;
                        //Comment the below line in and the one two below to get time based trajectory instead.
                        //tListNode[] trajectory = tList.percentageTimeReturn().clone();
                        tListNode[] trajectory = tList.percentageReturn().clone();

                        // We need to ensure that there are a list, and that there are points enough
                        if(trajectory[0] != null && tList.getSize()>= 10){
                            int temp = trajectory[0].getxCoordinate();
                            int temp2 = trajectory[1].getxCoordinate();
                            int temp3 = trajectory[2].getxCoordinate();
                            int temp4 = trajectory[3].getxCoordinate();
                            int temp5 = trajectory[4].getxCoordinate();
                            int temp6 = trajectory[5].getxCoordinate();
                            int temp7 = trajectory[6].getxCoordinate();
                            int temp8 = trajectory[7].getxCoordinate();
                            int temp9 = trajectory[8].getxCoordinate();
                            int temp10 = trajectory[9].getxCoordinate();
                            int tempY = trajectory[0].getyCoordinate();
                            int tempY2 = trajectory[1].getyCoordinate();
                            int tempY3 = trajectory[2].getyCoordinate();
                            int tempY4 = trajectory[3].getyCoordinate();
                            int tempY5 = trajectory[4].getyCoordinate();
                            int tempY6 = trajectory[5].getyCoordinate();
                            int tempY7 = trajectory[6].getyCoordinate();
                            int tempY8 = trajectory[7].getyCoordinate();
                            int tempY9 = trajectory[8].getyCoordinate();
                            int tempY10 = trajectory[9].getyCoordinate();
                            listView.setText("X: " + String.valueOf(temp) + " " + String.valueOf(temp2) + " " + String.valueOf(temp3)+ " " + String.valueOf(temp4)+ " " + String.valueOf(temp5)+ " " + String.valueOf(temp6)+ " " + String.valueOf(temp7)+ " " + String.valueOf(temp8)+ " " + String.valueOf(temp9)+ " " + String.valueOf(temp10));
                            listYView.setText("Y: " + String.valueOf(tempY) + " " + String.valueOf(tempY2) + " " + String.valueOf(tempY3)+ " " + String.valueOf(tempY4)+ " " + String.valueOf(tempY5)+ " " + String.valueOf(tempY6)+ " " + String.valueOf(tempY7)+ " " + String.valueOf(tempY8)+ " " + String.valueOf(tempY9)+ " " + String.valueOf(tempY10));
                            CoordinatesX2 = CoordinatesX;
                            CoordinatesY2 = CoordinatesY;
                            CoordinatesX = new int[]{temp,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10};
                            CoordinatesY = new int[]{tempY,tempY2,tempY3,tempY4,tempY5,tempY6,tempY7,tempY8,tempY9,tempY10};
                        }
                        // There either no list or too few points
                        else{
                            listView.setText("Too few points sorry! " + fail);
                            listYView.setText("Too few points sorry!");
                            fail++;
                        }

                    }
                    //We need to make a new list for the trajectory
                    if(listCreated == false){
                        tList = new tLinkedList();
                        listCreated = true;
                        tList.add(x,y,passedTime);
                    }
                }

                // We need to feed data to the list, if there is a list
                if(actionString == "MOVE" && listCreated == true){
                    tList.add(x,y,passedTime);
                }
                // The finger has gone up, however we cannot rely on this function
                if(actionString =="UP" && listCreated == true){

                    listCreated = false;
                    tListNode[] trajectory = tList.percentageReturn().clone();
                    //tListNode[] trajectory = tList.percentageTimeReturn().clone();
                    if(trajectory[0] != null && tList.getSize()>= 10){
                        int temp = trajectory[0].getxCoordinate();
                        int temp2 = trajectory[1].getxCoordinate();
                        int temp3 = trajectory[2].getxCoordinate();
                        int temp4 = trajectory[3].getxCoordinate();
                        int temp5 = trajectory[4].getxCoordinate();
                        int temp6 = trajectory[5].getxCoordinate();
                        int temp7 = trajectory[6].getxCoordinate();
                        int temp8 = trajectory[7].getxCoordinate();
                        int temp9 = trajectory[8].getxCoordinate();
                        int temp10 = trajectory[9].getxCoordinate();
                        int tempY = trajectory[0].getyCoordinate();
                        int tempY2 = trajectory[1].getyCoordinate();
                        int tempY3 = trajectory[2].getyCoordinate();
                        int tempY4 = trajectory[3].getyCoordinate();
                        int tempY5 = trajectory[4].getyCoordinate();
                        int tempY6 = trajectory[5].getyCoordinate();
                        int tempY7 = trajectory[6].getyCoordinate();
                        int tempY8 = trajectory[7].getyCoordinate();
                        int tempY9 = trajectory[8].getyCoordinate();
                        int tempY10 = trajectory[9].getyCoordinate();
                        listView.setText("X: " +String.valueOf(temp) + " " + String.valueOf(temp2) + " " + String.valueOf(temp3)+ " " + String.valueOf(temp4)+ " " + String.valueOf(temp5)+ " " + String.valueOf(temp6)+ " " + String.valueOf(temp7)+ " " + String.valueOf(temp8)+ " " + String.valueOf(temp9)+ " " + String.valueOf(temp10));
                        listYView.setText("Y: " +String.valueOf(tempY) + " " + String.valueOf(tempY2) + " " + String.valueOf(tempY3)+ " " + String.valueOf(tempY4)+ " " + String.valueOf(tempY5)+ " " + String.valueOf(tempY6)+ " " + String.valueOf(tempY7)+ " " + String.valueOf(tempY8)+ " " + String.valueOf(tempY9)+ " " + String.valueOf(tempY10));
                        CoordinatesX2 = CoordinatesX;
                        CoordinatesY2 = CoordinatesY;
                        CoordinatesX = new int[]{temp,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10};
                        CoordinatesY = new int[]{tempY,tempY2,tempY3,tempY4,tempY5,tempY6,tempY7,tempY8,tempY9,tempY10};
                    }
                    else{
                        listView.setText("Too few points sorry! " + fail);
                        listYView.setText("Too few points sorry!");
                        fail++;
                    }
                }
            }
        }
    }
    // Calculate Euclidean difference using 10 points
    public void calcEuDis(View v){
        //Input properties
        int arrayAmount = 1;
        int arraySize = 10;
        //Fetch output text fields
        TextView debugView=(TextView)findViewById(R.id.textView);
        TextView disEuView=(TextView)findViewById(R.id.textView3);
        //An array to store the arrays, currently hardcoded
        int[][] multiArray = new int[arrayAmount][];
        int[][] multiArrayY = new int[arrayAmount][];
        //The array of the average trajectory for x coordinates (Hardcoded example)
        int[] xAVG = new int[]{104,154,173,193,230,267,300,332,360,376};
        int[] yAVG = new int[]{283,279,276,270,267,265,262,259,259,257};
        // X Coordinates
        multiArray[0] = CoordinatesX.clone();
        //multiArray[1] = CoordinatesX2.clone();
        // Y Coordinates
        multiArrayY[0] = CoordinatesY.clone();
        //multiArrayY[1] = CoordinatesY2.clone();


        int tempDisX = 0;
        int tempDisY = 0;
        //int[] EUDistance = new int[arrayAmount];
        //int[] EUDistanceY = new int[arrayAmount];
        int[] EUDistanceXY = new int[arrayAmount];
        int[] newAVG = new int[]{0,0,0,0,0,0,0,0,0,0};
        int[] newAVGY = new int[]{0,0,0,0,0,0,0,0,0,0};
        // Iterate through the array to find the euclidean distances and sum results for coordinate average

        for(int i = 0; i < arrayAmount; i++){
            for(int j = 0; j < arraySize; j++){
                tempDisX = multiArray[i][j] -xAVG[j];
                tempDisY = multiArrayY[i][j] -yAVG[j];
                newAVG[j] += tempDisX;
                newAVGY[j] += tempDisY;
                EUDistanceXY[i] += Math.abs(tempDisX) + Math.abs(tempDisY);
            }
        }

        for(int i = 0; i < arrayAmount; i++){
            newAVG[i] = newAVG[i] / arrayAmount;
            newAVGY[i] = newAVGY[i] / arrayAmount;
            // Remember to ADD these to the average coordinates when drawing
        }


        debugView.setText(newAVG[0]+","+newAVGY[0] + " " + newAVG[1]+","+newAVGY[1] + " " + newAVG[2]+","+newAVGY[2] + " " + newAVG[3]+","+newAVGY[3]
            +" "+ newAVG[4]+","+newAVGY[4]+" "+ newAVG[5]+","+newAVGY[5]+" "+ newAVG[6]+","+newAVGY[6]+ " "+ newAVG[7]+","+newAVGY[7]+ " "+ newAVG[8]+","+newAVGY[8]+ " " + newAVG[9]+","+newAVGY[9]);
        disEuView.setText("Euclidean Distance:  "+ EUDistanceXY[0] );
    }

    public void CalcDTWDis(View v){
        // Hardcoded Comparison Trajectory
        int[] SequenceB = new int[]{104,154,173,193,230,267,300,332,360,376};
        int[] SequenceBy = new int[]{283,279,276,270,267,265,262,259,259,257};
        CalcDTW dtw = new CalcDTW(CoordinatesX, CoordinatesY, SequenceB, SequenceBy);
        TextView dtwView = (TextView)findViewById(R.id.textView4);
        dtwView.setText("DTW distance: " + dtw.getFinalDTWDistance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
