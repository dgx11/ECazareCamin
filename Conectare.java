import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JConnection {
    public static Connection conn;

    public static void ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ecazare";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connected to database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String accountType = rs.getString("account_type");
            User user = new User(id, username, email, password, accountType);
            userList.add(user);
        }
        for (User user : userList) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getTipCont());
            System.out.println("---- USER ----");
        }
        return userList;
    }
}
