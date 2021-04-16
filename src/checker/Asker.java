package checker;

import data.Color;
import data.UnitOfMeasure;

import java.util.Scanner;

/**
 * Интерфейс, имеющий методы, которые будут запрашивать ввод полей
 */
public interface Asker {

    String askKey();

    String askId(boolean willExist);

    String askName();

    String askCoordinatesX();

    String askCoordinatesY();

    String askPrice();

    String askPartNumber();

    String askManufactureCost();

    String askUnitOfMeasure();

    String askOwnerName();

    String askOwnerPassportID();

    String askOwnerHairColor();

    String askOwnerLocationX();

    String askOwnerLocationY();

    String askOwnerLocationZ();

    String askOwnerLocationName();

    void setScanner(Scanner scanner);

    Scanner getScanner();

    void setScriptMode(boolean scriptMode);

    boolean isScriptMode();
}
