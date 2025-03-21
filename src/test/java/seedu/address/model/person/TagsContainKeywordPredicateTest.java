package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsContainKeywordPredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        TagsContainKeywordPredicate firstPredicate = new TagsContainKeywordPredicate(firstPredicateKeyword);
        TagsContainKeywordPredicate secondPredicate = new TagsContainKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainKeywordPredicate firstPredicateCopy = new TagsContainKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeyword_returnsTrue() {
        // One tag
        TagsContainKeywordPredicate predicate = new TagsContainKeywordPredicate("chemistry");
        assertTrue(predicate.test(new PersonBuilder().withTags("chemistry").build()));

        // Multiple tags
        predicate = new TagsContainKeywordPredicate("chemistry");
        assertTrue(predicate.test(new PersonBuilder().withTags("math", "chemistry").build()));

        // Mixed-case keyword
        predicate = new TagsContainKeywordPredicate("chEmiStRy");
        assertTrue(predicate.test(new PersonBuilder().withTags("math", "chemistry").build()));

        // Mixed-case tags
        predicate = new TagsContainKeywordPredicate("chemistry");
        assertTrue(predicate.test(new PersonBuilder().withTags("math", "chEmIsTry").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        TagsContainKeywordPredicate predicate = new TagsContainKeywordPredicate("chemistry");
        assertFalse(predicate.test(new PersonBuilder().withTags("math", "biology").build()));

        // Keyword match name and address, but does not match name
        predicate = new TagsContainKeywordPredicate("Math");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Main").withPhone("12345")
                .withAddress("main").withTags("chemistry", "biology").build()));
    }

    public void toStringMethod() {
        String keyword = "chemistry";
        TagsContainKeywordPredicate predicate = new TagsContainKeywordPredicate(keyword);

        String expected = TagsContainKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
