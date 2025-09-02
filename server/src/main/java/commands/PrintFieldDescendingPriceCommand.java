package commands;

import mainClasses.Ticket;
import managers.CollectionManager;

public class PrintFieldDescendingPriceCommand extends Command {
    public PrintFieldDescendingPriceCommand() {
        super("print_field_descending_price", "вывести значения поля price всех элементов в порядке убывания",
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
        return collectionManager.printFieldDescendingPrice();
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        return "";
    }
}
