package com.example.nilehenry.eggtimer;


import android.util.Log;


public class Clocker {
    private int hours;
    private int minutes;
    private int seconds;

    public void setTime(int hours,int minutes, int seconds){
        if (seconds>59){
            minutes=minutes+seconds/60;
            seconds=seconds%60;
        }
        this.seconds=seconds;

        if (minutes>59){
            hours=hours+minutes/60;
            minutes=minutes%60;
        }
        this.minutes=minutes;
        if (hours>100){
            throw new StringIndexOutOfBoundsException("Hours cant be >=100"); //check over later
        }
        this.hours=hours;
    }
    public Clocker(int hours, int minutes, int seconds){
        setTime(hours,minutes,seconds);
    }
    public Clocker(int minutes, int seconds){
        setTime(0,minutes,seconds);
    }
    public Clocker(int seconds){
        setTime(0,0,seconds);
    }
    public int getTotalSeconds(){ //returns total seconds = 3600*hours + 60*minutes + seconds
        return 3600*hours+60*minutes+seconds;
    }
    public int getSeconds(){
        return seconds;
    }
    public int getMinutes(){
        return minutes;
    }
    public int getHours(){
        return hours;
    }
    public boolean hasNext(){
        if (getTotalSeconds()==0){
            return false;
        }
        return true;
    }
    public boolean tickDown(){
        if (hasNext()){
            int totalSeconds=getTotalSeconds()-1;
            setTime(0,0,totalSeconds);
            return true;
        }
        return false;
    }
    public String toString(){
        String hrs= Integer.toString(hours);
        String mins;
        String scnds;


        if (hours<10){
            hrs="0"+Integer.toString(hours);
        }
        else{
            hrs=Integer.toString(hours);
        }
        if (minutes<10){
            mins= "0"+Integer.toString(minutes);
        }
        else{
            mins= Integer.toString(minutes);
        }

        if (seconds<10){
            scnds= "0"+Integer.toString(seconds);
        }
        else{
            scnds= Integer.toString(seconds);
        }

        String time= hrs + ":" + mins + ":" + scnds;
        return time;
    }

}
