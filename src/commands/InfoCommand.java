package commands;

import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда, которая отображает информацию о коллекции.
 */
public class InfoCommand implements Command {

    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;

    public InfoCommand(Collection collectionManager, Messenger messenger, Printable printer){
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
                messenger.generateIncorrectNumberOfArgumentsMessage("info",0));
        String info = collectionManager.info();
        printer.println(info);
        printer.println(messenger.commandIsFinished("info"));
        return true;
    }
}
