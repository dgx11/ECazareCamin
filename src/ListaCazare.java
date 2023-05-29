import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaCazare {
    public static void create(JPanel bookingListPanel) {
        List<DetaliiCazare> bookingList = JDetaliiCazare.getBookingList();

        // Create a DefaultTableModel with column names
        DefaultTableModel bookingTable = new DefaultTableModel();
        bookingTable.addColumn("Student");
        bookingTable.addColumn("Coleg Camera");
        bookingTable.addColumn("Domiciliu");
        bookingTable.addColumn("An");
        bookingTable.addColumn("M. Anuala");
        bookingTable.addColumn("M. Admitere");

        // Add user data to the model
        for (DetaliiCazare booking : bookingList) {
            // Get User by booking user id and display the username
            // TODO: From where should we get the user name? Student or Users table?
            Utilizatori utilizatoriById = JUtilizatori.getUserById(booking.getUserId(), "users");
            Utilizatori getColegCameraNameById = JUtilizatori.getUserById(booking.getColegCamera(), "users");
            if (getColegCameraNameById == null) {
                continue;
            }
            Object[] rowData = {
                    utilizatoriById.getUsername(),
                    getColegCameraNameById.getUsername(),
                    booking.getDomiciliu(),
                    booking.getAn(),
                    booking.getMedieAnuala(),
                    booking.getMedieAdmitere()
            };
            bookingTable.addRow(rowData);
        }

        // Create the JTable with the model
        JTable table = new JTable(bookingTable);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 340)); // Set preferred size
        bookingListPanel.add(scrollPane);
    }
}
