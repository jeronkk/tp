package seedu.address.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * A factory class that creates {@link TableCell} instances with text wrapping and white text color
 * for use in JavaFX {@link TableColumn} controls. The generated cells will:
 * <ul>
 *     <li>Automatically wrap text to fit within the column width</li>
 *     <li>Display text in white color</li>
 *     <li>Adjust row height dynamically based on content</li>
 * </ul>
 *
 * @param <S> The type of the TableView generic type (typically the data model class)
 * @param <T> The type of the item contained within the cell (typically String)
 */
public class WrappedTextTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    /**
     * Creates and returns a new {@link TableCell} instance configured with text wrapping
     * and white text color for the specified table column.
     *
     * @param param The table column that will use this cell factory
     * @return A new TableCell instance with:
     *         - White text color
     *         - Automatic text wrapping
     *         - Dynamic row height adjustment
     */
    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new TableCell<S, T>() {
            private Text text = new Text();

            {
                text.setFill(Color.WHITE);
                text.wrappingWidthProperty().bind(widthProperty().subtract(10));
                setGraphic(text);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setStyle("-fx-text-fill: white;");
            }

            /**
             * Updates the item displayed in the cell.
             *
             * @param item  The new item for the cell, or null if empty
             * @param empty true if the cell represents a null item, false otherwise
             */
            @Override
            public void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    text.setText(null);
                } else {
                    text.setText(item.toString());
                    setStyle("-fx-text-fill: white;");
                }
            }
        };
    }
}
