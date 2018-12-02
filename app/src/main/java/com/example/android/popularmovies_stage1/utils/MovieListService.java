package com.example.android.popularmovies_stage1.utils;

public class MovieListService {

    public static String buildPosterURL(String posterPath, String posterSize) {

        // Build poster uri:
        String baseUri = "http://image.tmdb.org/t/p/";
        posterPath = baseUri + posterSize + posterPath;
        return posterPath;
    }
}
