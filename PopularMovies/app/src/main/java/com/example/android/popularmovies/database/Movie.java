package com.example.android.popularmovies.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "favorites")
public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    private int key;
    private String title;
    private String release_date;
    private String posterPath;
    private String vote_average;
    private String overview;
    private String popularity;
    private String isFavorite;
    private String id;
    private String videoKey;
    private String movieReview;

    @Ignore
    public Movie(String title, String release_date, String posterPath, String vote_average,
                 String overview, String popularity, String isFavorite, String id, String videoKey,
                 String movieReview) {

        this.title = title;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.id = id;
        this.videoKey = videoKey;
        this.movieReview = movieReview;

    }

    public Movie(int key, String title, String release_date, String posterPath, String vote_average,
                 String overview, String popularity, String isFavorite, String id, String videoKey,
                 String movieReview) {

        this.key = key;
        this.title = title;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.id = id;
        this.videoKey = videoKey;
        this.movieReview = movieReview;

    }

    // Getter and Setter methods

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVoteAverage(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public String getVideoKey(){
        return videoKey;
    }

    public String getMovieReview() {return movieReview;}

    public Movie(Parcel in) {
        this.key = in.readInt();
        this.title = in.readString();
        this.release_date = in.readString();
        this.posterPath = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
        this.popularity = in.readString();
        this.isFavorite = in.readString();
        this.id = in.readString();
        this.videoKey = in.readString();
        this.movieReview = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.key);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.posterPath);
        dest.writeString(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.popularity);
        dest.writeString(this.isFavorite);
        dest.writeString(this.id);
        dest.writeString(this.videoKey);
        dest.writeString(this.movieReview);
    }
}
