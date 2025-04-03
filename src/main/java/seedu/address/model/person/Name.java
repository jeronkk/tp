package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {
    // 1. Public static final constants
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain at least one letter (Unicode characters allowed), "
                    + "optionally numbers/punctuation (',./-), must not be blank, "
                    + "and must be at most 80 characters long.";

    public static final String VALIDATION_REGEX =
            "^(?=.*[\\p{L}\\p{M}])[\\p{L}\\p{M}\\p{N}'./\\- ]+$";

    // 2. Private static final constants
    private static final int MAX_LENGTH = 80;

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name (must not exceed 80 characters and match regex).
     */
    public Name(String name) {
        requireNonNull(name);

        // 1. Check length constraint
        checkArgument(name.length() <= MAX_LENGTH, MESSAGE_CONSTRAINTS);

        // 2. Check content/format constraint
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);

        this.fullName = name;
    }

    /**
     * Returns true if a given string is a valid name (must match regex).
     */
    public static boolean isValidName(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
