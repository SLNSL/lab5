package commands;

import checker.*;
import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда которая удаляет из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLowerCommand implements Command {

    private Collection collectionManager;
    private Asker asker;
    private Printable printer;
    private Messenger messenger;

    public RemoveLowerCommand(Collection collectionManager, Asker asker, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.asker = asker;
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
        if (!argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("remove_lower", 0));

        Creator creator = new ObjectCreator();
        Product product = new Product();
        Result<Product> productResult = new FieldResult<>();
        if (productResult.hasError()) {
            printer.printlnError(productResult.getError());
            return false;
        } else {
            product = productResult.getResult();
            collectionManager.removeLower(product);
            printer.println(messenger.commandIsFinished("remove_lower"));
            return true;
        }
    }
}
