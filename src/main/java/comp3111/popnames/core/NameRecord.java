package comp3111.popnames.core;

import org.apache.commons.csv.CSVRecord;

/**
 * This class is used for storing csv data.
 * @author ZHANG Jiekai
 * @version 1.0
 */
public class NameRecord {
    String name;        // name of this record
    int gender;         // the gender, 0 for Male, 1 for Female
    int occurrence;     // number of occurrence

    /**
     * Create an object by input its name, gender and occurrence
     * @param name name of the record
     * @param gender gender of the record
     * @param occurrence number of occurrence of this name
     */
    NameRecord(String name, int gender, int occurrence) {
        this.name = name;
        this.gender = gender;
        this.occurrence = occurrence;
    }

    /**
     * Create an object directly from the CSV record
     * @param rec CSV Record in the data files
     */
    NameRecord(CSVRecord rec) {
        this.name = rec.get(0);
        this.gender = rec.get(1).equals("M") ? 0 : 1;
        this.occurrence = Integer.parseInt(rec.get(1));
    }

    /**
     * get the gender of this record
     * @return gender of this record
     */
    public int getGender() {
        return gender;
    }

    /**
     * get the number of occurrence of this name
     * @return number of occurrence
     */
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * get the name of this record
     * @return name
     */
    public String getName() {
        return name;
    }

}
