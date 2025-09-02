package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

public class PrintFieldAscendingVenueCommand extends Command {
    public PrintFieldAscendingVenueCommand() {
        super("print_field_ascending_venue", "вывести значения поля venue всех элементов в порядке возрастания",
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
        return collectionManager.printFieldAscendingVenue();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        return "";
    }
}
