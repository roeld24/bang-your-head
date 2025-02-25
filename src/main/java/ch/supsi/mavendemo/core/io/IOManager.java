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
                String star1 = record.get("Star1");
                String star2 = record.get("Star2");
                String star3 = record.get("Star3");
                String star4 = record.get("Star4");
                int year = Integer.parseInt(record.get("Released_Year"));
                int runtime = Integer.parseInt(record.get("Runtime").split(" ")[0]);
                double rating = Double.parseDouble(record.get("IMDB_Rating"));

                movies.add(new Movie(title,director,star1,star2,star3,star4,year,runtime,rating));
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

        sb.append("Total_Movies,");
        sb.append("Runtime_Avg,");
        sb.append("Best_Director");
        sb.append("Most_Present_Actor");
        sb.append('\n');
        sb.append(MovieStats.totalMovies(movies)).append(',');
        sb.append(MovieStats.averageRuntime(movies)).append(',');
        sb.append(MovieStats.bestDirector(movies)).append(',');
        sb.append(MovieStats.mostPresentActor(movies));

        return sb.toString();
    }
}