package com.example.android.popularmovies_stage1;

/* Example code modified from these sources:
ud851-Exercises\Lesson03-Green-Recycler-View\T03.04-Exercise-WiringUpRecyclerView
StackOverflow: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-
recyclerview-with-gridlayoutmanager-like-the
and 101 apps.co.za:
https://www.101apps.co.za/index.php/articles/android-recyclerview-and-picasso-tutorial.html
https://www.101apps.co.za/index.php/articles/gridview-tutorial-using-the-
picasso-library.html
https://www.101apps.co.za/index.php/ebooks/let-your-apps-take-a-giant-leap-a-tutorial.html*/

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int[] mMovieList;

    private static final String TAG = MovieRecyclerViewAdapter.class.getSimpleName();

    public MovieRecyclerViewAdapter(Context mContext, int[] mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,
                parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        holder.posterImage.setImageResource(mMovieList[position]);
    }

    @Override
    public int getItemCount() {
        return mMovieList.length;
    }
}

class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView posterImage;

    public MovieViewHolder(View itemView) {
        super(itemView);
        posterImage = itemView.findViewById(R.id.rvPosters);
    }
}