package commands;

import interfaces.Executable;
import mainClasses.Ticket;
import managers.CollectionManager;

import java.io.Serializable;
import java.util.Objects;

/**
 * Абстрактный базовый класс для всех команд
 * Реализует интерфейс Executable и определяет основные характеристики команды
 */
public abstract class Command implements Executable, Serializable {

    public enum CommandType{
        WITH_TICKET_DATA, WITHOUT_TICKET_DATA, WITH_SCRIPT_FILE, WITH_TYPE_DATA;
    }

    private final String name;
    private final String description;
    public final CommandType commandType;
    public boolean needArgs;

    /**
     * Конструктор команды
     * @param name название команды
     * @param description описание команды
     */
    public Command(String name, String description, CommandType commandType, boolean needArgs) {
        this.name = name;
        this.description = description;
        this.commandType = commandType;
        this.needArgs = needArgs;
    }

    /**
     * Получает название команды
     * @return название команды
     */
    public String getName(){
        return name;
    }

    /**
     * Получает описание команды
     * @return описание команды
     */
    public String getDescription(){
        return description;
    }

    public CommandType getCommandType() { return commandType; }

    /**
     * Сравнивает текущий объект с другим объектом
     * @param o объект для сравнения
     * @return true, если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    /**
     * Вычисляет хеш-код объекта
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Преобразует объект в строковое представление
     * @return строковое представление команды
     */
    @Override
    public String toString() {
        return name + " - " + description + ";";
    }

    /**
     * Выполняет команду с заданными аргументами
     * @param args аргументы команды
     * @param collectionManager менеджер коллекции, над которой выполняется команда
     */
    public abstract String execute(String[] args, CollectionManager collectionManager);
    public abstract String execute(String[] args, CollectionManager collectionManager, Ticket ticket);
}

