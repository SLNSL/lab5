package pattern;

import exception.IncorrectNumberOfArgumentsException;

@FunctionalInterface
public interface Converter <T, F>{
    T convert(F from) throws IncorrectNumberOfArgumentsException;
}
