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

/*-----------------------------------------------------------------------------------------------------------------
 * Purpose: Main Menu of the game that lets you choose which of the three mini games you will play. It also designs
 *          the main menu and tells the player the objective of the game: to make Chicken feel better. The main menu
 *          is also where the player will be taken after sucessfuly completing the game. It shows the ending of each
 *          mini game and if 3/3 mini games are completed, the secret ending will be unlocked.
 * Images: The chicken gifs I used for the endings were from tenor. The music is made by 茶葉のぎか on youtube.
/*-----------------------------------------------------------------------------------------------------------------*/

public class ChickenGui extends JFrame implements ActionListener { 

    /* Some variables will be used by Library Game */
    public JButton friendButton;
    public JLabel friendLabel; // label that makes friend pop up after clicking button to hang out
    public  JButton complimentButton;
    public boolean complimenting = false;
    public JLabel complimentSuccessLabel;
    public JLabel complimentFailLabel;

    public JButton foodButton;
    public  JLabel foodLabel;

    private JButton resetButton;
    
    public JLabel objective; // label that has the objective of GUI which is to make chicken happy
    public JLabel goodEnding;
    public JLabel badEnding;

    // for the sad music
    private final File sadAudioFile;
    private final AudioInputStream sadAudio;
    private final Clip sadClip;

    // for the happy music
    private final File happyAudioFile;
    private final AudioInputStream happyAudio;
    private final Clip happyClip;

    // Counts if player has done all three mini games
    private boolean[] gamesFinished = {false, false, false}; // Represent Food, Library, and Clothes game
    private JButton endingButton;

    String[] endingDialogue = {"Chicken: Thank you, penguin.", 
                                "Chicken: Sometimes, I get so caught up in my work,", 
                                "Chicken: that I forget to breath,",
                                "Chicken: eat some good food,", 
                                "Chicken: appreciate all I've done",
                                "Chicken: and the friends I've made along the way", 
                                "Chicken: Thank you, for sticking around :D"};
    private int dialogueClicked = 0;

    public ChickenGui() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /* The constructor generates all the necessary components for the main menu's GUI */ 

        // music customization
        sadAudioFile = new File("soundForSadChicken.wav");
        sadAudio = AudioSystem.getAudioInputStream(sadAudioFile);
        sadClip = AudioSystem.getClip();
        sadClip.open(sadAudio);
        sadClip.start();

        happyAudioFile = new File("soundForHappyChicken.wav");
        happyAudio = AudioSystem.getAudioInputStream(happyAudioFile);
        happyClip = AudioSystem.getClip();
        happyClip.open(happyAudio);

        // creating a hanging out with friend button
        friendButton = new JButton(); 
        friendButton.setBounds(250, 350, 100, 50); 

        // customizing the friend button
        friendButton.setText("Give Hug");
        friendButton.setFocusable(false); // removes random border around text
        friendButton.setHorizontalTextPosition(JButton.CENTER);
        friendButton.setVerticalTextPosition(JButton.CENTER); 
        friendButton.setFont(new Font("Mali", Font.BOLD, 12)); 
        friendButton.setForeground(Color.white); // forgeround is text color
        friendButton.setBackground(new Color(0xD3C163));
        friendButton.setBorder(BorderFactory.createEtchedBorder()); // gives a 3d effect to the button
        this.add(friendButton); 

        // display a label on our frame after clicking friend button
        friendLabel = new JLabel();
        ImageIcon friend = new ImageIcon("img/friend.gif");
        friendLabel.setIcon(friend);
        friendLabel.setBounds(14, 0, 400, 424);
        friendLabel.setVisible(false);
        this.add(friendLabel);
        friendButton.addActionListener(this); // friend now pops up after button is hit

        // Creating a complimenting the chicken button
        complimentButton = new JButton();
        complimentButton.setBounds(150, 350, 100, 50);
        complimentButton.setText("Compliment");
        complimentButton.setFocusable(false);
        complimentButton.setFont(new Font("Mali", Font.BOLD, 12));
        complimentButton.setForeground(Color.white);
        complimentButton.setBackground(new Color(0xD3C163));
        complimentButton.setBorder(BorderFactory.createEtchedBorder());
        this.add(complimentButton);

        // Getting the image of good and bad endings of complimenting chicken game
        complimentSuccessLabel = new JLabel();
        ImageIcon complimentedChick = new ImageIcon("img/complimented.gif");
        complimentSuccessLabel.setIcon(complimentedChick);
        complimentSuccessLabel.setBounds(32, -10, 400, 400);
        complimentSuccessLabel.setVisible(false);
        this.add(complimentSuccessLabel);

        complimentFailLabel = new JLabel();
        ImageIcon cryingChick = new ImageIcon("img/sad.gif");
        complimentFailLabel.setIcon(cryingChick);
        complimentFailLabel.setBounds(50, 0, 400, 400);
        complimentFailLabel.setVisible(false);
        this.add(complimentFailLabel);

