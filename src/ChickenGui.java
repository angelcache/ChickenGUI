import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class ChickenGui extends JFrame implements ActionListener { 
    /* This class is my attempt at learning and implementing what I learnt by making a little 
    *  chicken GUI. In this GUI, you are given a tasks to make a sad and overworked chick happy and are given 
    *  some options to do so. The chicken gifs I used were from tenor. The music is made by 茶葉のぎか on youtube.
    */

    // extends makes MyFrame a sublass of parent class Jframe
    // implement adds a method from ActionListener

    JButton friendButton;
    JLabel friendLabel; // label that makes friend pop up after clicking button to hang out

    JButton complimentButton;
    JLabel complimentSuccessLabel;
    JLabel complimentFailLabel;

    JButton foodButton;
    JLabel foodLabel;

    JButton resetButton;
    
    JLabel objective; // label that has the objective of GUI which is to make chicken happy
    JLabel goodEnding;
    JLabel badEnding;

    // for the sad music
    File sadAudioFile;
    AudioInputStream sadAudio;
    Clip sadClip;

    // for the happy music
    File happyAudioFile;
    AudioInputStream happyAudio;
    Clip happyClip;

    public ChickenGui() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
        /* music customization */
        sadAudioFile = new File("soundForSadChicken.wav");
        sadAudio = AudioSystem.getAudioInputStream(sadAudioFile);
        sadClip = AudioSystem.getClip();
        sadClip.open(sadAudio);
        sadClip.start();

        happyAudioFile = new File("soundForHappyChicken.wav");
        happyAudio = AudioSystem.getAudioInputStream(happyAudioFile);
        happyClip = AudioSystem.getClip();
        happyClip.open(happyAudio);

        /* creating a hanging out with friend button */
        // ImageIcon buttonIcon = new ImageIcon("chickicon"); // can add image to buttons
        friendButton = new JButton(); 
        friendButton.setBounds(250, 350, 100, 50); 

        /*customizing the button*/
        friendButton.setText("Give Hug");
        friendButton.setFocusable(false); // removes random border around text
        // button.setIcon(buttonIcon); button.setIconTextGap(); 
        friendButton.setHorizontalTextPosition(JButton.CENTER);
        friendButton.setVerticalTextPosition(JButton.CENTER); 
        friendButton.setFont(new Font("Mali", Font.BOLD, 12)); 
        friendButton.setForeground(Color.white); // forgeround is text color
        friendButton.setBackground(new Color(0xD3C163));
        friendButton.setBorder(BorderFactory.createEtchedBorder()); // gives a 3d effect to the button
        this.add(friendButton); 

        /* display a label on our frame after clicking friend button */
        friendLabel = new JLabel();
        ImageIcon friend = new ImageIcon("friend.gif");
        friendLabel.setIcon(friend);
        friendLabel.setBounds(14, 0, 400, 424);
        friendLabel.setVisible(false);
        this.add(friendLabel);
        friendButton.addActionListener(this); // friend now pops up after button is hit
        // button.addActionListener(e -> System.out.println("poo")); // action listener lamda expression shortcut

        /* Creating a complimenting the chicken button */
        complimentButton = new JButton();
        complimentButton.setBounds(150, 350, 100, 50);
        complimentButton.setText("Compliment");
        complimentButton.setFocusable(false);
        complimentButton.setFont(new Font("Mali", Font.BOLD, 12));
        complimentButton.setForeground(Color.white);
        complimentButton.setBackground(new Color(0xD3C163));
        complimentButton.setBorder(BorderFactory.createEtchedBorder());
        this.add(complimentButton);

        /* getting the image of good and bad endings of complimenting chicken */
        complimentSuccessLabel = new JLabel();
        ImageIcon complimentedChick = new ImageIcon("complimented.gif");
        complimentSuccessLabel.setIcon(complimentedChick);
        complimentSuccessLabel.setBounds(32, -10, 400, 400);
        complimentSuccessLabel.setVisible(false);
        this.add(complimentSuccessLabel);

        complimentFailLabel = new JLabel();
        ImageIcon cryingChick = new ImageIcon("sad.gif");
        complimentFailLabel.setIcon(cryingChick);
        complimentFailLabel.setBounds(20, 0, 400, 400);
        complimentFailLabel.setVisible(false);
        this.add(complimentFailLabel);

        complimentButton.addActionListener(this);

        /* Adding in the giving food to chicken button */
        foodButton = new JButton();
        foodButton.setBounds(50, 350, 100, 50);
        foodButton.setText("Give Food");
        foodButton.setFont(new Font("Mali", Font.BOLD, 12));
        foodButton.setFocusable(false);
        foodButton.setForeground(Color.white);
        foodButton.setBackground(new Color(0xD3C163));
        foodButton.setBorder(BorderFactory.createEtchedBorder());
        this.add(foodButton);

        foodLabel = new JLabel();
        ImageIcon foodIcon = new ImageIcon("eating.gif");
        foodLabel.setIcon(foodIcon);
        foodLabel.setBounds(20, -20, 500,500);
        foodLabel.setVisible(false);
        this.add(foodLabel);

        foodButton.addActionListener(this);

        /* Making a reset button */
        resetButton =  new JButton();
        resetButton.setBounds(150, 350, 100, 50);
        resetButton.setText("Reset");
        resetButton.setFont(new Font("Mali", Font.BOLD, 12));
        resetButton.setFocusable(false);
        resetButton.setForeground(Color.white);
        resetButton.setBackground(new Color(0xD3C163));
        resetButton.setBorder(BorderFactory.createEtchedBorder());
        this.add(resetButton);
        resetButton.addActionListener(this);

        /* Creating the Objective Label */
        objective = new JLabel(); // creates label
        objective.setText("make chicken happy :)"); // set text of label

        /* customizing text of Objective JLabel */
        objective.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of ImageIcon
        objective.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER,BOTTOM of ImageIcon
        objective.setForeground(new Color(0x9E6B1D)); // sets color of text
        objective.setFont(new Font("Mali", Font.BOLD, 20)); // sets font of text

        objective.setIconTextGap(0); // set gap of text to image
        // objective.setBackground(Color.black); // set background color
        // objective.setOpaque(true); // to display background color
        objective.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon + text within label
        objective.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon + text within label
        
        /* add sad chicken image to Objective */
        ImageIcon chicken = new ImageIcon("sadchick.png"); // creates image
        objective.setIcon(chicken); // adds it to JLabel
        // creating a border
        Border border = BorderFactory.createLineBorder(new Color(0xEEE7D0), 30);
        objective.setBorder(border);
        
        /* Ending Jlabel - text after good or bad ending */
        goodEnding = new JLabel("Chicken is now happy :D");
        goodEnding.setBounds(88,0, 400, 148);
        goodEnding.setForeground(new Color(0xEFFF7F)); // sets color of text
        goodEnding.setFont(new Font("Mali", Font.BOLD, 20)); // sets font of text
        goodEnding.setVisible(false); // makes Ending Jlabel invisible

        badEnding = new JLabel("Wow, shame on you. Try Again.");
        badEnding.setBounds(54,0, 400, 148);
        badEnding.setForeground(new Color(0xEFFF7F)); 
        badEnding.setFont(new Font("Mali", Font.BOLD, 20)); 
        badEnding.setVisible(false); 
        
        // adds the endings and objective labels to JFrame
        this.add(badEnding);
        this.add(goodEnding);
        this.add(objective); // adds the objective label w/ text + image we made earlier

        /* JFrame customization */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sad Chicken");
        this.setSize(420,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // sets location of frame to the middle of your computer screen
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
        friendButton.setVisible(false);
        complimentButton.setVisible(false);
        foodButton.setVisible(false);

        if(e.getSource() == friendButton) {
            sadClip.stop();
            happyClip.start();

            objective.setText(null);
            objective.setIcon(null);
            HugWindow hugWindow = new HugWindow(this);
            hugWindow.setVisible(true);
            this.setVisible(false);

            friendLabel.setVisible(true); // after hitting button, friend image pops up
            goodEnding.setVisible(true);
            this.setTitle("Happy Chicken");
        }

        if (e.getSource() == complimentButton) {
            sadClip.stop();
            happyClip.start();

            friendButton.setEnabled(false); // disables button
            foodButton.setEnabled(false);
            this.dispose();

            LibraryGame libraryGame = new LibraryGame(this);
            libraryGame.setVisible(true);

            /*
            int result = 0;
            if (libraryGameResult == 1) {
                ComplimentWindow window = new ComplimentWindow();
                result = window.Result();
            }

            // complimented chicken
            if (result == 1) { 
                friendButton.setEnabled(false);
                badEnding.setVisible(false);
                complimentFailLabel.setVisible(false);
                complimentSuccessLabel.setVisible(true);
                objective.setText(null);
                objective.setIcon(null);
                goodEnding.setVisible(true);
                this.setVisible(true);
                this.setTitle("Happy Chicken");
                complimentButton.setEnabled(false); // got to be at end, doesn't work at front
            }

            // insulted chicken
            if (result == 0 ||  result == 2) { 
                friendButton.setVisible(true);
                complimentButton.setVisible(true);
                foodButton.setVisible(true);
                badEnding.setVisible(true);
                complimentFailLabel.setVisible(true);
                objective.setText(null);
                objective.setIcon(null);
                this.setVisible(true);
            }
            */      
        }
        
        // chicken eating
        if (e.getSource() == foodButton) {
            sadClip.stop();
            happyClip.start();

            objective.setText(null);
            objective.setIcon(null);
            
            FoodWindow foodWindow = new FoodWindow(this);
            foodWindow.setVisible(true);
            this.setVisible(false);

            foodLabel.setVisible(true);
            goodEnding.setVisible(true);
            
            this.setTitle("Happy Chicken");
        }

        if (e.getSource() == resetButton) {
            happyClip.stop();
            sadClip.start();

            // gets rid of any of the endings you got
            this.setTitle("Sad Chicken");
            friendLabel.setVisible(false);
            complimentFailLabel.setVisible(false);
            complimentSuccessLabel.setVisible(false);
            foodLabel.setVisible(false);
            goodEnding.setVisible(false);

            // shows the options / buttons again
            friendButton.setVisible(true);
            complimentButton.setVisible(true);
            foodButton.setVisible(true);
            friendButton.setEnabled(true);
            complimentButton.setEnabled(true);
            foodButton.setEnabled(true);

            // shows the sad chicken and objective again [not the best way since I had to rewrite same code...]
            objective.setText("make chicken happy :)"); 
            objective.setHorizontalTextPosition(JLabel.CENTER); 
            objective.setVerticalTextPosition(JLabel.TOP); 
            objective.setForeground(new Color(0x9E6B1D)); 
            objective.setFont(new Font("Mali", Font.BOLD, 20)); 

            objective.setIconTextGap(0); 
            objective.setVerticalAlignment(JLabel.CENTER); 
            objective.setHorizontalAlignment(JLabel.CENTER);
            
            /* add image to JLabel */
            ImageIcon chicken = new ImageIcon("sadchick.png"); // creates image
            objective.setIcon(chicken); // adds it to JLabel

        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
