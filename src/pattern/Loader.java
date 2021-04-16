package pattern;

import checker.Checker;
import data.Product;
import exception.InputOutputException;

import java.io.IOException;
import java.util.Map;

public interface Loader {
    void save(Map<Integer, Product> collection) throws InputOutputException;

    StringBuilder load(Checker checker);


}
