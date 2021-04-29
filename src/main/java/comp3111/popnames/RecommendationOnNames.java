package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for task4, recommend names
 * @author ZHANG Jiekai
 * @version 1.0
 */
public class RecommendationOnNames {
    /**
     * total number of male births
     */
    private int maleTotalBirth;
    /**
     * total number of female births
     */
    private int femaleTotalBirth;
    /**
     * the recommend boy name
     */
    private NameRecord boyName;
    /**
     * the recommend girl name
     */
    private NameRecord girlName;
    /**
     * the backup recommend boy names, as reference
     */
    private List<NameRecord> boyRecommendList;
    /**
     * the backup recommend girl names, as reference
     */
    private List<NameRecord> girlRecommendList;

    /**
     * get input data from user input and generate the recommended name
     * @param dadName dad's name
     * @param momName mom's name
     * @param dadYob dad's year of birth
     * @param momYob mom's year of birth
     * @param isAlgo1 whether using T4X1 Algorithm
     * @param nameType additional information if use T4X2, the type of the name
     *                 0 for T4X1, 1 for popular, 2 for unique
     */
    public void setData(String dadName, String momName, int dadYob, int momYob, boolean isAlgo1, int nameType) {
        if (dadYob < 1880 || dadYob > 2019) return;
        if (momYob < 1880 || momYob > 2019) return;
        if(boyRecommendList != null) boyRecommendList.clear();
        if(girlRecommendList != null) girlRecommendList.clear();
        maleTotalBirth = FileReader.getTotalBirthsByYear(dadYob, 0);
        femaleTotalBirth = FileReader.getTotalBirthsByYear(momYob, 1);
        if(isAlgo1)
            task4Algo1(dadYob, momYob);
        else
            task4Algo2(dadName, momName, dadYob, momYob, nameType);
    }

    /**
     * T4X1 algorithm, based on the dad and mom's year of birth to recommend
     * the most popular name at that time
     * @param dadYob dad's year of birth
     * @param momYob mom's year of birth
     */
    private void task4Algo1(int dadYob, int momYob) {
        boyRecommendList = FileReader.getTopNNamesByYear(dadYob, 8, 0);
        girlRecommendList = FileReader.getTopNNamesByYear(momYob, 8, 1);
        boyName = boyRecommendList.get(0);
        girlName = girlRecommendList.get(0);
    }

    /**
     * TODO
     * @param dadName dad's name
     * @param momName mom's name
     * @param dadYob dad's year of birth
     * @param momYob mom's year of birth
     * @param nameType 1 for popular 2 for unique
     */
    private void task4Algo2(String dadName, String momName, int dadYob, int momYob, int nameType) {

    }

    /**
     * get recommend name
     * @return recommend boy name
     */
    public NameRecord getBoyName() {
        return boyName;
    }

    /**
     * get recommend name
     * @return recommend girl name
     */
    public NameRecord getGirlName() {
        return girlName;
    }

    /**
     * get recommend names
     * @return recommend boy name list
     */
    public List<NameRecord> getBoyRecommendList() {
        return boyRecommendList;
    }

    /**
     * get recommend names
     * @return recommend girl name list
     */
    public List<NameRecord> getGirlRecommendList() {
        return girlRecommendList;
    }

    /**
     * get the summary to display for T4X1
     * @return summary of the report
     */
    public String getSummaryAlgo1() {
        if(boyName == null) return "Internal Error";
        StringBuilder sb = new StringBuilder();

        sb.append("\n * ");
        sb.append("The Recommend Name for Boy is ").append(boyName.getName()).append(".\n\n * ");
        sb.append("The Recommend Name for Girl is ").append(girlName.getName()).append(".\n\n\n\n");
        sb.append("How it works?\n");
        sb.append("We used our data base to find a popular name at the year of parents' birth.\n");
        sb.append("- Boy's name is from the most popular boy name at dad's year of birth.\n");
        sb.append("- Girl's name is from the most popular girl name at mom's year of birth.\n");
        sb.append("  (You can also view other popular name on the Table and Bar Chart page)\n\n");
        sb.append("Hope you enjoy it and why not try once more with another algorithm ;)\n");

        return sb.toString();
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

    /**
     * get table for setting the table chart
     * @param gender 0 for male, 1 for female
     * @return a map for table view
     */
    public ObservableList<Map> getMapList(int gender) {
        ObservableList<Map> list = FXCollections.<Map>observableArrayList();

        for(NameRecord rec : (gender==0 ? boyRecommendList : girlRecommendList)) {
            Map<String, Object> item = new HashMap<>();
            String name = rec.getName();
            item.put("key1" , rec.getName());
            item.put("key2" , rec.getOccurrence());
            item.put("key3" , String.format("%.5f" ,(float)rec.getOccurrence() / (float)(gender==0 ? maleTotalBirth : femaleTotalBirth) ) );
            list.add(item);
        }

        return list;
    }
}
