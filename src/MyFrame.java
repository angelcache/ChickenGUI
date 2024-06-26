import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener{ 
    // extends makes MyFrame a sublass of parent class Jframe
    // implement adds a method from ActionListener

    JButton button;
    JLabel friendLabel; // label that makes friend pop up after clicking button to hang out
    JLabel objective; // label that has the objective of GUI which is to make chicken happy
    JLabel ending;

    public MyFrame() { 

        /* creating a button */
        // ImageIcon buttonIcon = new ImageIcon("chickicon"); // can add image to buttons
        button = new JButton(); 
        button.setBounds(150, 350, 100, 50); 

        /*customizing the button*/
        button.setText("Hang out");
        button.setFocusable(false); // removes random border around text
        // button.setIcon(buttonIcon); button.setIconTextGap(); 
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER); 
        button.setFont(new Font("Mali", Font.BOLD, 10)); 
        button.setForeground(Color.white); // forgeround is text color
        button.setBackground(new Color(0xD3C163));
        button.setBorder(BorderFactory.createEtchedBorder()); // gives a 3d effect to the button
        this.add(button); 

        /* display a label on our frame after clicking a button */
        friendLabel = new JLabel();
        ImageIcon friend = new ImageIcon("friend.gif");
        friendLabel.setIcon(friend);
        friendLabel.setBounds(14, 0, 400, 400);
        friendLabel.setVisible(false);
        this.add(friendLabel);
        button.addActionListener(this); // friend now pops up after button is hit
        // button.addActionListener(e -> System.out.println("poo")); // action listener lamda expression shortcut

        /* JLabel = GUI display area for a string of text, an image, or both */
        objective = new JLabel(); // creates label
        objective.setText("make chicken happy :D"); // set text of label

        /* customizing text of JLabel */
        objective.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of ImageIcon
        objective.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER,BOTTOM of ImageIcon
        objective.setForeground(new Color(0x9E6B1D)); // sets color of text
        objective.setFont(new Font("Mali", Font.BOLD, 20)); // sets font of text

        objective.setIconTextGap(0); // set gap of text to image
        // objective.setBackground(Color.black); // set background color
        // objective.setOpaque(true); // to display background color
        objective.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon + text within label
        objective.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon + text within label
        
        /* add image to JLabel */
        ImageIcon chicken = new ImageIcon("sadchick.png"); // creates image
        objective.setIcon(chicken); // adds it to JLabel
        // creating a border
        Border border = BorderFactory.createLineBorder(new Color(0xEEE7D0), 30);
        objective.setBorder(border);
        
        /* Ending Jlabel */
        ending = new JLabel();
        ending.setText("Chicken is now happy :)");
        ending.setBounds(88,0, 400, 148);
        ending.setForeground(new Color(0xEFFF7F)); // sets color of text
        ending.setFont(new Font("Mali", Font.BOLD, 20)); // sets font of text
        ending.setVisible(false); // makes Ending Jlabel invisible
        
        // adds the ending and objective labels to JFrame
        this.add(ending);
        this.add(objective); // adds the objective label w/ text + image we made earlier

        /* JFrame customization */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sad Chicken");
        this.setSize(420,500);
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("chickicon.png");
        this.setIconImage(image.getImage()); // change icon of frame
        this.getContentPane().setBackground(new Color(0xCCE8B8));
        // this.setLayout(null); // wont show the labels 
        // objective.setBounds(0, 0 , 400, 250); // determines whole labels parameter
        
        /* another way to change labels parameter */
        // this.pack(); // make sure you add all components then pack 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            button.setEnabled(false); // disables a button
            friendLabel.setVisible(true); // after hitting button, friend image pops up
            objective.setText(null);
            objective.setIcon(null);
            ending.setVisible(true);
            this.setTitle("Happy Chicken");
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
