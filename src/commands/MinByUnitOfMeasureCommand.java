package commands;

import checker.Result;
import data.Product;
import exception.EmptyCollectionException;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая выводит любой объект из коллекции, значение поля unitOfMeasure которого является минимальным
 */
public class MinByUnitOfMeasureCommand implements Command {

    private Collection collectionManager;
    private Printable printer;
    private Messenger messenger;

    public MinByUnitOfMeasureCommand(Collection collectionManager, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.printer = printer;
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
                messenger.generateIncorrectNumberOfArgumentsMessage("min_by_unit_of_measure", 0));

        Result<Product> productResult = collectionManager.minByUnitOfMeasure();
        if (productResult.hasError()) {
            printer.printlnError(productResult.getError());
            return false;
        }
        Product product = productResult.getResult();


        Result<Integer> keyResult = collectionManager.getKeyById(product.getId());
        if (keyResult.hasError()) {
            printer.printlnError(keyResult.getError());
            return false;
        } else {
            String elementInfo = messenger.getFieldsInfo(keyResult.getResult(), product);
            printer.println(elementInfo);
            printer.println(messenger.commandIsFinished("min_by_unit_of_measure"));
            return true;
        }

    }
}