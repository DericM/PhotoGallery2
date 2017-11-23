package com.example.deric.photogallery2;

import java.util.Calendar;

/**
 * Created by Deric on 17/11/02.
 */

public class ImageFilter {
    public Calendar fromDate;
    public Calendar toDate;
    public String Location;
    public String Caption;


    public void reset(){
        fromDate = null;
        toDate = null;
        Location = null;
        Caption = null;
    }


    public void setFromDate(int FromYear, int FromMonth, int FromDay){
        fromDate = Calendar.getInstance();
        fromDate.set(FromYear, FromMonth, FromDay);
    }

    public void setToDate(int ToDateYear, int ToDateMonth, int ToDateDay){
        toDate = Calendar.getInstance();
        toDate.set(ToDateYear, ToDateMonth, ToDateDay);
    }
}
