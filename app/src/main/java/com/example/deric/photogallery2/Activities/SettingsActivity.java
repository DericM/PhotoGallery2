package com.example.deric.photogallery2.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.deric.photogallery2.Utilities.MyEditTextDatePicker;
import com.example.deric.photogallery2.R;

public class SettingsActivity extends AppCompatActivity {


    MyEditTextDatePicker fromDate;
    MyEditTextDatePicker toDate;

    EditText location;
    EditText caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fromDate = new MyEditTextDatePicker(this, R.id.FromDate);
        toDate = new MyEditTextDatePicker(this, R.id.ToDate);

        location = (EditText) findViewById(R.id.Location);
        caption = (EditText) findViewById(R.id.Caption);
    }

    public void returnResult(View target){
        Intent _result = new Intent();

        if(!fromDate.isEmpty()){
            _result.putExtra("FromYear", fromDate.getYear());
            _result.putExtra("FromMonth", fromDate.getMonth());
            _result.putExtra("FromDay", fromDate.getDay());
        }

        if(!toDate.isEmpty()){
            _result.putExtra("ToDateYear", toDate.getYear());
            _result.putExtra("ToDateMonth", toDate.getMonth());
            _result.putExtra("ToDateDay", toDate.getDay());
        }

        if(!location.getText().toString().isEmpty()){
            _result.putExtra("Location", location.getText().toString() );
        }

        if(!caption.getText().toString().isEmpty()){
            _result.putExtra("Caption", caption.getText().toString() );
        }


        setResult(Activity.RESULT_OK, _result);
        finish();
    }



}
