package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

import java.util.Vector;

public class SortCommand extends Command {
    public SortCommand() {
        super("sort", "отсортировать коллекцию в естественном порядке",
                CommandType.WITHOUT_TICKET_DATA, false);
    }

    /**
     * Выполняет команду с заданными аргументами
     *
     * @param args              аргументы команды
     * @param collectionManager менеджер коллекции, над которой выполняется команда
     */
    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        Vector<Ticket> sortedCollection =  collectionManager.sortCollection();
        return "Коллекция отсортирована успешно: \n" + sortedCollection.toString();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        return "";
    }
}
