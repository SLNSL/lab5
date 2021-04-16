package commands;

import checker.*;
import data.Person;
import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда, которая подсчитывает количество элементов, значение поля owner которых меньше указанного значения
 */
public class CountLessThanOwnerCommand implements Command {

    private Collection collectionManager;
    private Asker fieldsAsker;
    private Printable printer;
    private Messenger messenger;
    private Checker fieldsChecker;

    public CountLessThanOwnerCommand(Collection collectionManager, Checker fieldsChecker, Asker fieldsAsker, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
        this.printer = printer;
        this.messenger = messenger;
        this.fieldsChecker = fieldsChecker;
    }

    /**
     * Выполнение команды
     * @param argument - аргумент, который может быть передан или не передан вместе с командой
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public boolean execute(String argument) throws IncorrectNumberOfArgumentsException{
        if(!argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("count_less_than_owner",0));
//        try {
            Person owner;
            Creator creator = new ObjectCreator();
            Result<Object> ownerResult = creator.createOwner(false, fieldsChecker, fieldsAsker, printer);
            if (ownerResult.hasError()){
                printer.printlnError(ownerResult.getError());
                return false;
            } else {
                owner = (Person) ownerResult.getResult();
                int count = collectionManager.countLessThanOwner(owner);
                printer.println(messenger.lessThanOwner(count));
                printer.println(messenger.commandIsFinished("count_less_than_owner"));
            }

            return true;
//        } catch (IncorrectInputException e){
//            printer.printlnError(e.getMessage());
//        }
//
//        return false;
    }
}
