package com.example.ganeshtikone.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private List<MovieItem> movieItemList;
    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -- My Code

        movieItemList = new ArrayList<>();
        createMovieDummyData();
        initUi();
        getMovieList();
        // -- My Code


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getMovieList() {

        new MovieListTask(adapter).execute();
    }

    private void initUi() {

        movieRecyclerView = findViewById(R.id.movieRecyclerView);

        adapter = new MovieAdapter(this,movieItemList);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setAdapter(adapter);

    }


    /**
     *  Create Movie Dummy Data List
     */
    private void createMovieDummyData() {


        MovieItem it = new MovieItem();
        it.setMovieName("IT");
        it.setMovieReleaseYear("2017");
        it.setMoviePoster("https://image.tmdb.org/t/p/w185_and_h278_bestv2/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg");

        movieItemList.add(it);

        MovieItem howToTrainYourDragon = new MovieItem();
        howToTrainYourDragon.setMovieName("How To Train Your Dragon");
        howToTrainYourDragon.setMovieReleaseYear("2016");
        howToTrainYourDragon.setMoviePoster("https://image.tmdb.org/t/p/w185_and_h278_bestv2/hIXX3IRFy0InUOmYeWjvhCAgQNj.jpg");

        movieItemList.add(howToTrainYourDragon);

        MovieItem minions = new MovieItem();
        minions.setMovieName("Minions");
        minions.setMovieReleaseYear("2017");
        minions.setMoviePoster("https://image.tmdb.org/t/p/w185_and_h278_bestv2/q0R4crx2SehcEEQEkYObktdeFy.jpg");
        movieItemList.add(minions);

        MovieItem despicableMe = new MovieItem();
        despicableMe.setMovieName("Despicable Me");
        despicableMe.setMovieReleaseYear("2014");
        despicableMe.setMoviePoster("https://image.tmdb.org/t/p/w185_and_h278_bestv2/4zHJhBSY4kNZXfhTlmy2TzXD51M.jpg");

        movieItemList.add(despicableMe);

        MovieItem newton = new MovieItem("Newton",
                "2017",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/m3B8yX7hzQBjagQQken3CfEcoRF.jpg");

        movieItemList.add(newton);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
