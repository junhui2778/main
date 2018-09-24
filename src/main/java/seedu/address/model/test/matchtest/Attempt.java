package seedu.address.model.test.matchtest;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;

/**
 * Represent an attempt to answer the question during a test.
 */
public class Attempt {
    private final Card card;
    private final Answer matchedAnswer;
    private final boolean correctness;

    public Attempt(Card card, Answer matchedAnswer) {
        this.card = card;
        this.matchedAnswer = matchedAnswer;
        this.correctness = matchedAnswer.equals(card.getAnswer());
    }
}
