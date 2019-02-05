package com.example.android.popularmovies;
/*
Popular Movies App Stage 2 (ud851 - AND)

Internet permissions and network state permissions are required in AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

Project rubric:
https://review.udacity.com/#!/rubrics/1881/view

Implementation Guide:
https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true#h.7sxo8jefdfll

Example code adapted from:
ud851-Exercises\Lesson09b...\T09b.04-Solution-Executors
ud851-Exercises\Lesson03-Green-Recycler-View\T03.04-Exercise-WiringUpRecyclerView
https://www.codingdemos.com/android-gridlayout-example-recyclerview/
Sections of code functionality modified from NewsApp Stage 2 (ABND Project 6) and
https://medium.com/@sanjeevy133/an-idiots-guide-to-android-asynctaskloader-76f8bfb0a0c0
implementing background thread call to TMDb api.
YouTube player example - https://www.youtube.com/watch?v=W4hTJybfU7s&t=440s
*/

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies.database.AppDatabase;
import com.example.android.popularmovies.database.Movie;
import com.example.android.popularmovies.utils.MovieListService;

import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    RecyclerView mMoviePostersRecyclerView;
    String movieSort = null;
    Boolean showFavorites = false;
    SharedPreferences sharedPrefs;
    GridLayoutManager mGridLayoutManager;
    Parcelable mListState;
    String LIST_STATE_KEY;

    private static final int MOVIE_LOADER_ID = 1;
    private MovieRecyclerViewAdapter mAdapter;

    private AppDatabase mDb;
    private int noOfColumns = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviePostersRecyclerView = findViewById(R.id.rvPosters);
        mMoviePostersRecyclerView.setHasFixedSize(false);

        calculateNoOfColumns(getApplicationContext());

        mGridLayoutManager = new GridLayoutManager(this, noOfColumns);
        mMoviePostersRecyclerView.setLayoutManager(mGridLayoutManager);

        // Setup reference to SharedPreferences and register to be notified of changes
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        movieSort = sharedPrefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_order_popular_value));

        showFavorites = sharedPrefs.getBoolean(getString(R.string.pref_show_favorites_key),
                false);

        sharedPrefs.registerOnSharedPreferenceChangeListener(this);

        // Get favorites database
        mDb = AppDatabase.getInstance(getApplicationContext());

        if (showFavorites == false) {

            // Get a reference to the ConnectivityManager to check state of network connectivity
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data

            if (networkInfo != null && networkInfo.isConnected()) {
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getSupportLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

            } else {
                // Otherwise, display no connection error message
                Toast.makeText(this, "No internet connection found. Please try again later.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Build Uri
            String mURL = MovieListService.buildMovieSortURL(movieSort);
            return new MovieLoader(this, mURL);
        } else {
            // Otherwise, display error no connection error message
            Toast.makeText(this, "No internet connection found. Please try again later.",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movieList) {
        if (showFavorites == false && movieList != null && !movieList.isEmpty()) {
            setMovieInAdapter(movieList);
            Toast.makeText(this, "Movies Loaded", Toast.LENGTH_SHORT).show();
        } else if (movieList == null || movieList.isEmpty()) {
            // no movies returned, display error message
            Toast.makeText(this, "No internet connection found. Please try again later.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.mMovieList.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_order_popular_value))) {
            // order by popular
            movieSort = "popular";
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        } else if (key.equals(getString(R.string.pref_order_topRated_value))) {
            // order by top rated
            movieSort = "top_rated";
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        } else if (key.equals(getString(R.string.pref_show_favorites_key))) {
            // show favorites
            getFavorites();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        showFavorites = sharedPrefs.getBoolean(getString(R.string.pref_show_favorites_key),
                false);
        if (showFavorites == true) {
            getFavorites();
        } else {
            movieSort = sharedPrefs.getString(getString(R.string.pref_sort_key),
                    getString(R.string.pref_order_popular_value));
            getSupportLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        }

        if (mListState != null) {
            mGridLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    void getFavorites() {

                final LiveData<List<Movie>> movieList = mDb.movieDao().loadAllMovies();
                movieList.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> movies) {
                        setMovieInAdapter(movies);
                    }
                });
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if (noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save list state
        mListState = mGridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

        // Restore list state and list/item positions

        if (savedState != null)
            mListState = savedState.getParcelable(LIST_STATE_KEY);
    }

    void setMovieInAdapter(List<Movie> movieList) {
        if (movieList != null && !movieList.isEmpty()) {
            mAdapter = new MovieRecyclerViewAdapter(this, movieList);
            mMoviePostersRecyclerView.setAdapter(mAdapter);
        }
    }
}
