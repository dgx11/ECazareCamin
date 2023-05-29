import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JAutentificare extends JConexiune {
    public static boolean checkEmailExists(String email) {
        boolean exists = false;
        String query = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while checking email existence: " + e.getMessage());
        }

        return exists;
    }

    public static boolean createAccount(String username, String email, String password, String accountType) throws SQLException {
        boolean success = false;
        // Check if email already exists in database
        if (checkEmailExists(email)) {
            JOptionPane.showMessageDialog(null, "An account with this email already exists.");
            return false;
        }

        String query = "INSERT INTO users (username, email, password, account_type) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement  = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, accountType);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account created successfully.");
                success = true;
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while creating account: " + e.getMessage());
        }

        return success;
    }

    public static boolean checkLogin(String email, String password, String accountType) {
        boolean loggedIn = false;
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND account_type = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, accountType);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // User exists and matches login credentials
                loggedIn = true;
                getLoggedUser(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedIn;
    }

    public static void getLoggedUser(String userEmail) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userEmail);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String accountType = rs.getString("account_type");
                Utilizatori loggedInUtilizatori = new Utilizatori(id, username, email, password, accountType);
                Depozit.setLoggedUser(loggedInUtilizatori);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean updatePassword(String userId, String newPassword) throws SQLException {
        boolean success = false;

        String query = "UPDATE users SET password = ? WHERE id = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(query);
            updateStatement.setString(1, newPassword);
            updateStatement.setString(2, userId);
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully.");
                success = true;
            }
            updateStatement.close();
        } catch (SQLException e) {
            System.err.println("Error while updating password: " + e.getMessage());
        }

        return success;
    }
}
