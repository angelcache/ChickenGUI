//import java.awt.BorderLayout;
import java.awt.Color;
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
        
        ChickenGui frame = new ChickenGui();
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
        */

        /* Layout Manager = Defines natural layout for components within a container
         * FlowLayout [second type] = places components in row, sized at preffered size. If
         *                            horizontal space in container too small, FlowLayout
         *                            class uses next available row.
        

         JFrame frame3 = new JFrame();
         frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame3.setSize(500, 500);
         frame3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // can set an allignment, default: CENTER, left: LEADING, right: TRAILING
         
         JPanel panel = new JPanel();
         panel.setPreferredSize(new Dimension(250,250));
         panel.setBackground(Color.lightGray);
         panel.setLayout(new FlowLayout()); // by default, panels already use a flowlayout

         // with flow layouts, when it runs out of room in the first row, components pushed down to next row
         panel.add(new JButton("1"));
         panel.add(new JButton("2"));
         panel.add(new JButton("3"));
         panel.add(new JButton("4"));
         panel.add(new JButton("5"));
         panel.add(new JButton("6"));
         panel.add(new JButton("7"));
         panel.add(new JButton("8"));
         panel.add(new JButton("9"));

         frame3.add(panel);
         frame3.setVisible(true);

        /* Layout Manger = Defines the natural layout for components with a cotnainer
          * Grid Layout [third type] = places components in grid of cells. Each component takes all available space within cell,
          *                            and each cell is the same size.
        
        
        JFrame frame4 = new JFrame();
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setSize(500,500);
        frame4.setLayout(new GridLayout(3,3, 10, 10)); // first colum is amount of rows, second is amount of columns

        frame4.add(new JButton("1"));
        frame4.add(new JButton("2"));
        frame4.add(new JButton("3"));
        frame4.add(new JButton("4"));
        frame4.add(new JButton("5"));
        frame4.add(new JButton("6"));
        frame4.add(new JButton("7"));
        frame4.add(new JButton("8"));
        frame4.add(new JButton("9"));
          
        frame4.setVisible(true);
        */

        /* JLayered Pane = Swing container that provides a third dimension for positioning components eg. depth, Z-index

        JLabel label1 = new JLabel();
        label1.setOpaque(true); // so we can see label
        label1.setBackground(Color.RED);
        label1. setBounds(50,50,200,200);

        JLabel label2 = new JLabel();
        label2.setOpaque(true); 
        label2.setBackground(Color.GREEN);
        label2. setBounds(100,100,200,200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true); 
        label3.setBackground(Color.BLUE);
        label3. setBounds(150,150,200,200);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 500, 500);

        // can change the order --> there is a name to each layer -> default, palette, modal, popup, drag
        layeredPane.add(label1, Integer.valueOf(0)); // there is associate number for each layer
        layeredPane.add(label2, Integer.valueOf(2)); // the higher the number, the higher it is
        layeredPane.add(label3, Integer.valueOf(1));

        JFrame frame5 = new JFrame("JLayeredPane");
        frame5.add(layeredPane);
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame5.setSize(new Dimension(500,500));
        frame.setLayout(null);
        frame5.setVisible(true);
        */

        //LaunchPage page = new LaunchPage(); Used to practice making a new window

        /* JOptionPane = pop up a standard dialog box that promts users for a value or informs them of somehthing. */

        /* Basic Option Panes */
        //JOptionPane.showMessageDialog(null, "Hi there!", "FirstPane", JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Hello to whom this may concern!", "FirstPane", JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Hello?", "FirstPane", JOptionPane.QUESTION_MESSAGE);
        //JOptionPane.showMessageDialog(null, "HEY WATCH OUT!", "FirstPane", JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showMessageDialog(null, "H-h-h-HIIIJSDIIUSDIFJI!", "FirstPane", JOptionPane.ERROR_MESSAGE);
        
        /* Show confirm dialogue has a yes, no and cancel button
           Yes returns 0, No returns 1, cancel returns 2 
        */
        //int answer = JOptionPane.showConfirmDialog(null, "Do you like chicks?", "Chicken", JOptionPane.YES_NO_CANCEL_OPTION);
        
        /* Show Input Dialogue: allows user to input something */
        //String name = JOptionPane.showInputDialog("What is your name?");

        /* Last Option Pane: combines all the rest to one 
        String[] responses = {"No, you're awesome!", "Thank you!", "*blush*", "HEHEHE"}; // array of options
        ImageIcon icon = new ImageIcon("complimented.gif");
        JOptionPane.showOptionDialog(null, 
                                    "you are awesome!", 
                                    "secret message", 
                                    JOptionPane.YES_NO_CANCEL_OPTION, 
                                    JOptionPane.INFORMATION_MESSAGE, 
                                    icon, 
                                    responses, 
                                    0);
        */

        /* JTextField =  A GUI textbox component used to add, set, or get text eg. user can type out password*/
        //TestFrame textfield = new TestFrame();

        /* Checkboxes */
        // TestFrame checkbox = new TestFrame();

        /* JRadioButton = One or more buttons in a grouping in which only 1 may be selected per group*/
        // TestFrame buttons = new TestFrame();

        /* JComboBox = a component that combines a button or editable field and a drop-down list */
        // TestFrame comboBox = new TestFrame();

        /* JSlider = GUI component that lets user enter a value by using an adjustable sliding knob on a track */
        //TestFrame slide = new TestFrame();

        /* Progress bar = Visual adi to let the user know that an operation is proceding*/
        //TestFrame progressBar = new TestFrame();

        /* JMenuBars = a menu bar which many menus and items that have a specific function can be added */
        /* JFileChoose = a GUI mechanism that let's a user choose a file (helpful for opening or saving files) */
        //TestFrame menuBar = new TestFrame();

        // JColorChooser = a GUI mechanism that let's a user choose a cooler
        // TestFrame colorChooser = new TestFrame();

        // MouseListener = allows you to move components when mous button is invoked
        //TestFrame mouseListener = new TestFrame();

        TestFrame dragAndDrop = new TestFrame();

    }
}
