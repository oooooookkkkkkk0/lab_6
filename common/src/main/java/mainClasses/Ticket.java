package mainClasses;

import interfaces.Validatable;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий работника
 * Содержит всю информацию о работнике: личные данные, должность, зарплату и даты работы
 */
public class Ticket implements Validatable, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private Boolean refundable; //Поле не может быть null
    private TicketType ticketType; //Поле может быть null
    private Venue venue; //Поле может быть null

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm z");

    /**
     * Конструктор без параметров
     */
    public Ticket() {}

    /**
     * Конструктор с параметрами
     * @param id уникальный идентификатор
     * @param name имя работника
     * @param coordinates координаты работника
     * @param creationDate дата создания записи
     * @param venue личные данные работника
     */
    public Ticket(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, int price,
                  Boolean refundable, TicketType ticketType, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.refundable = refundable;
        this.ticketType = ticketType;
        this.venue = venue;
    }

    /**
     * Получает идентификатор работника
     * @return идентификатор
     */
    public Integer getId() {
        return id;
    }

    /**
     * Получает имя работника
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получает координаты работника
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public TicketType getType() {
        return ticketType;
    }

    /**
     * Получает дату создания записи
     * @return дата создания
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }


    /**
     * Получает личные данные работника
     * @return личные данные
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Устанавливает идентификатор работника
     * @param id идентификатор
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Устанавливает имя работника
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает координаты работника
     * @param coordinates координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает дату создания записи
     * @param creationDate дата создания
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Устанавливает личные данные работника
     * @param venue личные данные
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }
    public Boolean getRefundable() {
        return refundable;
    }
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
    public TicketType getTicketType() {
        return ticketType;
    }


    /**
     * Преобразует объект в строковое представление
     * @return строковое представление работника со всеми его данными
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Билет:\n");
        result.append("  ID билета: ").append(id != null ? id : "не установлен").append("\n");
        result.append("  Имя владельца: ").append(name != null ? name : "не указано").append("\n");
        result.append("  Координаты: ").append(coordinates != null ? coordinates : "не указаны").append("\n");
        result.append("  Дата создания: ").append(creationDate != null ? creationDate.format(dateFormatter) : "не указана").append("\n");
        result.append("  Цена билета: ").append(price > 0 ? price : "не указана").append("\n");
        result.append("  Возвратный билет?: ").append(refundable == false ? "нет" : "да").append("\n");
        result.append("  Тип билета: ").append(ticketType != null ? ticketType.getValue() : "не указан").append("\n");
        result.append("  Место проведения: ").append(venue != null ? venue.getName() : "не указано").append("\n");

        if (venue != null) {
            result.append("  Информация о месте проведения:\n");
            result.append("    ").append(venue.toString());
        } else {
            result.append("  Нет данных о месте проведения");
        }

        return result.toString();
    }

    /**
     * Вычисляет хеш-код объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + coordinates.hashCode() + creationDate.hashCode()
                + ticketType.hashCode() + venue.hashCode() + price + refundable.hashCode();
    }




    //    /**
//     * Сравнивает текущий объект с другим объектом
//     * @param o объект для сравнения
//     * @return true, если объекты равны, false в противном случае
//     */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ticket ticket = (Ticket) o;
//        return salary == ticket.salary &&
//                id.equals(ticket.id) &&
//                name.equals(ticket.name) &&
//                coordinates.equals(ticket.coordinates) &&
//                creationDate.equals(ticket.creationDate) &&
//                startDate.equals(ticket.startDate) &&
//                ticketType.equals(ticket.ticketType) &&
//                Objects.equals(endDate, ticket.endDate) &&
//                Objects.equals(venue, ticket.venue);
//    }
//
    @Override
    public boolean validate() throws IllegalArgumentException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным числом");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        coordinates.validate();

        if (creationDate == null) {
            throw new IllegalArgumentException("Дата создания не может быть null");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Зарплата должна быть больше 0");
        }
        if (refundable   == null) {
            throw new IllegalArgumentException("Дата начала работы не может быть null");
        }
        if (ticketType == null) {
            throw new IllegalArgumentException("Должность не может быть null");
        }
        if (venue != null) {
            venue.validate();
        }
        return true;
    }

}