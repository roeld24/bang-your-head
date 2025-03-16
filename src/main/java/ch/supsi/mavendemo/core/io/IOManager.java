package ch.supsi.mavendemo.core.io;

import ch.supsi.mavendemo.core.core.MovieStats;
import ch.supsi.mavendemo.core.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOManager {
    public void readCsv(String path, List<Movie> movies){
        try (Reader in = new FileReader(path)){
            System.out.println("Reading from " + path);
            List<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader().parse(in).getRecords();

            for (CSVRecord record : csvRecords) {
                String title = record.get("Series_Title");
                String director = record.get("Director");
                List<String> stars = new ArrayList<>();
                if (record.isSet("Star1")) stars.add(record.get("Star1"));
                if (record.isSet("Star2")) stars.add(record.get("Star2"));
                if (record.isSet("Star3")) stars.add(record.get("Star3"));
                if (record.isSet("Star4")) stars.add(record.get("Star4"));
                int year = Integer.parseInt(record.get("Released_Year"));
                int runtime = Integer.parseInt(record.get("Runtime").split(" ")[0]);
                double rating = Double.parseDouble(record.get("IMDB_Rating"));

                try {
                    movies.add(new Movie(title, director, stars, year, runtime, rating));
                } catch (IllegalArgumentException e) {
                    // Se ci sono dati non validi, registriamo un messaggio di errore
                    System.out.println("Error processing movie: " + title + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + path + "check it exists and corresponds to the prefered path");
        }
    }

    public void writeFile(String path, String text) {
        File file = new File(path);
        File parentDir = file.getParentFile();

        //we are checking if the directories exist, if not they'll be created
        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Created missing directories: " + parentDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directories for: " + path);
            }
        }

        boolean fileExists = file.exists();

        if (fileExists) {
            System.out.println("Overwriting the output file...");
        } else {
            System.out.println("Writing the output file...");
        }

        try (Writer out = new FileWriter(file)) {
            out.write(text);
            System.out.println("Output successfully written to " + path);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + path);
        }
    }

    public Path[] getPreferences() throws IOException{
        String home = System.getProperty("user.home");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("input", "Mario");
        jsonObject.put("output", "Rossi");

        Path path = Paths.get(home, "preferences.txt");
        if(!Files.exists(path)){
            Writer writeFile = new FileWriter(String.valueOf(path));
            writeFile.write(jsonObject.toString(4));
            writeFile.flush();
        }

        String content = new String(Files.readAllBytes(path));
        JSONObject preferences = new JSONObject(content);

        String in = preferences.getString("input");
        String out = preferences.getString("output");

        if (in == null || out == null || in.isBlank() || out.isBlank()) {
            throw new IOException("Preferences file is not complete");
        } else {
            System.out.println("Preferences successfully read.");
            Path[] paths = {Paths.get(in),Paths.get(out)};
            if (paths.length != 2) {
                throw new IOException("Invalid preferences file format, there should be 2 paths");
            }
            if (!Files.exists(paths[0])) {
                throw new IOException("Input file does not exist: " + paths[0]);
            }
            return paths;
        }

    }

    public String generateStats(List<Movie> movies) {
        System.out.println("Generating movie statistics...");
        StringBuilder sb = new StringBuilder();

        sb.append("Total_Movies,");
        sb.append("Runtime_Avg,");
        sb.append("Best_Director,");
        sb.append("Most_Present_Actor,");
        sb.append("Most_Productive_Year");
        sb.append('\n');
        sb.append(MovieStats.totalMovies(movies)).append(',');
        sb.append(MovieStats.averageRuntime(movies)).append(',');

        Optional<String> bestDirector = MovieStats.bestDirector(movies);
        bestDirector.ifPresentOrElse(
                director -> sb.append(director).append(','),
                () -> sb.append("Unknown").append(',')
        );

        Optional<String> mostPresentActor = MovieStats.mostPresentActor(movies);
        mostPresentActor.ifPresentOrElse(
                actor -> sb.append(actor).append(','),
                () -> sb.append("Unknown").append(',')
        );

        sb.append(MovieStats.getMostProductiveYear(movies));
        System.out.println("Statistics successfully generated.");
        return sb.toString();
    }
}
