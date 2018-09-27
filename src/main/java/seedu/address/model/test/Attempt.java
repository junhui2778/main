package seedu.address.model.test;

import seedu.address.model.card.Card;

/**
 * A base model for the different kinds of attempts that can be made in different tests.
 */
public class Attempt {
    protected boolean correctness;
    protected Card card;

    public Attempt(Card card, boolean correctness) {
        this.card = card;
        this.correctness = correctness;
    }

    public boolean isCorrect() {
        return correctness;
    }
}
