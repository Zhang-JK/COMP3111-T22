package comp3111.popnames.core;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for reading data from the source file
 * @author ZHANG Jiekai, LIANG Houdong, CHENG Yiren
 * @version 1.3
 */
public class FileReader {

    /**
     * To read the file line by line and get the CSV data
     * @param year the target file
     * @return the CSV data
     */
    private static CSVParser getFileParser(int year) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        return fr.getCSVParser(false);
    }

    /**
     * Get all the data from a file return in the list
     * @param year the target year to get the data
     * @return all the data in a year's file, in form of list
     */
    public static List<NameRecord> getFileByYear(int year) {
        List<NameRecord> result = new ArrayList<>();
        for (CSVRecord rec : getFileParser(year)) {
            result.add(new NameRecord(rec));
        }
        return result;
    }
    
    /**
     * input data and find the occurrence list
     * @param year1 target starting year
     * @param year2 target ending year
     * @param name name of interest
     * @param gender gender of the name want to research
     * @return  a list of OccurrenceRecord of that name during the period
     */  
    public static List<OccurrenceRecord> getFileOfYearAndName(int year1, int year2, String name, String gender){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	List<OccurrenceRecord> result = new ArrayList<>();
    	
    	for(int i = year1; i<= year2; i++) {
			int occurrences = 0;
			int totalBirths = 0;
			String percentage = "";
			int genderCheck = gender.equals("M") ? 0 : 1;
			int rank = getRankByYearAndName(name, i, genderCheck);
			
			if(rank < 1001) {
				for (CSVRecord rec : getFileParser(i)) {
					int numBorn = Integer.parseInt(rec.get(2));
					if (rec.get(1).equals(gender)) {
						totalBirths += numBorn;
					}
					if (rec.get(0).equals(name)) {
						occurrences = numBorn;
					}
				}
				
				numberFormat.setMaximumFractionDigits(2);  
				percentage = numberFormat.format((float) occurrences / (float) totalBirths * 100);
				percentage = percentage + "%";
				
				result.add(new OccurrenceRecord(i, rank, occurrences, percentage, totalBirths));
			}		

    	}
    	
    	return result;

    }
    
    /**
     * input data and find the whether there's one year the popularity of name is within 100
     * @param mateName the name want to research the popularity
     * @param agePreference the age preference of the user
     * @param mateGender gender of interest
     * @param userYOB year of birth of user
     * @return a boolean variable indicating whether the two names are within 100 ranks distance
     */  
    public static boolean findPopularity(String mateName, String agePreference, String mateGender, int userYOB) {
    	boolean within100 = false;
    	int genderCheck = mateGender.equals("M") ? 0 : 1;
    	
    	if(agePreference == "younger") {
    		for (int i = userYOB - 1; i >= 1880; i--) {
    			int rank = getRankByYearAndName(mateName, i, genderCheck);
    			if(rank <= 100) within100 = true;
    		}
    	}
    	
    	else {
    		for (int i = userYOB + 1; i <= 2019; i++) {
    			int rank = getRankByYearAndName(mateName, i, genderCheck);
    			if(rank <= 100) within100 = true;
    		}
    	}
    	
    	return within100;
    }

    /**
     * Find the top popular names in a year and in a gender
     * @param year the target year to get the data
     * @param n how many names do you want
     * @param gender the target gender, 0 for Male, 1 for Female
     * @return top N popular names in a list
     */
    public static List<NameRecord> getTopNNamesByYear(int year, int n, int gender) {
        List<NameRecord> result = new ArrayList<>();
        int counter = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                result.add(new NameRecord(rec));
                if (counter == n-1) break;
                else counter++;
            }
        }
        return result;
    }

    /**
     * Find the top Nth popular names in a year and in a gender
     * @param year the target year to get the data
     * @param n rank of the name that you want
     * @param gender the target gender, 0 for Male, 1 for Female
     * @return Nth popular names in a NameRecord
     */
    public static NameRecord getNthNamesByYear(int year, int n, int gender) {
        int counter = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                counter++;
                if (counter < n) continue;
                NameRecord result = new NameRecord(rec);
                return result;
            }
        }
        return null;
    }



    /**
     * get the total number of births of a year and gender
     * @param year the target year to get the data
     * @param gender the target gender, 0 for Male, 1 for Female
     * @return total number of births
     */
    public static int getTotalBirthsByYear(int year, int gender) {
        int counter = 0;
        for (CSVRecord rec : getFileParser(year)) {
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
    public static int getRankByYearAndName(String name, int year, int gender) {
        int rank = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                rank++;
                if(rec.get(0).equals(name)) return rank;
            }
        }
        return 0;
    }

    /**
     * Input a name and get its occurrence in a certain year
     * @param name the name to search
     * @param year the target year
     * @param gender the target gender
     * @return occurrence, 0 for NOT FOUND
     */
    public static int getOccurrenceByYearAndName(String name, int year, int gender) {
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) {
                if(rec.get(0).equals(name)) return Integer.parseInt(rec.get(2));
            }
        }
        return 0;
    }


    /**
     * Input a gender and get the number of records in a certain year
     * @param gender the gender to search
     * @param year the target year
     * @return number of records
     */
    public static int getNumRecordByYearAndGender(int gender, int year) {
        int numRecord = 0;
        for (CSVRecord rec : getFileParser(year)) {
            if (rec.get(1).equals(gender==1 ? "F" : "M")) numRecord++;
        }
        return numRecord;
    }

    /**
     * Work as a proxy to create a NameScorePairObject for the outside class
     * @param name the gender to search
     * @param score Compatible Score of this record
     * @param lengthScore Length Score of this record
     * @param avg_rankScore Average Rank Score of this record
     * @return a produced NameScorePair containing input parameters
     */
    public static NameScorePair produceNameScorePair(String name, double score, double lengthScore, double avg_rankScore) {
        return new NameScorePair(name, score, lengthScore, avg_rankScore);
    }

    /**
     * find a name begin with a certain string
     * @param beginning the beginning string
     * @param year which year
     * @param gender 0 for male and 1 for girl
     * @return a list of names
     */
    public static List<NameRecord> getNameBeginWith(String beginning, int year, int gender) {
        List<NameRecord> result = new ArrayList<>();
        for (CSVRecord rec : getFileParser(year)) {
            if(rec.get(1).equals(gender==0 ? "M" : "F") && rec.get(0).toLowerCase().substring(0, 1).equals(beginning))
                result.add(new NameRecord(rec));
        }
        return result;
    }

    /**
     * find a name begin with a certain string and end with another
     * @param beginning the beginning string
     * @param ending the ending string
     * @param year which year
     * @param gender 0 for male and 1 for girl
     * @return a list of names
     */
    public static List<NameRecord> getNameBeginEndWith(String beginning, String ending, int year, int gender) {
        List<NameRecord> result = new ArrayList<>();
        for (CSVRecord rec : getFileParser(year)) {
            if(rec.get(1).equals(gender==0 ? "M" : "F") &&
                    rec.get(0).toLowerCase().substring(0, beginning.length()).equals(beginning) &&
                    rec.get(0).toLowerCase().substring(rec.get(0).length() - ending.length(), rec.get(0).length()).equals(ending) )
                result.add(new NameRecord(rec));
        }
        return result;
    }

}
