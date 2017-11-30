package com.example.deric.photogallery2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.deric.photogallery2.Activities.GalleryActivity;
import com.example.deric.photogallery2.Database.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestData {


    public static List<Image> generateTestImages(Context context){
        List<Image> images = new ArrayList<>();

        List<Bitmap> Bitmaps = setTestImages(context);
        List<String> Captions = setTestCaptions(Bitmaps.size());
        List<String> Locations = setTestLocations(Bitmaps.size());
        List<Calendar> Modified = setTestDates(Bitmaps.size());
        List<String> Filename = setTestFilenames(Modified);

        for(int i=0; i<Bitmaps.size(); i++){
            Image image = new Image(Filename.get(i), Captions.get(i), Locations.get(i),
                    Bitmaps.get(i), Modified.get(i));
            images.add(image);
        }

        return images;
    }

    private static List<String> setTestFilenames(List<Calendar> modified){
        List<String> Filenames = new ArrayList<>();

        for(int i=0; i<modified.size(); i++){
            Filenames.add("" + i + modified.get(i).toString());
        }

        return Filenames;
    }


    private static List<Bitmap> setTestImages(Context context) {
        List<Bitmap> bitmaps = new ArrayList<>();

        Integer[] mThumbIds = new Integer[]{
                R.drawable.amazed, R.drawable.angelic, R.drawable.cool, R.drawable.crying, R.drawable.devil,
                //R.drawable.laughing, R.drawable.loving, R.drawable.question, R.drawable.sad, R.drawable.silence,
                //R.drawable.simple, R.drawable.sleeping, R.drawable.smiling, R.drawable.tongue, R.drawable.winking,
                R.drawable.worried};

        for(Integer item : mThumbIds){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),item);
            bitmaps.add(bitmap);
            //bitmap.recycle();
        }

        return bitmaps;
    }


    private static List<Calendar> setTestDates(int size) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH");
        List<Calendar> Modified = new ArrayList<>();
        try{
            Date d2013 = sdf.parse("2013-05-26:16");
            Date d2014 = sdf.parse("2014-05-26:16");
            Date d2015 = sdf.parse("2015-05-26:16");
            Date d2016 = sdf.parse("2016-05-26:16");
            Date d2017 = sdf.parse("2017-05-26:16");

            for(int i=0; i < size; i++){
                Calendar calendar = Calendar.getInstance();
                switch(i % 5){
                    case 0:
                        calendar.setTime(d2013);
                        Modified.add(calendar);
                        break;
                    case 1:
                        calendar.setTime(d2014);
                        Modified.add(calendar);
                        break;
                    case 2:
                        calendar.setTime(d2015);
                        Modified.add(calendar);
                        break;
                    case 3:
                        calendar.setTime(d2016);
                        Modified.add(calendar);
                        break;
                    case 4:
                        calendar.setTime(d2017);
                        Modified.add(calendar);
                        break;
                    default:
                        break;
                }
            }
        }
        catch (ParseException ex) {
            Logger.getLogger(GalleryActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Modified;
    }

    private static List<String> setTestLocations(int size) {
        List<String> Locations = new ArrayList<>();
        for(int i=0; i < size; i++){
            switch(i % 8){
                case 0:
                    Locations.add("Vancouver");
                    break;
                case 1:
                    Locations.add("Seattle");
                    break;
                case 2:
                    Locations.add("Calgary");
                    break;
                case 3:
                    Locations.add("Kamloops");
                    break;
                case 4:
                    Locations.add("Kelowna");
                    break;
                case 5:
                    Locations.add("Hope");
                    break;
                case 6:
                    Locations.add("Burnaby");
                    break;
                case 7:
                    Locations.add("Surrey");
                    break;
                default:
                    break;
            }
        }
        return Locations;
    }

    private static List<String> setTestCaptions(int size) {
        List<String> Captions = new ArrayList<>();
        for(int i=0; i < size; i++){
            switch(i % 8){
                case 0:
                    Captions.add("I had this for lunch");
                    break;
                case 1:
                    Captions.add("At the hockey game");
                    break;
                case 2:
                    Captions.add("Party time");
                    break;
                case 3:
                    Captions.add("Driving to work");
                    break;
                case 4:
                    Captions.add("Drinks friday night");
                    break;
                case 5:
                    Captions.add("Running in the park");
                    break;
                case 6:
                    Captions.add("Playing with the dogs");
                    break;
                case 7:
                    Captions.add("Going for a swim");
                    break;
                default:
                    break;
            }
        }
        return Captions;
    }
}
