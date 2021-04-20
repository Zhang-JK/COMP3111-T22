package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;

import java.util.List;

public class RecommendationOnNames {
    private NameRecord boyName;
    private NameRecord girlName;
    private List<NameRecord> boyRecommendList;
    private List<NameRecord> girlRecommendList;

    public boolean setData(String dadName, String momName, int dadYob, int momYob, boolean isAlgo1, int nameType) {
        if (dadYob < 1880 || dadYob > 2019) return false;
        if (momYob < 1880 || momYob > 2019) return false;
        if(isAlgo1)
            task4Algo1(dadYob, momYob);
        else
            task4Algo2(dadName, momName, dadYob, momYob, nameType);
        return true;
    }

    private void task4Algo1(int dadYob, int momYob) {
        boyRecommendList = FileReader.getTopNNamesByYear(dadYob, 8, 0);
        girlRecommendList = FileReader.getTopNNamesByYear(momYob, 8, 1);
        boyName = boyRecommendList.get(0);
        girlName = girlRecommendList.get(0);
    }

    private void task4Algo2(String dadName, String momName, int dadYob, int momYob, int nameTpye) {

    }

    public NameRecord getBoyName() {
        return boyName;
    }

    public NameRecord getGirlName() {
        return girlName;
    }

    public List<NameRecord> getBoyRecommendList() {
        return boyRecommendList;
    }

    public List<NameRecord> getGirlRecommendList() {
        return girlRecommendList;
    }

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
        sb.append("  (You can also view other popular name on the Bar Chart page)\n\n");
        sb.append("Hope you enjoy it and why not try once more with another algorithm ;)\n");

        return sb.toString();
    }

    public String getSummaryAlgo2() {
        StringBuilder sb = new StringBuilder();
        //TODO
        return sb.toString();
    }
}
