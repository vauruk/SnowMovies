package br.com.snow.vanderson.snowmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.snow.vanderson.snowmovies.R;
import br.com.snow.vanderson.snowmovies.adapter.PopularAdapter;
import br.com.snow.vanderson.snowmovies.app.FragmentApp;
import br.com.snow.vanderson.snowmovies.db.dao.MovieDAO;
import br.com.snow.vanderson.snowmovies.db.model.MovieBO;
import br.com.snow.vanderson.snowmovies.util.CreateUrl;
import br.com.snow.vanderson.snowmovies.util.UtilRest;

/**
 * Created by vanderson on 25/03/2017.
 *
 * Fragment que popula o tipo de filmes Popular
 */

public class PopularFragment extends FragmentApp {
    private List<MovieBO> listMovie = new ArrayList<MovieBO>();
    private ListView listViewPopular;
    private PopularAdapter popularAdapter;
    private MovieDAO dao;
    private int totalPage = 1;
    private int page = 1 ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular, container, false );

        dao = (MovieDAO) getDaoFactory().createMovieDAO(rootView.getContext());
        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPopular));

        listViewPopular = (ListView) rootView.findViewById(R.id.listViewPopular);
        popularAdapter = new PopularAdapter(getContext(), R.layout.item_movie, listMovie, dao);
        listViewPopular.setAdapter(popularAdapter);

        listMovie.clear();
        loadMovies();

        return rootView;
    }

    private void loadMovies(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = CreateUrl.getPopularMovies(page);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            totalPage = response.getInt("total_pages");
                            listMovie.clear();
                            listMovie.addAll(UtilRest.getListMovieBo(response));
                            popularAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest );

    }


    /**
     * Faz retroceder a paginação
     * @return
     */
    public View.OnClickListener getBack(){
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page -= 1;
                if(page >= 1 && page <= totalPage){
                    loadMovies();
                }else if( page < 1 ) {
                    Toast.makeText(getContext(), "Pagina inicial", Toast.LENGTH_LONG).show();
                }
            }
        };
        return onClick;
    }

    /**
     * Avanca paginacao
     * @return
     */
    public View.OnClickListener getFoward(){
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                if( page >= 1 && page <= totalPage ){
                    loadMovies();
                }else if ( page > totalPage ) {
                    Toast.makeText(getContext(), "Ultima "+page, Toast.LENGTH_LONG).show();
                }
            }
        };
        return onClick;
    }

}
