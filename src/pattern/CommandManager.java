package pattern;

import checker.Asker;
import checker.Checker;
import commands.*;
import exception.IncorrectNumberOfArgumentsException;
import printer.Printable;
import messenger.Messenger;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CommandManager implements Commander {

    /**
     * map, which contains links to the methods by their names
     */
    private HashMap<String, Converter<Boolean, String>> mapOfCommands = new LinkedHashMap<>();

    private Command help;
    private Command exit;
    private Command show;
    private Command save;
    private Command insert;
    private Command executeScript;
    private Command info;
    private Command removeKey;
    private Command update;
    private Command clear;
    private Command removeLower;
    private Command replaceIfGreater;
    private Command replaceIfLower;
    private Command minByUnitOfMeasure;
    private Command maxByUnitOfMeasure;
    private Command countLessThanOwner;

    public CommandManager(Collection collectionManager, Messenger messenger, Asker fieldsAsker, Checker fieldsChecker, Printable printer) {
        putLinkToCommands();
        createCommands(collectionManager, messenger, fieldsAsker, fieldsChecker, printer);
    }

    @Override
    public void createCommands(Collection collectionManager, Messenger messenger, Asker fieldsAsker, Checker fieldsChecker, Printable printer){
        help = new HelpCommand(collectionManager, messenger, printer);
        exit = new ExitCommand(messenger, printer);
        show = new ShowCommand(collectionManager, messenger, printer);
        save = new SaveCommand(collectionManager, messenger, printer);
        insert = new InsertCommand(collectionManager, fieldsAsker, fieldsChecker, messenger, printer);
        executeScript = new ExecuteScriptCommand(messenger);
        info = new InfoCommand(collectionManager, messenger, printer);
        removeKey = new RemoveKeyCommand(collectionManager, fieldsChecker, messenger, printer);
        update = new UpdateCommand(collectionManager, fieldsAsker, messenger, fieldsChecker, printer);
        clear = new ClearCommand(collectionManager, messenger, printer);
        removeLower = new RemoveLowerCommand(collectionManager, fieldsAsker, messenger, printer);
        replaceIfGreater = new ReplaceIfGreaterCommand(collectionManager, fieldsChecker, fieldsAsker, messenger, printer);
        replaceIfLower = new ReplaceIfLowerCommand(collectionManager, fieldsChecker, fieldsAsker, messenger, printer);
        minByUnitOfMeasure = new MinByUnitOfMeasureCommand(collectionManager, messenger, printer);
        maxByUnitOfMeasure = new MaxByUnitOfMeasureCommand(collectionManager, messenger, printer);
        countLessThanOwner = new CountLessThanOwnerCommand(collectionManager, fieldsChecker, fieldsAsker, messenger, printer);

    }

    /**
     * Кладёт в мапу ссылки на выполнение методов, которые выполняют команды
     */
    @Override
    public void putLinkToCommands() {
        mapOfCommands.put("help", this::help);
        mapOfCommands.put("exit", this::exit);
        mapOfCommands.put("show", this::show);
        mapOfCommands.put("save", this::save);
        mapOfCommands.put("insert", this::insert);
        mapOfCommands.put("execute_script", this::executeScript);
        mapOfCommands.put("info", this::info);
        mapOfCommands.put("remove_key", this::removeKey);
        mapOfCommands.put("update", this::update);
        mapOfCommands.put("clear", this::clear);
        mapOfCommands.put("remove_lower", this::removeLower);
        mapOfCommands.put("replace_if_greater", this::replaceIfGreater);
        mapOfCommands.put("replace_if_lower", this::replaceIfLower);
        mapOfCommands.put("min_by_unit_of_measure", this::minByUnitOfMeasure);
        mapOfCommands.put("max_by_unit_of_measure", this::maxByUnitOfMeasure);
        mapOfCommands.put("count_less_than_owner", this::countLessThanOwner);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean help(String argument) throws IncorrectNumberOfArgumentsException {
        return help.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean exit(String argument) throws IncorrectNumberOfArgumentsException {
        return exit.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean show(String argument) throws IncorrectNumberOfArgumentsException {
        return show.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean save(String argument) throws IncorrectNumberOfArgumentsException {
        return save.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean insert(String argument) throws IncorrectNumberOfArgumentsException {
        return insert.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean executeScript(String argument) throws IncorrectNumberOfArgumentsException {
        return executeScript.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean info(String argument) throws IncorrectNumberOfArgumentsException {
        return info.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean removeKey(String argument) throws IncorrectNumberOfArgumentsException {
        return removeKey.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean update(String argument) throws IncorrectNumberOfArgumentsException {
        return update.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean clear(String argument) throws IncorrectNumberOfArgumentsException {
        return clear.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean removeLower(String argument) throws IncorrectNumberOfArgumentsException {
        return removeLower.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean replaceIfGreater(String argument) throws IncorrectNumberOfArgumentsException {
        return replaceIfGreater.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean replaceIfLower(String argument) throws IncorrectNumberOfArgumentsException {
        return replaceIfLower.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean minByUnitOfMeasure(String argument) throws IncorrectNumberOfArgumentsException {
        return minByUnitOfMeasure.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean maxByUnitOfMeasure(String argument) throws IncorrectNumberOfArgumentsException {
        return maxByUnitOfMeasure.execute(argument);
    }

    /**
     * выполняет команду
     *
     * @param argument - аргумент
     * @return true, если команда сработала хорошо, false, если нет
     * @throws IncorrectNumberOfArgumentsException - если количество аргументов неверно
     */
    public boolean countLessThanOwner(String argument) throws IncorrectNumberOfArgumentsException {
        return countLessThanOwner.execute(argument);
    }


    public HashMap<String, Converter<Boolean, String>> getMapOfCommands() {
        return mapOfCommands;
    }
}
