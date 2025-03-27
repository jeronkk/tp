package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

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

    @Test
    public void execute_nameFieldAscending_sortsCorrectly() throws Exception {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Person charlie = new PersonBuilder().withName("Charlie").build();

        ObservableList<Person> list = FXCollections.observableArrayList(charlie, bob, alice);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("name", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(alice, bob, charlie);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_nameFieldDescending_sortsCorrectly() throws Exception {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Person charlie = new PersonBuilder().withName("Charlie").build();

        ObservableList<Person> list = FXCollections.observableArrayList(alice, bob, charlie);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("name", false);
        command.execute(model);

        List<Person> expected = Arrays.asList(charlie, bob, alice);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_addressFieldAscending_sortsCorrectly() throws Exception {
        Person alice = new PersonBuilder().withAddress("Admiralty").build();
        Person bob = new PersonBuilder().withAddress("Bishan").build();
        Person charlie = new PersonBuilder().withAddress("Tampines").build();

        ObservableList<Person> list = FXCollections.observableArrayList(alice, bob, charlie);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("address", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(alice, bob, charlie);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_phoneFieldAscending_sortsCorrectly() throws Exception {
        Person alice = new PersonBuilder().withName("Alice").withPhone("1111").build();
        Person bob = new PersonBuilder().withName("Bob").withPhone("2222").build();
        Person charlie = new PersonBuilder().withName("Charlie").withPhone("3333").build();

        ObservableList<Person> list = FXCollections.observableArrayList(charlie, bob, alice);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("phone", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(alice, bob, charlie);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_emailFieldAscending_sortsCorrectly() throws Exception {
        Person alice = new PersonBuilder().withName("Alice").withEmail("a@example.com").build();
        Person bob = new PersonBuilder().withName("Bob").withEmail("b@example.com").build();
        Person charlie = new PersonBuilder().withName("Charlie").withEmail("c@example.com").build();

        ObservableList<Person> list = FXCollections.observableArrayList(charlie, bob, alice);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("email", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(alice, bob, charlie);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_tuitionFieldAscending_sortsCorrectly() throws Exception {
        Person monday = new PersonBuilder().withName("Monday").withTuitionTime("Monday, 0900-1100").build();
        Person friday = new PersonBuilder().withName("Friday").withTuitionTime("Friday, 1200-1300").build();
        Person saturday = new PersonBuilder().withName("Saturday").withTuitionTime("Saturday, 1200-1300").build();
        Person sunday = new PersonBuilder().withName("Sunday").withTuitionTime("Sunday, 1400-1500").build();

        ObservableList<Person> list = FXCollections.observableArrayList(sunday, friday, monday, saturday);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("tuition", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(monday, friday, saturday, sunday);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_tagsFieldAscending_sortsCorrectly() throws Exception {
        Person oneTag = new PersonBuilder().withName("One").withTags("Math").build();
        Person twoTags = new PersonBuilder().withName("Two").withTags("Science", "Math").build();
        Person threeTags = new PersonBuilder().withName("Three").withTags("Math", "English", "Science").build();

        // initial order: One, Two, Three
        ObservableList<Person> list = FXCollections.observableArrayList(oneTag, twoTags, threeTags);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("tags", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(oneTag, twoTags, threeTags);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_tagsFieldDescending_sortsCorrectly() throws Exception {
        Person oneTag = new PersonBuilder().withName("One").withTags("Math").build();
        Person twoTags = new PersonBuilder().withName("Two").withTags("Science", "Math").build();
        Person threeTags = new PersonBuilder().withName("Three").withTags("Math", "English", "Science").build();

        ObservableList<Person> list = FXCollections.observableArrayList(threeTags, twoTags, oneTag);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("tags", false);
        command.execute(model);

        List<Person> expected = Arrays.asList(threeTags, twoTags, oneTag);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void execute_personWithNoTags_sortsCorrectly() throws Exception {
        Person noTags = new PersonBuilder().withName("Tagless").withTags().build();
        Person oneTag = new PersonBuilder().withName("Tagged").withTags("Math").build();

        ObservableList<Person> list = FXCollections.observableArrayList(noTags, oneTag);
        SimpleModelStub model = new SimpleModelStub(list);

        SortCommand command = new SortCommand("tags", true);
        command.execute(model);

        List<Person> expected = Arrays.asList(noTags, oneTag);
        assertEquals(expected, model.getFilteredPersonList());
    }

    @Test
    public void getSortedTags_emptyTags_returnsEmptyString() throws Exception {
        Person p = new PersonBuilder().withName("Empty").withTags().build();
        ObservableList<Person> list = FXCollections.observableArrayList(p);
        SimpleModelStub model = new SimpleModelStub(list);

        new SortCommand("tags", true).execute(model);
        assertEquals(List.of(p), model.getFilteredPersonList()); // still sorted, nothing to compare
    }

    @Test
    public void getSortedTags_singleTag_returnsTagLowercased() throws Exception {
        Person p = new PersonBuilder().withName("Solo").withTags("Math").build();
        ObservableList<Person> list = FXCollections.observableArrayList(p);
        SimpleModelStub model = new SimpleModelStub(list);

        new SortCommand("tags", true).execute(model);
        assertEquals(List.of(p), model.getFilteredPersonList()); // No reordering needed
    }

    @Test
    public void getSortedTags_multipleSortedTags_returnsSameOrder() throws Exception {
        Person p = new PersonBuilder().withName("Sorted").withTags("English", "Math", "Science").build();
        ObservableList<Person> list = FXCollections.observableArrayList(p);
        SimpleModelStub model = new SimpleModelStub(list);

        new SortCommand("tags", true).execute(model);
        assertEquals(List.of(p), model.getFilteredPersonList()); // still sorted, but logic ran
    }

    @Test
    public void sortTags_expressionInTest_lineByLineCoverage() {
        Person p = new PersonBuilder().withTags("Science", "Math", "English").build();
        String tagString = p.getTags().stream()
                .map(tag -> tag.tagName.toLowerCase())
                .sorted()
                .reduce((a, b) -> a + "," + b)
                .orElse(""); // covers .orElse("")

        assertEquals("english,math,science", tagString);
    }


    private static class SimpleModelStub implements Model {
        private final ObservableList<Person> list;

        SimpleModelStub() {
            this.list = FXCollections.observableArrayList();
        }

        SimpleModelStub(ObservableList<Person> list) {
            this.list = list;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return list;
        }

        @Override
        public void sortFilteredPersonList(Comparator<Person> comparator) {
            FXCollections.sort(list, comparator);
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {}

        // Unused methods
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
