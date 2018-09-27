package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.StartTestMEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.card.TagIsKeywordPredicate;
import seedu.address.model.state.AppState;
import seedu.address.model.state.State;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.matchtest.MatchTest;

/**
 * The command which will executing the matching test of trivia.
 */
public class TestMCommand extends Command {
    public static final String COMMAND_WORD = "testM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a matching test. "
            + "Parameters: "
            + PREFIX_TAG + "TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Physics ";

    public static final String MESSAGE_SUCCESS = "Matching test started.";
    private static final String MESSAGE_NEED_MORE_THAN_ONE_CARD = "Matching test needs more than 1 card with the"
            + " corresponding tag to proceed.";

    private final Tag tag;

    public TestMCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Card> cards = model.getListOfCardFilteredByTag(new TagIsKeywordPredicate(tag.tagName));

        cards.stream().forEach(System.out::println);
        if (cards.size() <= 1) {
            throw new CommandException(MESSAGE_NEED_MORE_THAN_ONE_CARD);
        }

        MatchTest test = new MatchTest(cards);
        AppState.setAppState(State.TESTM);

        EventsCenter.getInstance().post(new StartTestMEvent(getQuestions(cards), getAnswers(cards)));
        test.startTimer();
        //        test.showResults();
        //        test.recordResults();
        //        test.navigateToHomePage();

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        // All the tests are different even if they have the same parameters.
        return other == this;
    }

    /**
     * Retrieve an unmodifiable observable list of questions for the UI.
     * @param cards The cards to retrieve the questions from.
     * @return an unmodifiable observable list of questions
     */
    private ObservableList<Question> getQuestions(List<Card> cards) {
        List<Question> questions = cards.stream()
                .map(card -> card.getQuestion())
                .collect(Collectors.toList());
        Collections.shuffle(questions);
        return FXCollections.unmodifiableObservableList(FXCollections.observableList(questions));
    }

    /**
     * Retrieve an unmodifiable observable list of answers for the UI.
     * @param cards The cards to retrieve the answers from.
     * @return an unmodifiable observable list of answers
     */
    private ObservableList<Answer> getAnswers(List<Card> cards) {
        List<Answer> answers = cards.stream()
                .map(card -> card.getAnswer())
                .collect(Collectors.toList());
        Collections.shuffle(answers);
        return FXCollections.unmodifiableObservableList(FXCollections.observableList(answers));
    }

}
