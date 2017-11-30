package com.example.deric.photogallery2.Database;

import android.graphics.Bitmap;

import java.util.Calendar;

/**
 * Created by Deric on 17/11/23.
 */

public class Image {

    private int id;
    private String filename;
    private String caption;
    private String location;

    private Bitmap image;
    private Calendar modified;


    public Image(){}

    public Image(String filename, String caption, String location, Bitmap image, Calendar modified) {
        super();
        this.filename = filename;
        this.caption = caption;
        this.location = location;
        this.image = image;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getImage() {
        return BitmapUtility.getBytes(image);
    }

    public void setImage(byte[] image) {
        this.image = BitmapUtility.getImage(image);
    }

    public long getModified() {
        return modified.getTimeInMillis();
    }

    public void setModified(long modified) {
        this.modified = Calendar.getInstance();
        this.modified.setTimeInMillis(modified);
    }

    @Override
    public String toString() {
        return "Image [id=" + id + ", filename=" + filename + ", caption=" + caption
                + ", location=" + location + ", modified=" + modified.toString()
                + "]";
    }
}
