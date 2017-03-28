package br.com.snow.vanderson.snowmovies.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vanderson on 21/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO_DB = 2;
    private static final String NAME_DB = "snow_movie.db";

    public static final String TBL_MOVIE= "tbl_movie";

    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSAO_DB);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS "+ TBL_MOVIE +" (";
        sql += "idDb integer PRIMARY KEY AUTOINCREMENT, ";
      //  sql += "id integer not null, ";
        sql += "title TEXT NOT NULL, ";
        sql += "originalTitle TEXT NOT NULL, ";
        sql += "voteAverage TEXT NOT NULL, ";
        sql += "backdropPath TEXT NOT NULL, ";
        sql += "posterPath TEXT NOT NULL, ";
        sql += "releaseDate TEXT NOT NULL, ";
        sql += "overview TEXT NOT NULL, ";
        sql += "idMovie TEXT NOT NULL, ";
        sql += "serialVersionUID TEXT NULL );";

        db.execSQL(sql);

     //   String insert = "insert into "+ TBL_MOVIE +" (id, name, dataIn, beaconAddress ) values (1, 'Vanderson Teste','22/03/1981', 'DD:EE:FF:33:44');";
    //    db.execSQL(insert);
     //   String insert2 = "insert into "+ TBL_MOVIE +" (id, name, dataIn, beaconAddress ) values (2, 'Teste 2','31/12/1981', 'FF:00:00:33:44');";
   //     db.execSQL(insert2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TBL_MOVIE;

        db.execSQL(sql);

        onCreate(db);
    }
}
