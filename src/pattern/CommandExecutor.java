package pattern;

import checker.Asker;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import printer.Printable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandExecutor implements Executor {

    private Map<String, Boolean> mapOfScripts;

    private Asker asker;

    private Printable printer;
    private Messenger messenger;

    private Commander commandManager;

    /**
     * Класс отвечающий за выполнение команд
     * @param commandManager - класс хранящий в себе выполнение команд
     * @param printer - класс выводящий информацию
     * @param messenger - класс возвращающий информацию на нужном языке
     * @param asker - класс запраивающий ввод полей
     */
    CommandExecutor(Commander commandManager, Printable printer, Messenger messenger, Asker asker){
        this.commandManager = commandManager;
        this.printer = printer;
        this.messenger = messenger;
        this.asker = asker;
    }

    /**
     * Выполняет введённую команду
     * @param command - команда и аргументы
     * @return - true - если команда выполнена успешно
     * @throws IncorrectNumberOfArgumentsException - некорректное число аргументов

     */
    public int runCommand(String[] command) throws IncorrectNumberOfArgumentsException{

        if (command[0].trim().equals("exit") && command[1].trim().equals("")) return 2;

        if (command[0].trim().equals("execute_script")) {
            if (!commandManager.getMapOfCommands().get(command[0]).convert(command[1].trim())) return 0;
            else {
                if (runScript(command[1].trim())) return 1;
                else return 0;
            }

        } else {
            if (commandManager.getMapOfCommands().get(command[0]) == null){
                if (!command[0].equals("")) {
                    printer.printlnError(messenger.generateUnknownCommandMessage());
                }
                return 0;
            }
            else {
                if (commandManager.getMapOfCommands().get(command[0]).convert(command[1].trim())) return 1;
                else return 0;
            }
        }
    }

    /**
     * Обрабатывает скрипт
     * @param argument - путь до скрипта
     * @return - true - если скрипт выполнен успешно

     */
    public boolean runScript(String argument){
        mapOfScripts.put(argument, true);
        try (Scanner scriptScanner = new Scanner(new FileReader(argument))) {
            do {
                String fullCommand = scriptScanner.nextLine().trim() + " ";
                String[] command = fullCommand.split(" ", 2);

                asker.setScanner(scriptScanner);
                asker.setScriptMode(true);
                if (command[0].equals("execute_script")) {
                    if (mapOfScripts.get(command[1].trim()) != null && mapOfScripts.get(command[1].trim())) {
                        printer.printlnError(messenger.generateScriptRecursionMessage());
                        continue;
                    }
                }
                if (!command[0].equals("")) {
                    for (String s : command) {
                        printer.print(s + " ");
                    }
                    printer.println("");
                }
                try {
                    runCommand(command);
                } catch (IncorrectNumberOfArgumentsException e) {
                    printer.printlnError(e.getMessage());
                }

            } while (scriptScanner.hasNext());

            mapOfScripts.put(argument, false);
            printer.println(messenger.scriptIsFinished(argument));
        } catch (FileNotFoundException e) {
            printer.printlnError(messenger.generateFileWasNotFoundMessage());
        } catch (NoSuchElementException e) {
            printer.printlnError(messenger.generateNoLineFoundMessage());

        }

        return true;
    }

    public Map<String, Boolean> getMapOfScripts() {
        return mapOfScripts;
    }

    @Override
    public void setMapOfScripts(Map<String, Boolean> mapOfScripts) {
        this.mapOfScripts = mapOfScripts;
    }
}
