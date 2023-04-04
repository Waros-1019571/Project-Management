package avans.groep15.themoviedb.domain;

public class Validation {
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty() || string.equals(" ");
    }
}
