package managers;

import mainClasses.Ticket;
import mainClasses.TicketType;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Менеджер коллекции работников
 * Управляет коллекцией объектов Worker, обеспечивая операции добавления, удаления и модификации элементов
 */
public class CollectionManager {
    private Vector<Ticket> ticketsCollection;
    private final LocalDate creationDate;
    private final CollectionWriter writer = new CollectionWriter();
    private final CollectionParser parser = new CollectionParser(this);
    private String filePath;

    /**
     * Создает новый менеджер коллекции
     * Инициализирует пустую коллекцию и устанавливает дату создания
     */
    public CollectionManager() {
        ticketsCollection = new Vector<Ticket>();
        creationDate = LocalDate.now();
    }

    /**
     * Устанавливает путь к файлу для сохранения/загрузки коллекции
     * @param filePath путь к файлу
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Загружает коллекцию из файла
     *
     * @param filePath путь к файлу
     */
    public String loadCollectionFromFile(String filePath) {
        this.filePath = filePath;
        HashMap<String, Vector<Ticket>> zalupa = new HashMap<>();
        zalupa = parser.parseFromFile(filePath);
        String message = zalupa.keySet().iterator().next();
        ticketsCollection = zalupa.get(message);
        return message;
    }

    /**
     * Сохраняет коллекцию в файл
     * @throws IllegalStateException если путь к файлу не установлен
     */
    public void saveCollectionToFile() {
        if (filePath == null) {
            throw new IllegalStateException("Путь к файлу не установлен");
        }
        writer.writeToFile(filePath, ticketsCollection);
    }

    /**
     * Возвращает дату создания коллекции
     * @return дата создания
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает коллекцию работников
     * @return коллекция работников
     */
    public Vector<Ticket> getTicketsCollection() {
        return ticketsCollection;
    }

    /**
     * Устанавливает новую коллекцию работников
     * @param ticketsCollection новая коллекция
     */
    public void setTicketsCollection(Vector<Ticket> ticketsCollection) {
        this.ticketsCollection = ticketsCollection;
    }

    /**
     * Возвращает информацию о коллекции
     * @return строка с информацией о типе, дате создания и размере коллекции
     */
    public String getCollectionInfo() {
        return ("Type - " + ticketsCollection.getClass().getName() +
                "\nCreation date - " + getCreationDate() +
                "\nAmount of elements - " + ticketsCollection.size());
    }

    /**
     * Выводит все элементы коллекции
     */
    public String showCollectionElements() {
        StringBuilder res = new StringBuilder();
        if (ticketsCollection.isEmpty()) {
            res.append("Коллекция пуста");
        } else {
            ticketsCollection.stream()
                    .sorted(Comparator.comparing(Ticket::getName))
                    .map(Ticket::toString).forEach(res::append);
        }
        return res.toString();
    }

    /**
     * Добавляет нового работника в коллекцию
     * @param ticket новый работник
     */
    public void addElement(Ticket ticket) {
        ticket.setId(generateId());
        if (ticket == null) {
            throw new IllegalArgumentException("Билет не может быть null");
        }

        ticketsCollection.add(ticket);
    }

    /**
     * Обновляет данные работника по его id
     * @param id идентификатор работника
     * @param new_ticket новые данные работника
     */
    public void updateElement(int id, Ticket new_ticket) {
        ticketsCollection.stream().filter(ticket -> ticket.getId() == id).findFirst().
                ifPresent(ticket -> {
                    ticket.setId(new_ticket.getId());
                    ticket.setName(new_ticket.getName());
                    ticket.setCoordinates(new_ticket.getCoordinates());
                    ticket.setPrice(new_ticket.getPrice());
                    ticket.setRefundable(new_ticket.getRefundable());
                    ticket.setTicketType(new_ticket.getTicketType());
                    ticket.setVenue(new_ticket.getVenue());
                });
    }

    /**
     * Удаляет работника по его id
     * @param id идентификатор работника
     */
    public void removeElement(int id) {
        ticketsCollection.removeIf(ticket -> ticket.getId() == id);
    }

    /**
     * Очищает коллекцию
     */
    public void clearCollection() {
        ticketsCollection.clear();
    }

    /**
     * Удаляет первый элемент коллекции
     */
    public void removeFirstElement() {
        ticketsCollection.remove(0);
    }

    public String insertAt(Ticket ticket, int index) {
        if (ticketsCollection.size() <= index) {
            return "Позиция нового элемента не может быть больше количества элементов в коллекции!";
        }
        ticket.setId(generateId());
        ticketsCollection.add(index, ticket);
        return "Элемент успешно добавлен на позицию " + index;
    }

    public Vector<Ticket> sortCollection() {
        Comparator<Ticket> idComparator = Comparator.comparingInt(Ticket::getId);
        Vector<Ticket> sortedCollection = ticketsCollection.stream()
                .sorted(idComparator)
                .collect(Collectors.toCollection(Vector::new));
        return sortedCollection;
    }


    /**
     * Выводит значения поля salary в порядке возрастания
     */
    public String printFieldAscendingVenue() {
        if (ticketsCollection.isEmpty()) {
            return "Коллекция пуста";
        }
        StringBuilder res = new StringBuilder();
        ticketsCollection.stream()
                .sorted(Comparator.comparing(ticket -> ticket.getVenue().getCapacity()))
                .forEach(ticket ->  res.append(ticket.getVenue()).append("\n"));
        return res.toString();
    }

    public String printFieldDescendingPrice() {
        if (ticketsCollection.isEmpty()) {
            return "Коллекция пуста";
        }
        StringBuilder res = new StringBuilder();
        ticketsCollection.stream()
                .sorted(Comparator.comparing(Ticket::getPrice).reversed())
                .forEach(ticket -> res.append(ticket.getPrice()).append("\n"));
        return res.toString();
    }

    public long countGreaterThan (TicketType ticketType) {
        return ticketsCollection.stream()
                .filter(ticket -> ticket.getTicketType() != null)
                .filter(ticket -> ticket.getTicketType().ordinal() > ticketType.ordinal()).count();
    }



    /**
     * Генерирует уникальный идентификатор для нового работника
     * @return новый уникальный id, на единицу больше максимального в коллекции
     */
    public int generateId() {
        if (ticketsCollection.isEmpty()) {
            return 1;
        }
        return ticketsCollection.stream()
                .mapToInt(Ticket::getId)
                .max()
                .orElse(0) + 1;
    }


}