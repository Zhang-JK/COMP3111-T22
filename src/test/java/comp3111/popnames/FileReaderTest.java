package comp3111.popnames;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import comp3111.popnames.core.*;

import java.util.*;

public class FileReaderTest {

    @Test
    public void testGetFileByYear() {
        List<NameRecord> recordList = FileReader.getFileByYear(2019);
        String name = recordList.get(0).getName();
        int gender = recordList.get(0).getGender();
        int times = recordList.get(0).getOccurrence();
        assertEquals(name, "Olivia");
        assertEquals(gender, 1);
        assertEquals(times, 18451);
    }

    @Test
    public void testGetTopNNamesByYear1() {
        List<NameRecord> recordList = FileReader.getTopNNamesByYear(2019, 5, 0);
        String name = recordList.get(1).getName();
        int gender = recordList.get(1).getGender();
        int times = recordList.get(1).getOccurrence();
        assertEquals(name, "Noah");
        assertEquals(gender, 0);
        assertEquals(times, 19048);
    }

    @Test
    public void testGetTopNNamesByYear2() {
        List<NameRecord> recordList = FileReader.getTopNNamesByYear(2019, 3, 1);
        String name = recordList.get(2).getName();
        int gender = recordList.get(2).getGender();
        int times = recordList.get(2).getOccurrence();
        assertEquals(name, "Ava");
        assertEquals(gender, 1);
        assertEquals(times, 14440);
    }
    
    @Test
    public void testGetNthNamesByYear1() {
        NameRecord record = FileReader.getNthNamesByYear(2019, 9, 0);
        String name = record.getName();
        int gender = record.getGender();
        int times = record.getOccurrence();
        assertEquals(name, "Mason");
        assertEquals(gender, 0);
        assertEquals(times, 11408);
    }
    
    @Test
    public void testGetNthNamesByYear2() {
        NameRecord record = FileReader.getNthNamesByYear(2019, 7, 1);
        String name = record.getName();
        int gender = record.getGender();
        int times = record.getOccurrence();
        assertEquals(name, "Amelia");
        assertEquals(gender, 1);
        assertEquals(times, 12862);
    }


    @Test
    public void testGetTotalBirthsByYear1() {
        int totalMale = FileReader.getTotalBirthsByYear(2019, 0);
        assertEquals(totalMale, 1779948);
    }

    @Test
    public void testGetTotalBirthsByYear2() {
        int totalMale = FileReader.getTotalBirthsByYear(2019, 1);
        assertEquals(totalMale, 1665373);
    }

    @Test
    public void testGetRankByYearAndName1() {
        int rank = FileReader.getRankByYearAndName("Emma", 2019, 1);
        assertEquals(rank, 2);
    }

    @Test
    public void testGetRankByYearAndName2() {
        int rank = FileReader.getRankByYearAndName("Noah", 2019, 0);
        assertEquals(rank, 2);
    }

    @Test
    public void testGetRankByYearAndName3() {
        int rank = FileReader.getRankByYearAndName("zhangjiekai", 2019, 0);
        assertEquals(rank, 0);
    }

}
