package ch.supsi.mavendemo.core.model;

import java.util.Collections;
import java.util.List;

public class Movie {
    private final String title;
    private final String director;
    private final int year;
    private final int runtime;
    private final double rating;
   private final List<String> stars;

    public Movie(String title, String director, List<String> stars, int year, int runtime, double rating) {
        if (title == null || director == null || stars == null || stars.isEmpty()) {
            throw new IllegalArgumentException("Title, director, and stars must not be null or empty.");
        }
        if (year <= 1890 || runtime <= 0 || rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Invalid year, runtime, or rating value.");
        }
        this.title = title;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
        this.stars = List.copyOf(stars);
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    public double getRating() {
        return rating;
    }

    public List<String> getStars() {
        return Collections.unmodifiableList(stars);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", rating=" + rating +
                ", stars=" + stars +
                '}';
    }
}
