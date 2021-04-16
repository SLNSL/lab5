package pattern;

import checker.*;
import com.google.gson.Gson;
import data.Person;
import data.Product;
import exception.*;
import messenger.Messenger;
import printer.Printable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс для коллекции
 */
public class CollectionManager implements Collection {
    /**
     * Коллекция
     */
    private Map<Integer, Product> productCollection = new LinkedHashMap<>();

    private LocalDateTime initTime;
    private Loader fileManager;
    private Messenger messenger;
    private Checker fieldsChecker;

    public CollectionManager(Loader fileManager, Checker fieldsChecker, Messenger messenger, Printable printer){

        this.fileManager = fileManager;
        this.messenger = messenger;
        this.fieldsChecker = fieldsChecker;
        load(printer);
    }

    /**
     * Загрузить коллекцию используя файл менеджер
     */
    public void load(Printable printer) {
        Creator creator = new ObjectCreator();
        productCollection = creator.createCollection(fileManager.load(fieldsChecker), new Gson(), fieldsChecker, messenger, printer);
        initTime = LocalDateTime.now();
    }

    /**
     * удаляет элемент по ключу
     *
     * @param key - ключ
     */
    public Result<Object> delete(Integer key) {
        Result<Object> result = new FieldResult<>();
        if (productCollection.get(key) == null) {
            result.setError(messenger.generateElementDoesntExistMessage());
            return result;
        }
        if (productCollection.get(key).getOwner() != null)
            fieldsChecker.getMapOfPassportId().put(productCollection.get(key).getOwner().getPassportID(), false);
        fieldsChecker.getMapOfId().put(productCollection.get(key).getId(), false);
        productCollection.remove(key);
        return result;
    }

    /**
     * сохранить коллекцию в файле
     */
    public void save() throws InputOutputException {
        fileManager.save(productCollection);
    }

    /**
     * добавить элемент в коллекцию
     *
     * @param key     - ключ
     * @param product - элемент
     */
    public void add(Integer key, Product product) {
        productCollection.put(key, product);
        if (product.getOwner() != null)
            fieldsChecker.getMapOfPassportId().put(product.getOwner().getPassportID(), true);
        fieldsChecker.getMapOfId().put(product.getId(), true);
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        productCollection = new LinkedHashMap<>();
        fieldsChecker.setMapOfId(new HashMap<>());
        fieldsChecker.setMapOfPassportId(new HashMap<>());
    }

    /**
     * Возвращает информацию о коллекции
     *
     * @return информация о коллекции в строковом представлении
     */
    public String info() {
        for (long i : fieldsChecker.getMapOfId().keySet()) {
            System.out.println(i + " " + fieldsChecker.getMapOfId().get(i) + " ");
        }
        System.out.println();
        for (String i : fieldsChecker.getMapOfPassportId().keySet()) {
            System.out.println(i + " " + fieldsChecker.getMapOfPassportId().get(i) + " ");
        }
        System.out.println();

        return messenger.getInfo(productCollection.getClass().getName(), Product.class.getName(), initTime, productCollection.size());
    }


    /**
     * Подсчитывает количество элементов, значение поля owner которых меньше указанного значения.
     *
     * @param person - человек
     * @return - количество
     */
    public int countLessThanOwner(Person person) {
        int count = 0;
        for (Integer key : productCollection.keySet()) {
            if (productCollection.get(key).getOwner() != null && person.compareTo(productCollection.get(key).getOwner()) > 0) {
                count += 1;
            }
        }
        return count;
    }


    /**
     * Возвращает поля элементов коллекции
     *
     * @return - поля элементов коллекции в строков представлении
     * @throws EmptyCollectionException - если коллекция пуста
     */
    public String writeCollection(){
        String collectionInfo = "";

        if (productCollection.size() == 0) {
            return messenger.generateEmptyCollectionMessage();
        } else {
            for (Integer key : productCollection.keySet()) {
                collectionInfo += messenger.getFieldsInfo(key, productCollection.get(key));
            }
        }
        return collectionInfo;
    }

    /**
     * Генерирует id, который еще не существует
     *
     * @return id
     */
    public long generateID() {
        Long id = Long.valueOf(productCollection.size()) + 1;
        while (fieldsChecker.isIdExist(id)) {
            id = id + 1;
        }
        return id;
    }

    /**
     * Возвращает продукт по id
     *
     * @param id
     * @return - product
     */
    public Result<Product> getProductById(long id) {
        Product product = null;
        Result<Product> result = new FieldResult<>();
        for (Integer key : productCollection.keySet()) {
            if (productCollection.get(key).getId() == id) product = productCollection.get(key);
        }
        if (product == null) {
            result.setError(messenger.generateElementDoesntExistMessage());
            return result;
        }
        result.setResult(product);
        return result;
    }

    /**
     * Выдаёт ключ по id
     *
     * @param id
     * @return - key
     */
    public Result<Integer> getKeyById(long id) {
        Result<Integer> keyResult = new FieldResult<>();
        for (Map.Entry<Integer, Product> pair : productCollection.entrySet()) {
            if (pair.getValue().getId() == id) {
                keyResult.setResult(pair.getKey());
            }
        }
        if (keyResult.getResult() == null) keyResult.setError(messenger.generateElementDoesntExistMessage());
        return keyResult;
    }

