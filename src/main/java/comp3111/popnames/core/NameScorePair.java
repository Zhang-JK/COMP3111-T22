package comp3111.popnames.core;
/**
 * This class is used for storing a pair of name and score for task #5.
 * @author LIANG Houdong
 * @version 1.0
 */
public class NameScorePair implements Comparable<NameScorePair> {

    String name; // Name of this record
    double score; // Compatible Score of this record
    double lengthScore; // Length Score of this record
    double avg_rankScore; // Average Rank Score of this record

    /**
     * Create an object by input its name and score
     * @param name Name of this record
     * @param score Compatible Score of this record
     * @param lengthScore Length Score of this record
     * @param avg_rankScore Average Rank Score of this record
     */
    NameScorePair(String name, double score, double lengthScore, double avg_rankScore){
        this.name = name;
        this.score = score;
        this.lengthScore = lengthScore;
        this.avg_rankScore = avg_rankScore;
    }


    /**
     * get the name of this record
     * @return Name of this record
     */
    public String getName() {
        return name;
    }

    /**
     * get the compatible score of this record
     * @return compatible score of this record
     */
    public double getScore() {
        return score;
    }

    /**
     * get the length score of this record
     * @return length score of this record
     */
    public double getLengthScore() {
        return lengthScore;
    }

    /**
     * get the average rank score of this record
     * @return  average rank score of this record
     */
    public double getAvgRankScore() {
        return avg_rankScore;
    }

    /**
     * function that implements the surface, define the comparision criterion for two NameScorePairs
     */
    public int compareTo(NameScorePair name2){
        if (score == name2.score) return 0;
        else if (score > name2.score) return 1;
        else return -1;
    }

}
