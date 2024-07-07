//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;

public class LaunchPage /*implements ActionListener*/ {
    /* In this class, we learn how to open up a new window 
     * To sum it up: create an instance of class that contains instance of class you want to appear
     * Then set it up for some sort of event to trigger it --> in this case, we used a button, there
     * are other ways, typically need a way for it to be instantiated
    */

    /* 
    JFrame frame = new JFrame();
    JButton myButton = new JButton("New Window");

    LaunchPage() {
        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        
        frame.add(myButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            frame.dispose(); // closes out of our frame
            NewWindow myWindow = new NewWindow();
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    */
}

