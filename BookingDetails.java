import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JBookingDetails extends JConnection {

    public static void addBookingDetails(
            int userId, String colegCamera, String domiciliu, String an, String medieAnuala, String medieAdmitere)
            throws SQLException {
        String query = "INSERT INTO booking_details " +
                "(user_id, coleg_camera, domiciliu, an, medie_anuala, medie_admitere, medie, fara_taxa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        User loggedUser = Storage.getLoggedUser();
        JDetaliiStudent.getStudentDetails(String.valueOf(loggedUser.getId()));
        DetaliiStudent DetaliiStudent = Storage.getStudentDetails();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, colegCamera);
            statement.setString(3, domiciliu);
            statement.setString(4, an);
            statement.setString(5, medieAnuala);
            statement.setString(6, medieAdmitere);
            // Send medie to sortBy medie anuala/admitere
            if (!medieAnuala.isEmpty()) {
                statement.setString(7, medieAnuala); // medie column to sortBy
            } else if (!medieAdmitere.isEmpty()) {
                statement.setString(7, medieAdmitere); // medie column to sortBy
            }

            // booking_details table has a column named "fara_taxa",
            // based on students details we send if it's true or not (1 or 0)
            statement.setBoolean(8, DetaliiStudent.getTipDeStudii().equals("Fara Taxa"));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking details added");
            }
        } catch (SQLException e) {
            System.err.println("Error while adding booking details: " + e.getMessage());
        }
    }

    public static void getBookingDetails(String userId) {
        System.out.println(userId);
        try {
            String sql = "SELECT * FROM booking_details WHERE user_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, userId);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String colegCamera = rs.getString("coleg_camera");
                        String domiciliu = rs.getString("domiciliu");
                        String an = rs.getString("an");
                        String medieAnuala = rs.getString("medie_anuala");
                        String medieAdmitere = rs.getString("medie_admitere");

                        BookingDetails bookingDetails = new BookingDetails(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere, "", false);
                        Storage.setBookingDetails(bookingDetails);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<BookingDetails> getBookingList() {
        List<BookingDetails> bookingList = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking_details";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    String colegCamera = rs.getString("coleg_camera");
                    String domiciliu = rs.getString("domiciliu");
                    String an = rs.getString("an");
                    String medieAnuala = rs.getString("medie_anuala");
                    String medieAdmitere = rs.getString("medie_admitere");
                    String medie = rs.getString("medie");
                    Boolean faraTaxa = rs.getBoolean("fara_taxa");
                    BookingDetails bookingDetails = new BookingDetails(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere, medie, faraTaxa);
                    bookingList.add(bookingDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}
