package ch.supsi.mavendemo.core.model;

public class Movie {
    private final String title;
    private final String director;
    private final int year;
    private final int runtime;
    private final double rating;
    private final String star1;
    private final String star2;
    private final String star3;
    private final String star4;

    public Movie(String title, String director,String star1, String star2, String star3, String star4, int year, int runtime, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
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

    public String getStar1() {
        return star1;
    }

    public String getStar2() {
        return star2;
    }

    public String getStar3() {
        return star3;
    }

    public String getStar4() {
        return star4;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", rating=" + rating +
                ", star1='" + star1 + '\'' +
                ", star2='" + star2 + '\'' +
                ", star3='" + star3 + '\'' +
                ", star4='" + star4 + '\'' +
                '}';
    }
}
