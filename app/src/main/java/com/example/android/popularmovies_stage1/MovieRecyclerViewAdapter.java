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
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    List<Movie> data;
    private LayoutInflater inflater;

    private static final String TAG = MovieRecyclerViewAdapter.class.getSimpleName();

    // private AdapterView.OnItemClickListener mClickListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForGridItem = R.layout.recyclerview_item;

        View view = inflater.inflate(layoutIdForGridItem, viewGroup, false);
        MovieViewHolder movieHolder = new MovieViewHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder (MovieViewHolder holder, int i) {
        // Movie current = data.get(i);
        //TODO: movie poster image from TMDb replaces this placeholder image
        Uri uri = Uri.parse("http://i.imgur.com/DvpvklR.png");
        Context context = holder.posterImage.getContext();
        Picasso.with(context).load(uri).into(holder.posterImage);
    }

    public int getItemCount() {
        return data.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        public MovieViewHolder (View itemView){
            super(itemView);
            posterImage = itemView.findViewById(R.id.rvPosters);
            itemView.setOnClickListener(MainActivity.movieOnClickListener);
        }
    }
}
