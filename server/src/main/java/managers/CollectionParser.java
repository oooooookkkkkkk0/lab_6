package managers;

import mainClasses.Ticket;
import mainClasses.TicketType;
import mainClasses.Venue;
import mainClasses.VenueType;
import mainClasses.Coordinates;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс для парсинга коллекции билетов из XML файла с использованием Scanner
 */
public class CollectionParser {
    private final CollectionManager collectionManager;

    public CollectionParser(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public HashMap<String, Vector<Ticket>> parseFromFile(String filePath) {
        Vector<Ticket> tickets = new Vector<>();
        Set<Integer> idishniki = new HashSet<>();
        String res = "";

        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Читаем весь файл в строку
            StringBuilder xmlContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                xmlContent.append(scanner.nextLine());
            }

            // Парсим XML из строки
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent.toString())));

            NodeList ticketNodes = document.getElementsByTagName("ticket");
            HashMap<String, Vector<Ticket>> result = new HashMap<>();

            for (int i = 0; i < ticketNodes.getLength(); i++) {
                Node ticketNode = ticketNodes.item(i);
                if (ticketNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ticketElement = (Element) ticketNode;
                    Ticket ticket = parseTicket(ticketElement);
                    if (ticket != null) {
                        if (idishniki.add(ticket.getId())) {
                            tickets.add(ticket);
                        } else {
                            res = "Обнаружено несколько билетов с одинаковым ID!" +
                                    "\nВ коллекцию добавлен только один билет с id = " +
                                    idishniki.stream().max(Integer::compareTo).orElse(null);
                        }
                    }
                }
            }
            result.put(res, tickets);
            return result;
        } catch (Exception e) {
            HashMap<String, Vector<Ticket>> result = new HashMap<>();
            result.put("Ошибка: " + e.getMessage() + " Коллекция не добавлена!", new Vector<>());
            return result;
        }
    }

    private Ticket parseTicket(Element ticketElement) {
        try {
            Integer id = Integer.parseInt(getElementText(ticketElement, "id"));
            String name = getElementText(ticketElement, "name");
            Coordinates coordinates = parseCoordinates(ticketElement.getElementsByTagName("coordinates").item(0));
            ZonedDateTime creationDate = ZonedDateTime.parse(getElementText(ticketElement, "creationDate"));
            int price = Integer.parseInt(getElementText(ticketElement, "price"));
            Boolean refundable = Boolean.parseBoolean(getElementText(ticketElement, "refundable"));

            TicketType type = null;
            Node typeNode = ticketElement.getElementsByTagName("type").item(0);
            if (typeNode != null) {
                type = TicketType.valueOf(typeNode.getTextContent());
            }

            Venue venue = parseVenue(ticketElement.getElementsByTagName("venue").item(0));

            Ticket ticket = new Ticket(id, name, coordinates, creationDate, price, refundable, type, venue);
            ticket.validate();
            return ticket;
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге билета: " + e.getMessage());
            return null;
        }
    }

    private Coordinates parseCoordinates(Node coordinatesNode) {
        if (coordinatesNode.getNodeType() == Node.ELEMENT_NODE) {
            Element coordinatesElement = (Element) coordinatesNode;
            Double x = Double.parseDouble(getElementText(coordinatesElement, "x"));
            float y = Float.parseFloat(getElementText(coordinatesElement, "y"));
            Coordinates coordinates = new Coordinates(x, y);
            coordinates.validate();
            return coordinates;
        }
        return null;
    }

    private Venue parseVenue(Node venueNode) {
        if (venueNode == null || venueNode.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        Element venueElement = (Element) venueNode;
        try {
            Integer id = Integer.parseInt(getElementText(venueElement, "id"));
            String name = getElementText(venueElement, "name");
            long capacity = Long.parseLong(getElementText(venueElement, "capacity"));
            VenueType type = VenueType.valueOf(getElementText(venueElement, "type"));

            Venue venue = new Venue(id, name, capacity, type);
            venue.validate();
            return venue;
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге Venue: " + e.getMessage());
            return null;
        }
    }

    private String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}