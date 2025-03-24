package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests whether a {@link Person}'s tuition time contains a specified keyword.
 * This predicate is typically used for filtering or searching {@code Person} objects
 * based on their tuition time descriptions.
 */
public class TuitionTimeContainsKeywordPredicate implements Predicate<Person> {

    private final String keyword;

    public TuitionTimeContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Tests whether the given {@code Person}'s tuition time contains the specified keyword.
     *
     * @param person The person whose tuition time will be tested.
     * @return {@code true} if the tuition time contains the keyword, {@code false} otherwise.
     */
    @Override
    public boolean test(Person person) {
        return person.getTuitionTime().match(this.keyword);
    }

    /**
     * Checks whether another object is equal to this predicate.
     * Two {@code TuitionTimeContainsKeywordPredicate} objects are considered equal
     * if they have the same keyword.
     *
     * @param other The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TuitionTimeContainsKeywordPredicate)) {
            return false;
        }

        TuitionTimeContainsKeywordPredicate otherTuitionTimeContainsKeywordPredicate =
                (TuitionTimeContainsKeywordPredicate) other;
        return keyword.equals(otherTuitionTimeContainsKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}


