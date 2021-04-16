package pattern;

import checker.Result;
import data.Person;
import data.Product;
import exception.*;
import printer.Printable;

import java.io.IOException;
import java.util.*;


/**
 * Интерфейс для менджера коллекций. Содержит методы для сортировки, очистки и т.д.
 */
public interface Collection {
    void load(Printable printer) throws IOException;

    Result<Object> delete(Integer key);

    void save();

    void add(Integer key, Product product);

    void clear();

    String info();

    int countLessThanOwner(Person person);

    String writeCollection();

    long generateID();

    Result<Product> getProductById(long id);

    Result<Integer> getKeyById(long id);

    Product getFirst();

    Result<Product> minByUnitOfMeasure();

    Result<Product> maxByUnitOfMeasure();

    Result<Product> get(Integer key);

    int removeLower(Product product);

    Result<Boolean> replaceIfGreater(Integer key, Product p1);

    Result<Boolean> replaceIfLower(Integer key, Product p1);

    void sort();

    Integer getKey(Product product);

    Map<Integer, Product> getProductCollection();

}



