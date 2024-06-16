package Kiosk;

import javax.swing.*;
import java.awt.*;

public class ContainerFrame extends JFrame {
    public ContainerFrame() {
        setTitle("키오스크");
        setSize(1200, 800);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new Header(contentPane), BorderLayout.NORTH);
        contentPane.add(new MenuGrid(), BorderLayout.WEST);

        setVisible(true);
    }
}
