package ch.supsi.mavendemo.core.core;

import ch.supsi.mavendemo.core.model.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieStats {

    //return the number of movies present in the list
    public static int totalMovies(List<Movie> movies) {
        return movies.size();
    }

    //average movies runtime
    public static double averageRuntime(List<Movie> movies) {
        return movies.stream()
                .mapToInt(Movie::getRuntime)
                .average()
                .orElse(0);
    }

    //best director
    public static Optional<String> bestDirector(List<Movie> movies) {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getDirector,
                        Collectors.averagingDouble(Movie::getRating)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }


    public static Optional<String> mostPresentActor(List<Movie> movies) {
        Map<String, Long> actorCount = movies.stream()
                .flatMap(movie -> movie.getStars().stream())  // Usa la lista di attori (stars)
                .filter(Objects::nonNull)
                .filter(actor -> !actor.isBlank())
                .collect(Collectors.groupingBy(
                        actor -> actor,
                        Collectors.counting()
                ));

        return actorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }


    public static int getMostProductiveYear(List<Movie> movies) {
        Map<Integer, Integer> yearCount = new HashMap<>();

        //count number of flims for each year
        for (Movie movie : movies) {
            int year = movie.getYear();
            yearCount.put(year, yearCount.getOrDefault(year, 0) + 1);
        }

        return Collections.max(yearCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
