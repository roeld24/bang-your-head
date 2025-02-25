package ch.supsi.mavendemo.core.io;

import ch.supsi.mavendemo.core.core.MovieStats;
import ch.supsi.mavendemo.core.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;

public class IOManager {
    public void readCsv(String path, List<Movie> movies){
        try {
            Reader in = new FileReader(path);
            List<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader().parse(in).getRecords();

            for (CSVRecord record : csvRecords) {
                String title = record.get("Series_Title");
                String director = record.get("Director");
                String leadActor = record.get("Star1");
                int year = Integer.parseInt(record.get("Released_Year"));
                int runtime = Integer.parseInt(record.get("Runtime").split(" ")[0]);
                double rating = Double.parseDouble(record.get("IMDB_Rating"));
                movies.add(new Movie(title,director,leadActor,year,runtime, rating));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String path, String text){
        try {
            Writer out = new FileWriter(path);
            out.write(text);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPreferences(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("files/preferences.txt"));

            String in = br.readLine(); // Read first line
            String out = br.readLine(); // Read second line

            if(in.isBlank() || out.isBlank()){
                System.out.println("Preferences file is not complete");
            }else{
                return in + "," + out;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String generateStats(List<Movie> movies) {
        StringBuilder sb = new StringBuilder();

        sb.append("Total number of movies: ");
        sb.append(MovieStats.totalMovies(movies));
        sb.append('\n');
        sb.append("Average movie runtime: ");
        sb.append(MovieStats.averageRuntime(movies));
        sb.append('\n');
        sb.append("Best director: ");
        sb.append(MovieStats.bestDirector(movies));
        sb.append('\n');

        return sb.toString();
    }
}