package commands;

import checker.*;
import data.*;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая заменяет элемент по полю id на ведённый
 */
public class UpdateCommand implements Command {

    private Asker asker;
    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;
    private Checker fieldsChecker;

    public UpdateCommand(Collection collectionManager, Asker asker, Messenger messenger, Checker fieldsChecker, Printable printer) {
        this.asker = asker;
        this.collectionManager = collectionManager;
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
                messenger.generateIncorrectNumberOfArgumentsMessage("update", 1));

        Result<Object> idResult = fieldsChecker.checkId(argument, false);
        if (idResult.hasError()) {
            printer.printlnError(idResult.getError());
            return false;
        }
        long id = (Long) idResult.getResult();

        Result<Integer> keyResult = collectionManager.getKeyById(id);
        if (keyResult.hasError()) {
            printer.printlnError(keyResult.getError());
            return false;
        } else {
            Creator creator = new ObjectCreator();
            if (collectionManager.get(keyResult.getResult()).getResult().getOwner() != null)
                fieldsChecker.getMapOfPassportId().put(collectionManager.get(keyResult.getResult()).getResult().getOwner().getPassportID(), false);
            Result<Object> productResult = creator.createProduct(true, fieldsChecker, asker, printer, collectionManager);
            if (productResult.hasError()) {
                printer.printlnError(productResult.getError());
                return false;
            } else {
                Product product = (Product) productResult.getResult();

                fieldsChecker.getMapOfId().remove(product.getId()); // ДЕЛАЕТСЯ ДЛЯ ТОГО,
                product.setId(id);                                    // ЧТОБЫ ID У ПРОДУКТА НЕ ИЗМЕНИЛСЯ
                collectionManager.add(keyResult.getResult(), product);
                printer.println(messenger.commandIsFinished("update"));
                return true;

            }

        }
    }
}
