package ch.supsi.mavendemo.core.app;

import ch.supsi.mavendemo.core.io.IOManager;
import ch.supsi.mavendemo.core.model.Movie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = new IOManager();
        List<Movie> movieList = new ArrayList<>();

        Path[] paths;

        try{
            paths = ioManager.getPreferences();
        }catch (IOException e){
            System.err.println(e);
            return;
        }

        Path inputPath = paths[0];
        Path outputPath = paths[1];

        ioManager.readCsv(inputPath.toAbsolutePath().toString(), movieList);
        String stats = ioManager.generateStats(movieList);

        ioManager.writeFile(outputPath.toAbsolutePath().toString(), stats);
    }
}
