package com.example.deric.photogallery2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class GalleryActivity extends AppCompatActivity {

    static final int REQUEST_SETTINGS_CHANGE = 2;

    private ImageList imagelist;

    MySQLiteHelper db;


    GridView gridview;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        db = new MySQLiteHelper(this);

        imagelist = new ImageList();
        imagelist.filterImages();

        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new ImageAdapter(this);

        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GalleryActivity.this, PreviewActivity.class);
                intent.putExtra("imageId", imagelist.mThumbIds[position]);
                startActivity(intent);
            }
        });

    }


    //    our custom adapter
    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        private static final String TAG = "ImageAdapter";

        public ImageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return imagelist.mFilteredThumbIds.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ImageView imageView;
//            check to see if we have a view
            if (convertView == null) {
//                no view - so create a new one
                imageView = new ImageView(mContext);
            } else {
//                use the recycled view object
                imageView = (ImageView) convertView;
            }

            Picasso.with(GalleryActivity.this)
                    .load(imagelist.mFilteredThumbIds.get(position))
                    .placeholder(R.raw.place_holder)
                    .error(R.raw.big_problem)
                    .noFade().resize(150, 150)
                    .centerCrop()
                    .into(imageView);

            return imageView;
        }
    }





    public void cameraClickHandeler(View target){

        //dispatchTakePictureIntent();
    }

    public void filterClickHandeler(View target){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, REQUEST_SETTINGS_CHANGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SETTINGS_CHANGE && resultCode == RESULT_OK) {

            Calendar calendar = Calendar.getInstance();

            int FromYear = data.getIntExtra("FromYear", 1950);
            int FromMonth = data.getIntExtra("FromMonth", 1);
            int FromDay = data.getIntExtra("FromDay", 1);

            int ToDateYear = data.getIntExtra("ToDateYear", calendar.get(Calendar.YEAR));
            int ToDateMonth = data.getIntExtra("ToDateMonth", calendar.get(Calendar.MONTH));
            int ToDateDay = data.getIntExtra("ToDateDay", calendar.get(Calendar.DAY_OF_MONTH));

            String Location = data.getStringExtra("Location");
            String Caption = data.getStringExtra("Caption");

            imagelist.filter.reset();
            imagelist.filter.setFromDate(FromYear, FromMonth, FromDay);
            imagelist.filter.setToDate(ToDateYear, ToDateMonth, ToDateDay);
            imagelist.filter.Location = Location;
            imagelist.filter.Caption = Caption;

            imagelist.filterImages();
            gridview.invalidateViews();
            adapter = new ImageAdapter(this);

            gridview.setAdapter(adapter);
        }
    }
}


