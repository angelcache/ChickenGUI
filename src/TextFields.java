
/* import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
*/

public class TextFields /* extends JFrame implements ActionListener */ {
    /* In this class, we learnt how to use a textField which is helpful for taking in inputs from the user
     * We use JTextField and played around with its size forerground, background and caret color, we also
     * learnt how to submit the input by using .getText() as well as a submit button and using actionPerformed
     * to make it all come to life. 
     */

     /* 
    JTextField textField;
    JButton button;

    TextFields() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        this.setLayout(new FlowLayout());

        // allows us to type out text
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Calibri", Font.PLAIN, 22)); // set font, style, and size
        textField.setForeground(new Color(0xADA039)); // sets colour of inputted text
        textField.setBackground(Color.white);
        textField.setCaretColor(new Color(0xADA039)); // line that shows up when you type something

        // to submit text, add a button
        button = new JButton("Submit");
        button.addActionListener(this);

        this.add(button);
        this.add(textField);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Welcome " + textField.getText());
            button.setEnabled(false);
            textField.setEditable(false); 
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    */
    
}
