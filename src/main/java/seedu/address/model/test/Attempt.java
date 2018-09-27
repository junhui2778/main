package seedu.address.model.test;

/**
 * A base model for the different kinds of attempts that can be made in different tests.
 */
public class Attempt {
    protected boolean correctness;

    public Attempt(boolean correctness) {
        this.correctness = correctness;
    }

    public boolean getCorrectness() {
        return correctness;
    }
}
