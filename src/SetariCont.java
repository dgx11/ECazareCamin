import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.sql.SQLException;

public class SetariCont {
    public static void init(JPanel accountPanel) {
        Icon testIcon = new ImageIcon("./Images/account icon.png");
        Icon accountIcon = new ImageIcon(((ImageIcon) testIcon).getImage().getScaledInstance(28, 19, java.awt.Image.SCALE_SMOOTH));
        JButton accountBtn = new JButton(accountIcon);
        accountBtn.setPreferredSize(new Dimension(35, 25));
        accountPanel.add(accountBtn);
        accountBtn.addActionListener(ae -> {
            changePasswordPanel();
        });
    }
    public static void changePasswordPanel() {
        JPanel changePassPanel = new JPanel();
        changePassPanel.setLayout((new BoxLayout(changePassPanel, BoxLayout.Y_AXIS)));
        JPasswordField currentPassField = new JPasswordField(10);
        JPasswordField newPassField = new JPasswordField(10);
        JPasswordField confirmPassField = new JPasswordField(10);

        changePassPanel.add(new JLabel("Current Password"));
        changePassPanel.add(currentPassField);
        changePassPanel.add(new JLabel("New Password:"));
        changePassPanel.add(newPassField);
        changePassPanel.add(new JLabel("Confirm new Password:"));
        changePassPanel.add(confirmPassField);

        int panelResult = JOptionPane.showConfirmDialog(
                null, changePassPanel,
                "Actualizare parola",
                JOptionPane.OK_CANCEL_OPTION);

        if (panelResult == JOptionPane.CANCEL_OPTION) {
            currentPassField.setText("");
            newPassField.setText("");
            confirmPassField.setText("");
            return;
        }

        if (panelResult == JOptionPane.OK_OPTION) {
            String currentPassword = String.valueOf(currentPassField.getPassword());
            String newPassword = String.valueOf(newPassField.getPassword());
            String confirmedPassword = String.valueOf(confirmPassField.getPassword());

            boolean emptyFields = newPassword.isEmpty() || confirmedPassword.isEmpty();
            boolean newPasswordMatch = confirmedPassword.equals(newPassword);

            if (emptyFields) {
                alert("Completeaza toate campurile!");
                changePasswordPanel();
                return;
            }

            if (currentPassword.isEmpty()) {
                alert("Introdu parola actuala");
                changePasswordPanel();
                return;
            }
            if (!newPasswordMatch) {
                alert("New passwords don't match");
                changePasswordPanel();
                return;
            }
            if (newPassword.equals(currentPassword)) {
                alert("New password can't be the same as the old password!");
                changePasswordPanel();
            }

            boolean passUpdateWithSuccess = false;
            try {
                Utilizatori loggedUtilizatori = Depozit.getLoggedUser();
                passUpdateWithSuccess = JAutentificare.updatePassword(String.valueOf(loggedUtilizatori.getId()), newPassword);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            if (!passUpdateWithSuccess) {
                alert("Password updated failed");
                changePasswordPanel();
                return;
            }

            alert("Password updated!");
        }
    }
        public static void alert(String message) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

