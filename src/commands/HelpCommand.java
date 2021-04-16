package commands;

import exception.IncorrectNumberOfArgumentsException;
import pattern.Collection;
import messenger.Messenger;
import printer.Printable;

/**
 * Команда, которая отображает справку для доступных команд
 */
public class HelpCommand implements Command {

    private Collection collectionManager;
    private Messenger messenger;
    private Printable printer;

    public HelpCommand(Collection collectionManager, Messenger messenger, Printable printer) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.printer = printer;
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
                messenger.generateIncorrectNumberOfArgumentsMessage("help",0));
        printer.println(messenger.getCommandsInfo());
        printer.println(messenger.commandIsFinished("help"));
        return true;
    }
}
