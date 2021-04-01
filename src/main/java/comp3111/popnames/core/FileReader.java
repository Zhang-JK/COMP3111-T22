package comp3111.popnames.core;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for reading data from the source file
 * @author ZHANG Jiekai
 * @version 1.0
 */
public class FileReader {

    /**
     * Get all the data from a file return in the list
     * @param year the target year to get the data
     * @return all the data in a year's file, in form of list
     */
    static List<NameRecord> getFileByYear(int year) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        List<NameRecord> result = new ArrayList<>();
        for (CSVRecord rec : fr.getCSVParser()) {
            result.add(new NameRecord(rec));
        }
        return result;
    }

    /**
     * Find the top popular names in a year and in a gender
     * @param year the target year to get the data
     * @param n how many names do you want
     * @param gender the target gender, 0 for Male, 1 for Female
     * @return top N popular names in a list
     */
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

    /**
     * get the total number of births of a year and gender
     * @param year the target year to get the data
     * @param gender the target gender, 0 for Male, 1 for Female
     * @return total number of births
     */
    static int getTotalBirthsByYear(int year, int gender) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser()) {
            if (rec.get(1).equals(gender==1 ? "F" : "M"))
                counter += Integer.parseInt(rec.get(2));
        }
        return counter;
    }

    /**
     * Input a name and get its rank in a certain year
     * @param name the name to search
     * @param year the target year
     * @param gender the target gender
     * @return rank, 0 for NOT FOUND
     */
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
