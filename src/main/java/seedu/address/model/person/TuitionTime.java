package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's tuition time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTuitionTime(String)}
 */
public class TuitionTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Tuition time should not be blank and must contain meaningful scheduling information.";

    /*
     * The first character of the tuition time must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code TuitionTime}.
     *
     * @param tuitionTime A valid tuition time.
     */
    public TuitionTime(String tuitionTime) {
        requireNonNull(tuitionTime);
        checkArgument(isValidTuitionTime(tuitionTime), MESSAGE_CONSTRAINTS);
        value = tuitionTime;
    }

    /**
     * Returns true if a given string is a valid tuition time.
     */
    public static boolean isValidTuitionTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TuitionTime)) {
            return false;
        }

        TuitionTime otherTuitionTime = (TuitionTime) other;
        return value.equals(otherTuitionTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
