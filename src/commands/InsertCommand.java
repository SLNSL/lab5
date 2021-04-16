package commands;

import checker.*;
import data.*;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда, которая добавляет новый элемент с заданным ключом
 */
public class InsertCommand implements Command {

    private Collection collectionManager;
    private Asker fieldsAsker;
    private Printable printer;
    private Messenger messenger;
    private Checker fieldsChecker;

    public InsertCommand(Collection collectionManager, Asker fieldsAsker, Checker fieldsChecker, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
        this.printer = printer;
        this.messenger = messenger;
        this.fieldsChecker = fieldsChecker;
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
        if (!argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("insert", 0));
            Creator creator = new ObjectCreator();
            Integer key;

            Result<Integer> keyResult = fieldsChecker.checkKey(fieldsAsker.askKey());
            if (keyResult.hasError()) {
                printer.printlnError(keyResult.getError());
                return false;
            } else {
                key = keyResult.getResult();
                Result<Object> productResult = creator.createProduct(true, fieldsChecker, fieldsAsker, printer, collectionManager);
                if (!productResult.hasError()) {
                    collectionManager.add(key, (Product) productResult.getResult());
                    printer.println(messenger.commandIsFinished("insert"));
                    return false;
                } else {
                    printer.printlnError(productResult.getError());
                }
            }
            return true;
    }
}
