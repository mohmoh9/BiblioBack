package ml.tamboura.Bibliotheque.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateUtils {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Empêche l'instanciation
    private DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Retourne la date du jour
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * Ajoute un nombre de jours à une date
     */
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    /**
     * Formate une date en String (yyyy-MM-dd)
     */
    public static String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Parse une date depuis une String (yyyy-MM-dd)
     */
    public static LocalDate parse(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
