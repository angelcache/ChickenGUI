
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class TestFrame /*implements ChangeListener extends JFrame implements ActionListener*/ {
    JFrame frame = new JFrame();
    JProgressBar bar = new JProgressBar(0, 100);

    TestFrame() { 
        bar.setValue(0);
        bar.setBounds(0, 0, 420, 50);
        bar.setStringPainted(true);
        bar.setForeground(Color.green); // change color of bar
        bar.setBackground(Color.black); // change color of bar background

        frame.add(bar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null); // when using null layout manager must set bounds
        frame.setVisible(true);
        
        fill();
    }

    public void fill() {
        int counter = 0;

        // makes the progress bar move by 1% every milliseconds
        while (counter <= 100) {

            bar.setValue(counter);
            try {
                Thread.sleep(50); // moves by 50 milliseconds, 1000 is a second
            } catch (InterruptedException ex) {
                
            }
            counter += 1; // increments by 1 percent each time
        }

        bar.setString("You got Revived! :)"); // text shows up after bar is complete
        bar.setFont(new Font("Calibri", Font.BOLD, 25));
        
    }
    /* Sliders -- made a little temperature slider, which showed which tick you were on through the use of change listener
     * the change listner is very similar to action listener but instead of using buttons, it is used for sliders.
     * Sliders ould be great for making a little cooking game that needs you to set the temperature of an oven.
    */

    /*
    JFrame frame;
    JPanel panel;
    JLabel label;
    JSlider slider; 

    TestFrame() {
        frame = new JFrame("Slider Demo");
        panel = new JPanel();
        label = new JLabel();
        slider = new JSlider(0, 100, 50);

        slider.setPreferredSize(new Dimension(400, 200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10); // adds tick spacing

        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25); // after each 25 unit, there are larger ticks

        slider.setPaintLabels(true); // adds numbert o our major ticks
        slider.setFont(new Font ("Calibri", Font.PLAIN, 15)); // changed font

        //slider.setOrientation(SwingConstants.HORIZONRAL);
        slider.setOrientation(SwingConstants.VERTICAL); // makes orientation vertical

        slider.addChangeListener(this);

        label.setFont(new Font("Calibri", Font.BOLD, 25));
        // label.setText("°C = " + slider.getValue()); // get value of what slide is, when you move slider value doesn't change

        panel.add(slider);
        panel.add(label);
        frame.add(panel);
        frame.setSize(420,420);
        frame.setVisible(true);

    }
    @Override
    public void stateChanged(ChangeEvent e) {
        /*Whenever state changes for the slide it will execute the line of code below
        label.setText("°C = " + slider.getValue());
    
        throw new UnsupportedOperationException("Not supported yet.");
    }
    */

    /*JComboBox = a component that combines a button or editable field and a drop-down list, learnt more about drop down menus,
     * adding, removing and inserting items to it --> also using action performed and button so that after you select
     * an item from the menu something will happen.
     */

    /*
    JComboBox comboBox;

    TestFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        String[] animals = {"dog", "cat", "bird"}; // reference dat type to use for JComboBox

        // int[] numbers = {1,2,3} // does not work because it is a permitive data type
        // in order to do something like this you have to use Integer (wrapper class version)
        // Integer[] numbers = {1,2,3}

        comboBox = new JComboBox(animals);
        comboBox.addActionListener(this);

        //comboBox.setEditable(true); // can type item you're searching for
        //comboBox.addItem("horse"); // can add item 
        //comboBox.insertItemAt("pig", 0);
        //comboBox.setSelectedIndex(0); // makes the selected item as the pig
        //comboBox.removeItem("cat");
        //comboBox.removeItemAt(0); // removes the item at that index
        //comboBox.removeAllItems(); // removes all items

        this.add(comboBox);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            // System.out.println(comboBox.getSelectedItem()); // returns item
            // System.out.println(comboBox.getSelectedIndex()); // returns index
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }
    */

    //-----------------------------------------------------------------------------------------------------------------------//

    /* Below, we learnt how to use a textField which is helpful for taking in inputs from the user
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
        /*
        if (e.getSource() == button) {
            System.out.println("Welcome " + textField.getText());
            button.setEnabled(false);
            textField.setEditable(false); 
        }
        
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    */
    
    //-----------------------------------------------------------------------------------------------------------------------//

    /* Below, we learnt how to open up a new window 
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

    //-----------------------------------------------------------------------------------------------------------------------//

    /* Below we learnt how to create a checkbox as well as customize it, all of the customization is very similar
     * to the way we edit text so, overall, it is very self-explanatory. Once again, you can use buttons and action listener
     * to use it for multiple purposes.
     */

    /* 
    JButton button;
    JCheckBox checkBox;
    ImageIcon xIcon;
    ImageIcon checkIcon;

    CheckBox() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        
        xIcon = new ImageIcon("sadchick.png");
        checkIcon = new ImageIcon("complimented.gif");

        button = new JButton();
        button.setText("submit");
        button.addActionListener(this);

        checkBox = new JCheckBox();
        checkBox.setText("Learn GUI");
        checkBox.setFocusable(false);
        checkBox.setFont(new Font("Consolas", Font.PLAIN, 22));
        checkBox.setIcon(xIcon); // adds a custom icon for the checkbox
        checkBox.setSelectedIcon(checkIcon); 


        this.add(button);
        this.add(checkBox);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println(checkBox.isSelected()); // returns true or false if checkbox was selected
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    */

    //-----------------------------------------------------------------------------------------------------------------------//
    /* Below, we learnt about the basics of radio buttons, unlike a button, you can only select one of the buttons in 
     * a radio button. Once again, we can use action listener to create multiple functions for it. For instance, it could be
     * used to make a simple survey or exams which needs a multiple choice layout.
     */
    
     /* 
    JRadioButton pizzaButton;
    JRadioButton hamburgerButton;
    JRadioButton hotdogButton;
    ImageIcon pizzaIcon;
    ImageIcon hamburgerIcon;
    ImageIcon hotdogIcon;

    TestFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        // can change the button image
        pizzaIcon = new ImageIcon("chickicon.png");
        hamburgerIcon = new ImageIcon("complimented.gif");
        hotdogIcon = new ImageIcon("sadchick.png");
        
        pizzaButton = new JRadioButton("Pizza");
        hamburgerButton = new JRadioButton("Hamburger");
        hotdogButton = new JRadioButton("hotdog");

        pizzaButton.addActionListener(this);
        hamburgerButton.addActionListener(this);
        hotdogButton.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(pizzaButton);
        group.add(hamburgerButton);
        group.add(hotdogButton);

        // changing the button images, using function: .setIcon()
        pizzaButton.setIcon(pizzaIcon);
        hamburgerButton.setIcon(hamburgerIcon);
        hotdogButton.setIcon(hotdogIcon);

        // note: every time you hit one of the choices, it triggers event and can use action performed message to detect 
        //       which button was selected

        this.add(pizzaButton);
        this.add(hamburgerButton);
        this.add(hotdogButton);

        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pizzaButton) {
            System.out.println("You ordered a pizza! :)");
        } else if (e.getSource() == hamburgerButton) {
            System.out.println("You ordered a hamburger! :D");
        } else {
            System.out.println("You ordered a hotdog! :>");
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    */

    //--------------------------------------------------------------------------------------------------------------------//
    
}  
