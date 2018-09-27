package seedu.address.commons.events.ui;

import javafx.collections.ObservableList;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Question;

/**
 * Indicates a new matching test will be starting.
 */
public class StartTestMEvent extends BaseEvent {
    private ObservableList<Question> questions;
    private ObservableList<Answer> answers;

    public StartTestMEvent(ObservableList<Question> questions, ObservableList<Answer> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public ObservableList<Answer> getAnswers() {
        return answers;
    }
}
