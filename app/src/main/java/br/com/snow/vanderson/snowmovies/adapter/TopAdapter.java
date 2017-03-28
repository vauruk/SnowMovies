package br.com.snow.vanderson.snowmovies.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import br.com.snow.vanderson.snowmovies.DetailMovieDialog;
import br.com.snow.vanderson.snowmovies.R;
import br.com.snow.vanderson.snowmovies.db.dao.MovieDAO;
import br.com.snow.vanderson.snowmovies.db.model.MovieBO;
import br.com.snow.vanderson.snowmovies.util.CreateUrl;

/**
 * Created by vanderson on 26/03/2017.
 */

public class TopAdapter extends ArrayAdapter<MovieBO> {

    private List<MovieBO>  list;
    private Context context;
    private MovieDAO dao;

    private int res = 0;

    public TopAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MovieBO> list,  @NonNull MovieDAO dao) {
        super(context, resource, list);
        this.list = list;
        this.context = context;
        this.res = resource;
        this.dao = dao;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater layoutItem = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutItem.inflate(res, null);
        }

        final MovieBO movieBO = list.get(position);

        TextView txtMovieName = (TextView) rowView.findViewById(R.id.txtNameMovie);
        txtMovieName.setText(movieBO.getTitle());

        RatingBar averageMovie = (RatingBar) rowView.findViewById(R.id.averageMovie);
        averageMovie.setRating(Float.valueOf(movieBO.getVoteAverage()));
        averageMovie.setIsIndicator(true);

        String urlPosterPath = CreateUrl.BACKDROP_PATH+movieBO.getPosterPath();
        ImageView imgMovie = (ImageView) rowView.findViewById(R.id.imgMovie);
        Picasso.with(context).load(urlPosterPath).into(imgMovie);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailMovieDialog detailMovieDialog = new DetailMovieDialog(
                        v.getContext(), R.string.title_detail_popular, movieBO, dao);
                detailMovieDialog.getDialogCustom().show();
            }
        });
        return rowView;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void addAll(@NonNull Collection<? extends MovieBO> collection) {
        list.addAll(collection);
    }

    @Override
    public void add(@Nullable MovieBO object) {
        list.add(object);
    }
}
