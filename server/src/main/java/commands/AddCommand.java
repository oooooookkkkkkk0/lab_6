package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddCommand extends Command {

    /**
     * Создает команду add
     */
    public AddCommand() {
        super("add", "добавить новый элемент в коллекцию",
                CommandType.WITH_TICKET_DATA, false);
    }

    /**
     * Исполняет команду
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции
     */
    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        return "";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        collectionManager.addElement(ticket);
        return "Билет добавлен в коллекцию";
    }


}