        complimentButton.addActionListener(this);

        // Adding in the giving food to chicken button
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
        ImageIcon foodIcon = new ImageIcon("img/eating.gif");
        foodLabel.setIcon(foodIcon);
        foodLabel.setBounds(80, -20, 500,500);
        foodLabel.setVisible(false);
        this.add(foodLabel);

        foodButton.addActionListener(this);

        // Making a reset button
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

        // Creating the Objective Label
        objective = new JLabel(); // creates label
        objective.setText("make chicken happy :)"); // set text of label

        // customizing text of Objective JLabel
        objective.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of ImageIcon
        objective.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER,BOTTOM of ImageIcon
        objective.setForeground(new Color(0x9E6B1D)); // sets color of text
        objective.setFont(new Font("Mali", Font.BOLD, 20)); // sets font of text

        objective.setIconTextGap(0); // set gap of text to image
        objective.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon + text within label
        objective.setHorizontalAlignment(JLabel.CENTER); // set horizontal position of icon + text within label
        
        // add sad chicken image to Objective
        ImageIcon chicken = new ImageIcon("img/sadchick.png"); // creates image
        objective.setIcon(chicken); // adds it to JLabel

        // creating a border
        Border border = BorderFactory.createLineBorder(new Color(0xEEE7D0), 30);
        objective.setBorder(border);
        
        // Ending Jlabel - text after good or bad ending */
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

        // JFrame customization
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sad Chicken");
        this.setSize(420,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // sets location of frame to the middle of your computer screen
        this.setVisible(true);

        ImageIcon image = new ImageIcon("chickicon.png");
        this.setIconImage(image.getImage()); // change icon of frame
        this.getContentPane().setBackground(new Color(0xCCE8B8));
    }

    public void setGameFinished() {
        /* Increase Games Finished variable. Used by ComplimentWindow when complimenting game finished successfully. */
        gamesFinished[1] = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Ensures clicking the buttons leads to desired game, retry button resets the options 
           and tracks succesful attempts. It also sets up the good endings for when the player
           sucessfully finishes the game. */

        friendButton.setVisible(false);
        complimentButton.setVisible(false);
        foodButton.setVisible(false);

        if(e.getSource() == friendButton) {
            sadClip.stop();
            happyClip.start();
            happyClip.loop(Clip.LOOP_CONTINUOUSLY);

            objective.setText(null);
            objective.setIcon(null);
            
            HugClothesGame clothesGame = new HugClothesGame(this);
            clothesGame.setVisible(true);
            this.setVisible(false);

            friendLabel.setVisible(true); // after hitting button, friend image pops up
            goodEnding.setVisible(true);
            this.setTitle("Happy Chicken");
            gamesFinished[2] = true;
        }

        if (e.getSource() == complimentButton) {
            sadClip.stop();
            happyClip.start();
            happyClip.loop(Clip.LOOP_CONTINUOUSLY);

            friendButton.setEnabled(false); // disables button
            foodButton.setEnabled(false);
            this.dispose();
            ComplimentLibraryGame libraryGame = new ComplimentLibraryGame(this);
            libraryGame.setVisible(false);

            if (!complimenting) {
                // Library Game
                libraryGame.setVisible(true);
            }

            if (complimenting) {
                libraryGame.gameOver();
            }
            
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
            gamesFinished[0] = true;
        }

        if (e.getSource() == endingButton) {
            dialogueClicked += 1;

            if (dialogueClicked < endingDialogue.length) {
                endingButton.setText(endingDialogue[dialogueClicked]);
            } else {
            }
        }

        if (e.getSource() == resetButton) {
            // gets rid of any of the endings you got
            friendLabel.setVisible(false);
                complimentFailLabel.setVisible(false);
                complimentSuccessLabel.setVisible(false);
                foodLabel.setVisible(false);
                goodEnding.setVisible(false);

            int success = 0;
            for (boolean gameSuccessful: gamesFinished) {
                if (gameSuccessful) {
                    success++;
                    System.out.println("game finished: " + gameSuccessful);
                    System.out.println("success: " + success);
                }
            }

            if (success > 2) {
                this.remove(resetButton);
                resetButton.setEnabled(false);
                endingButton = new JButton(endingDialogue[0]);
                endingButton.setForeground(Color.BLACK);
                endingButton.setBackground(new Color(0xCCE8B8));
                endingButton.setFocusable(false);
                endingButton.setVisible(true);
                endingButton.setBounds(30, 310, 343, 120); 
                endingButton.addActionListener(this);
                objective.setText("Chicken is happy! :D"); 
                objective.add(endingButton);
                objective.setIcon(new ImageIcon("img/ending.gif"));
            } else {
                this.setTitle("Sad Chicken");

                // shows the options / buttons again
                friendButton.setVisible(true);
                complimentButton.setVisible(true);
                complimenting = false; // reset the maze game
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

            happyClip.stop();
            sadClip.start();
            sadClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}