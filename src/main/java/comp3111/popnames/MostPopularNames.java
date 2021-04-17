package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;

import java.util.List;

public class MostPopularNames {
    private List<NameRecord> maleList;
    private List<NameRecord> femaleList;

    public boolean setData(int year, int n) {
        if (n < 1 || n > 10) return false;
        if (year < 1880 || year > 2019) return false;
        maleList = FileReader.getTopNNamesByYear(year, n, 0);
        femaleList = FileReader.getTopNNamesByYear(year, n, 1);
        return true;
    }

    public List<NameRecord> getMaleList() {
        return maleList;
    }

    public List<NameRecord> getFemaleList() {
        return femaleList;
    }
}
