import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        /* 
        many ways to create a frame, example below creates an instance of frame and change its properties
            
            import java.awt.Color; // this import is needed to make a frame
            import javax.swing.ImageIcon; // imports ImageIcon
            import javax.swing.JFrame; // lets us get colors

            // JFrame = GUI window to add components to

                JFrame frame = new JFrame(); // creates a frame, right now it isn't visible

                frame.setTitle("My first GUI"); // sets title of frame
                frame.setSize(420,420); // sets x-dimension and y-dimension of frame

                frame.setResizable(false); // prevent frame from being resize
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so now when we hit the x button it will exit the program
                frame.setVisible(true); // makes the frame visible

                ImageIcon image = new ImageIcon("chickicon.png"); // creates an image icon
                frame.setIconImage(image.getImage()); // change icon of frame
                frame.getContentPane().setBackground(new Color(0xCCE8B8)); // can use hexidecimal number

        another way is to use JFrame as a parent class to a child class     
        */

        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
