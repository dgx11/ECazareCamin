import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JConexiune {
    public static Connection conn;
    public static void ConnecrDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/cazareCamine";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("connected");
//            conn.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static List<Utilizatori> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Utilizatori> utilizatoriList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String accountType = rs.getString("account_type");
            Utilizatori utilizatori = new Utilizatori(id, username, email, password, accountType);
            utilizatoriList.add(utilizatori);
        }
        for (Utilizatori utilizatori : utilizatoriList) {
            System.out.println(utilizatori.getId());
            System.out.println(utilizatori.getUsername());
            System.out.println(utilizatori.getEmail());
            System.out.println(utilizatori.getPassword());
            System.out.println(utilizatori.getAccountType());
            System.out.println("---- USER ----");
        }
        return utilizatoriList;
    }
}
