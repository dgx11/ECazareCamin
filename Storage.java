public class Storage {
    private static User loggedUser;
    private static DetaliiStudent DetaliiStudent;
    private static List<BookingDetails> bookingDetailsList;

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void clearLoggedUser() {
        loggedUser = null;
    }

    public static void setDetaliiStudent(DetaliiStudent detalii) {
        DetaliiStudent = detalii;
    }

    public static DetaliiStudent getStudentDetails() {
        return DetaliiStudent;
    }

    public static void clearStudentsDetails() {
        studentDetails = null;
    }

    public static void setBookingDetailsList(List<BookingDetails> detailsList) {
        bookingDetailsList = detailsList;
    }

    public static List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public static void clearBookingDetailsList() {
        bookingDetailsList = null;
    }

    public static void resetAllStorages() {
        clearLoggedUser();
        clearStudentsDetails();
        clearBookingDetailsList();
    }
}

