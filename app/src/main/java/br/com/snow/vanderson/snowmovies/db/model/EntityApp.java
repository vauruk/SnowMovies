package br.com.snow.vanderson.snowmovies.db.model;

import br.com.snow.vanderson.snowmovies.app.annotation.Id;

/**
 * Created by vanderson on 21/03/2017.
 */

public class EntityApp {
    @Id
    private int idDb = 0;

    public int getIdDb() {
        return idDb;
    }

    public void setIdDb(int idDb) {
        this.idDb = idDb;
    }
}

