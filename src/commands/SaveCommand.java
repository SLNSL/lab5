package commands;

import exception.IncorrectNumberOfArgumentsException;
import exception.InputOutputException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command {
    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;

    public SaveCommand(Collection collectionManager, Messenger messenger, Printable printer){
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
                messenger.generateIncorrectNumberOfArgumentsMessage("save",0));
        collectionManager.sort();
        try {
            collectionManager.save();
            printer.println(messenger.commandIsFinished("save"));
            return true;
        } catch (InputOutputException e){
            printer.printlnError(e.getMessage());
        }
        return false;
    }
}
