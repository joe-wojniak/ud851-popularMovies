package com.example.android.popularmovies.utils;

import com.example.android.popularmovies.BuildConfig;

public class MovieListService {

    static String apiKey = BuildConfig.ApiKey;

    public static String buildMovieSortURL (String movieSort){
        // Build movie sort uri by popular or by vote_average:
        final String baseUri = "https://api.themoviedb.org/3/movie/";
        String movieSortURL = baseUri + movieSort + "?api_key=" + apiKey + "&language=en-US&page=1";
        return movieSortURL;
    }

    public static String buildPosterURL(String posterSize, String posterPath) {

        // Build poster uri:
        final String baseUri = "http://image.tmdb.org/t/p/";
        String finishedPosterPath = baseUri + posterSize + posterPath;
        return finishedPosterPath;
    }

    public static String buildReviewURL(String movieID) {

        // Build review uri:
        final String baseUri = "https://api.themoviedb.org/3/movie/";
        String finishedReviewURL = baseUri + movieID + "/reviews?" + "api_key=" + apiKey + "&language=en-US";
        return finishedReviewURL;
    }

    public static String buildTrailerURL(String movieID) {
        // Build review uri:
        final String baseUri = "https://api.themoviedb.org/3/movie/";
        String finishedTrailerURL = baseUri + movieID + "/videos?" + "api_key=" + apiKey + "&language=en-US";
        return finishedTrailerURL;
    }

}
