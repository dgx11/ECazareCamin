import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDetaliiCazare extends JConexiune {

    public static void addBookingDetails(
            int userId, String colegCamera, String domiciliu, String an, String medieAnuala, String medieAdmitere)
            throws SQLException {
        String query = "INSERT INTO booking_details " +
                "(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere, medie, faraTaxa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Utilizatori loggedUtilizatori = Depozit.getLoggedUser();  // get logged user details
        JDetaliiStudent.getStudentDetails(String.valueOf(loggedUtilizatori.getId())); // get student details
        DetaliiStudent detaliiStudent = Depozit.getStudentDetails();

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            statement.setString(2, colegCamera);
            statement.setString(3, domiciliu);
            statement.setString(4, an);
            statement.setString(5, medieAnuala);
            statement.setString(6, medieAdmitere);
            // Send medie to sortBy medie anuala/admitere
            if (medieAnuala.length() != 0) {
                statement.setString(7, medieAnuala); // medie column to sortBy
            }
            if (medieAdmitere.length() != 0) {
                statement.setString(7, medieAdmitere); // medie column to sortBy
            }

            // booking_details table has a column named "faraTaxa",
            // based on students details we send if it's true or not (1 or 0)
            statement.setBoolean(8, detaliiStudent.getTipDeStudii().equals("Fara Taxa"));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking details added");
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while adding booking details " + e.getMessage());
        }
    }

    public static void getBookingDetails(String userId) {
        System.out.println(userId);
        try {
            String sql = "SELECT * FROM booking_details WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String colegCamera = rs.getString("colegCamera");
                String domiciliu = rs.getString("domiciliu");
                String an = rs.getString("an");
                String medieAnuala = rs.getString("medieAnuala");
                String medieAdmitere = rs.getString("medieAdmitere");

                DetaliiCazare detaliiCazare = new DetaliiCazare(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere, "", false);
                Depozit.setBookingDetails(detaliiCazare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<DetaliiCazare> getBookingList()  {
        List<DetaliiCazare> bookingList = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking_details";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userId = rs.getString("userId");
                String colegCamera = rs.getString("colegCamera");
                String domiciliu = rs.getString("domiciliu");
                String an = rs.getString("an");
                String medieAnuala = rs.getString("medieAnuala");
                String medieAdmitere = rs.getString("medieAdmitere");
                String medie = rs.getString("medie");
                Boolean faraTaxa = rs.getBoolean("faraTaxa");
                DetaliiCazare detaliiCazare = new DetaliiCazare(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere, medie, faraTaxa);
                bookingList.add(detaliiCazare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}