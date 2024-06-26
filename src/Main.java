import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        /* Basics of making a GUI
            
            import java.awt.Color; // this import is needed to change a frames color
            import javax.swing.ImageIcon; // imports ImageIcon
            import javax.swing.JFrame; // needed to make a frame

            // JFrame = GUI window to add components to

                JFrame frame = new JFrame(); // creates a frame, right now it isn't visible

                frame.setTitle("My first GUI"); // sets title of frame
                frame.setSize(420,420); // sets x-dimension and y-dimension of frame

                frame.setResizable(false); // prevent frame from being resize
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so now when we hit the x button it will exit the program
                frame.setVisible(true); // makes the frame visible

                ImageIcon image = new ImageIcon("chickicon.png"); // creates an image icon
                frame.setIconImage(image.getImage()); // change icon of frame
                frame.getContentPane().setBackground(new Color(0xCCE8B8)); // can use hexidecimal number for color of frame

            another way to make it is to use JFrame as a parent class to a child class MyFrame    
        */

        /* JPanel = a GUI component that functions as a container to hold other components

            // Can add Images and Label to our JPanel, we will not be adding this to our JPanels, however
            ImageIcon icon = new ImageIcon("chickicon.png"); 
            JLabel label = new JLabel(); 
            label.setText("Hi");  
            label.setIcon(icon);  
            label.setVerticalAlignment(JLabel.CENTER); 
            label.setHorizontalAlignment(JLabel.CENTER); // would use if we were using borderlayout
        */

        // Creating JPanels for sadchicken GUI
        JPanel wallShadow = new JPanel(); // creates new panel
        wallShadow.setBackground(new Color(0xD3C163)); // set colour of panel
        wallShadow.setBounds(0, 0, 250, 255); // set size of panel

        JPanel wall = new JPanel();
        wall.setBackground(new Color(0xE6DC77));
        wall.setBounds(0, 0, 450, 320);

        JPanel greenForeground = new JPanel();
        greenForeground.setBackground(new Color(0xCCE8B8));
        greenForeground.setBounds(0,250, 250, 250);
        // panelThree.setLayout(new BorderLayout()); this is a border layout, another way to determine where text should go
        // label.setBounds(0, 0, 200, 200); // sets where text goes
        // panelThree.add(label); // adds text + image to the panel
        
        MyFrame frame = new MyFrame();
        frame.add(wallShadow);
        frame.add(wall);
        frame.add(greenForeground);

        /* Next we learn about border layout
         * Layout manager = Defines tha natural layout for components within a container [3 types]
         * BorderLayout [1st type] = A Border Layout places component in five areas: NORTH, WEST, EAST, SOUTH, CENTER 
         * 
         * expand proportionately...
         *      north + south: horizontally [can use north as title and south as footer]
         *      west + east: vertically [can use west or east as sidebars / navigation bars]
         *      center: to size of frame [used as main body of your program]
         */

        // Let's create a new frame to test out border layouts
        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500, 500);
        frame2.setLayout(new BorderLayout(10, 10)); // sets up new border layout, can send margin of width + height
        frame2.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(new Color(0xE5FDEE));
        panel2.setBackground(new Color(0xFEFED9));
        panel3.setBackground(new Color(0xD9FEF9));
        panel4.setBackground(new Color(0xF5D9FE));
        panel5.setBackground(new Color(0xFFFFFF));

        panel1.setPreferredSize((new Dimension(100,100)));
        panel2.setPreferredSize((new Dimension(100,100)));
        panel3.setPreferredSize((new Dimension(100,100)));
        panel4.setPreferredSize((new Dimension(100,100)));
        panel5.setPreferredSize((new Dimension(100,100)));

        // using borderlayouts to put these panels to their designated places
        frame2.add(panel1, BorderLayout.NORTH);
        frame2.add(panel2, BorderLayout.WEST);
        frame2.add(panel3, BorderLayout.EAST);
        frame2.add(panel4, BorderLayout.SOUTH);
        frame2.add(panel5, BorderLayout.CENTER);

        //------------------ sub panels = can add panels within a panel -------------------//
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();

        panel6.setBackground(new Color(0xE5FDEE));
        panel7.setBackground(new Color(0xFEFED9));
        panel8.setBackground(new Color(0xD9FEF9));
        panel9.setBackground(new Color(0xF5D9FE));
        panel10.setBackground(new Color(0xFFFFFF));

        panel5.setLayout(new BorderLayout());

        panel6.setPreferredSize((new Dimension(50,50)));
        panel7.setPreferredSize((new Dimension(50,50)));
        panel8.setPreferredSize((new Dimension(50,50)));
        panel9.setPreferredSize((new Dimension(50,50)));
        panel10.setPreferredSize((new Dimension(50,50)));

        panel5.add(panel6, BorderLayout.NORTH);
        panel5.add(panel7, BorderLayout.WEST);
        panel5.add(panel8, BorderLayout.EAST);
        panel5.add(panel9, BorderLayout.SOUTH);
        panel5.add(panel10, BorderLayout.CENTER);
        //-----------------------------------------------------------------------------------//

        /* Layout Manager = Defines natural layout for components within a container
         * FlowLayout [second type] = places components in row, sized at preffered size. If
         *                            horizontal space in container too small, FlowLayout
         *                            class uses next available row.
         */

         JFrame frame3 = new JFrame();
         frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame3.setSize(500, 500);
         frame3.setLayout(new FlowLayout());
         frame3.setVisible(true);

         frame3.add(new JButton("1"));

        
    }
}
