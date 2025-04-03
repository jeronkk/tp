package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid names
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only symbols not allowed
        assertFalse(Name.isValidName("peter*")); // contains invalid symbol '*'
        assertFalse(Name.isValidName("🚀 Rocket")); // emoji not allowed
        assertFalse(Name.isValidName("12345")); //numbers only not allowed

        // valid names (Unicode and culturally relevant)
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric
        assertTrue(Name.isValidName("Capital Tan")); // with capitals
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("O'Connor")); // apostrophe
        assertTrue(Name.isValidName("Nguyễn Văn A")); // Vietnamese
        assertTrue(Name.isValidName("张伟")); // Chinese characters
        assertTrue(Name.isValidName("Jean-Luc Picard")); // hyphenated
        assertTrue(Name.isValidName("Vignesh S/O Muniyandi")); // with slashes
        assertTrue(Name.isValidName("Mary J. Blige")); // with dot
        assertTrue(Name.isValidName("Dr. Tan")); // title with dot
        assertTrue(Name.isValidName("A. B. C.")); // initials
    }


    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
