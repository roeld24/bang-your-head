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
    public static String bestDirector(List<Movie> movies) {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getDirector,
                        Collectors.averagingDouble(Movie::getRating)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }

    public static String mostPresentActor(List<Movie> movies) {
        Map<String, Integer> actorCount = new HashMap<>();

        for (Movie movie : movies) {
            countActor(actorCount, movie.getStar1());
            countActor(actorCount, movie.getStar2());
            countActor(actorCount, movie.getStar3());
            countActor(actorCount, movie.getStar4());
        }

        return actorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }

    private static void countActor(Map<String, Integer> actorCount, String actor) {
        if (actor != null && !actor.isBlank()) {
            actorCount.put(actor, actorCount.getOrDefault(actor, 0) + 1);
        }
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
