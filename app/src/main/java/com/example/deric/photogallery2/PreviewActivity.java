package com.example.deric.photogallery2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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
                    .placeholder(R.raw.place_holder)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .error(R.raw.big_problem)
                    .into(imageView);
        } else {
            Picasso.with(PreviewActivity.this)
                    .load(R.raw.big_problem)
                    .noFade()
                    .resize(800, 800)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