    /**
     * Возвращает первый элемент в коллекции
     *
     * @return first element
     */
    public Product getFirst() {
        sort();
        return productCollection.values().iterator().next();
    }

    /**
     * Возвращает элемент, который минимален по unitOfMeasure
     *
     * @return - элемент
     * @throws EmptyCollectionException - если коллекция пуста
     */
    public Result<Product> minByUnitOfMeasure(){
        if (productCollection.isEmpty()) {
            Result<Product> result = new FieldResult<>();
            result.setError(messenger.generateEmptyCollectionMessage());
            return result;
        }
        Product p1 = getFirst();
        for (Integer key : productCollection.keySet()) {
            if (productCollection.get(key).getUnitOfMeasure().compareTo(p1.getUnitOfMeasure()) <= 0) {
                p1 = productCollection.get(key);
            }
        }
        Result<Product> result = new FieldResult<>();
        result.setResult(p1);
        return result;
    }

    /**
     * Возвращает элемент, максимальный по unitOfMeasure
     *
     * @return элемент
     * @throws EmptyCollectionException - если коллекция пуста
     */
    public Result<Product> maxByUnitOfMeasure(){
        if (productCollection.isEmpty()) {
            Result<Product> result = new FieldResult<>();
            result.setError(messenger.generateEmptyCollectionMessage());
            return result;
        }
        Product p1 = getFirst();
        for (Integer key : productCollection.keySet()) {
            if (productCollection.get(key).getUnitOfMeasure().compareTo(p1.getUnitOfMeasure()) > 0) {
                p1 = productCollection.get(key);
            }
        }
        Result<Product> result = new FieldResult<>();
        result.setResult(p1);
        return result;
    }

    /**
     * Возвращает элемент по ключу
     *
     * @param key
     * @return - element
     */
    public Result<Product> get(Integer key) {
        Product product = productCollection.get(key);
        Result<Product> result = new FieldResult<>();
        if (product == null) {
            result.setError(messenger.generateElementDoesntExistMessage());
            return result;
        }
        result.setResult(product);
        return result;
    }

    /**
     * Удаляет все элементы из коллекции, которые меньше указанного
     *
     * @param product
     * @return количество элементов
     */
    public int removeLower(Product product) {
        int sizeBefore = productCollection.size();
        Iterator<Product> iterator = productCollection.values().iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.compareTo(product) < 0) {
                iterator.remove();
            }
        }
        int sizeAfter = productCollection.size();
        return sizeBefore - sizeAfter;

    }

    /**
     * Заменяет элемент с заданным ключом, если ведённый элемент больше старого
     *
     * @param key
     * @param p1  - product
     * @return true - если заменён или false - если не заменён
     */
    public Result<Boolean> replaceIfGreater(Integer key, Product p1){
        Result<Boolean> result = new FieldResult<>();
        Result<Product> productResult = get(key);
        if (productResult.hasError()) {
            result.setError(productResult.getError());
            return result;
        }
        Product p2 = productResult.getResult();

        if (p1.compareTo(p2) > 0) {
            if (p2.getOwner() != null) fieldsChecker.getMapOfPassportId().put(p2.getOwner().getPassportID(), false);
            fieldsChecker.getMapOfId().put(p2.getId(), false);
            add(key, p1);
            result.setResult(true);
            return result;
        } else {
            result.setResult(false);
            return result;
        }
    }

    /**
     * Заменяет элемент с заданным ключом, если ведённый элемент меньше старого
     *
     * @param key
     * @param p1  - product
     * @return true - если заменён или false - если не заменён
     */
    public Result<Boolean> replaceIfLower(Integer key, Product p1){
        Result<Boolean> result = new FieldResult<>();
        Result<Product> productResult = get(key);
        if (productResult.hasError()) {
            result.setError(productResult.getError());
            return result;
        }
        Product p2 = productResult.getResult();

        if (p1.compareTo(p2) < 0) {
            if (p2.getOwner() != null) fieldsChecker.getMapOfPassportId().put(p2.getOwner().getPassportID(), false);
            fieldsChecker.getMapOfId().put(p2.getId(), false);
            add(key, p1);
            result.setResult(true);
            return result;
        } else {
            result.setResult(false);
            return result;
        }
    }

    /**
     * Сортирует коллекции
     */
    public void sort() {
        List<Product> mapValues = new ArrayList<>(productCollection.values());
        LinkedHashMap<Integer, Product> newProductCollection = new LinkedHashMap<>();
        Collections.sort(mapValues);
        for (Product product : mapValues) {
            newProductCollection.put(getKey(product), product);
        }
        productCollection = newProductCollection;

    }

    /**
     * Возвращает ключ по продукту
     *
     * @param product
     * @return key
     */
    public Integer getKey(Product product) {
        for (Integer i : productCollection.keySet()) {
            if (productCollection.get(i).equals(product)) return i;
        }
        return null;
    }

    public Map<Integer, Product> getProductCollection() {
        return productCollection;
    }

}
