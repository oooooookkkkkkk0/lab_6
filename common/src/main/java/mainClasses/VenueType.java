package mainClasses;

import java.io.Serializable;

/**
 * Перечисление, представляющее должности работников
 */
public enum VenueType implements Serializable {
    BAR("бар"),
    LOFT("лофт"),
    THEATRE("театр"),
    MALL("молл"),
    STADIUM("стадион");

    private final String value;

    /**
     * Конструктор перечисления
     * @param value строковое представление должности
     */
    VenueType(String value) {
        this.value = value;
    }

    /**
     * Получает строковое представление должности
     * @return строковое представление должности на русском языке
     */
    public String getValue() {
        return value;
    }
}