package commands;

import exception.IncorrectNumberOfArgumentsException;

public interface Command{
    boolean execute(String argument) throws IncorrectNumberOfArgumentsException;
}
