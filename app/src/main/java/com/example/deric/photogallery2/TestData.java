package com.example.deric.photogallery2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Deric on 17/11/02.
 */

public class TestData {

    Date[] Modified;
    String[] Locations;
    String[] Captions;
    Integer[] mThumbIds;

    public TestData(){
        setTestImages();
        setTestDates();
        setTestLocations();
        setTestCaptions();
    }


    private void setTestImages() {
        mThumbIds = new Integer[]{
                R.raw.amazed, R.raw.angelic, R.raw.cool, R.raw.crying, R.raw.devil,
                R.raw.laughing, R.raw.loving, R.raw.question, R.raw.sad, R.raw.silence,
                R.raw.simple, R.raw.sleeping, R.raw.smiling, R.raw.tongue, R.raw.winking,
                R.raw.worried};
    }


    private void setTestDates() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH");
        Modified = new Date[mThumbIds.length];
        try{
            Date d2013 = sdf.parse("2013-05-26:16");
            Date d2014 = sdf.parse("2014-05-26:16");
            Date d2015 = sdf.parse("2015-05-26:16");
            Date d2016 = sdf.parse("2016-05-26:16");
            Date d2017 = sdf.parse("2017-05-26:16");

            for(int i=0; i < mThumbIds.length; i++){
                switch(i % 5){
                    case 0:
                        Modified[i] = d2013;
                        break;
                    case 1:
                        Modified[i] = d2014;
                        break;
                    case 2:
                        Modified[i] = d2015;
                        break;
                    case 3:
                        Modified[i] = d2016;
                        break;
                    case 4:
                        Modified[i] = d2017;
                        break;
                    default:
                        break;

                }
            }
        }
        catch (ParseException ex) {
            Logger.getLogger(GalleryActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTestLocations() {
        Locations = new String[mThumbIds.length];
        for(int i=0; i < mThumbIds.length; i++){
            switch(i % 8){
                case 0:
                    Locations[i] = "Vancouver";
                    break;
                case 1:
                    Locations[i] = "Seattle";
                    break;
                case 2:
                    Locations[i] = "Calgary";
                    break;
                case 3:
                    Locations[i] = "Kamloops";
                    break;
                case 4:
                    Locations[i] = "Kelowna";
                    break;
                case 5:
                    Locations[i] = "Hope";
                    break;
                case 6:
                    Locations[i] = "Burnaby";
                    break;
                case 7:
                    Locations[i] = "Surrey";
                    break;
                default:
                    break;

            }
        }
    }

    private void setTestCaptions() {
        Captions = new String[mThumbIds.length];
        for(int i=0; i < mThumbIds.length; i++){
            switch(i % 8){
                case 0:
                    Captions[i] = "I had this for lunch";
                    break;
                case 1:
                    Captions[i] = "At the hockey game";
                    break;
                case 2:
                    Captions[i] = "Party time";
                    break;
                case 3:
                    Captions[i] = "Driving to work";
                    break;
                case 4:
                    Captions[i] = "Drinks friday night";
                    break;
                case 5:
                    Captions[i] = "Running in the park";
                    break;
                case 6:
                    Captions[i] = "Playing with the dogs";
                    break;
                case 7:
                    Captions[i] = "Going for a swim";
                    break;
                default:
                    break;

            }
        }
    }
}
