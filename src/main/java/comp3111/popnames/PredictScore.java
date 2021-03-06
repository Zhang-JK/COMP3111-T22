package comp3111.popnames;

import comp3111.popnames.core.FileReader;
import comp3111.popnames.core.NameRecord;
import comp3111.popnames.core.OccurrenceRecord;

/**
 * This class is for task6, predict the compatible score
 * @author CHENG Yiren
 * @version 1.0
 */
public class PredictScore {
	/**
	 *
	 * @param userName user's name
	 * @param mateName mate's name
	 * @return a string of score
	 */
	public String predict(String userName, String mateName) {
    	String score = "";
		
		if(userName.length() == mateName.length()) {
			score = "100%";
			return score;
		}
    	else {
    		score = "0%";
    		return score;
    	}
	}

	/**
	 *
	 * @param mateName  mate's name
	 * @param agePreference preference for age
	 * @param mateGender the gender of the mate
	 * @param userYOB  user's birth year
	 * @return a string of score
	 */
	public String predict2(String mateName, String agePreference, String mateGender, int userYOB) {
		String score = "0%";
		FileReader tempReader = new FileReader();
		
		if(tempReader.findPopularity(mateName, agePreference, mateGender, userYOB)) score = "100%";
		
		return score;
	}
}
