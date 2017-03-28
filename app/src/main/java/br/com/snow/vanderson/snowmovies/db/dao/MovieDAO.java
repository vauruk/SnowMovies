package br.com.snow.vanderson.snowmovies.db.dao;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import br.com.snow.vanderson.snowmovies.db.DataStore;
import br.com.snow.vanderson.snowmovies.db.model.EntityApp;
import br.com.snow.vanderson.snowmovies.db.model.MovieBO;

/**
 * Created by vauruk on 19/03/16.
 * A implementacao especifica para o UC relacionado
 */
public class MovieDAO extends DAOGeneric {

    public MovieDAO(DataStore dataStore){
        super(dataStore);
    }

    @Override
    public List<MovieBO> list( EntityApp entidade, String whereClause, String[] whereArgs , String orderBy ) {
        db = super.dataStore.getDbHelper().getReadableDatabase();
        Cursor cursor = createQuerySqLite(db, entidade,whereClause, whereArgs, "title");
        List<MovieBO> list = new ArrayList<MovieBO>();
        if(cursor.moveToFirst() && cursor.getCount()>0){
            do{
               list.add(convertCursorInObject(cursor));
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    @Override
    public MovieBO load(EntityApp entity) {
        db = super.dataStore.getDbHelper().getReadableDatabase();
        Cursor cursor = createQuerySqLite(db, entity,ID_SQL, new String[]{String.valueOf(entity.getIdDb())}, null);
        MovieBO movieBO = null;
        if(cursor.moveToFirst() && cursor.getCount()>0){
            do{
                movieBO = convertCursorInObject(cursor);
            }while (cursor.moveToNext());
        }
        db.close();
        return movieBO;
    }

   /* protected Cursor createQuerySqLite( String[] whereArgs) {
        return db.rawQuery("select * from tbl_movie where idMovie= ?", new String[]{293660});

    }*/

    @Override
    public EntityApp load(EntityApp entity, String whereClause, String[] whereArgs, String orderBy) {
        db = super.dataStore.getDbHelper().getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM tbl_movie WHERE  idMovie = 293660 ORDER BY idMovie", null);
        Cursor cursor = createQuerySqLite(db, entity, whereClause, whereArgs, orderBy);
        MovieBO movieBO = null;
        if(cursor.moveToFirst() && cursor.getCount()>0){
            do{

                movieBO = convertCursorInObject(cursor);

            }while (cursor.moveToNext());
        }
        db.close();
        return movieBO;
    }

    /**
     * Carrega movie atraves de ID the movie db
     * @param idMovie
     * @return
     */
    public EntityApp loadMovie(String idMovie) {
        db = super.dataStore.getDbHelper().getReadableDatabase();
        String sql = "SELECT * FROM tbl_movie WHERE  idMovie = "+idMovie;
        Cursor cursor = db.rawQuery(sql, null);

        MovieBO movieBO = null;
        if(cursor.moveToFirst() && cursor.getCount()>0){
            do{
                movieBO = convertCursorInObject(cursor);
            }while (cursor.moveToNext());
        }
        db.close();
        return movieBO;
    }

    /**
    Converte cursor em movieBO
    @TODO Vanderson - rever e implementar de maneira generica
    * */

    private MovieBO convertCursorInObject(Cursor cursor){
        MovieBO movieBO = new MovieBO();
        movieBO.setIdDb(cursor.getInt(cursor.getColumnIndex("idDb")));
        movieBO.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        movieBO.setOriginalTitle(cursor.getString(cursor.getColumnIndex("originalTitle")));
        movieBO.setVoteAverage(cursor.getString(cursor.getColumnIndex("voteAverage")));
        movieBO.setBackdropPath(cursor.getString(cursor.getColumnIndex("backdropPath")));
        movieBO.setOverview(cursor.getString(cursor.getColumnIndex("overview")));
        movieBO.setPosterPath(cursor.getString(cursor.getColumnIndex("posterPath")));
        movieBO.setReleaseDate(cursor.getString(cursor.getColumnIndex("releaseDate")));
        movieBO.setIdMovie(cursor.getString(cursor.getColumnIndex("idMovie")));

        return movieBO;

    }

}

