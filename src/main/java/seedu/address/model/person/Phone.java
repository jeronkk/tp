package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should be valid international or local numbers, e.g., +65 9123 4567 or 91234567";

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     * Supports numbers with or without international prefix.
     */
    public static boolean isValidPhone(String test) {
        if (test == null) {
            return false; // ✅ safe for optional usage
        }

        try {
            // Strip spaces for leniency
            String cleaned = test.replaceAll("\\s+", "");
            Phonenumber.PhoneNumber parsed = phoneUtil.parse(cleaned, "SG");
            return phoneUtil.isPossibleNumber(parsed) && phoneUtil.isValidNumber(parsed);
        } catch (NumberParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Phone && value.equals(((Phone) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
