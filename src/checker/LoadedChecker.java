package checker;

import data.Product;
import printer.Printable;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that checks the collection after it is loaded.
 */
public class LoadedChecker {
    public static Map<Integer, Product> checkCollection(Map<Integer, Product> products, Checker fieldsChecker, Printable printer) {
        Map<Integer, Product> newCollection = new HashMap<>();
        Result<Object> result;
        for (Integer i : products.keySet()) {

                result = fieldsChecker.checkId(String.valueOf(products.get(i).getId()), true);
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkName(products.get(i).getName());
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkCoordinates(products.get(i).getCoordinates());
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkCoordinatesX(String.valueOf(products.get(i).getCoordinates().getX()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkCoordinatesY(String.valueOf(products.get(i).getCoordinates().getY()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkCreationDate(products.get(i).getCreationDate());
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkPrice(String.valueOf(products.get(i).getPrice()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkPartNumber(String.valueOf(products.get(i).getPartNumber()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkManufactureCost(String.valueOf(products.get(i).getManufactureCost()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                result = fieldsChecker.checkUnitOfMeasure(String.valueOf(products.get(i).getUnitOfMeasure()));
                if (result.hasError()) {
                    printer.printlnError(result.getError());
                    continue;
                }

                if (fieldsChecker.checkOwner(products.get(i).getOwner()) != null) {
                    result = fieldsChecker.checkOwnerPassportId(products.get(i).getOwner().getPassportID(), true);
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerName(products.get(i).getOwner().getName());
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerHairColor(String.valueOf(products.get(i).getOwner().getHairColor()));
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerLocationX(String.valueOf(products.get(i).getOwner().getLocation().getX()));
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerLocationY(String.valueOf(products.get(i).getOwner().getLocation().getY()));
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerLocationY(String.valueOf(products.get(i).getOwner().getLocation().getZ()));
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    result = fieldsChecker.checkOwnerLocationName(products.get(i).getOwner().getLocation().getName());
                    if (result.hasError()) {
                        printer.printlnError(result.getError());
                        continue;
                    }

                    fieldsChecker.getMapOfPassportId().put(products.get(i).getOwner().getPassportID(), true);
                }
                fieldsChecker.getMapOfId().put(products.get(i).getId(), true);
                newCollection.put(i, products.get(i));
        }
        products = newCollection;
        return products;
    }
}
