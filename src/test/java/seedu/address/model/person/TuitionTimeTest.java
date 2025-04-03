package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TuitionTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TuitionTime(null));
    }

    @Test
    public void constructor_invalidTuitionTime_throwsIllegalArgumentException() {
        String invalidTuitionTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TuitionTime(invalidTuitionTime));
    }

    @Test
    public void isValidTuitionTime() {
        // null tuitionTime
        assertThrows(NullPointerException.class, () -> TuitionTime.isValidTuitionTime(null));

        // invalid tuitionTime
        assertFalse(TuitionTime.isValidTuitionTime("")); // empty string
        assertFalse(TuitionTime.isValidTuitionTime(" ")); // spaces only
        assertFalse(TuitionTime.isValidTuitionTime("^")); // only non-alphanumeric characters
        assertFalse(TuitionTime.isValidTuitionTime("peter*")); // contains non-alphanumeric characters
        assertFalse(TuitionTime.isValidTuitionTime("Monday, 2100-2500"));
        assertFalse(TuitionTime.isValidTuitionTime("Monday, 2100-1800"));

        // valid tuitionTime
        assertTrue(TuitionTime.isValidTuitionTime("Monday, 1800-2100"));
    }

    @Test
    public void equals() {
        TuitionTime tuitionTime = new TuitionTime("Monday, 1800-2100");

        // same values -> returns true
        assertTrue(tuitionTime.equals(new TuitionTime("Monday, 1800-2100")));

        // same object -> returns true
        assertTrue(tuitionTime.equals(tuitionTime));

        // null -> returns false
        assertFalse(tuitionTime.equals(null));

        // different types -> returns false
        assertFalse(tuitionTime.equals(5.0f));

        // different values -> returns false
        assertFalse(tuitionTime.equals(new TuitionTime("Tuesday, 1800-2100")));
    }
}
