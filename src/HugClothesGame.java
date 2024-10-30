import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class HugClothesGame extends JFrame {
    private HugClothesGame thisFrame;
    private ChickenGui mainFrame;

    public HugClothesGame(ChickenGui frame) {
        thisFrame = this;
        mainFrame = frame;

        this.add(new introPanel());
        //this.add(new gamePanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setSize(567, 590); // 517
        this.setLocationRelativeTo(null);
        this.setTitle("Fold the Clothes Neatly");
        this.setIconImage(new ImageIcon("chickicon.png").getImage());
        this.pack();
    }

    private class introPanel extends JPanel implements ActionListener {
        private String[] strings = {"Hey chi- What is going on here?", 
                            "This place looks like a dump..", 
                            "Go take a bath I'll help you clean up!"};
        private ImageIcon door = new ImageIcon("Door");                    
        private JButton transitionButton;

        public introPanel() {
            this.setBackground(new Color(0xD3C163));
            this.setPreferredSize(new Dimension(550, 550));
            this.setFocusable(true);

            transitionButton = new JButton("*knock* *knock*");
            this.add(transitionButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == transitionButton) {
                
            }
        }
    }

    private class gamePanel extends JPanel implements ActionListener{
        // width and height of game
        private static final int SCREEN_WIDTH = 550;
        private static final int SCREEN_HEIGHT = 550;

        // panel grid / units
        private static final int UNIT_SIZE = 50;
        private static final int DELAY = 120; // how fast we want the game to be
        private int velocity = 50;
        private int movementTracker = 50; // moves the same way pink shirt would

        // tshirt images + their locations
        private Image pinkShirt = new ImageIcon("pinkShirt.png").getImage();
        private Image blueShirt = new ImageIcon("blueShirt.png").getImage();
        private Image greenShirt = new ImageIcon("greenShirt.png").getImage();
        private int[] shirtXLocations = {50, 100, 150};
        private int[] shirtYLocations = {50, 50, 50};
        private int[] goalXLocations = {200, 250, 300};
        private int[] goalYLocations = {500, 500, 500};
        private int[] availableLocations = {200, 250, 300};

        private java.util.List<Integer> finalizedShirtXLocations = new ArrayList<>();
        private java.util.List<Integer> finalizedShirtYLocations = new ArrayList<>();
        private java.util.List<Image> finalizedShirts = new ArrayList<>();

        private boolean clothesDrop = false;
        private boolean newClothes = false;
        private int clothesLeft = 3;  // the amount of clothes you can still drop down
        private int finalVelocity = 0;
        private int stackedClothes = 0; // must stack 10 to win
        private int successfulStacks = 9;
        private boolean running = true;

        private JButton retryButton;
        private JButton hugButton;

        Timer timer;

        /**
         * The Constructor of gamePanel sets up the panel and starts the game
         */
        public gamePanel() {
            this.setBackground(new Color(0xD3C163));
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            this.setFocusable(true);

            retryButton = new JButton("Try Again");

            retryButton.setSize(100, 50);
            retryButton.setFont(new Font("Mali", Font.PLAIN, 12));
            retryButton.setFocusable(true);
            retryButton.setForeground(Color.white);
            retryButton.setBackground(new Color(0xCCE8B8));
            retryButton.setBorder(BorderFactory.createEtchedBorder());
            retryButton.setVisible(false);
            retryButton.addActionListener(this);

            hugButton = new JButton("Hug Chicken");
            hugButton.setSize(100, 50);
            hugButton.setFont(new Font("Mali", Font.BOLD, 12));
            hugButton.setFocusable(true);
            hugButton.setForeground(Color.white);
            hugButton.setBackground(new Color(0xCCE8B8));
            hugButton.setBorder(BorderFactory.createEtchedBorder());
            hugButton.setVisible(false);
            hugButton.addActionListener(this);

            this.add(retryButton);
            this.add(hugButton);

            this.addKeyListener(new TAdapter());
            startGame();
        }

        /**
         * Starts the game by starting the timer
         */
        public void startGame() {
            timer = new Timer(DELAY, this);
            timer.start();
        }

        /**
         * Moves the clothes around left to right
         */
        public void moveClothes() {
            for (int i = 0; i < shirtXLocations.length; i++) {
                if (i == 0) {
                    if (movementTracker >= SCREEN_WIDTH - (UNIT_SIZE * 3) || movementTracker == 0) { 
                        velocity = velocity * -1; // changes velocity, so wont go out of bounds
                    }
                    movementTracker += velocity; 
                    shirtXLocations[i] += velocity;
                } else {
                    shirtXLocations[i] += velocity;
                }
            }
        }

        /**
         * Moves the clothes down
         */
        public void dropClothes() {
            velocity = 50;
            for (int i = 0; i < shirtYLocations.length; i++) {
                if (shirtYLocations[i] == goalYLocations[i] - finalVelocity - (UNIT_SIZE)) { 
                    shirtCollisions();
                    return;
                } else {
                    shirtYLocations[i] += velocity;
                }
            }
        }
        
        /**
         * Checks if it collided with another shirt
         * If one did not --> wont respawn again and amount of shirts you have gets smaller
         * If one did --> will spawn again
         * if none of them did --> game over
         */
        public void shirtCollisions() {
            finalVelocity += 50;
            int allShirtsSuccessful = 0;
            clothesDrop = false;
            newClothes = true;

            for (int i = 0; i < shirtXLocations.length; i++) {
                if (shirtXLocations[i] == goalXLocations[0] || 
                shirtXLocations[i] == goalXLocations[1] || shirtXLocations[i] == goalXLocations[2]) {
                        allShirtsSuccessful += 1;
                }
            }

            // Stores finalized shirt locations and corresponding images
            if (allShirtsSuccessful == 3) {
                stackedClothes += 1;
                clothesLeft = 3; // resetting the counter for next round of clothes
                shirtYLocations[0] = 50; // putting the locations back to the beginning
                shirtYLocations[1] = 50;
                shirtYLocations[2] = 50;

                for (int i = 0; i < shirtXLocations.length; i++) {
                    finalizedShirtXLocations.add(goalXLocations[i]);
                    finalizedShirtYLocations.add(goalYLocations[i] - finalVelocity);
     
                    // Add the corresponding shirt image to the finalized list
                    switch(i) {
                        case 0:
                            finalizedShirts.add(pinkShirt);
                            break;
                        case 1:
                            finalizedShirts.add(blueShirt);
                            break;
                        case 2:
                            finalizedShirts.add(greenShirt);
                            break;
                    }
                }
            } else{
                checkEachClothesLocation();
            }
        }

        public void checkEachClothesLocation() {
            clothesLeft = 3;
            boolean availableSpot1 = false; boolean availableSpot2 = false; boolean availableSpot3 = false;
            
            for (int i = 0; i < shirtXLocations.length; i++) {
                if (shirtXLocations[i] == availableLocations[0]) {
                    availableSpot1 = true;
                    addFinalizeShirt(i);
                } else if (shirtXLocations[i] == availableLocations[1]) {
                    availableSpot2 = true;
                    addFinalizeShirt(i);
                } else if (shirtXLocations[i] == availableLocations[2]) {
                    availableSpot3 = true;
                    addFinalizeShirt(i);
                } else {
                    clothesLeft -= 1;
                    // want to make out of bounds and out of use
                    shirtXLocations[i] = 1000;
                    shirtYLocations[i] = 1000;
                }
            }

            if (!availableSpot1) {
                availableLocations[0] = 0;
            } 

            if (!availableSpot2) {
                availableLocations[1] = 0;
            } 

            if (!availableSpot3) {
                availableLocations[2] = 0;
            } 

            if (clothesLeft > 0) {
                stackedClothes++;
            }

        }

        public void addFinalizeShirt(int i) {
            finalizedShirtXLocations.add(shirtXLocations[i]);
            finalizedShirtYLocations.add(goalYLocations[i] - finalVelocity);
            switch(i) {
                case 0:
                    finalizedShirts.add(pinkShirt);
                    break;
                case 1:
                    finalizedShirts.add(blueShirt);
                    break;
                case 2:
                    finalizedShirts.add(greenShirt);
                    break;
            }
            shirtYLocations[i] = 50;
        }

        /**
         * All of the different draw methods draw a specific component of the mini game
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            draw(g);

        }

        public void drawFinalizedClothes(Graphics g) {
            for (int i = 0; i < finalizedShirtXLocations.size(); i++) {
                g.drawImage(finalizedShirts.get(i), finalizedShirtXLocations.get(i), finalizedShirtYLocations.get(i), this);
            }
        }

        public void drawBaseClothes(Graphics g) {
            g.drawImage(pinkShirt, goalXLocations[0], goalYLocations[0], this);
            g.drawImage(blueShirt, goalXLocations[1], goalYLocations[1], this);
            g.drawImage(greenShirt, goalXLocations[2], goalYLocations[2], this);

            g.drawImage(pinkShirt, shirtXLocations[0], shirtYLocations[0], this);
            g.drawImage(blueShirt, shirtXLocations[1], shirtYLocations[1], this);
            g.drawImage(greenShirt, shirtXLocations[2], shirtYLocations[2], this);
        }

        public void drawGrids(Graphics g) {
            g.setColor(Color.white);
                // creating panel grid to see where we can place our items
                for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i++) {
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }
        }

        public void drawTryAgain(Graphics g) {
            running = false;
            retryButton.setVisible(true);
            retryButton.revalidate(); // have to revalide and repaint for retry 
            retryButton.repaint(); // button to show up
            g.setColor(new Color(0x9E6B1D));
            g.setFont(new Font("Calibri", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont()); // so we can put it in center
            g.drawString("Still messy. Restack please T-T", ((SCREEN_WIDTH) - metrics.stringWidth("Still messy. Restack please T-T")) / 2, 220); 
        }

        public void gameOver(Graphics g) {
            running = false;
            hugButton.setVisible(true);
            hugButton.revalidate();
            hugButton.repaint();
            g.setColor(new Color(0x9E6B1D));
            g.setFont(new Font("Calibri", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont()); // so we can put it in center
            g.drawString("Nice! Chicken is back and thanks you, give them a hug!", ((SCREEN_WIDTH) - metrics.stringWidth("Nice! Chicken is back and thanks you, give them a hug!")) / 2, 220);
            
        }

        public void draw(Graphics g) {
            if (stackedClothes == successfulStacks) { 
                gameOver(g);
            } else if (clothesLeft > 0) {
                if (newClothes) {
                    drawFinalizedClothes(g);
                }
                drawGrids(g);
                drawBaseClothes(g);
            } else {
                drawTryAgain(g);
            }
        }

        /*
         * Action performed is responsible for moving the drawn clothes and dropping them
         * after receiving instructions from TAdapter via clothesDrop variable
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (clothesDrop) {
                dropClothes();
            } else {
                moveClothes();
            }
            
            if (e.getSource() == retryButton) {
                // reset everything again
                running = true;
                clothesLeft = 3;
                finalVelocity = 0;
                finalizedShirtXLocations.clear();
                finalizedShirtYLocations.clear();
                finalizedShirts.clear();

                int addXAmount = 50;
                int addYAmount = 50;
                for (int i = 0; i < shirtXLocations.length; i++) {
                    shirtXLocations[i] = addXAmount;
                    shirtYLocations[i] = addYAmount;
                    availableLocations[i] = goalXLocations[i];
                    addXAmount += 50;
                }
                movementTracker = 50;
                retryButton.setVisible(false);
            }

            if (e.getSource() == hugButton) {
                thisFrame.setVisible(false);
                HugWindow hugChickenFrame = new HugWindow(mainFrame);
                hugChickenFrame.setVisible(true);
            }

            if (running) {
                repaint();
            }
        }

        /*-----------------------------------------------------------------------------------------------------------------*/
        
        /**
         * When keys are pressed, the instructions to drop the clothes will be set to true
        */
        private class TAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    clothesDrop = true;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 's') {
                    clothesDrop = true; 
                }
            }
        }
    }
}
