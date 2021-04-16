package pattern;

import checker.Asker;
import exception.*;
import printer.Printable;
import messenger.Messenger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Обрабатывает введённые команды и выполняет их.
 */
public class CommandReader implements Reader {

    private Asker asker;
    private Printable printer;

    public CommandReader(Asker fieldsAsker, Printable printer) {
        this.asker = fieldsAsker;
        this.printer = printer;

    }

    /**
     * Ловит команды из пользовательского ввода
     */
    public void start(Messenger messenger, Commander commandManager) {
        String[] command;
        String fullCommand;
        Executor executor = new CommandExecutor(commandManager, printer, messenger, asker);
        while (true) {
            try {
                printer.print(">");
                executor.setMapOfScripts(new LinkedHashMap<>());
                asker.setScriptMode(false);
                asker.setScanner(new Scanner(System.in));
                fullCommand = asker.getScanner().nextLine().trim() + " ";
                command = fullCommand.split(" ", 2);
                if (executor.runCommand(command) == 2) break;
            } catch (IncorrectNumberOfArgumentsException e) {
                printer.printlnError(e.getMessage());
            } catch (SecurityException e) {
                printer.printlnError(messenger.generateSecurityExceptionMessage());
                break;
            } catch (NoSuchElementException e) {
                printer.printlnError(messenger.generateEmptyFileMessage());
                break;
            } catch (NumberFormatException e) {
                printer.printlnError(messenger.generateIncorrectFieldInDataMessage());
                break;
            } catch (IllegalStateException e) {
                printer.printlnError(messenger.generateUnexpectedErrorMessage());
                break;
            } catch (InputOutputException e) {
                printer.printlnError(messenger.generateInputOutputMessage());
                break;
            }
        }
    }

}
