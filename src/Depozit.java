public class Depozit {
    private static Utilizatori loggedUtilizatori;
    private static DetaliiStudent detaliiStudent;
    private static DetaliiCazare bookingDetails;

    public static void setLoggedUser(Utilizatori utilizatori) {
        loggedUtilizatori = utilizatori;
    }

    public static Utilizatori getLoggedUser() {
        return loggedUtilizatori;
    }

    public static void clearLoggedUser() {
        loggedUtilizatori = null;
    }

    public static void setStudentDetails(DetaliiStudent details) {
        detaliiStudent = details;
    }

    public static DetaliiStudent getStudentDetails() {
        return detaliiStudent;
    }

    public static void clearStudentsDetails() {
        detaliiStudent = null;
    }

    public static void setBookingDetails(DetaliiCazare details) {
        bookingDetails = details;
    }

    public static DetaliiCazare getBookingDetails() {
        return bookingDetails;
    }

    public static void clearBookingDetails() {
        bookingDetails = null;
    }

    public static void resetAllStorages() {
        clearLoggedUser();
        clearStudentsDetails();
        clearBookingDetails();
    }
}
