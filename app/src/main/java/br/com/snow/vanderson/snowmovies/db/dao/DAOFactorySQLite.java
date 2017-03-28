package br.com.snow.vanderson.snowmovies.db.dao;

import android.content.Context;
import br.com.snow.vanderson.snowmovies.db.DataStore;

/**
 * Created by vauruk on 19/03/17.
 * Implementacao da DAO Factory para criar qualquer outra classe DAO
 *
 */
public class DAOFactorySQLite implements DAOFactory {

    @Override
    public DAO createMovieDAO(Context context) {
        return new MovieDAO(DataStore.sharedInstance(context));
    }
}
