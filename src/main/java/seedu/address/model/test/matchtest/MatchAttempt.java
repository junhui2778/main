package seedu.address.model.test.matchtest;

import seedu.address.model.card.Card;
import seedu.address.model.test.Attempt;

/**
 * Represent an attempt to answer the question during a test.
 */
public class MatchAttempt extends Attempt {
    private final Card cardWithQuestion;
    private final Card cardWithAnswer;

    public MatchAttempt(Card cardWithQuestion, Card cardWithAnswer) {
        super(cardWithQuestion.equals(cardWithAnswer));
        this.cardWithQuestion = cardWithQuestion;
        this.cardWithAnswer = cardWithAnswer;
    }

    public Card getCardWithQuestion() {
        return cardWithQuestion;
    }

    public Card getCardWithAnswer() {
        return cardWithAnswer;
    }

    @Override
    public String toString() {
        return "Attempted to match Question '" + cardWithQuestion.getQuestion().value
                + "' with Answer:'" + cardWithAnswer.getAnswer().value + "'.";
    }
}
