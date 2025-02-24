package ch.supsi.mavendemo.core.app;

import ch.supsi.mavendemo.core.io.IOManager;
import ch.supsi.mavendemo.core.model.Movie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ch.supsi.mavendemo.core.core.MovieStats.*;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = new IOManager();
        List<Movie> movieList = new ArrayList<>();

        String[] paths = ioManager.getPreferences().split(",");

        Path inputPath = Paths.get(paths[0]);
        Path outputPath = Paths.get(paths[1]);

        if(!Files.exists(inputPath)) {
            return;
        }

        ioManager.readCsv(inputPath.toAbsolutePath().toString(), movieList);

        StringBuilder sb = new StringBuilder();

        sb.append(totalMovies(movieList)).append('\n');
        sb.append(averageRuntime(movieList)).append('\n');
        sb.append(bestDirector(movieList)).append('\n');

        ioManager.writeFile(outputPath.toAbsolutePath().toString(), sb.toString());

        /*for(Movie movie : movieList){
            System.out.println(movie);
        }*/
    }
}