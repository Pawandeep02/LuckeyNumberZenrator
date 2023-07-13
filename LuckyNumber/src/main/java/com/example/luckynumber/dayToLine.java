package com.example.luckynumber;

public class dayToLine {
    public static int dayToLine(String day)
    {
        int num=0;
        if(day.contentEquals("SUNDAY")){num=1;};
        if(day.contentEquals("MONDAY")){num=2;};
        if(day.contentEquals("TUESDAY")){num=3;};
        if(day.contentEquals("WEDNESDAY")){num=4;};
        if(day.contentEquals("THURSDAY")){num=5;};
        if(day.contentEquals("FRIDAY")){num=6;};
        if(day.contentEquals("SATURDAY")){num=7;};
        return num;
    }
}
