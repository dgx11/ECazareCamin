import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class Printare {
    public static void init (JPanel panel) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());
                panel.printAll(g);
                return PAGE_EXISTS;
            }
        });

        // display the print dialog
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                // print the JFrame
                job.print();
            } catch (PrinterException e) {
                // handle exception
            }
        }
    }
}
