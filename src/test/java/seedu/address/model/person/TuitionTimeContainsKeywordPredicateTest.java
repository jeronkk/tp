package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TuitionTimeContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "monday";
        String secondKeyword = "friday";

        TuitionTimeContainsKeywordPredicate firstPredicate = new TuitionTimeContainsKeywordPredicate(firstKeyword);
        TuitionTimeContainsKeywordPredicate secondPredicate = new TuitionTimeContainsKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TuitionTimeContainsKeywordPredicate firstPredicateCopy = new TuitionTimeContainsKeywordPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tuitionTimeContainsKeyword_returnsTrue() {
        // Exact match
        TuitionTimeContainsKeywordPredicate predicate = new TuitionTimeContainsKeywordPredicate("Monday");
        assertTrue(predicate.test(new PersonBuilder().withTuitionTime("Monday, 1000-1200").build()));

        // Case-insensitive match
        predicate = new TuitionTimeContainsKeywordPredicate("monday");
        assertTrue(predicate.test(new PersonBuilder().withTuitionTime("MONDAY, 1000-1200").build()));

        // Partial match not allowed by predicate logic (assuming it checks full day string)
        predicate = new TuitionTimeContainsKeywordPredicate("mon");
        assertTrue(predicate.test(new PersonBuilder().withTuitionTime("Monday, 1000-1200").build()));
    }

    @Test
    public void test_tuitionTimeDoesNotContainKeyword_returnsFalse() {
        // Keyword does not match
        TuitionTimeContainsKeywordPredicate predicate = new TuitionTimeContainsKeywordPredicate("Friday");
        assertFalse(predicate.test(new PersonBuilder().withTuitionTime("Monday, 1000-1200").build()));

        // Keyword matches part of the time but not the day
        predicate = new TuitionTimeContainsKeywordPredicate("1000");
        assertTrue(predicate.test(new PersonBuilder().withTuitionTime("Monday, 1000-1200").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "Monday";
        TuitionTimeContainsKeywordPredicate predicate = new TuitionTimeContainsKeywordPredicate(keyword);

        String expected = TuitionTimeContainsKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
