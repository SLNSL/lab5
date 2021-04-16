package commands;

import exception.IncorrectNumberOfArgumentsException;
import printer.Printable;
import messenger.Messenger;

/**
 * Команда, которая завершает программу
 */
public class ExitCommand implements Command {

    private Printable printer;
    private Messenger messenger;

    public ExitCommand(Messenger messenger, Printable printer) {
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
    public boolean execute(String argument) throws IncorrectNumberOfArgumentsException{
        if(!argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("exit",0));
        printer.println(messenger.commandIsFinished("exit"));
        return true;
    }
}
