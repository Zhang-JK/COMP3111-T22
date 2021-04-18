package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        rawList = new ArrayList<NameRecord>();
        modifiedList = new ArrayList<NameRecord>();
        for(int i = year1; i <= year2; i++){
            rawList.add( FileReader.getNthNamesByYear(i, k, (gender.equals("M")? 0 : 1) ) );
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
