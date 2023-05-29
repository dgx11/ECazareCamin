import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RepartizareCamereLista {
    public static void create(JPanel dormRepartitionPanel) {
        boolean repartitionListGenerated = JRepartiziareLista.checkRepartitionList();
        // Show "Generate Btn" or repartition list table based on repartitionListGenerated in database
        if (repartitionListGenerated) {
            createList(dormRepartitionPanel);
            return;
        }

        JButton generateBtn = new JButton("Creaza lista de repartizare in camin");
        dormRepartitionPanel.add(generateBtn);
        generateBtn.addActionListener(ae -> {
            // Mark creation of the repartition list in database
            JRepartiziareLista.insertGeneratedValue(true);
            createList(dormRepartitionPanel);
            // Remove Generate List button from panel
            dormRepartitionPanel.remove(generateBtn);
        });
    }

    public static void createList(JPanel dormRepartitionPanel) {
        List<DetaliiCazare> repartitionList = JRepartiziareLista.getRepartitionList();

        // Create a DefaultTableModel with column names
        DefaultTableModel repartitionTable = new DefaultTableModel();
        repartitionTable.addColumn("Student");
        repartitionTable.addColumn("Coleg Camera");
        repartitionTable.addColumn("Domiciliu");
        repartitionTable.addColumn("An");
        repartitionTable.addColumn("M. Anuala");
        repartitionTable.addColumn("M. Admitere");
        repartitionTable.addColumn("Tip Taxa");

        // Add user data to the model
        for (DetaliiCazare repartition : repartitionList) {
            Utilizatori utilizatoriById = JUtilizatori.getUserById(repartition.getUserId(), "users");
            Utilizatori getColegCameraNameById = JUtilizatori.getUserById(repartition.getColegCamera(), "users");
            if (getColegCameraNameById == null) {
                continue;
            }
            Object[] rowData = {
                    utilizatoriById.getUsername(),
                    getColegCameraNameById.getUsername(),
                    repartition.getDomiciliu(),
                    repartition.getAn(),
                    repartition.getMedieAnuala(),
                    repartition.getMedieAdmitere(),
                    formatTipTaxa(repartition.getFaraTaxa())
            };
            repartitionTable.addRow(rowData);
        }

        // Create the JTable with the model
        JTable table = new JTable(repartitionTable);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 340)); // Set preferred size
        dormRepartitionPanel.add(scrollPane);
    }

    public static String formatTipTaxa(Boolean faraTaxa) {
        if (faraTaxa)
            return "Fara Taxa";
        return "Cu Taxa";
    }
}