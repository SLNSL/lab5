package commands;

import checker.*;
import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая заменяет значение по ключу, если новое значение больше старого
 */
public class ReplaceIfGreaterCommand implements Command {

    private Collection collectionManager;
    private Asker fieldsAsker;
    private Printable printer;
    private Messenger messenger;
    private Checker fieldsChecker;

    public ReplaceIfGreaterCommand(Collection collectionManager, Checker fieldsChecker, Asker fieldAsker, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldAsker;
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
        if (argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("replace_if_greater", 1));

        Result<Integer> keyResult = fieldsChecker.checkKey(argument);

        if (keyResult.hasError()) {
            printer.printlnError(keyResult.getError());
            return false;
        } else {
            if (collectionManager.get(keyResult.getResult()).getResult().getOwner() != null) {
                fieldsChecker.getMapOfPassportId().put(collectionManager.get(keyResult.getResult()).getResult().getOwner().getPassportID(), false);
            }
            Creator creator = new ObjectCreator();

            Result<Object> productResult = creator.createProduct(true, fieldsChecker, fieldsAsker, printer, collectionManager);
            if (productResult.hasError()) {
                printer.printlnError(productResult.getError());
                return false;
            } else {
                Result<Boolean> replaceOrNot = collectionManager.replaceIfGreater(keyResult.getResult(), (Product) productResult.getResult());
                if (replaceOrNot.hasError()) {
                    printer.printlnError(replaceOrNot.getError());
                    return false;
                }
                printer.println(messenger.elementReplaced(replaceOrNot.getResult()));
                if (!replaceOrNot.getResult() && collectionManager.get(keyResult.getResult()).getResult().getOwner() != null) {
                    fieldsChecker.getMapOfPassportId().put(collectionManager.get(keyResult.getResult()).getResult().getOwner().getPassportID(), true);
                }
                printer.println(messenger.commandIsFinished("replace_if_greater"));
                return true;
            }

        }
    }
}
