package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equalsIgnoreCaseTest() {
        // exactly equals
        Tag tag = new Tag("chemistry");
        assertTrue(tag.equalsIgnoreCase("chemistry"));

        // mixed-case tag
        tag = new Tag("cHemIsTry");
        assertTrue(tag.equalsIgnoreCase("chemistry"));

        // mixed-case keyword
        tag = new Tag("chemistry");
        assertTrue(tag.equalsIgnoreCase("chEmiSTry"));

        // not equalsTag
        tag = new Tag("chemistry");
        assertFalse(tag.equalsIgnoreCase("math"));
    }

}
