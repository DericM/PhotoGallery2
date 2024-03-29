package com.example.deric.photogallery2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.deric.photogallery2.R;
import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        int imageId = getIntent().getIntExtra("imageId", -1);
        if (imageId != -1) {
            Picasso.with(PreviewActivity.this)
                    .load(imageId)
                    .placeholder(R.drawable.place_holder)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .error(R.drawable.big_problem)
                    .into(imageView);
        } else {
            Picasso.with(PreviewActivity.this)
                    .load(R.drawable.big_problem)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
