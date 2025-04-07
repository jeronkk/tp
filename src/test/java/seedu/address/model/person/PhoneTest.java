package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertFalse(Phone.isValidPhone(null)); // null

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // too short (even for SG)
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9381e9173")); // alphabet in digits
        assertFalse(Phone.isValidPhone("+1 123-4567")); // US number too short (missing area code)
        assertFalse(Phone.isValidPhone("+999 12345678")); // invalid country code
        assertFalse(Phone.isValidPhone("9123  4567")); // multiple consecutive spaces

        // valid phone numbers
        assertTrue(Phone.isValidPhone("91234567")); // valid SG local
        assertTrue(Phone.isValidPhone("+6591234567")); // valid SG international
        assertTrue(Phone.isValidPhone("+14085551234")); // valid US number
        assertTrue(Phone.isValidPhone("93121534")); // another valid SG number
        assertTrue(Phone.isValidPhone("+442079460958")); // valid UK number
    }

    @Test
    public void equals() {
        Phone phone = new Phone("91234567");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("91234567")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("92234567")));
    }
}
