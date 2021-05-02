package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import comp3111.popnames.core.OccurrenceRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for task3, find the popularity of a name in the input period
 * @author CHENG Yiren
 * @version 1.0
 */
public class PopularityOfNames {
	String report = "";
	private List<OccurrenceRecord> popularityList;
	NumberFormat numberFormat = NumberFormat.getInstance();
	int maxRank = 0;
	int maxYear = 0;
	int maxOccurrence = 0;
	String percentage;
	
    /**
     * input data and find the occurrence list
     * @param year1 target starting year
     * @param year2 target ending year
     * @param name name of interest
     * @param gender gender of the name want to research
     */
	public void setData(int year1, int year2, String name, String gender) {
		popularityList = FileReader.getFileOfYearAndName(year1, year2, name, gender);
	}
	
    /**
     * get the list of the name occurrences in the year period
     * @return list of occurrences
     */
	public List<OccurrenceRecord> getList(){
		return popularityList;
	}
	
    /**
     * find the year the popularity of the name is the highest and set the data
     */
	public void findMaxYear() {
		OccurrenceRecord firstObject = popularityList.get(0);
		maxRank = firstObject.getRank();
		maxYear = firstObject.getYear();
		maxOccurrence = firstObject.getOccurrence();
		percentage = firstObject.getPercentage();

		for(OccurrenceRecord rec : popularityList) {
			if(maxRank > rec.getRank()) {
				maxRank = rec.getRank();
				maxYear = rec.getYear();
				maxOccurrence = rec.getOccurrence();
				percentage = rec.getPercentage();
			}
		}
	}
	
    /**
     * @return an ObservableListList of Map data for generating the table
     *
     */
    public ObservableList<Map> getMapList() {

        DecimalFormat format = new DecimalFormat("0.000");

        ObservableList<Map> list = FXCollections.<Map>observableArrayList();

        for(OccurrenceRecord rec : this.popularityList){
            Map<String, Object> item = new HashMap<>();
            int year = rec.getYear();
            int rank = rec.getRank();
            int occurrence = rec.getOccurrence();
            String percentage = rec.getPercentage();
            
            item.put("key1" , year);
            item.put("key2" , rank);
            item.put("key3" , occurrence);
            item.put("key4" , percentage);
            list.add(item);
        }
        return list;
    }
	
    /**
     * get the rank
     * @return rank
     */	
	public int getRank() {
		return maxRank;
	}
	
    /**
     * get the year
     * @return year 
     */	
	public int getYear() {
		return maxYear;
	}
	
    /**
     * get the occurrence
     * @return occurrence
     */	
	public int getOccurrence() {
		return maxOccurrence;
	}
	
    /**
     * get the percentage
     * @return percentage
     */	
	public String getPercentage() {
		return percentage;
	}
}
