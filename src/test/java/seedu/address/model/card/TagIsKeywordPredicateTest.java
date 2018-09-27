package seedu.address.model.card;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.CardBuilder;

public class TagIsKeywordPredicateTest {
    @Test
    public void test_cardContainsTag_returnsTrue() {
        TagIsKeywordPredicate predicate = new TagIsKeywordPredicate("Physics");
        assertTrue(predicate.test(new CardBuilder().withTags("Physics").build()));

        // Caps insensitive
        assertTrue(predicate.test(new CardBuilder().withTags("pHySicS").build()));

        // Works for cards with multiple tags
        assertTrue(predicate.test(new CardBuilder().withTags("GeneralKnowledge", "Physics").build()));
        assertTrue(predicate.test(new CardBuilder().withTags("Physics", "GeneralKnowledge").build()));
        assertTrue(predicate.test(new CardBuilder().withTags("GeneralKnowledge", "Physics", "Git").build()));
    }

    @Test
    public void test_cardDoesNotContainTag_returnsFalse() {
        TagIsKeywordPredicate predicate = new TagIsKeywordPredicate("NotATag");
        assertFalse(predicate.test(new CardBuilder().withTags("ATag").build()));

        // Works for cards with multiple tags
        assertFalse(predicate.test(new CardBuilder().withTags("ATag", "BTag").build()));
    }

    @Test
    public void equals() {
        String firstTagKeyword = "first";
        String secondTagKeyword = "second";

        TagIsKeywordPredicate firstPredicate = new
                TagIsKeywordPredicate(firstTagKeyword);
        TagIsKeywordPredicate secondPredicate = new
                TagIsKeywordPredicate(secondTagKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagIsKeywordPredicate firstPredicateCopy = new
                TagIsKeywordPredicate(firstTagKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
