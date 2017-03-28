package br.com.snow.vanderson.snowmovies.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.snow.vanderson.snowmovies.app.annotation.Id;
import br.com.snow.vanderson.snowmovies.app.annotation.Table;
import br.com.snow.vanderson.snowmovies.db.DataStore;
import br.com.snow.vanderson.snowmovies.db.model.EntityApp;


/**
 * Created by vanderson on 19/03/16.
 */
public abstract class DAOGeneric implements DAO {

    protected static DataStore dataStore;
    protected SQLiteDatabase db;
    protected final String ID_SQL = "idDb=?";

    public DAOGeneric(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /*
        Criado para criar o metodo query do SQLite
     */
    protected Cursor createQuerySqLite(SQLiteDatabase db, EntityApp obj, String whereClause, String[] whereArgs, String orderBy) {
        return db.query(getTableName(obj), loadArrayColunn(obj), whereClause, whereArgs, null, null, orderBy);

    }

    private String[] loadArrayColunn(EntityApp obj) {
        List<String> listColunn = new ArrayList<String>();
        Class<?> clazz1 = obj.getClass();
        //TODO vanderson revisar assim que possivel
        listColunn.add("idDb");
        for (Field field : clazz1.getDeclaredFields()) {
            field.setAccessible(true);
            listColunn.add(field.getName());
        }

        return listColunn.toArray(new String[listColunn.size()]);
    }

    /**
     * Pega o nome da tabela por reflection
     * @param obj
     * @return
     */
    protected String getTableName(EntityApp obj) {
        Class clazz1 = obj.getClass();
        String nameTable = "";
        if (clazz1.isAnnotationPresent(Table.class)) {
            Table an = (Table) clazz1.getAnnotation(Table.class);
            nameTable = an.name();
        }
        return nameTable;
    }

    /**
     * Criar o insert no sqllite
     * @param entidade
     * @return
     */
    public long createInsertSqlite(EntityApp entidade) {

        return db.insert(getTableName(entidade), null, createContentValues(entidade));
    }


    /**
     * Cria  o content value do sqllite
     * @param entidade
     * @return
     */
    private ContentValues createContentValues(EntityApp entidade) {
        Class<?> clazz1 = entidade.getClass();
        ContentValues values = new ContentValues();

        for (Field field : clazz1.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (!field.isAnnotationPresent(Id.class)) {
                    Object obj = field.get(entidade);
                    if(obj != null) {
                        values.put(field.getName(), obj.toString());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.e("Erro Reflecction:", e.getMessage());
            }
        }
        return values;
    }

    /**
     * Implementa o delete no formato sqlite android
     * @param entidade
     * @return inteiro se houver um delete executado com sucesso.
     */
    public int createDeleteSqlite(EntityApp entidade) {
        return db.delete(getTableName(entidade), ID_SQL, new String[]{String.valueOf(entidade.getIdDb())});
    }

    @Override
    public void save( EntityApp entidade ) {
        db = dataStore.getDbHelper().getWritableDatabase();
        long result = createInsertSqlite(entidade);
    }

    @Override
    public void delete( EntityApp entidade ) {
        db = dataStore.getDbHelper().getWritableDatabase();
        int result = createDeleteSqlite(entidade);
    }

    @Override
    public void update(EntityApp obj) {

    }

}
