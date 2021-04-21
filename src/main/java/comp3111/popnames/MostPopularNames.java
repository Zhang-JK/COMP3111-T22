package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;

import java.text.DecimalFormat;
import java.util.List;

/**
 * This class is for task1, find the most popular names
 * @author ZHANG Jiekai
 * @version 1.1
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
     * total number of male burns in that year
     */
    private  int totalMaleNumber;
    /**
     * total number of female burns in that year
     */
    private  int totalFemaleNumber;
    /**
     * which year is
     */
    private int year;

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
        totalMaleNumber = FileReader.getTotalBirthsByYear(year, 0);
        totalFemaleNumber = FileReader.getTotalBirthsByYear(year, 1);
        this.year = year;
        return true;
    }

    /**
     * get the summary to display
     * @return summary of the report
     */
    public String getSummary() {
        if(maleList.size() == 0) return null;
        DecimalFormat format = new DecimalFormat("0.00000");
        StringBuilder sb = new StringBuilder();
        sb.append("\n* ");
        sb.append(maleList.get(0).getName()).append(" is the most popular name with the number of occurrences of ");
        sb.append(maleList.get(0).getOccurrence()).append(", which represents ");
        sb.append(format.format((float) maleList.get(0).getOccurrence() / totalMaleNumber)).append("% of total male births in ").append(this.year);
        sb.append(".\n\n* ");
        sb.append(femaleList.get(0).getName()).append(" is the most popular name with the number of occurrences of ");
        sb.append(femaleList.get(0).getOccurrence()).append(", which represents ");
        sb.append(format.format((float) femaleList.get(0).getOccurrence() / totalFemaleNumber)).append("% of total female births in ").append(this.year);
        sb.append(".\n");
        return sb.toString();
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
