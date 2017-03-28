package br.com.snow.vanderson.snowmovies.db.dao;

import java.util.List;

import br.com.snow.vanderson.snowmovies.db.model.EntityApp;

/**
 * Created by vauruk on 21/03/17.
 * Padronizacao de metodos a serem implementado
 *
 */
public interface DAO {

    List<?> list(EntityApp entity, String whereClause, String[] whereArgs, String orderBy);
    void save(EntityApp entity);
    void delete(EntityApp entity);
    void update(EntityApp entity);
    EntityApp load( EntityApp entity);
    EntityApp load( EntityApp entity, String whereClause, String[] whereArgs, String orderBy );
}