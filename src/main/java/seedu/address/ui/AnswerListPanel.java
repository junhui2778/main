package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Answer;

/**
 * The panel which contains all the answers in a particular test.
 */
public class AnswerListPanel extends UiPart<Region> {
    private static final String FXML = "AnswerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnswerListPanel.class);

    @FXML
    private ListView<Answer> testMAnswerListView;

    public AnswerListPanel(ObservableList<Answer> answerList) {
        super(FXML);
        setConnections(answerList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Answer> answerList) {
        this.testMAnswerListView.setItems(answerList);
        testMAnswerListView.setCellFactory(listView -> new AnswerListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code AnswerView}.
     */
    class AnswerListViewCell extends ListCell<Answer> {
        @Override
        protected void updateItem(Answer answer, boolean empty) {
            super.updateItem(answer, empty);

            if (empty || answer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AnswerView(answer, getIndex() + 1).getRoot());
            }
        }
    }
}
