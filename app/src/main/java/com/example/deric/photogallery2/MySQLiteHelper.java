package com.example.deric.photogallery2;

/**
 * Created by Deric on 17/11/23.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Pictures.db";
    public static final String TABLE_NAME = "pictures";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FILENAME = "filename";
    public static final String COLUMN_CAPTION = "caption";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_MODIFIED = "modified";

    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_FILENAME, COLUMN_CAPTION,
            COLUMN_LOCATION, COLUMN_IMAGE, COLUMN_MODIFIED};
    private HashMap hp;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FILENAME + " TEXT, " +
                COLUMN_CAPTION + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_MODIFIED + " TEXT, " +
                COLUMN_IMAGE + " BLOB )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
    }

    public void addImage(Image image){
        //for logging
        Log.d("addImage", image.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILENAME, image.getFilename());
        values.put(COLUMN_CAPTION, image.getCaption());
        values.put(COLUMN_LOCATION, image.getLocation());
        values.put(COLUMN_MODIFIED, image.getModified());
        values.put(COLUMN_IMAGE, image.getImage());


        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }


    public Image getImage(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Image image = new Image();
        image.setId(Integer.parseInt(cursor.getString(0)));
        image.setFilename(cursor.getString(1));
        image.setCaption(cursor.getString(2));
        image.setLocation(cursor.getString(3));
        image.setModified(Long.parseLong(cursor.getString(4)));
        image.setImage(cursor.getString(5).getBytes());

        //log
        Log.d("getImage("+id+")", image.toString());

        // 5. return book
        return image;
    }

    public List<Image> getAllBooks() {
        List<Image> images = new LinkedList<Image>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Image image = null;
        if (cursor.moveToFirst()) {
            do {
                image = new Image();
                image.setId(Integer.parseInt(cursor.getString(0)));
                image.setFilename(cursor.getString(1));
                image.setCaption(cursor.getString(2));
                image.setLocation(cursor.getString(3));
                image.setModified(Long.parseLong(cursor.getString(4)));
                image.setImage(cursor.getString(5).getBytes());

                // Add book to books
                images.add(image);
            } while (cursor.moveToNext());
        }

        Log.d("getAllImages()", images.toString());

        // return books
        return images;
    }

    public int updateImage(Image image) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILENAME, image.getFilename());
        values.put(COLUMN_CAPTION, image.getCaption());
        values.put(COLUMN_LOCATION, image.getLocation());
        values.put(COLUMN_MODIFIED, image.getModified());
        values.put(COLUMN_IMAGE, image.getImage());

        // 3. updating row
        int i = db.update(TABLE_NAME, //table
                values, // column/value
                COLUMN_ID + " = ?", // selections
                new String[] { String.valueOf(image.getId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    public void deleteBook(Image image) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_NAME, //table name
                COLUMN_ID + " = ?",  // selections
                new String[] { String.valueOf(image.getId()) }); //selections args

        // 3. close
        db.close();

        //log
        Log.d("deleteImage", image.toString());

    }

}