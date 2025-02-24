package ch.supsi.mavendemo.core.io;

import ch.supsi.mavendemo.core.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CsvManager {
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
}