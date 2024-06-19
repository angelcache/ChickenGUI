import javax.swing.JFrame; // this import is needed to make a frame

public class Main {
    public static void main(String[] args) {

        // JFrame = GUI window to add components to

        JFrame frame = new JFrame(); // creates a frame, right now it isn't visible

        frame.setTitle("My first GUI"); // sets title of frame
        frame.setSize(420,420); // sets x-dimension and y-dimension of frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so now when we hit the x button it will exit the program
        frame.setVisible(true); // makes the frame visible


    }
}
