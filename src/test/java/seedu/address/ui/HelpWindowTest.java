package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class HelpWindowTest {

    @Test
    void constants_areProperlyDefined() {
        // Test the URL constant
        assertNotNull(HelpWindow.USERGUIDE_URL);
        assertTrue(HelpWindow.USERGUIDE_URL.startsWith("http"));
        assertTrue(HelpWindow.USERGUIDE_URL.contains("UserGuide"));

        // Test the help message
        assertEquals("Refer to the user guide: " + HelpWindow.USERGUIDE_URL,
                HelpWindow.HELP_MESSAGE);
    }
}
