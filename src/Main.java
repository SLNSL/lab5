
import checker.Asker;
import checker.Checker;
import checker.FieldsAsker;
import checker.FieldsChecker;
import commands.*;
import pattern.*;
import printer.Printable;
import printer.Printer;
import messenger.Messenger;
import messenger.Translator;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("unchecked")
    public static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);

            Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args){
        disableAccessWarnings();

            Printable printer = new Printer();

            Scanner scanner = new Scanner(System.in);

            Messenger messenger = new Translator().setLanguage(printer);

            Checker fieldsChecker = new FieldsChecker(messenger);

            Asker fieldsAsker = new FieldsAsker(scanner, fieldsChecker, printer, messenger);

            Loader fileLoader = new FileManager(System.getenv("MyProducts"), printer, messenger);

            Collection collectionManager = new CollectionManager(fileLoader, fieldsChecker, messenger, printer);

            Reader commandReader = new CommandReader(fieldsAsker, printer);

            commandReader.start(messenger, new CommandManager(collectionManager, messenger, fieldsAsker, fieldsChecker, printer));

    }
}
