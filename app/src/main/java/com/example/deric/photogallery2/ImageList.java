package com.example.deric.photogallery2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deric on 17/11/02.
 */

public class ImageList {

    ImageFilter filter;


    ArrayList<Integer> mFilteredThumbIds;

    Integer[] mThumbIds;
    Date[] Modified;
    String[] Locations;
    String[] Captions;


    public ImageList(){
        mFilteredThumbIds = new ArrayList<Integer>();
        filter = new ImageFilter();
        TestData td = new TestData();
        mThumbIds = td.mThumbIds;
        Modified = td.Modified;
        Locations = td.Locations;
        Captions = td.Captions;
    }

    public void filterImages(){
        mFilteredThumbIds.clear();
        for(int i=0; i < mThumbIds.length; i++){

            if(!filterFromDate(i))
                continue;
            if(!filterToDate(i))
                continue;
            if(!filterLocation(i))
                continue;
            if(!filterCaption(i))
                continue;
            mFilteredThumbIds.add(mThumbIds[i]);
        }
    }

    private boolean filterFromDate(int index){
        Calendar pictureDate = Calendar.getInstance();
        pictureDate.setTime(Modified[index]);

        if(filter.fromDate == null){
            return true;
        }
        if(filter.fromDate.compareTo(pictureDate) < 0) {
            return true;
        }
        return false;
    }

    private boolean filterToDate(int index){
        Calendar pictureDate = Calendar.getInstance();
        pictureDate.setTime(Modified[index]);

        if(filter.toDate == null){
            return true;
        }
        if(filter.toDate.compareTo(pictureDate) > 0) {
            return true;
        }
        return false;
    }

    private boolean filterLocation(int index){
        if(filter.Location == null){
            return true;
        }
        if(Locations[index].toLowerCase().contains(filter.Location)){
            return true;
        }
        return false;
    }

    private boolean filterCaption(int index){
        if(filter.Caption == null){
            return true;
        }
        if(Captions[index].toLowerCase().contains(filter.Caption)){
            return true;
        }
        return false;
    }

}
