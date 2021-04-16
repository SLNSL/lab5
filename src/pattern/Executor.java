package pattern;

import exception.IncorrectNumberOfArgumentsException;

import java.util.Map;

public interface Executor {

    int runCommand(String[] command) throws IncorrectNumberOfArgumentsException;

    boolean runScript(String argument);

    Map<String, Boolean> getMapOfScripts();

    void setMapOfScripts(Map<String, Boolean> mapOfScripts);
}
