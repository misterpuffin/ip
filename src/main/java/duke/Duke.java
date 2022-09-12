package duke;

import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.handlers.IHandler;
import duke.service.Parser;
import duke.service.Service;

/**
 * Main entrypoint for Duke Application
 */
public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\n";
    private static final Tasklist list = new Tasklist();
    private Service service;

    /**
     * Constructs a new Duke instance.
     * @param s service context in which Duke operates.
     */
    public Duke(Service s) {
        this.service = s;
        s.message(LOGO + "Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Main Entrypoint for CLI tool
     * @param args arguments passed by user in CLI
     */
    public static void main(String[] args) {
    }

    /**
     * Handles the command given to Duke.
     * @param str user input.
     * @throws DukeException
     */
    public String handleCommand(String str) {
        try {
            IHandler handler = Parser.parse(str);
            String message = handler.handle(this.service);
            this.service.updateStorage();
            return message;
        } catch (DukeException ex) {
            return " ☹ OOPS!!! " + ex.getMessage();
        }
    }
}
