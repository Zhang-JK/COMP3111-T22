package comp3111.popnames.core;

import java.io.IOException;
import java.lang.String;
import org.apache.commons.csv.*;

public class NameRecord {
    String name;
    int gender;
    int occurrence;

    NameRecord(String name, int gender, int occurrence) {
        this.name = name;
        this.gender = gender;
        this.occurrence = occurrence;
    }

    NameRecord(CSVRecord rec) {
        this.name = rec.get(0);
        this.gender = rec.get(1).equals("M") ? 0 : 1;
        this.occurrence = Integer.parseInt(rec.get(1));
    }

    public int getGender() {
        return gender;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public String getName() {
        return name;
    }

}
