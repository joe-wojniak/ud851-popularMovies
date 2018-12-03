package com.example.android.popularmovies_stage1;
/*
Popular Movies App Stage 1 (ud851 - AND)

Internet permissions and network state permissions are required in AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

Project rubric:
https://review.udacity.com/#!/rubrics/66/view

Implementation Guide:
https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true#h.cntdg23jy69n

Example code adapted from:
ud851-Exercises\Lesson03-Green-Recycler-View\T03.04-Exercise-WiringUpRecyclerView
https://www.codingdemos.com/android-gridlayout-example-recyclerview/
Sections of code functionality modified from NewsApp Stage 2 (ABND Project 6) implementing
background thread call to TMDb api.
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies_stage1.model.Movie;
import com.example.android.popularmovies_stage1.utils.JsonUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mMoviePostersRecyclerView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePostersRecyclerView = findViewById(R.id.rvPosters);
        mMoviePostersRecyclerView.setHasFixedSize(false);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
        mMoviePostersRecyclerView.setLayoutManager(mGridLayoutManager);

        String json = getResources().getString(R.string.json);
        List<Movie> movieList = JsonUtils.extractFeatureFromJson(json);

        MovieRecyclerViewAdapter mAdapter = new MovieRecyclerViewAdapter(this, movieList);
        mMoviePostersRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mostPopular) {
            Toast.makeText(this, "Most Popular", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.topRated) {
            Toast.makeText(this, "Top Rated", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*TODO: implement http request from TMDb
    In order to request popular movies you will want to request data from the /movie/popular and /movie/top_rated endpoints
    https://www.google.com/url?q=https://developers.themoviedb.org/3/discover/movie-discover&sa=D&ust=1541914498758000

    String apiKey = BuildConfig.ApiKey;

    Example request: http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
     */

    /*COMPLETE: resolve movie poster path
    A note on resolving poster paths with themoviedb.org API
    You will notice that the API response provides a relative path to a movie poster image when you request the metadata for a specific movie.

    For example, the poster path return for Interstellar is “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”

    You will need to append a base path ahead of this relative path to build the complete url you will need to fetch the image using Picasso.

    It’s constructed using 3 parts:

    The base URL will look like: http://image.tmdb.org/t/p/.
    Then you will need a ‘size’, which will be one of the following: "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
    And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”

    Combining these three parts gives us a final url of http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
     */
}
