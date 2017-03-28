package br.com.snow.vanderson.snowmovies.util;

/**
 * Created by vanderson on 26/03/2017.
 */

public class CreateUrl {

    public static final String API_KEY = "?api_key=b5127d1016c968d30de8d0d6cc725a73";
    public static final String PAGE_URL = "&page=";
    public static final String URL = "http://api.themoviedb.org/3/movie/";

    public static final String BACKDROP_PATH = "https://image.tmdb.org/t/p/w500";



    public static String getPopularMovies(final int page){
        return URL+"popular"+API_KEY+PAGE_URL+page;
    }

    public static String getTopRatedMovies(final int page){
        return URL+"top_rated"+API_KEY+PAGE_URL+page;
    }

}
