package com.example.android.popularmovies_stage1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        TextView textView = findViewById(R.id.text);
        textView.setText("Eureka!");
    }
}
