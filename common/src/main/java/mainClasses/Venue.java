package mainClasses;

import interfaces.Validatable;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий информацию о человеке
 * Содержит основные характеристики: дату рождения, рост и вес
 */
public class Venue implements Validatable , Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long capacity; //Значение поля должно быть больше 0
    private VenueType venueType; //Поле не может быть null

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Конструктор без параметров
     */
    public Venue() {}

    /**
     * Конструктор с параметрами
     */
    public Venue(Integer id, String name, long capacity, VenueType venueType) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.venueType = venueType;

    }

    public Venue(String name, long capacity, VenueType venueType) {
        this.name = name;
        this.capacity = capacity;
        this.venueType = venueType;

    }

    public Integer getId() {
        return id;
    }
    public VenueType getType() {
        return venueType;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getCapacity() {
        return capacity;
    }
    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }
    public VenueType getVenueType() {
        return venueType;
    }
    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }



    /**
     * Преобразует объект в строковое представление
     * @return строковое представление объекта Person
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" ID места проведения: ").append(id != null ? id : "не указан").append("\n");
        result.append("     Название: ").append(name != null ? name : "не указано").append("\n");
        result.append("     Вместимость: ").append(capacity > 0 ? capacity : "не указана").append("\n");
        result.append("     Тип места проведения: ").append(venueType != null ? venueType.getValue() : "не указан").append("\n");
        return result.toString();
    }

    /**
     * Вычисляет хеш-код объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + venueType.hashCode();
    }

    /**
     * Сравнивает текущий объект с другим объектом
     * @param o объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return id.equals(venue.id) &&
                name.equals(venue.name) &&
                venueType.equals(venue.venueType);
    }

    @Override
    public boolean validate() throws IllegalArgumentException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Рост не может быть null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Рост должен быть больше 0");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше 0");
        }
        if (venueType == null) {
            throw new IllegalArgumentException("Тип мероприятия не может быть null");
        }
        return true;
    }
}