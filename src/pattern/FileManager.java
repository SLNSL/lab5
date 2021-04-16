package pattern;

import checker.Checker;
import checker.LoadedChecker;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.*;
import exception.*;
import printer.Printable;
import messenger.Messenger;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class FileManager implements Loader {

    private File file;
    private Gson gson = new Gson();
    private Printable printer;
    private Messenger messenger;

    public FileManager(String path, Printable printer, Messenger messenger) {
        this.file = new File(path);
        this.messenger = messenger;
        this.printer = printer;
    }

    /**
     * Загружает коллекцию из файла
     *
     * @return - collection
     * @throws FileWasNotFoundException - если файл не был найден
     * @throws EmptyFileException       - если файл пуст
     * @throws SyntaxJsonException      - если это была синтаксическая ошибка json
     * @throws UnexpectedException      - если это была неожиданная ошибка
     * @throws InputOutputException     - если это было исключение ввода/вывода
     */
    public StringBuilder load(Checker fieldsChecker) {
        Map<Integer, Product> products = new LinkedHashMap<Integer, Product>();

        String thisLine;
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            thisLine = reader.readLine();
            while (thisLine != null) {
                result.append(thisLine);
                thisLine = reader.readLine();
            }
        } catch (IOException e){
            return null;
        }
        return result;
    }

    /**
     * Сохранение коллекции в файл
     *
     * @param collection
     * @throws InputOutputException - если это было исключение ввода/вывода
     */
    public void save(Map<Integer, Product> collection) throws InputOutputException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gson.toJson(collection));
            printer.printlnError(messenger.collectionIsSaved());
        } catch (IOException e) {
            throw new InputOutputException(messenger.generateInputOutputMessage());
        }
    }

    public Printable getPrinter() {
        return printer;
    }

    public void setPrinter(Printable printer) {
        this.printer = printer;
    }
}
