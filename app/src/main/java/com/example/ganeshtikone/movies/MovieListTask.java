package com.example.ganeshtikone.movies;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeshtikone on 21/01/18.
 * class: AsyncTask Class , which will perform
 * Asynchronous operation
 */

public class MovieListTask extends AsyncTask<Void,Void,List<MovieItem>>{

    private MovieAdapter adapter;

    public MovieListTask(MovieAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<MovieItem> doInBackground(Void... voids) {

        // 1. Connection with Server

        HttpURLConnection httpURLConnection;
        int connectionTimeOut = 1000*60*60;
        int readConnectionTimeOut = 1000*60*60;

        try {

            // Place your key here
            String url = "http://api.themoviedb.org/3/discover/movie?api_key= #############";
            URL movieAPIURL = new URL(url);

            httpURLConnection = (HttpURLConnection) movieAPIURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(connectionTimeOut);
            httpURLConnection.setReadTimeout(readConnectionTimeOut);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);


            httpURLConnection.connect();


            // Check Response Code and Convert Raw data into String

            int responseCode = httpURLConnection.getResponseCode();

            if (HttpURLConnection.HTTP_OK == responseCode){

                InputStream inputStream = httpURLConnection.getInputStream();

                String response =  convertToString(inputStream);
                //Log.e("####",response);

                return getMovieListFromResponse(response);
            }

        }catch (MalformedURLException ex){

        }catch (IOException ex){

        }
        return null;
    }

    /**
     * 1. Convert String into JSON
     * 2. Get Movie Title/Poster/Release Date From JSON
     * @param response
     */
    private List<MovieItem> getMovieListFromResponse(String response) {

        try {
            // Convert to JSON
            JSONObject root = new JSONObject(response);

            // Get results JSON Array
            JSONArray results = root.getJSONArray("results");
            int length = results.length();

            List<MovieItem> movieItemList = new ArrayList<>();


            for (int i = 0; i < length ; i++){

                JSONObject movie = results.getJSONObject(i);

                String title = movie.getString("title");
                String imagePath = movie.getString("poster_path");

                MovieItem movieItem = new MovieItem();
                movieItem.setMovieName(title);
                movieItem.setMoviePoster(imagePath);
                movieItem.setMovieReleaseYear(""); //TODO Try it your self

                movieItemList.add(movieItem);


                //Log.e("### Movie ###",title + " - " + imagePath);
            }

            return movieItemList;

        }catch (JSONException ex){
            Log.e("###",ex.getLocalizedMessage());
        }

        return null;
    }

    /**
     * Convert InputStream into String
     * @param inputStream
     * @return
     */
    private String convertToString(InputStream inputStream) throws IOException{


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line = "";

        while ( (line = bufferedReader.readLine()) !=null ){
            builder.append(line);
        }

        return builder.toString();
    }






    @Override
    protected void onPostExecute(List<MovieItem> movieItems) {
        super.onPostExecute(movieItems);

        if (null != movieItems){
            adapter.updateData(movieItems);
        }

    }
}
