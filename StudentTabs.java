import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class StudentTabs extends WindowRouter {
    public static JPanel studentDetailsPanel = new JPanel();
    public static final JPanel requestDormBooking = new JPanel();
    public static final JPanel bookingListPanel = new JPanel();

    public static void create() {
        // Student details panel creation
        JTabbedPane studentJTabs = new JTabbedPane();

        // Adding tabs to studentDetailsPanel
        createStudentsDetails();
        createBookingDetails();
        BookingList.create(bookingListPanel);

        studentJTabs.addTab("Detalii Student", studentDetailsPanel);
        studentJTabs.addTab("Cerere de cazare", requestDormBooking);
        studentJTabs.addTab("Lista cereri de cazare", bookingListPanel);

        // Add student tabs to home window panel
        homeWindowPanel.add(studentJTabs, BorderLayout.CENTER);
    }

    public static void createBookingDetails() {
        User loggedUser = Storage.getLoggedUser();

        JBookingDetails.getBookingDetails(String.valueOf(loggedUser.getId()));
        BookingDetails bookingDetails = Storage.getBookingDetails();
        if (bookingDetails != null) {
            JLabel submitted = new JLabel("Cererea a fost deja trimisa.");
            requestDormBooking.add(submitted);
            return;
        }
        requestDormBooking.setLayout(new GridLayout(9, 2, 10, 10));
        // Creating fields and labels for requestDormBooking panel
        JComboBox<String> colegCameraDropdown = new JComboBox<>();
        for (String element : JStudentDetails.getStudentsName()) {
            colegCameraDropdown.addItem(element);
        }
        JComboBox<String> anDropdown = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        anDropdown.setSelectedIndex(0); // Sets default selection to AN 1
        JLabel emptyspace1 = new JLabel((""));
        JLabel emptyspace2 = new JLabel((""));
        JLabel medieAnualaLabel = new JLabel("MEDIA ANUALA:");
        JLabel medieAdmitereLabel = new JLabel("MEDIE ADMITERE:");
        JTextField domiciliuField = new JTextField();
        JTextField medieAnualaField = new JTextField();
        JTextField medieAdmitereField = new JTextField();
        JButton createBooking = new JButton("Confirma cererea");

        // Add action listener to dropdown
        anDropdown.addActionListener(ae -> {
            int selectedYear = Integer.parseInt((String) anDropdown.getSelectedItem());
            boolean showEntryGrade = (selectedYear == 1); // Show entryGradeField only for year 1
            medieAdmitereLabel.setVisible(showEntryGrade);
            medieAdmitereField.setVisible(showEntryGrade);
            medieAnualaLabel.setVisible(!showEntryGrade);
            medieAnualaField.setVisible(!showEntryGrade);

            if (showEntryGrade) {
                requestDormBooking.remove(medieAnualaLabel);
                requestDormBooking.remove(medieAnualaField);
                requestDormBooking.remove(emptyspace2);
                requestDormBooking.add(medieAdmitereLabel);
                requestDormBooking.add(medieAdmitereField);
                requestDormBooking.add(emptyspace1);
                requestDormBooking.add(createBooking);
            } else {
                requestDormBooking.remove(medieAdmitereLabel);
                requestDormBooking.remove(medieAdmitereField);
                requestDormBooking.remove(emptyspace1);
                requestDormBooking.add(medieAnualaLabel);
                requestDormBooking.add(medieAnualaField);
                requestDormBooking.add(emptyspace2);
                requestDormBooking.add(createBooking);
            }
            requestDormBooking.revalidate();
            requestDormBooking.repaint();
        });

        // Add everything to the requestDormBooking panel
        requestDormBooking.add(new JLabel("Coleg camera:"));
        requestDormBooking.add(colegCameraDropdown);
        requestDormBooking.add(new JLabel("Domiciliu:"));
        requestDormBooking.add(domiciliuField);
        requestDormBooking.add(new JLabel("AN:"));
        requestDormBooking.add(anDropdown);
        requestDormBooking.add(medieAdmitereLabel);
        requestDormBooking.add(medieAdmitereField);
        requestDormBooking.add(emptyspace1);
        requestDormBooking.add(createBooking);

        createBooking.addActionListener(ae -> {
            String colegCamera = colegCameraDropdown.getItemAt(colegCameraDropdown.getSelectedIndex());
            String an = anDropdown.getItemAt(anDropdown.getSelectedIndex());
            String domiciliu = domiciliuField.getText();
            String medieAnuala = medieAnualaField.getText();
            String medieAdmitere = medieAdmitereField.getText();

            try {
                JBookingDetails.addBookingDetails(loggedUser.getId(), colegCamera, domiciliu, an, medieAnuala, medieAdmitere);
                StudentTabs.requestDormBooking.removeAll();
                requestDormBooking.repaint();
                requestDormBooking.revalidate();
                createBookingDetails();
                // Remount Booking List on add(refetch data from database)
                bookingListPanel.removeAll();
                bookingListPanel.repaint();
                bookingListPanel.revalidate();
                BookingList.create(bookingListPanel);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void createStudentsDetails() {
        User loggedUser = Storage.getLoggedUser();

        studentDetailsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JTextField numeField = new JTextField(20);
        JTextField prenumeField = new JTextField(20);
        JTextField facultateField = new JTextField(20);
        JTextField facultateAnField = new JTextField(20);
        JTextField specializareField = new JTextField(20);
        JTextField cnpField = new JTextField(20);
        cnpField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cnpField.setBorder(new LineBorder(Color.BLACK));
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        JTextField domiciliuField = new JTextField(20);
        final JComboBox<String> tipDeStudiiDropDown = new JComboBox<>(new String[]{"Cu Taxa", "Fara Taxa"});
        JButton submitStudentDetailsBtn = new JButton("Adauga");

        // Get Student Details from backend if exists and populate data into fields
        JStudentDetails.getStudentDetails(String.valueOf(loggedUser.getId()));
        StudentDetails studentDetails = Storage.getStudentDetails();

        // Access StudentDetails in Storage
        if (studentDetails != null) {
            numeField.setText(studentDetails.getNume());
            prenumeField.setText(studentDetails.getPrenume());
            facultateField.setText(studentDetails.getFacultate());
            facultateAnField.setText(studentDetails.getAnFacultate());
            specializareField.setText(studentDetails.getSpecializare());
            cnpField.setText(studentDetails.getCNP());
            domiciliuField.setText(studentDetails.getDomiciliu());
            tipDeStudiiDropDown.setSelectedItem(studentDetails.getTipDeStudii());
            submitStudentDetailsBtn.setText("Editeaza");
        }

        studentDetailsPanel.add(new JLabel("Nume:"));
        studentDetailsPanel.add(numeField);
        studentDetailsPanel.add(new JLabel("Prenume:"));
        studentDetailsPanel.add(prenumeField);
        studentDetailsPanel.add(new JLabel("Facultate:"));
        studentDetailsPanel.add(facultateField);
        studentDetailsPanel.add(new JLabel("An Facultate:"));
        studentDetailsPanel.add(facultateAnField);
        studentDetailsPanel.add(new JLabel("Specializare:"));
        studentDetailsPanel.add(specializareField);
        studentDetailsPanel.add(new JLabel("CNP:"));
        studentDetailsPanel.add(cnpField);
        studentDetailsPanel.add(new JLabel("Domiciliu:"));
        studentDetailsPanel.add(domiciliuField);
        studentDetailsPanel.add(new JLabel("Tip de studii:"));
        studentDetailsPanel.add(tipDeStudiiDropDown);

        JLabel emptySpace = new JLabel("");
        studentDetailsPanel.add(emptySpace);
        studentDetailsPanel.add(submitStudentDetailsBtn);

        // Listen to Submit Student Details Button
        submitStudentDetailsBtn.addActionListener(ae -> {
            String nume = numeField.getText();
            String prenume = prenumeField.getText();
            String facultate = facultateField.getText();
            String anFacultate = facultateAnField.getText();
            String specializare = specializareField.getText();
            String CNP = cnpField.getText();
            String domiciliu = domiciliuField.getText();
            String tipDeStudii = tipDeStudiiDropDown.getItemAt(tipDeStudiiDropDown.getSelectedIndex());

            if (!CNP.matches(Utils.cnpRegex)) {
                cnpField.setBorder(new LineBorder(Color.BLUE));
                JOptionPane.showMessageDialog(null, "CNP is invalid!");
                return;
            }

            // Add or Update student details to students_details table in database/backend
            try {
                JStudentDetails.addOrUpdateStudentsDetails(loggedUser.getId(), nume, prenume, facultate, anFacultate, specializare, CNP, domiciliu, tipDeStudii);
                // Alert User if add/update is successful
                String addOrUpdateDialogMessage = submitStudentDetailsBtn.getText().equals("Editeaza") ? "Editare cu succes" : "Adaugare cu succes";
                JOptionPane.showMessageDialog(null, addOrUpdateDialogMessage);
                submitStudentDetailsBtn.setText("Editeaza");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
