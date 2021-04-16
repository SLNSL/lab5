package pattern;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;

import java.util.Map;

public interface Reader {

    void start(Messenger messenger, Commander commandManager);

//    boolean runCommand(String[] command) throws IncorrectNumberOfArgumentsException, ExitTheProgramException;
//
//    boolean runScript(String argument) throws ExitTheProgramException;
//
//    Map<String, Converter<Boolean, String>> mapOfCommands();
}
