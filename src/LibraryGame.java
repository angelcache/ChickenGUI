/*
 * Purpose: Penguin goes to the library to learn more about how to compliment a friend, the game will be a maze
 *          through the library where penguin must collect books that will
 */

import java.awt.*;
import javax.swing.*;

class LibraryGame extends JFrame {
    private ChickenGui mainFrame;

    public LibraryGame(ChickenGui frame) {
        this.setTitle("Learn about complimenting");
        this.add(new LibraryPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();

        mainFrame = frame;
    }


    public class LibraryPanel extends JPanel {
        // The panel
        private final int PANEL_WIDTH = 420;
        private final int PANEL_HEIGHT = 500;
        private final int PANEL_UNITS = 40;

        public LibraryPanel() {
            this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            this.setBackground(new Color(0xCCE8B8));
            this.setFocusable(true);

        }

    }
}
