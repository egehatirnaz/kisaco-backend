package com.kisaco.application.analytics;

import java.util.ArrayList;

public class MonthlyAnalytic {
    private int[] months;

    public MonthlyAnalytic(){
        months = new int[12];
    }

    public void setMonth(int month, int visitor_count){
        months[month] = visitor_count;
    }

    public int[] getMonths() {
        return months;
    }

    public void setMonths(int[] months) {
        this.months = months;
    }

    public int getMonth(int month){
        return months[month];
    }

    public void addMonth(int month){
        months[month]++;
    }

}
