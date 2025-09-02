package managers;

import mainClasses.Ticket;
import mainClasses.TicketType;
import mainClasses.Venue;
import mainClasses.VenueType;
import mainClasses.Coordinates;

import java.io.PrintWriter;
import java.util.Vector;

/**
 * Класс для сохранения коллекции билетов в XML файл с использованием PrintWriter
 */
public class CollectionWriter {

    /**
     * Записывает коллекцию билетов в XML файл
     * @param filePath путь к файлу для сохранения
     * @param collection коллекция билетов для сохранения
     */
    public void writeToFile(String filePath, Vector<Ticket> collection) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            // Записываем XML декларацию и корневой элемент
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<tickets>");

            // Добавляем отступ для вложенных элементов
            String indent = "    ";

            // Записываем каждый билет
            for (Ticket ticket : collection) {
                writeTicketElement(writer, ticket, indent);
            }

            writer.println("</tickets>");
        } catch (Exception e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Записывает элемент ticket в XML
     */
    private void writeTicketElement(PrintWriter writer, Ticket ticket, String indent) {
        writer.println(indent + "<ticket>");

        // Записываем простые поля
        writeSimpleElement(writer, "id", String.valueOf(ticket.getId()), indent + "    ");
        writeSimpleElement(writer, "name", ticket.getName(), indent + "    ");

        // Записываем coordinates
        writer.println(indent + "    <coordinates>");
        writeSimpleElement(writer, "x", String.valueOf(ticket.getCoordinates().getX()), indent + "        ");
        writeSimpleElement(writer, "y", String.valueOf(ticket.getCoordinates().getY()), indent + "        ");
        writer.println(indent + "    </coordinates>");

        // Записываем остальные поля
        writeSimpleElement(writer, "creationDate", ticket.getCreationDate().toString(), indent + "    ");
        writeSimpleElement(writer, "price", String.valueOf(ticket.getPrice()), indent + "    ");
        writeSimpleElement(writer, "refundable", String.valueOf(ticket.getRefundable()), indent + "    ");

        // Записываем type (если есть)
        if (ticket.getType() != null) {
            writeSimpleElement(writer, "type", ticket.getType().toString(), indent + "    ");
        }

        // Записываем venue (если есть)
        if (ticket.getVenue() != null) {
            writeVenueElement(writer, ticket.getVenue(), indent + "    ");
        }

        writer.println(indent + "</ticket>");
    }

    /**
     * Записывает элемент venue в XML
     */
    private void writeVenueElement(PrintWriter writer, Venue venue, String indent) {
        writer.println(indent + "<venue>");
        writeSimpleElement(writer, "id", String.valueOf(venue.getId()), indent + "    ");
        writeSimpleElement(writer, "name", venue.getName(), indent + "    ");
        writeSimpleElement(writer, "capacity", String.valueOf(venue.getCapacity()), indent + "    ");
        writeSimpleElement(writer, "type", venue.getType().toString(), indent + "    ");
        writer.println(indent + "</venue>");
    }

    /**
     * Записывает простой XML элемент
     */
    private void writeSimpleElement(PrintWriter writer, String tagName, String value, String indent) {
        writer.println(indent + "<" + tagName + ">" + escapeXml(value) + "</" + tagName + ">");
    }

    /**
     * Экранирует специальные XML символы
     */
    private String escapeXml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}