package seedu.address.model.test.matchtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.testutil.TypicalCards.Q_DENISTY_FORMULA;
import static seedu.address.testutil.TypicalCards.Q_EARTH_ROUND;
import static seedu.address.testutil.TypicalCards.Q_FORCE_FORMULA;

import org.junit.Test;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.TagIsKeywordPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalCards;
import seedu.address.testutil.TypicalPersons;

public class MatchTestTest {
    @Test
    public void test_matchTest() {
        ModelManager model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalCards.getTypicalTriviaBundle(), new UserPrefs());
        TagIsKeywordPredicate tagToTest = new TagIsKeywordPredicate(VALID_TAG_PHYSICS);

        // There will be 3 cards in this matchTest
        MatchTest matchTest = new MatchTest(new Tag(VALID_TAG_PHYSICS), model.getListOfCardFilteredByTag(tagToTest));

        assertEquals(matchTest.getAnswers().size(), matchTest.getQuestions().size());
        assertEquals(matchTest.getAnswers().size(), 3);

        assertTrue(matchTest.getAnswers().contains(Q_EARTH_ROUND.getAnswer()));
        assertTrue(matchTest.getQuestions().contains(Q_EARTH_ROUND.getQuestion()));
        matchTest.removeCardFromUi(new MatchAttempt(Q_EARTH_ROUND, Q_EARTH_ROUND));
        assertFalse(matchTest.getAnswers().contains(Q_EARTH_ROUND.getAnswer()));
        assertFalse(matchTest.getQuestions().contains(Q_EARTH_ROUND.getQuestion()));

        // Remove the remaining cards from the trivia bundle.
        matchTest.removeCardFromUi(new MatchAttempt(Q_DENISTY_FORMULA, Q_DENISTY_FORMULA));
        matchTest.removeCardFromUi(new MatchAttempt(Q_FORCE_FORMULA, Q_FORCE_FORMULA));

        assertTrue(matchTest.isEndOfTest());
    }
}
