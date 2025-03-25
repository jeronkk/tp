package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2425s2-cs2103-f10-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandUsageInfo> commandsTable;

    @FXML
    private TableColumn<CommandUsageInfo, String> commandColumn;

    @FXML
    private TableColumn<CommandUsageInfo, String> formatColumn;

    @FXML
    private TableColumn<CommandUsageInfo, String> exampleColumn;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        initializeTable();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Initializes and configures the table with command usage information.
     *
     * <p>This method performs the following operations in sequence:
     * <ol>
     *   <li>Sets up cell value factories for each column to extract the appropriate data
     *       from {@link CommandUsageInfo} objects</li>
     *   <li>Applies the {@link WrappedTextTableCellFactory} to all columns to enable
     *       text wrapping and consistent styling</li>
     *   <li>Loads command data from {@link CommandConfig} into the table's items</li>
     *   <li>Refreshes the table view to ensure all updates are visible</li>
     * </ol>
     *
     * <p>The resulting table will:
     * <ul>
     *   <li>Display white text on dark background</li>
     *   <li>Automatically wrap long text in cells</li>
     *   <li>Show all commands from the configuration</li>
     * </ul>
     *
     * @implNote The method must be called after the FXML fields are injected but before
     *           the window is shown. The table columns ({@code commandColumn},
     *           {@code formatColumn}, {@code exampleColumn}) and the table view
     *           ({@code commandsTable}) must be properly initialized before calling this method.
     */
    private void initializeTable() {
        // 1. First set up the cell value factories
        commandColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().command()));
        formatColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().format()));
        exampleColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().example()));

        // 2. Apply the wrapped text factory to ALL columns
        commandColumn.setCellFactory(new WrappedTextTableCellFactory<>());
        formatColumn.setCellFactory(new WrappedTextTableCellFactory<>());
        exampleColumn.setCellFactory(new WrappedTextTableCellFactory<>());

        // 3. ACTUALLY LOAD THE DATA (This was missing)
        ObservableList<CommandUsageInfo> commands = FXCollections.observableArrayList(
                CommandConfig.getAllCommands()
        );
        commandsTable.setItems(commands);

        // 4. Refresh the table to ensure updates are visible
        commandsTable.refresh();
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *        <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
