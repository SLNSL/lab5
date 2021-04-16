package commands;

import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда, которая очищает коллекцию.
 */
public class ClearCommand implements Command {

    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;

    public ClearCommand(Collection collectionManager, Messenger messenger, Printable printer){
        this.collectionManager = collectionManager;
        this.printer = printer;
        this.messenger = messenger;
    }

    /**
     * Выполнение команды
     * @param argument - аргумент, который может быть передан или не передан вместе с командой
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public boolean execute(String argument) throws IncorrectNumberOfArgumentsException {
        if (!argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("clear",0));
        collectionManager.clear();
        printer.println(messenger.commandIsFinished("clear"));
        return true;
    }

}
