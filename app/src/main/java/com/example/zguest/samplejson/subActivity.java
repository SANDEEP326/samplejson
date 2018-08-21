package com.example.zguest.samplejson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class subActivity extends AppCompatActivity {

    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Log.e("to subActivity","it arrived");
        Intent intent=getIntent();
        String text=intent.getStringExtra("JSON");
        textview=(TextView)findViewById(R.id.textView);
        textview.setText(text);
    }
}
