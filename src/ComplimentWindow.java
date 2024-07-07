
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


// import java.awt.Font;
// import javax.swing.JFrame;
// import javax.swing.JLabel;

public class ComplimentWindow {
    // This class is part of the Chicken GUI, it gives you multiple options to compliment the chicken

    int answer;
    
    ComplimentWindow(){
        // create icon and text for the Option Dialog
        ImageIcon sadChick = new ImageIcon("sadchick.png");
        String[] complimentOptions = {"You looking kinda crusty >:)", "Are you a chick? Because you're impecabble :)", "I love to eat chicken nuggets <3"};
        answer = JOptionPane.showOptionDialog(null, 
                                              "Give a good compliment to cheer up chicken !!",
                                              "Complimenting Chicken",
                                              JOptionPane.YES_NO_CANCEL_OPTION, 
                                              JOptionPane.PLAIN_MESSAGE, sadChick , 
                                              complimentOptions, 
                                              0);
    }

    public int Result() {
        switch (answer) {
            case 0:
                return 0; // 0 is bad
            case 1:
                return 1; // 1 is good
            default:
                return 2; // 2 is good
        }
    }
    
}
