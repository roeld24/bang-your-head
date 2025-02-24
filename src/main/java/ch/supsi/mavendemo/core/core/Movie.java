package ch.supsi.mavendemo.core.core;

public class Movie {
    private String title;
    private String director;
    private String leadActor;
    private int year;
    private int runtime;

    public Movie(String title, String director, String leadActor, int year, int runtime) {
        this.title = title;
        this.director = director;
        this.leadActor = leadActor;
        this.year = year;
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getLeadActor() {
        return leadActor;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }
}
