package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;

import java.util.List;

/**
 * This class is for task1, find the most popular names
 * @author ZHANG Jiekai
 * @version 1.0
 */
public class MostPopularNames {
    /**
     * record for male's most popular names
     */
    private List<NameRecord> maleList;
    /**
     * record for female's most popular names
     */
    private List<NameRecord> femaleList;

    /**
     * input data and find the name list
     * @param year target year
     * @param n top n names in popularity
     * @return if data is valid
     */
    public boolean setData(int year, int n) {
        if (n < 1 || n > 10) return false;
        if (year < 1880 || year > 2019) return false;
        maleList = FileReader.getTopNNamesByYear(year, n, 0);
        femaleList = FileReader.getTopNNamesByYear(year, n, 1);
        return true;
    }

    /**
     * get male's most popular names
     * @return male's most popular name list
     */
    public List<NameRecord> getMaleList() {
        return maleList;
    }

    /**
     * get female's most popular names
     * @return female's most popular name list
     */
    public List<NameRecord> getFemaleList() {
        return femaleList;
    }
}
