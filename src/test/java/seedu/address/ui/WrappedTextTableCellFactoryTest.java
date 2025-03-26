package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class WrappedTextTableCellFactoryTest {

    private WrappedTextTableCellFactory<Object, String> factory;
    private TableColumn<Object, String> tableColumn;

    @BeforeAll
    static void initJfx() throws InterruptedException {
        // Initialize JavaFX toolkit
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> {
            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @BeforeEach
    void setUp() {
        factory = new WrappedTextTableCellFactory<>();
        tableColumn = new TableColumn<>();
    }

    @Test
    void call_createsTableCellWithCorrectProperties() {
        TableCell<Object, String> cell = factory.call(tableColumn);

        assertNotNull(cell);
        assertEquals(ContentDisplay.GRAPHIC_ONLY, cell.getContentDisplay());
        assertTrue(cell.getStyle().contains("-fx-text-fill: white;"));

        assertTrue(cell.getGraphic() instanceof Text);
        Text text = (Text) cell.getGraphic();
        assertEquals(Color.WHITE, text.getFill());
    }

    @Test
    void textWrappingWidth_bindsToCellWidth() {
        TableCell<Object, String> cell = factory.call(tableColumn);

        cell.setMinWidth(200);

        Text text = (Text) cell.getGraphic();
        assertEquals(-10, text.getWrappingWidth(), 0.1); // 200 - 10 (subtract 10)
    }
}
