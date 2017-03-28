package br.com.snow.vanderson.snowmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.snow.vanderson.snowmovies.R;
import br.com.snow.vanderson.snowmovies.adapter.FavoriteAdapter;
import br.com.snow.vanderson.snowmovies.app.FragmentApp;
import br.com.snow.vanderson.snowmovies.db.dao.MovieDAO;
import br.com.snow.vanderson.snowmovies.db.model.MovieBO;

/**
 * Created by vanderson on 26/03/2017.
 */

public class FavoriteFragment extends FragmentApp {

    private List<MovieBO> listMovie = new ArrayList<MovieBO>();
    private ListView listViewFavorite;
    private FavoriteAdapter favoriteAdapter;
    private MovieDAO dao;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false );


        dao = (MovieDAO) getDaoFactory().createMovieDAO(rootView.getContext());
        listMovie.addAll(dao.list(new MovieBO(), null, null, "title"));

        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPopular));

        listViewFavorite = (ListView) rootView.findViewById(R.id.listViewFavorite);

        favoriteAdapter = new FavoriteAdapter(getContext(), R.layout.item_movie, listMovie, dao);
        listViewFavorite.setAdapter(favoriteAdapter);

       return rootView;
    }
}
