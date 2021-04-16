package commands;

import exception.EmptyCollectionException;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Command {

    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;

    public ShowCommand(Collection collectionManager, Messenger messenger, Printable printer){
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
                messenger.generateIncorrectNumberOfArgumentsMessage("show",0));
        collectionManager.sort();
        try {
            String collectionInfo = collectionManager.writeCollection();
            printer.println(collectionInfo);
            printer.println(messenger.commandIsFinished("show"));
            return true;
        } catch (EmptyCollectionException e) {
            printer.printlnError(e.getMessage());
        }
        return false;
    }
}
