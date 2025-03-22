package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class SortCommandTest {

    @Test
    public void execute_validField_doesNotThrow() {
        Model model = new SimpleModelStub();
        SortCommand command = new SortCommand("name", true);

        assertDoesNotThrow(() -> command.execute(model));
    }

    @Test
    public void execute_invalidField_throwsCommandException() {
        Model model = new SimpleModelStub();
        SortCommand command = new SortCommand("banana", true);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    private static class SimpleModelStub implements Model {
        private final ObservableList<Person> list = FXCollections.observableArrayList();

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return list;
        }
        @Override public void sortFilteredPersonList(Comparator<Person> comparator) {}
        @Override public void updateFilteredPersonList(Predicate<Person> predicate) {}

        // Stub the rest as unused
        @Override public void setUserPrefs(seedu.address.model.ReadOnlyUserPrefs userPrefs) {}
        @Override public seedu.address.model.ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }
        @Override public seedu.address.commons.core.GuiSettings getGuiSettings() {
            return null;
        }
        @Override public void setGuiSettings(seedu.address.commons.core.GuiSettings guiSettings) {}
        @Override public java.nio.file.Path getAddressBookFilePath() {
            return null;
        }
        @Override public void setAddressBookFilePath(java.nio.file.Path path) {}
        @Override public void setAddressBook(seedu.address.model.ReadOnlyAddressBook ab) {}
        @Override public seedu.address.model.ReadOnlyAddressBook getAddressBook() {
            return null;
        }
        @Override public boolean hasPerson(Person person) {
            return false;
        }
        @Override public void deletePerson(Person person) {}
        @Override public void addPerson(Person person) {}
        @Override public void setPerson(Person target, Person editedPerson) {}
    }
}
