package ch.supsi.mavendemo.core;

public class Movie {
    private String title;
    private int year;
    private String director;
    private int runtime;
    private String[] actors;

    public Movie(String title, int year, String director, int runtime, String[] actors) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.runtime = runtime;
        this.actors = actors;
    }

    public String[] getActors() {
        return actors;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }
}
