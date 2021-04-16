package messenger;

import printer.Printable;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс ответственный за установку и проверку языка.
 */
public class Translator {
    private String language;
    private LinkedHashMap<String, Messenger> availableLanguages = new LinkedHashMap<>();

    {
        availableLanguages.put("rus", new MessengerRu());
        availableLanguages.put("eng", new MessengerEng());
    }

    /**
     * Спрашивает какой язык будет использовать пользователь
     *
     * @param printer
     * @return - Класс Messenger с ведённым языком
     */
    public Messenger setLanguage(Printable printer) {
        printer.println("Введите язык / Enter the language (rus/eng):");
        String language = "";
        Scanner scanner = new Scanner(System.in);
        while (language.equals("")) {
            try {
                language = scanner.nextLine().trim();
                if (!language.isEmpty()) {
                    if ((!getAvailableLanguages().containsKey(language))) {
                        printer.println("Поле language введено неверно! / Field language entered incorrectly!");
                        return null;
                    } else {
                        return availableLanguages.get(language);
                    }
                }
            } catch (NoSuchElementException e) {
                return null;
            }
        }
        throw null;
    }


    /**
     * gives map of available languages
     *
     * @return map of available languages
     */
    public LinkedHashMap<String, Messenger> getAvailableLanguages() {
        return availableLanguages;
    }

    /**
     * gives language on which the user is working
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * sets language on which the user is working
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}
