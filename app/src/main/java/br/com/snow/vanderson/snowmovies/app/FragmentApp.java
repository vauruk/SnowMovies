package br.com.snow.vanderson.snowmovies.app;

import android.support.v4.app.Fragment;


import br.com.snow.vanderson.snowmovies.db.dao.DAOFactory;
import br.com.snow.vanderson.snowmovies.db.dao.DAOFactorySQLite;

/**
 * Created by vanderson on 27/03/2017.
 */

public class FragmentApp extends Fragment {

    private static DAOFactory daoFactory;

    protected static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactorySQLite();
        }
        return daoFactory;
    }

}
