package seedu.address.testutil;

import javafx.embed.swing.JFXPanel;

/**
 * A utility class for initializing the JavaFX toolkit.
 * <p>
 * Instantiating a {@link JFXPanel} forces the JavaFX runtime to initialize.
 * This utility is typically used in testing environments to ensure that the
 * JavaFX platform is set up before any JavaFX components are used.
 * </p>
 */
public class JavaFxInitializer {

    /**
     * Initializes the JavaFX toolkit by instantiating a {@link JFXPanel}.
     * <p>
     * This method is synchronized to guarantee that the toolkit is initialized
     * only once, even if called from multiple threads.
     * </p>
     */
    public static synchronized void init() {
        // Instantiating a JFXPanel will initialize the JavaFX toolkit.
        new JFXPanel();
    }
}
