package com.example.android.popularmovies_stage1;

public class Movie {

    private String id;
    private String vote_average;
    private String title;
    private String popularity;
    private String posterPath;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;

    public Movie(String id, String vote_average, String title, String popularity, String posterPath,
                 String original_language, String original_title, String overview, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;

        this.popularity = popularity;
        this.posterPath = posterPath;
        this.original_language = original_language;

        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
    }

    // Getter and Setter methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language; }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}
