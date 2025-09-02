package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

public class InsertAtCommand extends Command {
    public InsertAtCommand() {
        super("insert_at", "добавить новый элемент в заданную позицию",
                CommandType.WITH_TICKET_DATA,  true);
    }

    /**
     * Выполняет команду с заданными аргументами
     *
     * @param args              аргументы команды
     * @param collectionManager менеджер коллекции, над которой выполняется команда
     */
    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        return "";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        try {
            if (args.length != 1) throw new IllegalArgumentException();
            int id = Integer.parseInt(args[0]);
            return collectionManager.insertAt(ticket, id);
        } catch (IllegalArgumentException e) {
            return "Использование команды: insert_at index {element}";
        }
    }
}
