package com.example.ganeshtikone.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ganeshtikone on 14/01/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // Declare
    private Context mContext;
    private List<MovieItem> movieItemList;

    public MovieAdapter(Context mContext, List<MovieItem> movieItemList) {
        this.mContext = mContext;
        this.movieItemList = movieItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // layoutinflator initialize
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_movie,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MovieItem movieItem = movieItemList.get(position);

        holder.textViewMovieName.setText(movieItem.getMovieName());
        holder.textViewMovieReleaseYear.setText(movieItem.getMovieReleaseYear());

        String imgURL = "http://image.tmdb.org/t/p/w185/"+movieItem.getMoviePoster();

        Picasso.with(mContext)
                .load(imgURL)
                .into(holder.imageMoviePoster);

    }

    @Override
    public int getItemCount() {
        return movieItemList.size(); // returns count of items to be display in recycler view
    }

    public void updateData(List<MovieItem> movieItems) {

        movieItemList.clear();
        movieItemList.addAll(movieItems);
        notifyDataSetChanged();

    }

    /**
     * ViewHolder for MovieItem Object
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        // Declare
        ImageView imageMoviePoster;
        TextView textViewMovieName;
        TextView textViewMovieReleaseYear;


        public ViewHolder(View itemView) {
            super(itemView);

            // Define
            imageMoviePoster = itemView.findViewById(R.id.imageMoviePoster);
            textViewMovieName = itemView.findViewById(R.id.textViewMovieName);
            textViewMovieReleaseYear = itemView.findViewById(R.id.textViewReleaseYear);


        }
    }




}






