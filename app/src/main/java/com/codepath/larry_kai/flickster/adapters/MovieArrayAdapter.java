package com.codepath.larry_kai.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.larry_kai.flickster.R;
import com.codepath.larry_kai.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by larry_kai on 8/4/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
        ImageView ivBackDropImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position.
        Movie movie = getItem(position);

        // check the existing view being reused.
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());



        // Find the Image View
        // Check orientation to determine which view to show :)
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieBackDropImage);
            viewHolder.ivImage.setImageResource(0); // clear out image from convertView
            Picasso.with(getContext()).load(movie.getPosterPath()).
                    placeholder(R.drawable.placeholder).into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewHolder.ivBackDropImage = (ImageView) convertView.findViewById(R.id.ivMovieBackDropImage);
            viewHolder.ivBackDropImage.setImageResource(0); // clear out image from convertView
            Picasso.with(getContext()).load(movie.getBackdropPath()).
                    placeholder(R.drawable.placeholder).into(viewHolder.ivBackDropImage);
        }

        // return the view
        return convertView;
    }
}
