package checker;

import com.google.gson.Gson;
import data.Product;
import messenger.Messenger;
import pattern.Collection;
import printer.Printable;

import java.util.Map;

public interface Creator {

    Result<Object> createProduct(boolean willExist, Checker fieldsChecker, Asker fieldsAsker, Printable printer, Collection collectionManager);

    Result<Object> createOwner(boolean willExist, Checker fieldsChecker, Asker fieldsAsker, Printable printer);

    Map<Integer, Product> createCollection(StringBuilder data, Gson gson, Checker fieldsChecker, Messenger messenger, Printable printer);
}
