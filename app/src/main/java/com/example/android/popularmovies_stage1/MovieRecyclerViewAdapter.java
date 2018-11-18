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

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    private static final String TAG = MovieRecyclerViewAdapter.class.getSimpleName();

    private int mNumberItems;

    //TODO: movie poster image from TMDb replaces this placeholder image
    public Uri uri = Uri.parse("http://i.imgur.com/DvpvklR.png");

    // private AdapterView.OnItemClickListener mClickListener;

    public MovieRecyclerViewAdapter(int numberOfItems){
        mNumberItems = numberOfItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForGridItem = R.layout.recyclerview_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder (MovieViewHolder holder, int i) {
        AnArticle current = data.get(i);
        Uri uri = Uri.parse(current.imageUrl);
        Context context = holder.articleImage.getContext();
        Picasso.with(context).load(uri).into(holder);
    }

    public int getItemCount() {
        return mNumberItems;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView gridItemImageView;
        public MovieViewHolder (View itemView){
            super(itemView);
            gridItemImageView = itemView.findViewById(R.id.rvPosters);
            itemView.setOnClickListener(MainActivity.movieOnClickListener);
        }
    }
}
