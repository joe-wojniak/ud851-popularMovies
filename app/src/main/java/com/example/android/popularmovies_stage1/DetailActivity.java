package com.example.android.popularmovies_stage1;

/*Example code adapted from:
        ud851-Exercises\Lesson03-Green-Recycler-View\T03.04-Exercise-WiringUpRecyclerView
        https://www.codingdemos.com/android-gridlayout-example-recyclerview/*/

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mMovie = findViewById(R.id.detailImageView);
        TextView mTitle = findViewById(R.id.detailTitle);
        TextView mReleaseDate = findViewById(R.id.detailReleaseDate);
        TextView mVoteAverage = findViewById(R.id.detailVoteAverage);
        TextView mOverview = findViewById(R.id.detailOverview);

        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null){
            //mMovie.setImageResource(mBundle.getInt("movie"));
            //mTitle.setText(mBundle.getString("movieTitle"));
            //mReleaseDate.setText(mBundle.getInt("movie"));
            //mVoteAverage.setText(mBundle.getInt("movie"));
            //mOverview.setText(mBundle.getInt("movie"));
        }
    }
}
