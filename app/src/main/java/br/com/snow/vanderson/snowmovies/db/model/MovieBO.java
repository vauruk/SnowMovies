package br.com.snow.vanderson.snowmovies.db.model;

import com.google.gson.annotations.SerializedName;

import br.com.snow.vanderson.snowmovies.app.annotation.Table;
import br.com.snow.vanderson.snowmovies.db.DBHelper;

/**
 * Created by vanderson on 26/03/2017.
 * Objeto de negocio movie
 */

@Table(name = DBHelper.TBL_MOVIE)
public class MovieBO extends EntityApp {

    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    private String overview;
    @SerializedName("id")
    private String idMovie;

    public MovieBO(){}

    public MovieBO(String title, String originalTitle, String voteAverage, String backdropPath, String posterPath, String releaseDate, String overview) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieBO that = (MovieBO) o;

        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
