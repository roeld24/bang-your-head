package ch.supsi.mavendemo.core.io;

import ch.supsi.mavendemo.core.core.MovieStats;
import ch.supsi.mavendemo.core.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
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
            throw new RuntimeException("Error reading CSV file: " + path, e);
        }
    }

    public void writeFile(String path, String text){
        File file = new File(path);
        boolean fileExists = file.exists();

        if (file.exists()) {
            System.out.println("Overwriting the output file...");
        } else {
            System.out.println("Writing the output file...");
        }

        try (Writer out = new FileWriter(path)) {
            out.write(text);
            System.out.println("Output successfully written to " + path);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + path, e);
        }
    }

    public String getPreferences(){
        try(BufferedReader br = new BufferedReader(new FileReader("files/preferences.txt"))) {
            String in = br.readLine(); // Read first line
            String out = br.readLine(); // Read second line

            if (in == null || out == null || in.isBlank() || out.isBlank()) {
                System.out.println("Preferences file is not complete");
                return "";
            } else {
                System.out.println("Preferences successfully read.");
                return in + "," + out;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading preferences file.", e);
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
