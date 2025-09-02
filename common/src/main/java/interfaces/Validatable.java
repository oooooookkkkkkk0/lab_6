package interfaces;

/**
 * Интерфейс для валидации данных объектов
 * Классы, реализующие этот интерфейс, должны предоставить метод проверки корректности своих данных
 */
public interface Validatable {
    /**
     * Проверяет корректность данных объекта
     *
     * @return
     * @throws IllegalArgumentException если данные некорректны
     */
    boolean validate() throws IllegalArgumentException;
}