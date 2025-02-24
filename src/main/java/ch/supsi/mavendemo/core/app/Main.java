package ch.supsi.mavendemo.core.app;

import ch.supsi.mavendemo.core.core.*;
import ch.supsi.mavendemo.core.model.Movie;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Movie> movies = List.of(
                new Movie("Inception", "Christopher Nolan", "Leonardo DiCaprio", 2010, 148, 8.8),
                new Movie("The Dark Knight", "Christopher Nolan", "Christian Bale", 2008, 152, 9.0),
                new Movie("Interstellar", "Christopher Nolan", "Matthew McConaughey", 2014, 169, 8.6),
                new Movie("Titanic", "James Cameron", "Leonardo DiCaprio", 1997, 195, 7.8),
                new Movie("Avatar", "James Cameron", "Sam Worthington", 2009, 162, 7.8)
        );

        System.out.println(MovieStats.bestDirector(movies));

    }
}