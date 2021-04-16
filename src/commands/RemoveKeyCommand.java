package commands;

import checker.Checker;
import checker.Result;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая удаляет элемент по ключу
 */
public class RemoveKeyCommand implements Command {

    private Collection collectionManager;
    private Checker fieldsChecker;
    private Printable printer;
    private Messenger messenger;

    public RemoveKeyCommand(Collection collectionManager, Checker fieldsChecker, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.fieldsChecker = fieldsChecker;
        this.printer = printer;
        this.messenger = messenger;
    }

    /**
     * Выполнение команды
     *
     * @param argument - аргумент, который может быть передан или не передан вместе с командой
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public boolean execute(String argument) throws IncorrectNumberOfArgumentsException {
        if (argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("remove_key", 1));
//        try {
            Result<Integer> keyResult = fieldsChecker.checkKey(argument);
            if (keyResult.hasError()) {
                printer.printlnError(keyResult.getError());
                return false;
            } else {
                Result<Object> result = collectionManager.delete(keyResult.getResult());
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    return false;
                } else {
                    printer.println(messenger.commandIsFinished("remove_key"));
                    return true;
                }
            }
//        } catch (IncorrectInputException e) {
//            printer.printlnError(e.getMessage());
//        }
//        return false;
    }
}
