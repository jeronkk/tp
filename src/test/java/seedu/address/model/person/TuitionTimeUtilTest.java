package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TuitionTimeUtilTest {

    @Test
    public void getSortKey_validInput_returnsCorrectSortKey() {
        assertEquals("1_0900", TuitionTimeUtil.getSortKey("Monday, 0900-1100"));
        assertEquals("2_1800", TuitionTimeUtil.getSortKey("Tuesday, 1800-2000"));
        assertEquals("7_2200", TuitionTimeUtil.getSortKey("Sunday, 2200-2300"));
        assertEquals("3_1000", TuitionTimeUtil.getSortKey("WEDNESDAY, 1000-1200")); // case-insensitive
    }

    @Test
    public void getSortKey_invalidFormat_returnsLowercaseOriginal() {
        assertEquals("invalidinput", TuitionTimeUtil.getSortKey("invalidinput"));
        assertEquals("", TuitionTimeUtil.getSortKey("")); // edge case: empty input
        assertEquals("justaday", TuitionTimeUtil.getSortKey("justaday"));
    }

    @Test
    public void getSortKey_unknownDay_returnsWithDefaultOrder8() {
        assertEquals("8_1234", TuitionTimeUtil.getSortKey("Blursday, 1234-5678")); // unknown day
        assertEquals("8_9999", TuitionTimeUtil.getSortKey("Holiday, 9999-1111")); // another unknown day
    }

    @Test
    public void getSortKey_extraSpaces_stillParsesCorrectly() {
        assertEquals("4_1300", TuitionTimeUtil.getSortKey("  Thursday,    1300-1500 "));
    }

    @Test
    public void getSortKey_malformedTimeRange_returnsPartialKey() {
        assertEquals("5_1400", TuitionTimeUtil.getSortKey("Friday, 1400")); // no dash
        assertEquals("5_1400", TuitionTimeUtil.getSortKey("Friday, 1400-")); // incomplete range
    }
}
