import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*-----------------------------------------------------------------------------------------------------------------
 * Purpose: After finishing the library game, player is taken to the compliment window where they are 
 *          given three options to compliment chicken. 1 /3 endings are good and will return a happy
 *          ending, otherwise compliment window will return a bad ending to ChickenGui.java
 * Images: The chicken gif I used is from tenor.
/*-----------------------------------------------------------------------------------------------------------------*/

public class ComplimentWindow {
    /* This class gives you multiple options to compliment the chicken, using JOptionPane */

    private int answer;
    
    public ComplimentWindow(){

        // creating panel to color JOptionPane
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xD3C163));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment

        JLabel messageLabel = new JLabel("Give a good compliment to cheer up chicken !!");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally


        panel.add(Box.createVerticalGlue()); // Add vertical glue for centering
        panel.add(messageLabel);
        panel.add(Box.createVerticalGlue()); // Add vertical glue for centering
        
        // create icon and text for the Option Dialog
        ImageIcon sadChick = new ImageIcon("img/sadchick.png");
        String[] complimentOptions = {"You looking kinda crusty >:)", "Are you a chick? Because you're impecabble :)", "I love to eat chicken nuggets <3"};
        answer = JOptionPane.showOptionDialog(null, 
                                              panel,
                                              "Complimenting Chicken",
                                              JOptionPane.YES_NO_CANCEL_OPTION, 
                                              JOptionPane.PLAIN_MESSAGE, sadChick , 
                                              complimentOptions, 
                                              0);
    }
    
    public int Result() {
        switch (answer) {
            case 0:
                return 0; // 0 is bad option
            case 1:
                return 1; // 1 is good option
            default:
                return 2; // 2 is bad option
        }
    }
}
