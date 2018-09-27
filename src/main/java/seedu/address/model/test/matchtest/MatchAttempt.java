package seedu.address.model.test.matchtest;

import seedu.address.model.card.Card;
import seedu.address.model.test.Attempt;

/**
 * Represent an attempt to answer the question during a test.
 */
public class MatchAttempt extends Attempt {
    private final Card cardWithAnswer;

    public MatchAttempt(Card cardWithQuestion, Card cardWithAnswer) {
        super(cardWithQuestion, cardWithQuestion.equals(cardWithAnswer));
        this.cardWithAnswer = cardWithAnswer;
    }

    public Card getCardWithQuestion() {
        return card;
    }

    public Card getCardWithAnswer() {
        return cardWithAnswer;
    }

}
