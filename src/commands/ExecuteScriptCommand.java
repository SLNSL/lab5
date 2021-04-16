package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;

/**
 * Команда, которая выполняет скрипт
 */
public class ExecuteScriptCommand implements Command {

    private Messenger messenger;

    public ExecuteScriptCommand(Messenger messenger){
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
        if (argument.isEmpty()) throw new IncorrectNumberOfArgumentsException(
                messenger.generateIncorrectNumberOfArgumentsMessage("execute_script",1));
        return true;
    }
}
