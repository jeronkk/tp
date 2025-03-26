package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.stage.Stage;
import seedu.address.testutil.JavaFxInitializer;

public class HelpWindowTest {

    @BeforeAll
    public static void initJavaFx() throws InterruptedException {
        // Initialize JavaFX using our helper that uses JFXPanel or TestFX.
        JavaFxInitializer.init();
    }

    @Test
    public void testHelpWindowInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                // Create and show the HelpWindow
                HelpWindow helpWindow = new HelpWindow(new Stage());
                helpWindow.show();

                // Lookup the help message Label by its fx:id ("helpMessage")
                Node labelNode = helpWindow.getRoot().getScene().lookup("#helpMessage");
                assertNotNull(labelNode, "The help message label should be present in the scene.");
                Label helpLabel = (Label) labelNode;
                assertEquals(HelpWindow.HELP_MESSAGE, helpLabel.getText(),
                        "The help message should match the expected text.");

                // Lookup the commands table by its fx:id ("commandsTable")
                Node tableNode = helpWindow.getRoot().getScene().lookup("#commandsTable");
                assertNotNull(tableNode, "The commands table should be present in the scene.");
                TableView<?> commandsTable = (TableView<?>) tableNode;
                // Check that the table is populated with the expected number of commands.
                int expectedSize = CommandConfig.getAllCommands().size();
                assertEquals(expectedSize, commandsTable.getItems().size(),
                        "The commands table should contain " + expectedSize + " entries.");

                // Hide the window after testing.
                helpWindow.hide();
            } finally {
                latch.countDown();
            }
        });
        // Wait for the UI thread to complete the test.
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testCopyUrlCopiesCorrectUrl() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                HelpWindow helpWindow = new HelpWindow(new Stage());
                // Invoke the copyUrl method.
                helpWindow.copyUrl();
                String clipboardContent = Clipboard.getSystemClipboard().getString();
                assertEquals(HelpWindow.USERGUIDE_URL, clipboardContent,
                        "The system clipboard should contain the correct user guide URL.");
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }
}
