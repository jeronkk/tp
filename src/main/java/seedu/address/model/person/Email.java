package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    // Email length standards
    private static final int MAX_TOTAL_LENGTH = 320; // entire email
    private static final int MAX_LOCAL_LENGTH = 64; // before '@'
    private static final int MAX_DOMAIN_LENGTH = 255; // after '@'

    private static final String SPECIAL_CHARACTERS = "+_.-";

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The total length (local part + '@' + domain) must be at most 320 characters.\n"
            + "2. The local-part must be at most 64 characters.\n"
            + "3. The domain name must be at most 255 characters.\n"
            + "4. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "5. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "   Each domain label must start and end with alphanumeric characters,\n"
            + "   and consist of alphanumeric characters, separated only by hyphens, if any.\n"
            + "   The last domain label must be at least 2 characters long.\n"
            + "   Underscores in the domain are disallowed.";

    /*
     * LOCAL PART:
     *  - underscores allowed
     *  - can have special chars interspersed
     */
    private static final String LOCAL_ALPHANUMERIC = "[\\p{Alnum}_]+";
    private static final String LOCAL_PART_REGEX = "^" + LOCAL_ALPHANUMERIC
            + "([" + SPECIAL_CHARACTERS + "]" + LOCAL_ALPHANUMERIC + ")*";

    /*
     * DOMAIN PART:
     *  - underscores disallowed
     *  - only letters or digits, optionally separated by hyphens
     */
    private static final String DOMAIN_ALPHANUMERIC = "[A-Za-z0-9]+";
    private static final String DOMAIN_PART_REGEX = DOMAIN_ALPHANUMERIC
            + "(-" + DOMAIN_ALPHANUMERIC + ")*";

    // last label must be >=2 chars
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$";

    // domain => zero or more labels + final label
    private static final String DOMAIN_REGEX =
            "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;

    // Final pattern => localPart@domain
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);

        // 1. Overall length check
        checkArgument(email.length() <= MAX_TOTAL_LENGTH, MESSAGE_CONSTRAINTS);

        // 2. Regex check
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);

        // 3. local & domain sub-checks
        String[] parts = email.split("@", 2);
        String localPart = parts[0];
        String domainPart = parts[1];

        checkArgument(localPart.length() <= MAX_LOCAL_LENGTH, MESSAGE_CONSTRAINTS);
        checkArgument(domainPart.length() <= MAX_DOMAIN_LENGTH, MESSAGE_CONSTRAINTS);

        this.value = email;
    }

    /**
     * Returns true if a given string matches this email's format.
     */
    public static boolean isValidEmail(String test) {
        requireNonNull(test);
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
        if (!(other instanceof Email)) {
            return false;
        }
        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
