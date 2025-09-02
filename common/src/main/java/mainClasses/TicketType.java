package mainClasses;

import java.io.Serializable;

/**
 * Перечисление, представляющее должности работников
 */
public enum TicketType implements Serializable {
    VIP("вип"),
    USUAL("обычный"),
    BUDGETARY("бюджетный"),
    CHEAP("дешевый");

    private final String value;

    /**
     * Конструктор перечисления
     * @param value строковое представление должности
     */
    TicketType(String value) {
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