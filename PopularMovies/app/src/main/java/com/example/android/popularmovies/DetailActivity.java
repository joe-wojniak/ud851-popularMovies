package com.example.android.popularmovies;

/*Example code adapted from:
        https://www.youtube.com/watch?v=W4hTJybfU7s&t=511s
        ud851-Exercises\Lesson09b.04-Executors
        ud851-Exercises\Lesson03-Green-Recycler-View\T03.04-Exercise-WiringUpRecyclerView
        https://www.codingdemos.com/android-gridlayout-example-recyclerview/
        YouTube player example - https://www.youtube.com/watch?v=W4hTJybfU7s&t=440s
        */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.database.AppDatabase;
import com.example.android.popularmovies.database.Movie;
import com.example.android.popularmovies.utils.MovieListService;
import com.example.android.popularmovies.utils.Utils;
import com.example.android.popularmovies.utils.appExecutors;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends MainActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    // Activity variables
    Context mContext;
    Movie movie;
    Button mButton;
    Button buttonTrailer;
    CheckBox buttonReview;
    ImageView mMovie;
    TextView mTitle;
    TextView mReleaseDate;
    TextView mVoteAverage;
    TextView mOverview;
    TextView movieIdTv;
    TextView movieReviewTv;

    String posterPath;
    String movieID;
    String mMovieReviewPath;
    String mMovieTrailerPath;
    String review;
    String videoKey;
    List<Movie> movieReview;
    List<Movie> movieTrailer;
    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = null;
        posterPath = null;
        mDb = AppDatabase.getInstance(getApplicationContext());

        initViews();

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onFavoriteButtonClicked();
            }
        });

        buttonReview.setActivated(false);
        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReviewButtonClicked();
            }
        });

        buttonTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrailerButtonClicked();
            }
        });

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            movie = mBundle.getParcelable("movie");
            mTitle.setText(movie.getTitle());
            mReleaseDate.setText(movie.getRelease_date());
            mVoteAverage.setText(movie.getVote_average());
            mOverview.setText(movie.getOverview());
            movieIdTv.setText(movie.getId());
            posterPath = movie.getPosterPath();
            movieID = movie.getId();

            String mMoviePosterPath = MovieListService.buildPosterURL("w500", posterPath);

            Picasso.with(mContext)
                    .load(mMoviePosterPath)
                    .into(mMovie);

            mMovieReviewPath = MovieListService.buildReviewURL(movieID);
            mMovieTrailerPath = MovieListService.buildTrailerURL(movieID);

            // get movie review
            appExecutors.getInstance().networkIO().execute(new Runnable() {
                @Override
                public void run() {
                    movieReview = Utils.fetchMovieData(mMovieReviewPath);
                    review = movieReview.get(0).getMovieReview();
                }
            });

            // get video key for trailer
            appExecutors.getInstance().networkIO().execute(new Runnable() {
                @Override
                public void run() {
                    movieTrailer = Utils.fetchMovieData(mMovieTrailerPath);
                    videoKey = movieTrailer.get(0).getVideoKey();
                }
            });
        }
    }

    private void initViews() {
        mMovie = findViewById(R.id.detailImageView);
        mTitle = findViewById(R.id.detailTitle);
        mReleaseDate = findViewById(R.id.detailReleaseDate);
        mVoteAverage = findViewById(R.id.detailVoteAverage);
        mOverview = findViewById(R.id.detailOverview);
        movieIdTv = findViewById(R.id.movieID);
        movieReviewTv = findViewById(R.id.tvReview);

        mButton = findViewById(R.id.buttonFavorite);
        buttonReview = findViewById(R.id.buttonReview);
        buttonTrailer = findViewById(R.id.buttonTrailer);
    }

    public void onFavoriteButtonClicked() {

        if (movie.getIsFavorite() == null) {
            movie.setIsFavorite("true");

            appExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.movieDao().insertMovie(movie);
                }
            });
        } else {
            movie.setIsFavorite(null);

            appExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.movieDao().deleteMovie(movie);
                }
            });

        }
    }

    public void onReviewButtonClicked() {

        if (buttonReview.isChecked() == true) {
            if (review != null) {
                movieReviewTv.setVisibility(View.VISIBLE);
                movieReviewTv.setText("\r\n" + review + "\r\n");
            } else {
                Toast.makeText(this, "Movie review not found. Please try again.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (buttonReview.isChecked() == false) {
            movieReviewTv.setVisibility(View.GONE);
        }
    }

    public void onTrailerButtonClicked() {
        // show movie trailer in mYouTubePlayer

        if (videoKey != null) {
            Intent mIntent = new Intent(this, mYouTubePlayer.class);
            mIntent.putExtra("videoKey", videoKey);
            this.startActivity(mIntent);
        }
        return;
    }
}
