package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

/**
 * Команда 'update'
 * Обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand extends Command {

    /**
     * Создает команду update
     */
    public UpdateCommand() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному",
                CommandType.WITH_TICKET_DATA, true);
    }

    /**
     * Исполняет команду
     * @param args аргументы команды (id элемента для обновления)
     * @param collectionManager менеджер коллекции
     */
    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        return "";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        try {
            if (args.length < 1) throw new IllegalArgumentException();
            int id = Integer.parseInt(args[0]);
            ticket.setId(id);
            collectionManager.updateElement(id, ticket);
            return "Значение элемента обновлено";
        } catch (IllegalArgumentException e) {
            return "Использование: update [id]";
        }
    }
}