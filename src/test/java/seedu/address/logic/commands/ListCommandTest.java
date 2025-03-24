package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsContainKeywordPredicate;
import seedu.address.model.person.TuitionTimeContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_filterByTuitionTime_showsMatchingPersons() {
        String keyword = "Monday";
        Predicate<Person> predicate = new TuitionTimeContainsKeywordPredicate(keyword);
        ListCommand command = new ListCommand(predicate, "Showing persons with tuition time on: ", keyword);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, "Showing persons with tuition time on: " + keyword, expectedModel);
    }

    @Test
    public void execute_filterByTag_showsMatchingPersons() {
        String keyword = "student";
        Predicate<Person> predicate = new TagsContainKeywordPredicate(keyword);
        ListCommand command = new ListCommand(predicate, "Showing persons with tag: ", keyword);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, "No student found.", expectedModel);
    }
}
