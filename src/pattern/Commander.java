package pattern;

import checker.Asker;
import checker.Checker;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import printer.Printable;

import java.util.HashMap;
import java.util.Map;

/**
 * Интерефейс для менджера команд. Содержит методы для выполнения команд.
 */
public interface Commander {

    boolean help(String argument) throws IncorrectNumberOfArgumentsException;

    boolean info(String argument) throws IncorrectNumberOfArgumentsException;

    boolean show(String argument) throws IncorrectNumberOfArgumentsException;

    boolean insert(String argument) throws IncorrectNumberOfArgumentsException;

    boolean update(String argument) throws IncorrectNumberOfArgumentsException;

    boolean removeKey(String argument) throws IncorrectNumberOfArgumentsException;

    boolean clear(String argument) throws IncorrectNumberOfArgumentsException;

    boolean save(String argument) throws IncorrectNumberOfArgumentsException;

    boolean executeScript(String argument) throws IncorrectNumberOfArgumentsException;

    boolean exit(String argument) throws IncorrectNumberOfArgumentsException;

    boolean removeLower(String argument) throws IncorrectNumberOfArgumentsException;

    boolean replaceIfGreater(String argument) throws IncorrectNumberOfArgumentsException;

    boolean replaceIfLower(String argument) throws IncorrectNumberOfArgumentsException;

    boolean minByUnitOfMeasure(String argument) throws IncorrectNumberOfArgumentsException;

    boolean maxByUnitOfMeasure(String argument) throws IncorrectNumberOfArgumentsException;

    boolean countLessThanOwner(String argument) throws IncorrectNumberOfArgumentsException;

    Map<String, Converter<Boolean, String>> getMapOfCommands();

    void putLinkToCommands();

    void createCommands(Collection collectionManager, Messenger messenger, Asker fieldsAsker, Checker fieldsChecker, Printable printer);

}
