package br.com.snow.vanderson.snowmovies.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.snow.vanderson.snowmovies.db.model.MovieBO;

/**
 * Created by vanderson on 26/03/2017.
 */
public class UtilRest {

    public static List<MovieBO> getListMovieBo(JSONObject response) throws JSONException {
        List<MovieBO> listMovie = new ArrayList<MovieBO>();
        JSONArray arrayData = response.getJSONArray("results");
        MovieBO movieItem = null;
        for (int i = 0; i < arrayData.length(); i++) {
            JSONObject data = arrayData.getJSONObject(i);
            Gson gson = new Gson();
            movieItem = gson.fromJson(data.toString(), MovieBO.class);
            listMovie.add(movieItem);
        }

        Collections.sort(listMovie, new Comparator<MovieBO>() {
            @Override
            public int compare(MovieBO o1, MovieBO o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        return listMovie;

    }
}
