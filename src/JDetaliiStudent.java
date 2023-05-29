import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDetaliiStudent extends JConexiune {
    public static void addOrUpdateStudentsDetails(
            int userId, String nume, String prenume, String facultate, String anFacultate,
            String specializare, String CNP, String domiciliu, String tipDeStudii) throws SQLException {

        String selectQuery = "SELECT * FROM students_details WHERE userId = ?";
        String updateQuery = "UPDATE students_details SET nume = ?, prenume = ?, facultate = ?, " +
                "anFacultate = ?, specializare = ?, cnp = ?, domiciliu = ?, tipDeStudii = ? WHERE userId = ?";
        String insertQuery = "INSERT INTO students_details " +
                "(userId, nume, prenume, facultate, anFacultate, specializare, cnp, domiciliu, tipDeStudii) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Check if the user already exists
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            boolean userExists = resultSet.next();
            selectStatement.close();

            // Perform update or insert based on user existence

            if (userExists) { // Update if user exists
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setString(1, nume);
                updateStatement.setString(2, prenume);
                updateStatement.setString(3, facultate);
                updateStatement.setString(4, anFacultate);
                updateStatement.setString(5, specializare);
                updateStatement.setString(6, CNP);
                updateStatement.setString(7, domiciliu);
                updateStatement.setString(8, tipDeStudii);
                updateStatement.setInt(9, userId);
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student details updated");
                }
                updateStatement.close();
                return;
            }

            // Add if user doesn't exists
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setInt(1, userId);
            insertStatement.setString(2, nume);
            insertStatement.setString(3, prenume);
            insertStatement.setString(4, facultate);
            insertStatement.setString(5, anFacultate);
            insertStatement.setString(6, specializare);
            insertStatement.setString(7, CNP);
            insertStatement.setString(8, domiciliu);
            insertStatement.setString(9, tipDeStudii);
            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student details added");
            }
            insertStatement.close();

        } catch (SQLException e) {
            System.err.println("Error while adding or updating student details: " + e.getMessage());
        }
    }

    public static void getStudentDetails(String userId) {
        try {
            String sql = "SELECT * FROM students_details WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String facultate = rs.getString("facultate");
                String anFacultate = rs.getString("anFacultate");
                String specializare = rs.getString("specializare");
                String CNP = rs.getString("CNP");
                String domiciliu = rs.getString("domiciliu");
                String tipDeStudii = rs.getString("tipDeStudii");

                DetaliiStudent detaliiStudent = new DetaliiStudent(userId, nume, prenume, facultate, anFacultate, specializare, CNP, domiciliu, tipDeStudii);
                Depozit.setStudentDetails(detaliiStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<DetaliiStudent> getStudentsName()  {
        List<DetaliiStudent> studentsNameList = new ArrayList<>();
        try {
            String query = "SELECT * FROM students_details";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userId = rs.getString("userId");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                DetaliiStudent student = new DetaliiStudent(userId, nume, prenume, "", "", "", "", "", "");
                studentsNameList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsNameList;
    }
}
