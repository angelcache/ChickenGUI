import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyFrame extends JFrame{ // MyFrame is a sublass of the parent class Jframe

    public MyFrame() {

        // JLabel = GUI display area for a string of text, an image, or both

        // add label to a frame
        JLabel labels = new JLabel(); // creates label
        labels.setText("make chicken happy :D"); // set text of label

        labels.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of ImageIcon
        labels.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER,BOTTOM of ImageIcon
        labels.setForeground(new Color(0x9E6B1D)); // sets color of text
        labels.setFont(new Font("Mali", Font.PLAIN, 20)); // sets font of text

        labels.setIconTextGap(0); // set gap of text to image
        // labels.setBackground(Color.black); // set background color
        // labels.setOpaque(true); // to display background color
        labels.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon + text within label
        labels.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon + text within label

        // add image to a frame
        ImageIcon chicken = new ImageIcon("sadchick.png"); // creates image
        labels.setIcon(chicken); 

        // creating a border
        Border border = BorderFactory.createLineBorder(new Color(0xEEE7D0), 30);
        //0xECC949 #EEE7D0
        labels.setBorder(border);

        this.add(labels); // adds the label and image we made earlier

        labels.setText("Hi!");
        
        this.setTitle("My first GUI");
        this.setSize(420,420);
        this.setResizable(true);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("chickicon.png");
        this.setIconImage(image.getImage()); // change icon of frame
        this.getContentPane().setBackground(new Color(0xCCE8B8));

        //this.setLayout(null); // wont show the labels 
        //labels.setBounds(0, 0 , 400, 250); // determines whole labels parameter
        /* another way to change labels parameter */
        this.pack(); // make sure you add all components then pack

       
    }
}
