package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Tutor's tuition time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTuitionTime(String)}
 */
public class TuitionTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Tuition time must be in the format: 'DayOfWeek, HHMM-HHMM'.\n"
                    + "Day must be Monday to Sunday (case-insensitive), times must be in 24-hour format and "
                    + "start time cannot be later than end time.\n"
                    + "There is a space after the comma.\n"
                    + "Example: Monday, 1000-1200 or Mon, 1000-1200";

    /**
     * Regex to validate strings in the format: "DayOfWeek, HHMM-HHMM".
     * Allows full day names (Monday to Sunday, case-insensitive), and times in 24-hour format without colons.
     * Example of a valid string: "Monday, 1400-1600".
     */
    public static final String VALIDATION_REGEX =
            "^(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday|mon|tue|wed|thu|fri|sat|sun),\\s"
                    + "(?:([01]?[0-9]|2[0-3])[0-5][0-9])-(?:([01]?[0-9]|2[0-3])[0-5][0-9])$";

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
        return test.matches(VALIDATION_REGEX) && TuitionTime.isStartTimeEarlierThanEndTime(test);
    }

    /**
     * Compares the start time and end time to ensure that the start time is not later than the end time.
     *
     * @param input The input string in the format "DayOfWeek, HHMM-HHMM", which includes start and end times.
     * @return true if the start time is earlier than or equal to the end time; false otherwise.
     */
    public static boolean isStartTimeEarlierThanEndTime(String input) {
        String[] parts = input.split(",\\s");
        String[] times = parts[1].split("-");

        int endTime = Integer.parseInt(times[1]);
        int startTime = Integer.parseInt(times[0]);

        return startTime <= endTime;
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

    public boolean match(String test) {
        return this.value.toLowerCase().contains(test.toLowerCase());
    }
}
