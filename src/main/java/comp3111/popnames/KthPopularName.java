package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.*;

/**
 * This class is for task2, find the k-th most popular name
 * @author LIANG Houdong
 * @version 1.0
 */

public class KthPopularName {

    /**
     * record for k-th most popular names (raw data)
     */
    private List<NameRecord> rawList;
    /**
     * record for k-th most popular names (combined and sorted)
     */
    private List<NameRecord> modifiedList;

    private int totalNameOccurrence;
    private int year1;
    private int year2;
    private int k;
    private String gender;


    /**
     * input data and find the name list
     * @param year1 starting year
     * @param year2 ending year
     * @param k k-th names in popularity
     * @param gender targeted gender
     * @return if data is valid
     */
    public boolean setData(int year1, int year2, int k, String gender) {
        if (year1 < 1880 || year1 > 2019) return false;
        if (year2 < 1880 || year2 > 2019) return false;
        if (year2 < year1) return false;
        this.year1 = year1;
        this.year2 = year2;
        this.k = k;
        this.gender = gender;
        rawList = new ArrayList<NameRecord>();
        modifiedList = new ArrayList<NameRecord>();
        for(int i = year1; i <= year2; i++){
            rawList.add( FileReader.getNthNamesByYear(i, k, (gender.equals("M")? 0 : 1) ) );
        }
        totalNameOccurrence = 0;
        for(NameRecord rec : rawList){
            totalNameOccurrence += rec.getOccurrence();
        }
        CombineEntries(rawList,modifiedList);
        Collections.sort(modifiedList, Collections.reverseOrder());
        return true;
    }

    /**
     * input a list of raw data and return a modified list with combined and sorted data
     * @param rawData a list of NameRecord with original data
     * @param rawData a list of NameRecord with modified data
     */

    public static void CombineEntries( List<NameRecord> rawData,  List<NameRecord> modifiedData  ) {
        for (int i = 0; i < rawData.size(); i++) {
            boolean found = false;
            String newName = rawData.get(i).getName();
            for (int j = 0; j < modifiedData.size(); j++) {
                if ( modifiedData.get(j).getName().equals(newName) ) {
                    int sumOccurence = modifiedData.get(j).getOccurrence() + rawData.get(i).getOccurrence();
                    modifiedData.get(j).setOccurrence(sumOccurence);
                    found = true;
                }
            }
            if (!found) {
                modifiedData.add(rawData.get(i));
            }
        }
    }

    /**
     * get the summary to display
     * @return summary of the report
     */
    public String getSummary() {
        if(rawList.size() == 0) return null;
        DecimalFormat format = new DecimalFormat("0.00000");
        StringBuilder sb = new StringBuilder();
        String targetName = modifiedList.get(0).getName();
        int time = 0;
        for (NameRecord rec: rawList){
            if(rec.getName().equals(targetName)) time++;
        }
        sb.append("\n* ");
        sb.append(modifiedList.get(0).getName()).append(" has hold the ");sb.append(k);
        sb.append("-th rank most often for a total of "); sb.append(time);
        sb.append(" times among names registered for baby ");
        sb.append( (gender.equals("M") ? "boys" :"girls") );sb.append(" born in the period from ");
        sb.append(year1);sb.append(" and ");sb.append(year2);
        sb.append(". The total number of occurrences of ");
        sb.append(targetName);
        sb.append(" is ");sb.append(modifiedList.get(0).getOccurrence());sb.append(" which represents ");
        sb.append(format.format( (float) 100*modifiedList.get(0).getOccurrence()/totalNameOccurrence ));
        sb.append("% of total female births at the ");
        sb.append(k); sb.append("-th rank in the period from ");
        sb.append(year1); sb.append(" and ");sb.append(year2);
        sb.append(".\n\n");
        sb.append("(There may be other names that holds the ");sb.append(k);
        sb.append("-th rank for "); sb.append(time);
        sb.append(" times. Among all these names, "); sb.append(targetName);
        sb.append(" has the largest total number of occurrences over the period.)");
        return sb.toString();
    }

    /**
     * return an ObservableListList of Map data for generating the table
     *
     */

    public ObservableList<Map> getMapList() {

        DecimalFormat format = new DecimalFormat("0.000");

        ObservableList<Map> list = FXCollections.<Map>observableArrayList();

        for(NameRecord rec : modifiedList){
            Map<String, Object> item = new HashMap<>();
            String name = rec.getName();
            int frequency = 0;
            for(NameRecord rec2 : rawList){
                if(rec2.getName().equals(name)) frequency++;
            }
            item.put("key1" , name);
            item.put("key2" , frequency);
            item.put("key3" , rec.getOccurrence());
            item.put("key4" , format.format((float)100*rec.getOccurrence()/totalNameOccurrence)+"%");
            list.add(item);
        }
        return list;
    }

    /**
     * get the rawList
     * @return k-th most popular name list with raw data
     */
    public List<NameRecord> getRawList() {
        return rawList;
    }

    /**
     * get the modifiedList
     * @return k-th most popular name list with combined and sorted data
     */
    public List<NameRecord> getModifiedList() {
        return modifiedList;
    }

}
