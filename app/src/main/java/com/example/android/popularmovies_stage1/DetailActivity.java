package com.example.android.popularmovies_stage1;

import android.os.Bundle;
import android.widget.ImageView;

public class DetailActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mMovie = findViewById(R.id.detailImageView);

        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null){
            mMovie.setImageResource(mBundle.getInt("image"));
        }
    }
}
