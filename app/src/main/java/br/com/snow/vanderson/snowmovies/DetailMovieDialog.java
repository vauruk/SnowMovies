package br.com.snow.vanderson.snowmovies;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.snow.vanderson.snowmovies.db.dao.MovieDAO;
import br.com.snow.vanderson.snowmovies.db.model.MovieBO;
import br.com.snow.vanderson.snowmovies.util.CreateUrl;

/**
 * Created by vanderson on 26/03/2017.
 */


public class DetailMovieDialog {

    private Dialog dialog;
    private Context context;
    private int resourceTitle;
    private MovieBO movie;
    private MovieDAO dao;

    private DetailMovieDialog() {}

    public DetailMovieDialog(@NonNull Context context, @NonNull int resourceTitle, @NonNull MovieBO movie, @NonNull MovieDAO dao) {
        this.context = context;
        this.resourceTitle = resourceTitle;
        this.movie = movie;
        this.dao = dao;
    }

    public Dialog getDialogCustom(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_movie_details);
        dialog.setTitle(context.getResources().getString(resourceTitle));
        dialog.setCancelable(true);

        String urlPosterPath = CreateUrl.BACKDROP_PATH+movie.getPosterPath();
        ImageView imgPosterMovie = (ImageView) dialog.findViewById(R.id.imgPosterMovie);
        Picasso.with(context).load(urlPosterPath).into(imgPosterMovie);

        TextView txtTitleMovieDetail = (TextView) dialog.findViewById(R.id.txtTitleMovieDetail);
        txtTitleMovieDetail.setText(movie.getTitle());

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = null;
        try {
            releaseDate = (Date) formatter.parse(movie.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        TextView txtReleaseDate = (TextView) dialog.findViewById(R.id.txtReleaseDate);
        txtReleaseDate.setText(sdf.format(releaseDate));

        TextView txtOverview = (TextView) dialog.findViewById(R.id.txtOverview);
        txtOverview.setText(movie.getOverview());

        RatingBar rateAvereageDetail = (RatingBar) dialog.findViewById(R.id.rateAvereageDetail);
        rateAvereageDetail.setRating(Float.valueOf(movie.getVoteAverage()));
        rateAvereageDetail.setIsIndicator(true);

        TextView txtOkDetail = (TextView) dialog.findViewById(R.id.txtOkDetail);
        txtOkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Switch swFavorite = (Switch) dialog.findViewById(R.id.swFavorite);

        final MovieBO movieLoad = (MovieBO) dao.loadMovie(movie.getIdMovie());
        boolean isFavorite = movieLoad != null;
        swFavorite.setChecked(isFavorite);
        if(isFavorite){
            movie.setIdDb(movieLoad.getIdDb());
        }
        swFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ){
                    if(movieLoad == null){
                        dao.save(movie);
                    }
                }else{
                    dao.delete(movie);
                }
            }
        });
        return dialog;
    }

}
