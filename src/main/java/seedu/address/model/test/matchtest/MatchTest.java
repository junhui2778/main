package seedu.address.model.test.matchtest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;

/**
 * Represents a trivia test that is started by the user.
 */
public class MatchTest {
    private final Date testDate;
    private final List<Attempt> attempts;
    private final List<Card> cards;
    private double duration;

    public MatchTest(List<Card> cards) {
        testDate = new Date();
        attempts = new ArrayList<>();
        duration = 0;
        this.cards = cards;
    }

    public void addAttempt(Card card, Answer matchedAnswer) {
        attempts.add(new Attempt(card, matchedAnswer));
    }

    /**
     * Start the matching test timer.
     */
    public void startTimer() {
        Timer timer = new Timer();
        DecimalFormat timerFormat = new DecimalFormat("#.#");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                duration = Double.parseDouble(timerFormat.format(duration + 0.1));
                System.out.println("Seconds passed: " + duration);
            }
        };

        timer.scheduleAtFixedRate(task, 0, 100);
    }
}
