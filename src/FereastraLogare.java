import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class FereastraLogare extends Rutare {
    private static String email;
    private static String password;
    private static String accountType;
    public static void init() {
        GridLayout layout = new GridLayout(0, 1);

        JPanel accountTypePanel = new JPanel(layout);
        final JComboBox<String> accountTypeField = new JComboBox<>(TipuriDeCont.accountTypeValues);
        accountTypePanel.add(new JLabel("Selectati tipul de cont:"));
        accountTypePanel.add(accountTypeField);


        JPanel emailPanel = new JPanel(layout);
        JTextField emailField = new JTextField(20);
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { //turns the field back to white when clicked
                emailField.setBorder(new LineBorder(Color.BLACK));
            }
            @Override
            public void focusLost(FocusEvent e) { //must be implemented else focusGained wont work
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

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.add(Box.createVerticalStrut(50)); // a spacer
        JButton loginBtn = new JButton("Conecteaza-te");
        JButton createAccountBtn = new JButton("Creeaza un cont nou");
        actionsPanel.add(loginBtn);
        actionsPanel.add(createAccountBtn);

        // Actions Events
        loginBtn.addActionListener(ae -> {
            email = emailField.getText();
            password = String.valueOf(passwordField.getPassword());
            accountType = accountTypeField.getItemAt(accountTypeField.getSelectedIndex());
            boolean bothFieldsEmpty = email.isEmpty() && password.isEmpty();

            if (bothFieldsEmpty) {
                emailField.setBorder(new LineBorder(Color.RED));
                passwordField.setBorder(new LineBorder(Color.RED));
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

            // Email validation via regex
            if(!email.matches(Utilitati.emailRegex)) {
                emailField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(null, "Email address is invalid!");
                return;
            }

            boolean loggedIn = JAutentificare.checkLogin(email, password, accountType);

            if (!loggedIn) {
                JOptionPane.showMessageDialog(null, "Account not found");
                return;
            }

            // Redirect on home window and initialize home window
            windowLayout.show(windowPanel, "homeWindowPanel");
            FereastraPrincipala.init();
            emailField.setText("");
            passwordField.setText("");
        });
        createAccountBtn.addActionListener(ae -> {
            windowLayout.show(windowPanel, "createAccountWindowPanel");
            emailField.setText("");
            passwordField.setText("");
        });

        // Panel Initialization
        loginWindowPanel.add(accountTypePanel);
        loginWindowPanel.add(emailPanel);
        loginWindowPanel.add(passwordPanel);
        loginWindowPanel.add(actionsPanel);
    }
}