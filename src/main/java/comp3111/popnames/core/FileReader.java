package comp3111.popnames.core;

import comp3111.popnames.core.NameRecord;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.*;

public class FileReader {
    static List<NameRecord> getFileByYear(int year) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        List<NameRecord> result = new ArrayList<>();
        for (CSVRecord rec : fr.getCSVParser()) {
            result.add(new NameRecord(rec));
        }
        return result;
    }

    static List<NameRecord> TopNNamesByYear(int year, int n, int gender) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        List<NameRecord> result = new ArrayList<>();
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                result.add(new NameRecord(rec));
                if (counter == n) break;
                else counter++;
            }
        }
        return result;
    }

    static int getTotalBirthsByYear(int year, int gender) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender==1 ? "F" : "M"))
                counter += Integer.parseInt(rec.get(2));
        }
        return counter;
    }

    static int getRankByYearAndName(String name, int year, int gender) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                rank++;
                if(rec.get(0).equals(name)) return rank;
            }
        }
        return 0;
    }
}
