package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.TestMCommand;
import seedu.address.model.tag.Tag;

public class TestMCommandParserTest {
    private TestMCommandParser parser = new TestMCommandParser();

    @Test
    public void parse_validArgs_returnsTestMCommand() {
        assertParseSuccess(parser, TAG_DESC_PHYSICS, new TestMCommand(new Tag(VALID_TAG_PHYSICS)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-sdds", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TestMCommand.MESSAGE_USAGE));
    }

}
