package seedu.address.model.test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;

/**
 * A base model for the different kinds tests.
 */
public class TriviaTest {
    protected final Tag tag;
    protected final List<Card> cards;
    protected final ObservableList<Question> questions;
    protected final ObservableList<Answer> answers;

    public TriviaTest(Tag tag, List<Card> cards) {
        this.tag = tag;
        this.cards = cards;
        this.questions = getQuestions(cards);
        this.answers = getAnswers(cards);
    }

    public Tag getTag() {
        return tag;
    }

    public ObservableList<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Retrieve an unmodifiable observable list of questions for the UI.
     * @param cards The cards to retrieve the questions from.
     * @return an observable list of questions
     */
    private ObservableList<Question> getQuestions(List<Card> cards) {
        List<Question> questions = cards.stream()
                .map(card -> card.getQuestion())
                .collect(Collectors.toList());
        Collections.shuffle(questions);
        return FXCollections.observableList(questions);
    }

    public ObservableList<Answer> getAnswers() {
        return this.answers;
    }

    /**
     * Retrieve an unmodifiable observable list of answers for the UI.
     * @param cards The cards to retrieve the answers from.
     * @return an observable list of answers
     */
    private ObservableList<Answer> getAnswers(List<Card> cards) {
        List<Answer> answers = cards.stream()
                .map(card -> card.getAnswer())
                .collect(Collectors.toList());
        Collections.shuffle(answers);
        return FXCollections.observableList(answers);
    }

    @Override
    public String toString() {
        return "A test for the tag of '" + tag.tagName + "'.";
    }
}
