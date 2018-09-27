package seedu.address.model.test.matchtest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import seedu.address.commons.core.index.Index;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.state.AppState;
import seedu.address.model.state.State;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.TriviaTest;

/**
 * Represents a trivia test that is started by the user.
 */
public class MatchTest extends TriviaTest {
    private final Date testDate;
    private final List<MatchAttempt> attempts;

    private double duration;
    private Timer timer;

    public MatchTest(Tag tag, List<Card> cards) {
        super(tag, cards);
        testDate = new Date();
        attempts = new ArrayList<>();
    }

    /**
     *
     * @return a boolean to indicate whether the test has come to an end
     */
    public boolean isEndOfTest() {
        return questions.isEmpty() && answers.isEmpty();
    }

    /**
     * Remove the involved card that is answered correctly from the UI.
     * @param attempt The attempt that was made by the user in the matching test.
     */
    public void removeCardFromUi(MatchAttempt attempt) {
        assert attempt.isCorrect(); // Ensure that attempt is correct before removing.

        questions.remove(attempt.getCardWithQuestion().getQuestion());
        answers.remove(attempt.getCardWithAnswer().getAnswer());
    }

    /**
     * Add an attempt to the matching test.
     *
     * @param questionIndex The index of the question to match.
     * @param answerIndex The index of the answer to match.
     * @return the new Matching attempt.
     * @throws IndexOutOfBoundsException when the given index is out of range of the given answers or questions.
     */
    public MatchAttempt addAttempt(Index questionIndex, Index answerIndex) throws IndexOutOfBoundsException {
        Question question = questions.get(questionIndex.getZeroBased());
        Answer answer = answers.get(answerIndex.getZeroBased());

        Card cardWithQuestion = cards.stream()
                .filter(card -> card.getQuestion().equals(question))
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);
        Card cardWithAnswer = cards.stream()
                .filter(card -> card.getAnswer().equals(answer))
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);

        MatchAttempt newAttempt = new MatchAttempt(cardWithQuestion, cardWithAnswer);
        attempts.add(newAttempt);
        return newAttempt;
    }

    @Override
    public void startTest() {
        duration = 0;
        timer = new Timer();
        DecimalFormat timerFormat = new DecimalFormat("#.#");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                duration = Double.parseDouble(timerFormat.format(duration + 0.1));
                System.out.println("Seconds passed: " + duration);
            }
        };

        timer.scheduleAtFixedRate(task, 0, 100);
        AppState.setAppState(State.TESTM);

    }

    @Override
    public void stopTest() {
        // TODO: Show the result screen.
        // TODO: Record results if test did not end prematurely.
        timer.cancel();
        AppState.setAppState(State.NORMAL);
    }

    @Override
    public String toString() {
        return "A matching test for the tag of '" + tag.tagName + "'.";
    }
}
