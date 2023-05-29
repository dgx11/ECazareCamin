import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JRepartiziareLista extends JConexiune {

    // Check if a Membru Comisie account generated a Repartition List
    public static boolean checkRepartitionList() {
        boolean generatedValue = false;
        // Select the column generated from repartitin_list table where generated is true
        String query = "SELECT generated FROM repartition_list WHERE generated = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, true);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                generatedValue = resultSet.getBoolean("generated");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while checking repartiton_list generated column: " + e.getMessage());
        }

        return generatedValue;
    }

    // If a Membru Comisie generates a repartition list we want to save this information in repartition_list table
    // Based on this value of generated column we will show the Repartition List Tab
    public static boolean insertGeneratedValue(boolean generatedValue) {
        boolean success = false;
        String query = "INSERT INTO repartition_list (generated) VALUES (?)";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, generatedValue);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while inserting generated value: " + e.getMessage());
        }

        return success;
    }

    // Create Repartition List based on booking_details table
    public static List<DetaliiCazare> getRepartitionList() {
        List<DetaliiCazare> repartitionList = JDetaliiCazare.getBookingList();

        // Sort the list by faraTaxa=true first, then by medie in descending order
        repartitionList.sort((bd1, bd2) -> {
            boolean faraTaxa1 = bd1.getFaraTaxa();
            boolean faraTaxa2 = bd2.getFaraTaxa();

            if (faraTaxa1 && !faraTaxa2) {
                return -1; // bd1 has faraTaxa=true and should be prioritized
            } else if (!faraTaxa1 && faraTaxa2) {
                return 1; // bd2 has faraTaxa=true and should be prioritized
            } else {
                double medie1 = Double.parseDouble(bd1.getMedie());
                double medie2 = Double.parseDouble(bd2.getMedie());

                // Sort by medie in descending order
                return Double.compare(medie2, medie1);
            }
        });

        // Print the sorted list
        for (DetaliiCazare booking : repartitionList) {
            System.out.println(booking.getMedie());
        }
        return repartitionList;
    }
}
