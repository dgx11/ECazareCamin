import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autentificare extends JConnection {
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

    public static boolean createAccount(String username, String email, String password, String tipCont) throws SQLException {
        boolean success = false;
        // Check if email already exists in database
        if (checkEmailExists(email)) {
            JOptionPane.showMessageDialog(null, "An account with this email already exists.");
            return false;
        }

        String query = "INSERT INTO users (username, email, password, tip_cont) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, tipCont);
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

    public static boolean checkLogin(String email, String password, String tipCont) {
        boolean loggedIn = false;
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND tip_cont = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, tipCont);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // User exists and matches login credentials
                loggedIn = true;
                getLoggedUser(email);
            }
            statement.close();
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
                String accountType = rs.getString("tip_cont");
                User loggedInUser = new User(id, username, email, password, TipuriCont);
                Storage.setLoggedUser(loggedInUser);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}