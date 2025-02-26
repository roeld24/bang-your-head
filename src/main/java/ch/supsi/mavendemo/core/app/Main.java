package ch.supsi.mavendemo.core.app;

import ch.supsi.mavendemo.core.io.IOManager;
import ch.supsi.mavendemo.core.model.Movie;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        IOManager ioManager = new IOManager();
        List<Movie> movieList = new ArrayList<>();

        Optional<String> preferences = Optional.ofNullable(ioManager.getPreferences());
        if (preferences.isEmpty()) {
            System.out.println("Preferences file is not valid or missing.");
            return;
        }

        String[] paths = preferences.get().split(",");
        if (paths.length != 2) {
            System.out.println("Invalid preferences file format, there should be 2 paths");
            return;
        }

        Path inputPath = Paths.get(paths[0]);
        Path outputPath = Paths.get(paths[1]);

        if (!Files.exists(inputPath)) {
            System.out.println("Input file does not exist: " + inputPath);
            return;
        }

        ioManager.readCsv(inputPath.toAbsolutePath().toString(), movieList);

        String stats = ioManager.generateStats(movieList);

        try {
            ioManager.writeFile(outputPath.toAbsolutePath().toString(), stats);
            System.out.println("Statistics successfully written to " + outputPath);
        } catch (Exception e) {
            System.out.println("Error writing the output file: " + e.getMessage());
        }
    }
}
