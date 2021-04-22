package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import comp3111.popnames.core.NameScorePair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.*;

/**
 * This class is for task5, predict name for compatible pairs
 * @author LIANG Houdong
 * @version 1.0
 */
public class NameforCompatiblePairs {
    /**
     * the NameRecord of soulmate
     */
    private NameRecord pairName;

    /**
     * the backup soulmate NameRecords, as reference
     */
    private List<NameRecord> pairNameList;

    /**
     * the NameScorePair of soulmate
     */
    private NameScorePair pairNameScore;

    /**
     * the backup soulmate NameScorePairs, as reference
     */
    private List<NameScorePair>  nameScorePairList;

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
     * @return whether get data is successful, the only case it is unsuccessful is when iName is not found in Database
     */
    public boolean setData(String iName, String iGender,int iYOB, String iGenderMate, String iPreference, boolean isAlgo1) {
        boolean algo2Check = true;
        this.iYOB = iYOB;
        this.iGenderMate = iGenderMate;
        if(isAlgo1)
            task5Algo1(iGenderMate, iYOB);
        else {
            algo2Check = task5Algo2(iName, iGender, iYOB, iGenderMate, iPreference);
            if(!algo2Check) return false;
        }
        return true;
    }

    /**
     * T5X1 algorithm, based on the user's birth year and gender preference
     * @param iGenderMate the gender of the soulmate that user wants to match
     * @param iYOB user's year of birth
     */
    private void task5Algo1(String iGenderMate, int iYOB) {
        pairNameList = FileReader.getTopNNamesByYear(iYOB, 8, (iGenderMate.equals("M") ? 0 : 1) );
        pairName = pairNameList.get(0);
    }

    /**
     * T5X2 algorithm, compute compatible scores for other names
     * @param iName user's name
     * @param iGender user's gender
     * @param iYOB user's year of birth
     * @param iGenderMate the gender of the soulmate that user wants to match
     * @param iPreference the age preference of the soulmate that user wants to match
     * @return whether get data is successful
     */
    private boolean task5Algo2(String iName, String iGender,int iYOB, String iGenderMate, String iPreference) {
        int preferenceInt = iPreference.equals("Younger")?0:1;
        int genderInt = (iGender.equals("M")?0:1);
        int genderMateInt = (iGenderMate.equals("M")?0:1);

        //check whether iName can be found in database
        int rankAtBirthYear = FileReader.getRankByYearAndName(iName, iYOB, genderInt);
        if(rankAtBirthYear == 0) return false;

        //based on iAgePreference, set the corresponding lower and upper bounds for years
        int lowerYearBound;
        int upperYearBound;
        if(preferenceInt == 0){
            lowerYearBound = iYOB;
            upperYearBound = (iYOB+10 > 2019)? 2019:iYOB+10;}
        else{
            lowerYearBound = (iYOB-10 < 1880)? 1880:iYOB-10;
            upperYearBound = iYOB;
        }

        ArrayList<NameRecord> scoreComputeList = new ArrayList<>();
        nameScorePairList  = new ArrayList<>();

        //lock the range of names we want to compute score for
        for(int i = lowerYearBound; i<= upperYearBound; i++){

            int rankAtIYear = FileReader.getRankByYearAndName(iName, i, genderInt);
            int totalNumRecord = FileReader.getNumRecordByYearAndGender(genderInt, i);
            int lowerRankBound = (rankAtIYear-20 < 1)? 1 : rankAtIYear-20;
            int upperRankBound = (rankAtIYear+20 > totalNumRecord)? totalNumRecord : rankAtIYear+20;
            for(int j = lowerRankBound; j <= upperRankBound; j++){
                if( j == rankAtIYear && genderInt == genderMateInt ) continue;;
                NameRecord pairRecord = FileReader.getNthNamesByYear(i, j, genderMateInt);
                String pairName = pairRecord.getName();
                boolean repeated = false;
                for(NameRecord rec: scoreComputeList){
                    if(rec.getName().equals(pairName)){
                        repeated = true; break;
                    }
                }
                if(!repeated) scoreComputeList.add(pairRecord);
            }
        }
        //at this point, scoreComputeList should contain all NameRecords that appears at least once within 20 ranks from iName

        for(NameRecord rec : scoreComputeList){
            //first we get the name we want to compute the score
            String pairName = rec.getName();
            //calculate the length score
            int lengthDiff = Math.abs(pairName.length() - iName.length());
            double lengthScore = (lengthDiff == 0)?1:((lengthDiff == 1)?0.7:( (lengthDiff == 2)?0.5:( (lengthDiff == 3)?0.2:0 ) ));

            //calculate the average rank score
            double rankScoreSum = 0;
            for(int j = lowerYearBound; j <= upperYearBound; j++ ){
                int iNameRank = FileReader.getRankByYearAndName(iName,j,genderInt);
                int iPairRank = FileReader.getRankByYearAndName(pairName,j,genderMateInt);
                int rankDiff = Math.abs(iNameRank-iPairRank);
                if(rankDiff <= 20) rankScoreSum += 100 - Math.pow((double)Math.abs(iNameRank-iPairRank)/2,2);
            }
            double rankScore_avg = rankScoreSum/(upperYearBound-lowerYearBound+1);

            //generate a NameScorePair and add it into the list
            NameScorePair new_pair = FileReader.produceNameScorePair(pairName, lengthScore*rankScore_avg, lengthScore, rankScore_avg);
            nameScorePairList.add(new_pair);
        }

        //sort the nameScorePairList by their score and put the first element into pairNameScore
        Collections.sort(nameScorePairList, Collections.reverseOrder());
        pairNameScore = nameScorePairList.get(0);
        return true;
    }

