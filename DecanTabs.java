
    import javax.swing.*;
    import java.awt.*;

    public class DecanTabs extends WindowRouter {
        public static void create() {
            JTabbedPane DecanJTabs = new JTabbedPane();

            JPanel tab1 = new JPanel();
            JPanel tab2 = new JPanel();

            DecanJTabs.addTab("Tab 1", tab1);
            DecanJTabs.addTab("Tab 2", tab2);

            // Add comisie tabs to home window panel
            homeWindowPanel.add(DecanJTabs, BorderLayout.CENTER);
        }
    }
