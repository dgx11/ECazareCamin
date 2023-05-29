import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class FereastraCreazaCont extends Rutare {
    private static String username;
    private static String email;
    private static String password;
    private static String confirmPassword;
    private static String accountType;

    public static void init() {
        GridLayout layout = new GridLayout(0, 1);

        JPanel accountTypePanel = new JPanel(layout);
        final JComboBox<String> accountTypeField = new JComboBox<>(TipuriDeCont.accountTypeValues);
        accountTypePanel.add(new JLabel("Selectati tipul de cont:"));
        accountTypePanel.add(accountTypeField);

        JPanel usernamePanel = new JPanel(layout);
        JTextField usernameField = new JTextField(20);
        usernamePanel.add(new JLabel(("Nume si prenume:")));
        usernamePanel.add(usernameField);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameField.setBorder(new LineBorder(Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        JPanel emailPanel = new JPanel(layout);
        JTextField emailField = new JTextField(20);
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                emailField.setBorder(new LineBorder(Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        JPanel passwordPanel = new JPanel(layout);
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setBorder(new LineBorder(Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        JPanel confirmPasswordPanel = new JPanel(layout);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordPanel.add(new JLabel("Confirm password"));
        confirmPasswordPanel.add(confirmPasswordField);
        confirmPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                confirmPasswordField.setBorder(new LineBorder(Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.add(Box.createVerticalStrut(50)); // a spacer
        JButton backToLogin = new JButton("Inapoi la login");
        JButton createAccountBtn = new JButton("Creeaza contul");
        actionsPanel.add(backToLogin);
        actionsPanel.add(createAccountBtn);

        backToLogin.addActionListener(ae -> {
            windowLayout.show(windowPanel, "loginWindowPanel");
        });

        // Create new account
        createAccountBtn.addActionListener(ae -> {
            // Get values from fields
            username = usernameField.getText();
            email = emailField.getText();
            password = String.valueOf(passwordField.getPassword());
            confirmPassword = String.valueOf(confirmPasswordField.getPassword());
            accountType = accountTypeField.getItemAt(accountTypeField.getSelectedIndex());

            boolean allFieldsEmpty = email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()
                    && username.isEmpty();

            if (allFieldsEmpty) {
                usernameField.setBorder(new LineBorder(Color.RED));
                emailField.setBorder(new LineBorder(Color.RED));
                passwordField.setBorder(new LineBorder(Color.RED));
                confirmPasswordField.setBorder(new LineBorder(Color.RED));
                return;
            }
            if (username.isEmpty()) {
                usernameField.setBorder(new LineBorder(Color.RED));
                return;
            }
            if (email.isEmpty()) {
                emailField.setBorder(new LineBorder(Color.RED));
                return;
            }
            if (password.isEmpty()) {
                passwordField.setBorder(new LineBorder(Color.RED));
                return;
            }
            if (confirmPassword.isEmpty()) {
                passwordField.setBorder(new LineBorder(Color.RED));
                return;
            }

            // Email Validation
            if(!email.matches(Utilitati.emailRegex)) {
                emailField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(null, "Email address is invalid!");
                return;
            }

            // Confirm Password Validation
            boolean confirmPasswordMatch = confirmPassword.equals(password);
            if (!confirmPasswordMatch) {
                JOptionPane.showMessageDialog(null, "Passwords don't match!");
                return;
            }

            // Check if account already exists
            boolean createAccountWithSuccess = false;
            try {
                createAccountWithSuccess  = JAutentificare.createAccount(username, email, password, accountType);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (!createAccountWithSuccess) {
                return;
            }

            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            accountTypeField.setSelectedIndex(0);
            windowLayout.show(windowPanel, "loginWindowPanel");
        });

        // Panel Initialization
        createAccountWindowPanel.add(accountTypePanel);
        createAccountWindowPanel.add(usernamePanel);
        createAccountWindowPanel.add(emailPanel);
        createAccountWindowPanel.add(passwordPanel);
        createAccountWindowPanel.add(confirmPasswordPanel);
        createAccountWindowPanel.add(actionsPanel);
    }
}