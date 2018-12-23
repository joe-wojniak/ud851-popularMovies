package com.example.android.popularmovies_stage1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private String title;
    private String release_date;
    private String posterPath;
    private String vote_average;
    private String overview;
    private String popularity;

    public Movie(String title, String release_date, String posterPath, String vote_average,
                 String overview, String popularity) {

        this.title = title;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;

    }

    // Getter and Setter methods

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

    public String getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public Movie(Parcel in) {
        this.title = in.readString();
        this.release_date = in.readString();
        this.posterPath = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
        this.popularity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.posterPath);
        dest.writeString(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.popularity);
    }
}
