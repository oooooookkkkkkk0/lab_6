package commands;

import mainClasses.Ticket;
import mainClasses.TicketType;
import managers.CollectionManager;

public class CountGreaterThanTypeCommand extends Command {
    public CountGreaterThanTypeCommand() {
        super("count_greater_than_type", "вывести количество элементов, значение поля type которых больше заданного",
                CommandType.WITHOUT_TICKET_DATA, true);
    }

    /**
     * Выполняет команду с заданными аргументами
     *
     * @param args              аргументы команды
     * @param collectionManager менеджер коллекции, над которой выполняется команда
     */

    @Override
    public String execute(String[] args, CollectionManager collectionManager, Ticket ticket) {
        return "";
    }

    @Override
    public String execute(String[] args, CollectionManager collectionManager) {
        TicketType ticketType = null;
        String type = args[0];
        for (TicketType ticketType1 : TicketType.values()) {
            if (ticketType1.getValue().equals(type)) {
                ticketType = ticketType1;
            }
        }
        if (ticketType == null) {
            return "Введен несуществующий тип билета!";
        }
        long count = collectionManager.countGreaterThan(ticketType);
        return "Итоговое кол-во: " + count;
    }
}
