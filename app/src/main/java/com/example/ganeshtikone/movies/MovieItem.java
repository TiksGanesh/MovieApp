package com.example.ganeshtikone.movies;

/**
 * Created by ganeshtikone on 14/01/18.
 */

public class MovieItem {

    // movie name , movie poster image, movie release year
    private String movieName;
    private String movieReleaseYear;
    private String moviePoster;


    public MovieItem() {
    }

    public MovieItem(String movieName, String movieReleaseYear, String moviePoster) {
        this.movieName = movieName;
        this.movieReleaseYear = movieReleaseYear;
        this.moviePoster = moviePoster;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}
