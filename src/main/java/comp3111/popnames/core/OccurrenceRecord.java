package comp3111.popnames.core;

import org.apache.commons.csv.CSVRecord;

/**
 * This class is used for storing data with year, rank, percentage, and occurrence.
 * @author CHENG Yiren
 * @version 1.0
 */
public class OccurrenceRecord {
	int year;			//year of this record
	int rank;			//rank of this record
	int occurrence;		//number of occurrence
	String percentage;	//percentage of the total number of names
	int totalBirths;	//total births of the year
	
    /**
     * Create an object by input its year, rank, occurrence, and percentage
     * @param year year of the record
     * @param rank rank of the record
     * @param occurrence number of occurrence of this name
     * @param percentage percentage of the total names of the year
     * @param totalBirths total births of that year
     */
	OccurrenceRecord(int year, int rank, int occurrence, String percentage, int totalBirths){
		this.year = year;
		this.occurrence = occurrence;
		this.rank = rank;
		this.percentage = percentage;
		this.totalBirths = totalBirths;
	}
	
    /**
     * get the rank of this record
     * @return rank of this record
     */	
	public int getRank() {
		return rank;
	}
	
    /**
     * get the year of this record
     * @return year of this record
     */	
	public int getYear() {
		return year;
	}
	
    /**
     * get the occurrence of this record
     * @return occurrence of this record
     */	
	public int getOccurrence() {
		return occurrence;
	}
	
    /**
     * get the percentage of this record
     * @return percentage of this record
     */	
	public String getPercentage() {
		return percentage;
	}
}
