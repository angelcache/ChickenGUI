
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// import java.awt.Font;
// import javax.swing.JFrame;
// import javax.swing.JLabel;

public class ComplimentWindow {
    // This class is part of the Chicken GUI, it gives you multiple options to compliment the chicken, used JOptionPane

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
        ImageIcon sadChick = new ImageIcon("sadchick.png");
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
