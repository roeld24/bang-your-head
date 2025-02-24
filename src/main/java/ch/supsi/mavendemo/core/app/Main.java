package ch.supsi.mavendemo.core.app;

import ch.supsi.mavendemo.core.core.*;
import ch.supsi.mavendemo.core.io.CsvManager;
import ch.supsi.mavendemo.core.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static ch.supsi.mavendemo.core.core.MovieStats.*;

public class Main {
    public static void main(String[] args) {
        CsvManager csvManager = new CsvManager();
        List<Movie> movieList = new ArrayList<>();

        csvManager.readCsv("files/imdb_top_1000.csv", movieList);

        /*for(Movie movie : movieList){
            System.out.println(movie);
        }*/

        System.out.println(totalMovies(movieList));
        System.out.println(averageRuntime(movieList));
        System.out.println(bestDirector(movieList));
    }
}