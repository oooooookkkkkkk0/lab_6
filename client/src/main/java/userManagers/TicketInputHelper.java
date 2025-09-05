package userManagers;

import mainClasses.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Вспомогательный класс для ввода данных работника
 * Предоставляет методы для интерактивного ввода всех полей работника через консоль
 */
public class TicketInputHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Создает нового работника путем последовательного ввода всех его полей
     * @return новый объект Worker с введенными данными
     */
    public Ticket inputTicket() {
        Ticket ticket = new Ticket();


        ticket.setName(inputName());
        ticket.setCoordinates(inputCoordinates());
        ticket.setPrice(inputPrice());
        ticket.setRefundable(inputRefundable());
        ticket.setTicketType(inputTicketType());
        ticket.setVenue(inputVenue());


        return ticket;
    }


    public static Boolean inputRefundable() {
        while (true) {
            System.out.print("Билет подлежит возврату? (y/n): ");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Введите (да или нет) (y/n)");
                continue;
            }
        }
    }

    public static int inputPrice() {
        while (true) {
            System.out.print("Введите цену билета: ");
            String price = scanner.nextLine();
            if (price.isEmpty()) {
                System.out.println("Ошибка: цена не может быть пустой.");
                continue;
            }

            return Integer.parseInt(price);
        }
    }

    /**
     * Запрашивает ввод имени работника
     * @return введенное имя
     */
    private static String inputName() {
        while (true) {
            System.out.print("Введите имя владельца билета: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Ошибка: название не может быть пустым.");
                continue;
            }

            if (name.length() > 100) {
                System.out.println("Ошибка: имя клиента не может быть таким длинным.");
                continue;
            }

            if (name.matches("\\d+")) {
                System.out.println("Ошибка: название не может состоять из цифр.");
                continue;
            }
            return name;
        }
    }

    /**
     * Создает объект координат путем ввода x и y
     * @return новый объект Coordinates
     */
    private static Coordinates inputCoordinates() {
        return new Coordinates(inputXCoordinate(), inputYCoordinate());
    }

    /**
     * Запрашивает ввод координаты X
     * @return введенное значение координаты X
     */
    private static Double inputXCoordinate() {
        while (true) {
            try {
                System.out.print("Введите координату x: ");
                String x = scanner.nextLine();
                if (!x.isEmpty()) {
                    return Double.parseDouble(x);
                }
                System.out.println("Ошибка! Значения поля Х не может быть null!");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введено некорректное число!");
            }
        }
    }

    /**
     * Запрашивает ввод координаты Y
     * @return введенное значение координаты Y
     */
    private static float inputYCoordinate() {
        while (true) {
            try {
                System.out.print("Введите координату y: ");
                String y = scanner.nextLine();

                if (!y.isEmpty() && Float.parseFloat(y) > -415) {
                    return Long.parseLong(y);
                }
                System.out.println("Значение поля y должно быть больше -415 и не может быть пустым.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введено некорректное число!");
            }
        }
    }



    /**
     * Запрашивает ввод должности
     * @return введенная должность
     */
    private static TicketType inputTicketType() {
        while (true) {
            try {
                System.out.print("Введите тип билета (вип, обычный, бюджетный, дешевый): ");
                String input = scanner.nextLine().trim().toLowerCase();

                // Поиск соответствующего значения перечисления по value
                for (TicketType ticketType : TicketType.values()) {
                    if (ticketType.getValue().equalsIgnoreCase(input)) {
                        return ticketType;
                    }
                }

                // Если введенное значение не найдено
                System.out.println("Ошибка: некорректный тип билета. Доступные значения: вип, обычный, бюджетный, дешевый.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: некорректный тип билета. Доступные значения: вип, обычный, бюджетный, дешевый.");
            }
        }
    }

    public static String inputVenueName() {
        while (true) {
            try {
                System.out.print("Введите название места проведения: ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    return name;
                }
                System.out.println("Ошибка! Название не может быть пустым");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка! Некорректное название");
            }
        }
    }

    public static long inputVenueCapacity() {
        while (true) {
            try {
                System.out.print("Введите вместительность места проведения: ");
                long capacity = Long.parseLong(scanner.nextLine());
                if (capacity > 0) {
                    return capacity;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректное количество!");
            }
        }
    }

    public static VenueType inputVenueType() {
        while (true) {
            try {
                System.out.print("Введите тип места проведения (бар, лофт, театр, молл, стадион): ");
                String input = scanner.nextLine().trim().toLowerCase();
                for (VenueType venueType : VenueType.values()) {
                    if (venueType.getValue().equalsIgnoreCase(input)) {
                        return venueType;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: некорректный тип билета. Доступные значения: вип, обычный, бюджетный, дешевый.");
            }
        }
    }

    /**
     * Запрашивает ввод данных о человеке (рост, вес, дата рождения)
     * @return новый объект Person с введенными данными
     */
    private static Venue inputVenue() {
        Venue venue = new Venue();
        venue.setName(inputVenueName());
        venue.setCapacity(inputVenueCapacity());
        venue.setVenueType(inputVenueType());
        return venue;
    }

    public static Ticket generateTicketFromScript(BufferedReader reader) throws IOException {
        String name = reader.readLine().trim();
        Double x = Double.parseDouble(reader.readLine().trim());
        float y = Long.parseLong(reader.readLine().trim());
        int price = Integer.parseInt(reader.readLine().trim());
        boolean refundable = (reader.readLine().trim().equalsIgnoreCase("y"));
        TicketType type = null;
        switch (reader.readLine().trim()) {
            case "вип":
                type = TicketType.VIP;
                break;
            case "обычный":
                type = TicketType.USUAL;
                break;
            case "дешевый":
                type = TicketType.CHEAP;
                break;
            case "бюджетный":
                type = TicketType.BUDGETARY;
                break;
        }

        String venueName = reader.readLine().trim();
        int capacity = Integer.parseInt(reader.readLine().trim());
        VenueType venueType = null;
        switch (reader.readLine().trim()) {
            case "бар":
                venueType = VenueType.BAR;
                break;
            case "лофт":
                venueType = VenueType.LOFT;
                break;
            case "театр":
                venueType = VenueType.THEATRE;
                break;
            case "молл":
                venueType = VenueType.MALL;
                break;
            case "стадион":
                venueType = VenueType.STADIUM;
                break;
        }

        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setCoordinates(new Coordinates(x, y));
        ticket.setPrice(price);
        ticket.setRefundable(refundable);
        ticket.setTicketType(type);
        ticket.setVenue(new Venue(venueName, capacity, venueType));

        return ticket;
    }
}