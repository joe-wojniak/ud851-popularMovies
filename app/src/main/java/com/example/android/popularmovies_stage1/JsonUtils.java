package com.example.android.popularmovies_stage1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    private static String KEY_PAGE = "page";
    private static String KEY_RESULTS = "results";
    private static String KEY_ID = "id";
    private static String KEY_VOTE="vote_average";
    private static String KEY_TITLE="title";
    private static String KEY_POP="popularity";
    private static String KEY_POSTER="poster_path";
    private static String KEY_LANG="original_language";
    private static String KEY_ORIG_TITLE="original_title";
    private static String KEY_OVERVIEW="overview";
    private static String KEY_RELEASE="release_date";

    private JsonUtils(){}

    private static List<Movie> extractFeatureFromJson (String json) {

        List<Movie> movieList = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject page = baseJsonResponse.optJSONObject(KEY_PAGE);
            JSONArray resultsArray = page.optJSONArray(KEY_RESULTS);

            // looping through the page results
            for (int i = 0; i < resultsArray.length(); i++) {
                // Extract out the first result (which is a movie)
                JSONObject firstResult = resultsArray.getJSONObject(i);
                // Extract out the title, poster_path, and overview
                String title = firstResult.optString(KEY_TITLE);
                String poster_path = firstResult.optString(KEY_POSTER);
                String overview = firstResult.optString(KEY_OVERVIEW);
                // TODO Add the remaining movie data attributes

                // Make new movie object using data attributes from json
                // Add movie object to list of movies
                Movie movie = new Movie(null,null,title,null,poster_path,
                        null,null,overview,null);
                movieList.add(movie);
            }

        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results ", e);
        }
        return movieList;
    }
}
