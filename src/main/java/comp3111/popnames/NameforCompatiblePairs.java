package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for task5, predict name for compatible pairs
 * @author LIANG Houdong
 * @version 1.0
 */
public class NameforCompatiblePairs {
    /**
     * the name of soulmate
     */
    private NameRecord pairName;

    /**
     * the backup soulmate names, as reference
     */
    private List<NameRecord> pairNameList;

    /**
     * record for input iYOB
     */
    private int iYOB;

    /**
     * record for input iGenderMate
     */
    private String iGenderMate;


    /**
     * get input data from user input and generate the recommended name
     * @param iName user's name
     * @param iGender user's gender
     * @param iYOB user's year of birth
     * @param iGenderMate the gender of the soulmate that user wants to match
     * @param iPreference the age preference of the soulmate that user wants to match
     * @param isAlgo1 whether using T4X1 Algorithm
     * @return whether get data is successful
     */
    public boolean setData(String iName, String iGender,int iYOB, String iGenderMate, String iPreference, boolean isAlgo1) {
        if (iYOB < 1880 || iYOB > 2019) return false;
        this.iYOB = iYOB; this.iGenderMate = iGenderMate;
        if(isAlgo1)
            task5Algo1(iGenderMate, iYOB);
        else
            task5Algo2(iName, iGender, iYOB, iGenderMate, iPreference);
        return true;
    }

    /**
     * T5X1 algorithm, based on the user's birth year and gender preference
     * the most popular name at that time
     * @param iGenderMate the gender of the soulmate that user wants to match
     * @param iYOB user's year of birth
     */
    private void task5Algo1(String iGenderMate, int iYOB) {
        pairNameList = FileReader.getTopNNamesByYear(iYOB, 8, (iGenderMate.equals("M") ? 0 : 1) );
        pairName = pairNameList.get(0);
    }

    /**
     * TODO
     * @param iName user's name
     * @param iGender user's gender
     * @param iYOB user's year of birth
     * @param iGenderMate the gender of the soulmate that user wants to match
     * @param iPreference the age preference of the soulmate that user wants to match
     * @return whether get data is successful
     */
    private void task5Algo2(String iName, String iGender,int iYOB, String iGenderMate, String iPreference) {

    }

    /**
     * get predicted name for compatible name
     * @return recommend pair name
     */
    public NameRecord getPairName() {
        return pairName;
    }

    /**
     * get predicted name for compatible name
     * @return recommend pair name list
     */
    public List<NameRecord> getPairNameList() {
        return pairNameList;
    }


    /**
     * get the summary to display for T5X1
     * @return summary of the report
     */
    public String getSummaryAlgo1() {
        if(pairName == null) return "Internal Error";
        StringBuilder sb = new StringBuilder();

        sb.append("\n * ");
        sb.append("The Predicted Soulmate Name is ").append(pairName.getName()).append(".\n\n\n\n");
        sb.append("How it works?\n");
        sb.append("- The soulmate's name is the most popular name at user's year of birth.\n");
        sb.append("  (You can also view other popular names on the Bar Chart page)\n\n");
        sb.append("Hope you enjoy it and why not try once more with another algorithm :)\n");

        return sb.toString();
    }

    /**
     * generate an ObservableListList of Map data for Algorithm T5X1
     *
     * @return an ObservableListList of Map data for generating the table
     */
    public ObservableList<Map> getMapListAlgo1() {

        DecimalFormat format = new DecimalFormat("0.000");

        ObservableList<Map> list = FXCollections.<Map>observableArrayList();
        int totalBirth = FileReader.getTotalBirthsByYear(iYOB, (iGenderMate.equals("M")?0:1) );

        for(NameRecord rec : pairNameList){
            Map<String, String> item = new HashMap<>();
            item.put("key1" , rec.getName());
            item.put("key2" , String.valueOf(rec.getOccurrence()));
            item.put("key3" , format.format((float)100*rec.getOccurrence()/totalBirth) + "%");
            list.add(item);
        }

        return list;
    }

    /**
     * generate a List of data for y Axis of Line Chart for Algorithm T5X1
     *
     * @return a List of data for generating the Line Chart's y Axis
     */
    public List<Integer> getYListAlgo1() {
        List<Integer> occurrenceList = new ArrayList<>();
        for(int i = -3; i <= 3; i++)
            occurrenceList.add( FileReader.getOccurrenceByYearAndName( pairName.getName(), iYOB+i, (iGenderMate.equals("M")?0:1) ) );
        return occurrenceList;
    }

    /**
     * generate a List of data for x Axis of Line Chart for Algorithm T5X1
     *
     * @return a List of data for generating the Line Chart's x Axis
     */
    public List<String> getXListAlgo1() {
        List<String> yearList = new ArrayList<>();
        for(int i = -3; i <= 3; i++)
            yearList.add(String.valueOf(iYOB+i));
        return yearList;
    }


    /**
     * get the summary to display for T4X2
     * @return summary of the report
     */
    public String getSummaryAlgo2() {
        StringBuilder sb = new StringBuilder();
        //TODO
        return sb.toString();
    }


}
