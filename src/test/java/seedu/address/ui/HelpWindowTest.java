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
import javafx.stage.Stage;


public class HelpWindowTest {

    private static boolean fxInitialized = false;

    @BeforeAll
    public static void initJavaFx() throws InterruptedException {
        if (!fxInitialized) {
            CountDownLatch latch = new CountDownLatch(1);
            try {
                Platform.startup(() -> {});
            } catch (IllegalStateException e) {
                // Toolkit is already initialized; count down the latch and continue.
                latch.countDown();
            }
            latch.await(5, TimeUnit.SECONDS);
            fxInitialized = true;
        }
    }

    @Test
    public void testHelpMessageIsDisplayed() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                HelpWindow helpWindow = new HelpWindow(new Stage());
                helpWindow.show(); // Ensure the scene is built

                // Lookup the help message label using its fx:id
                Node node = helpWindow.getRoot().getScene().lookup("#helpMessage");
                assertNotNull(node, "The help message label should be present.");
                Label helpLabel = (Label) node;
                assertEquals(HelpWindow.HELP_MESSAGE, helpLabel.getText(),
                        "Help message should match the expected text.");
                helpWindow.hide();
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testCommandsTableIsPopulated() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                HelpWindow helpWindow = new HelpWindow(new Stage());
                // Ensure the table is initialized (initializeTable() is public in this example)
                helpWindow.initializeTable();
                helpWindow.show(); // Ensure the scene is built

                // Lookup the commands table using its fx:id
                Node node = helpWindow.getRoot().getScene().lookup("#commandsTable");
                assertNotNull(node, "The commands table should be present in the scene.");
                TableView<?> table = (TableView<?>) node;

                // Get the expected number of commands from the CommandConfig.
                int expectedSize = CommandConfig.getAllCommands().size();
                assertEquals(expectedSize, table.getItems().size(),
                        "The commands table should contain the expected number of command entries.");
                helpWindow.hide();
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }
}
