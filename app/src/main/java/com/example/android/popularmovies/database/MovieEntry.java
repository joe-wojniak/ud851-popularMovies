package com.example.android.popularmovies.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String release_date;
    private String posterPath;
    private String vote_average;
    private String overview;
    private String popularity;
    private String isFavorite;
    private String movieId;

    @Ignore
    public MovieEntry(String title, String release_date, String posterPath, String vote_average,
                      String overview, String popularity, String isFavorite, String movieId) {
        this.title = title;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.movieId = movieId;
    }

    public MovieEntry(int id, String title, String release_date, String posterPath, String vote_average,
                      String overview, String popularity, String isFavorite, String movieId) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.posterPath = posterPath;
        this.vote_average = vote_average;
        this.overview = overview;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getMovieId(){
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