    /**
     * get predicted name for compatible name for Algorithm T5X1
     * @return recommend pair name
     */
    public NameRecord getPairName() {
        return pairName;
    }

    /**
     * get predicted name for compatible name for Algorithm T5X1
     * @return recommend pair name list
     */
    public NameScorePair getNameScorePair() {
        return pairNameScore;
    }

    /**
     * get predicted name for compatible name for Algorithm T5X2
     * @return recommend pair name list
     */
    public List<NameRecord> getPairNameList() {
        return pairNameList;
    }

    /**
     * get predicted name for compatible name for Algorithm T5X2
     * @return recommend pair name
     */
    public List<NameScorePair> getNameScorePairList() {
        return nameScorePairList;
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
        sb.append("  (You can view other popular names in that year on the Table page and Bar Chart page)\n");
        sb.append("  (You can view the trend of giving name Jacob to new babies on the Line Chart page)\n\n");
        sb.append("Hope you enjoy it and why not try once more with another algorithm :)\n");

        return sb.toString();
    }

    /**
     * generate an ObservableListList of Map data for Algorithm T5X1
     *
     * @return an ObservableListList of Map data for generating the table
     */
    public ObservableList<Map> getMapListAlgo1() {

        DecimalFormat format = new DecimalFormat("0.00000");

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
     * generate an ObservableListList of Map data for Algorithm T5X2
     *
     * @return an ObservableListList of Map data for generating the table
     */
    public ObservableList<Map> getMapListAlgo2() {

        DecimalFormat format = new DecimalFormat("0.00000");

        ObservableList<Map> list = FXCollections.<Map>observableArrayList();
        int totalBirth = FileReader.getTotalBirthsByYear(iYOB, (iGenderMate.equals("M")?0:1) );

        int count = 0;
        for(NameScorePair rec : nameScorePairList){
            Map<String, String> item = new HashMap<>();
            item.put("key1" , rec.getName());
            item.put("key2" , format.format(rec.getScore()));
            item.put("key3" , String.valueOf(rec.getLengthScore()));
            item.put("key4" , format.format(rec.getAvgRankScore()));
            list.add(item);
            count++;
            if(count == 10) break;
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
        for(int i = -3; i <= 3; i++){
            if(iYOB+i < 1880 || iYOB+i > 2019) continue;
            occurrenceList.add( FileReader.getOccurrenceByYearAndName( pairName.getName(), iYOB+i, (iGenderMate.equals("M")?0:1) ) );
        }
        return occurrenceList;
    }

    /**
     * generate a List of data for x Axis of Line Chart for Algorithm T5X1
     *
     * @return a List of data for generating the Line Chart's x Axis
     */
    public List<String> getXListAlgo1() {
        List<String> yearList = new ArrayList<>();
        for(int i = -3; i <= 3; i++){
            if(iYOB+i < 1880 || iYOB+i > 2019) continue;
            yearList.add(String.valueOf(iYOB+i));
        }
        return yearList;
    }


    /**
     * get the summary to display for T5X2
     * @return summary of the report
     */
    public String getSummaryAlgo2() {
        DecimalFormat format = new DecimalFormat("0.00000");
        StringBuilder sb = new StringBuilder();

        sb.append("\n * ");
        sb.append("The Predicted Soulmate Name is ").append(pairNameScore.getName()).append(".\n");
        sb.append("The Compatible score of ").append(pairNameScore.getName()).append(" is ").append(format.format(pairNameScore.getScore())).append("(out of 100)").append(".\n\n\n\n");
        sb.append("How it works?\n");
        sb.append("- We calculate compatible score between user's name and other names. The predicted soulmate name is the name that gets the highest compatible score.\n\n");
        sb.append("-- How is the compatible score computed?\n");
        sb.append(" Compatible score = (length score) * (average popularity score during period P) \n\n");
        sb.append(" Length score: if two names have a smaller length difference, then the length score is higher \n");
        sb.append("               length score = 1   if length difference = 0 \n");
        sb.append("               length score = 0.7 if length difference = 1 \n");
        sb.append("               length score = 0.5 if length difference = 2 \n");
        sb.append("               length score = 0.2 if length difference = 3 \n");
        sb.append("               length score = 0.0 if length difference > 3 \n\n");
        sb.append(" Popularity score: if two names have a smaller rank difference, which means they are similarly popular, then the popularity score is higher.\n");
        sb.append("                   popularity score = 100 - (x/2)^2 if rank difference = x, x ∈ [0,20] \n");
        sb.append("                   popularity score = 0 otherwise \n\n");
        sb.append(" Period P =  [iYOB-10, iYOB] if age preference is \"Older\"\n");
        sb.append(" Period P =  [iYOB, iYOB+10] if age preference is \"Younger\"\n");
        sb.append(" We do not consider data out of the period because it is less likely for two people to become soulmate because of the generation gap.");

        return sb.toString();
    }


}
