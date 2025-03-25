package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WrappedTextTableCellFactoryTest {

    @BeforeAll
    public static void initJavaFx() throws InterruptedException {
        // Ensure JavaFX is initialized
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testCellCreation_initialState() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                WrappedTextTableCellFactory<Object, String> factory = new WrappedTextTableCellFactory<>();
                TableColumn<Object, String> dummyColumn = new TableColumn<>("Dummy Column");
                TableCell<Object, String> cell = factory.call(dummyColumn);

                // The cell's graphic should be a Text node
                assertTrue(cell.getGraphic() instanceof Text, "Expected graphic to be a Text node.");
                Text text = (Text) cell.getGraphic();
                // Initially, the text should be null
                assertNull(text.getText(), "Expected initial text to be null.");
                // And the fill color should be white
                assertEquals(Color.WHITE, text.getFill(), "Expected text fill color to be white.");
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSetItem_withNonEmptyValue() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                WrappedTextTableCellFactory<Object, String> factory = new WrappedTextTableCellFactory<>();
                TableColumn<Object, String> dummyColumn = new TableColumn<>("Dummy Column");
                TableCell<Object, String> cell = factory.call(dummyColumn);

                // Use setItem to simulate a new value being set.
                cell.setItem("Hello World");

                // Check that the Text node now displays "Hello World"
                assertNotNull(cell.getGraphic(), "Graphic should not be null.");
                Text text = (Text) cell.getGraphic();
                assertEquals("Hello World", text.getText(), "Expected the text to be 'Hello World'.");
                // The text fill should remain white.
                assertEquals(Color.WHITE, text.getFill(), "Expected text fill color to remain white.");
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSetItem_withNullValueClearsText() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                WrappedTextTableCellFactory<Object, String> factory = new WrappedTextTableCellFactory<>();
                TableColumn<Object, String> dummyColumn = new TableColumn<>("Dummy Column");
                TableCell<Object, String> cell = factory.call(dummyColumn);

                // First set a value
                cell.setItem("Some Value");
                // Now, set the item to null
                cell.setItem(null);

                // The Text node's text should be cleared (set to null)
                Text text = (Text) cell.getGraphic();
                assertNull(text.getText(), "Expected the text to be cleared (null) when setting a null value.");
            } finally {
                latch.countDown();
            }
        });
        latch.await(5, TimeUnit.SECONDS);
    }
}
