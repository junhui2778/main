package seedu.address.model.test.matchtest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_EARTH_FLAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_GIT_COMMIT;
import static seedu.address.testutil.TypicalCards.Q_FLAT_EARTH;
import static seedu.address.testutil.TypicalCards.Q_GIT_COMMIT;

import org.junit.Test;

import seedu.address.model.card.Card;
import seedu.address.testutil.CardBuilder;

public class MatchAttemptTest {

    @Test
    public void testIncorrectAttempt() {
        // two obviously different cards
        assertFalse(new MatchAttempt(Q_FLAT_EARTH, Q_GIT_COMMIT).isCorrect());

        // different question but same answer and tag
        Card editedCard = new CardBuilder(Q_FLAT_EARTH).withQuestion(VALID_QUESTION_GIT_COMMIT).build();
        assertFalse(new MatchAttempt(Q_FLAT_EARTH, editedCard).isCorrect());
    }

    @Test
    public void testCorrectAttempt() {
        // two obviously same cards
        assertTrue(new MatchAttempt(Q_FLAT_EARTH, Q_FLAT_EARTH).isCorrect());

        // different answer and tag but same question
        Card editedCard = new CardBuilder(Q_GIT_COMMIT).withQuestion(VALID_QUESTION_EARTH_FLAT).build();
        assertTrue(new MatchAttempt(Q_FLAT_EARTH, editedCard).isCorrect());
    }

}
