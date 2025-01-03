import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*-----------------------------------------------------------------------------------------------------------------
 * Purpose: has the logic for the food catching game: includes drawing the GUI elements and collision detection
 * Images: Penguin jumping gif was from Tenor
 /*-----------------------------------------------------------------------------------------------------------------*/

public class FoodCatchGame extends JFrame{
    ChickenGui mainFrame;
    FoodCatchGame foodFrame;

    public FoodCatchGame(ChickenGui frame) {
        this.add(new FoodPanel());
        this.setTitle("Catch the Banana");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setIconImage(new ImageIcon("img/chickicon.png").getImage());
        this.setLocationRelativeTo(null);

        mainFrame = frame;
        foodFrame = this;
    }

    public class FoodPanel extends JPanel implements ActionListener, KeyListener {
        // width and height of game
        private static final int SCREEN_WIDTH = 521;
        private static final int SCREEN_HEIGHT = 700;

        // panel grid / units
        private static final int UNIT_SIZE = 40;
        private static final int DELAY = 75; // how fast we want the game to be

        // creating our penguin friend + its initial spot
        private final Image penguin;
        private final Image penguinBadEnding;
        private int penguinX = 0;
        private int penguinY = 450;

        // variables for our mangoes
        private final Image mango;
        private int mango1X = 0;
        private int mango1Y = 0;
        private int mango2X = 25;
        private int mango2Y = 0;

        // variables for our banana
        private final Image banana;
        private int bananasCollected;
        private int bananaX = 0;
        private int bananaY = 0;
        private int velocityY = 13;

        // buttons
        JButton retryButton;
        JButton giveButton;

        // game running
        private boolean running = false;
        private boolean success = false;
        private char direction = 'N';
        private Timer timer;
        private Random random;

        public FoodPanel() {
            // Cuztomizing FoodPanel
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            this.setBackground(new Color(0xCCE8B8));
            this.setFocusable(true);

            // Instantiating our penguin friend + fruits
            penguin = new ImageIcon("img/friendFood.gif").getImage();
            penguinBadEnding = new ImageIcon("img/friendGameOver.gif").getImage();
            banana = new ImageIcon("img/banana.png").getImage();
            bananasCollected = 0;
            mango = new ImageIcon("img/mango.png").getImage();

            // instantiating buttons
            retryButton = new JButton("Retry");
            giveButton = new JButton("Give to Chicken");

            retryButton.setSize(100, 50);
            retryButton.setFont(new Font("Mali", Font.BOLD, 12));
            retryButton.setFocusable(false);
            retryButton.setForeground(Color.white);
            retryButton.setBackground(new Color(0xD3C163));
            retryButton.setBorder(BorderFactory.createEtchedBorder());
            retryButton.setVisible(false);

            giveButton.setSize(100, 50);
            giveButton.setFont(new Font("Mali", Font.BOLD, 12));
            giveButton.setFocusable(false);
            giveButton.setForeground(Color.white);
            giveButton.setBackground(new Color(0xD3C163));
            giveButton.setBorder(BorderFactory.createEtchedBorder());
            giveButton.setVisible(false);

            this.add(retryButton);
            this.add(giveButton);

            retryButton.addActionListener(this);
            giveButton.addActionListener(this);
            this.addKeyListener(this);

            // starting game
            startGame();
        } 

        public void startGame() {
            random = new Random();
            newMango();
            newBanana();
            running = true;
            timer = new Timer(DELAY, this);
            timer.start();
        }

        private void newMango() {
            mango1X = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
            mango2X = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        }

        private void newBanana() {
            bananaX = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        public void draw(Graphics g) {
            if (running) {
                
                g.setColor(Color.white);
                // creating panel grid to see where we can place our items
                for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }
                

                // draw our penguin friend
                g.drawImage(penguin, penguinX, penguinY, this);

                // draw our mangoes
                g.setColor(new Color(0xF5E134));
                g.drawImage(mango, mango1X, mango1Y, this);
                g.drawImage(mango, mango2X, mango2Y, this);

                // draw our banana
                g.setColor(Color.yellow);
                g.drawImage(banana, bananaX, bananaY, this);

                // our objective + count of bananas
                g.setColor(Color.black);
                g.setFont(new Font("Calibri", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(g.getFont()); // so we can put it in center
                g.drawString("Chicken wants 3 bananas :>", ((SCREEN_WIDTH) - metrics.stringWidth("Chicken wants 3 bananas :>")) / 2, SCREEN_HEIGHT / 4);
                
                g.setColor(new Color(0xEF4742));
                g.setFont(new Font("Calibri", Font.BOLD, 12));
                FontMetrics metrics2 = getFontMetrics(g.getFont()); // so we can put it in center
                g.drawString("Bananas Collected: " + bananasCollected, ((SCREEN_WIDTH) - metrics2.stringWidth("Bananas Collected: " + bananasCollected)) / 2, 195);

            } else if (!running && !success) {
                tryAgain(g);
            } else {
                gameSuccess(g);
            }
            
        }

        public void tryAgain(Graphics g) {
            g.drawImage(penguinBadEnding, 90, 220, this);
            retryButton.setVisible(true);
            g.setColor(new Color(0x9E6B1D));
            g.setFont(new Font("Calibri", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont()); // so we can put it in center
            g.drawString("Wrong Fruit T-T", ((SCREEN_WIDTH) - metrics.stringWidth("Wrong Fruit T-T")) / 2, 220); 
        }

        public void gameSuccess(Graphics g) {
            g.drawImage(penguin, 70, 240, this);
            giveButton.setVisible(true);
            g.setColor(new Color(0x9E6B1D));
            g.setFont(new Font("Calibri", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont()); 
            g.drawString("Success! Give it to chicken :D", ((SCREEN_WIDTH) - metrics.stringWidth("Success! Give it to chicken :D")) / 2, 240); 
        }

        public void penguinMove() {
            switch(direction) {
                case 'L':
                    if (penguinX > -100) {
                        penguinX -= UNIT_SIZE;
                    }
                    break;
                case 'R':
                    if (penguinX < SCREEN_WIDTH - penguin.getWidth(null) + UNIT_SIZE * 2.6) {
                        penguinX += UNIT_SIZE;
                    }
                    break;
            }
        }

        public void fruitsMove() {
            mango1Y = mango1Y + velocityY;
            mango2Y = mango2Y + velocityY + 2; // one is faster than the other
            bananaY = bananaY + velocityY + 2;
        }

        public void checkFruits() {
            // checks to see if the fruits have left the screen, if so will spawn new ones
            if (mango1Y >= SCREEN_HEIGHT + UNIT_SIZE * 2) {
                mango1Y = 0; mango2Y = 0;
                newMango();
            }

            if (bananaY >= SCREEN_HEIGHT + UNIT_SIZE * 2) {
                bananaY = 0;
                newBanana();
            }
        }

        public void checkCollisions() {
            int sparkleWidth = 100; 
            int sparkleHeight = 100;

            /* Checks to see if penguin's boundbox intersects with bound box of fruits */
            int penguinHitboxX = penguinX + sparkleWidth;
            int penguinHitboxY = penguinY + sparkleHeight;
            int penguinHitboxWidth = penguin.getWidth(null) - (2 * sparkleWidth);
            int penguinHitboxHeight = penguin.getHeight(null) - (2 * sparkleHeight);
            int fruitsWidth = UNIT_SIZE - 10;
            int fruitsHeight = UNIT_SIZE - 10; 

            // check if penguin intersects with banana or mangoes
            if ( (penguinHitboxX < bananaX + fruitsWidth && penguinHitboxX + penguinHitboxWidth > bananaX) && 
               (penguinHitboxY < bananaY + fruitsHeight && penguinHitboxY + penguinHitboxHeight > bananaY)) {
                bananasCollected += 1; bananaY = 0;
                if (bananasCollected >= 3) {
                    running = false; success = true;
                }
                newBanana();
            } else if  ( (penguinHitboxX < mango1X + fruitsWidth && penguinHitboxX + penguinHitboxWidth > mango1X) && 
               (penguinHitboxY < mango1Y + fruitsHeight && penguinHitboxY + penguinHitboxHeight > mango1Y)) {
                running = false; success = false;

            } else if ( (penguinHitboxX < mango2X + fruitsWidth && penguinHitboxX + penguinHitboxWidth > mango2X) && 
               (penguinHitboxY < mango1Y + fruitsHeight && penguinHitboxY + penguinHitboxHeight > mango2Y)) {
                running = false; success = false;
            }

            if (!running) {
                timer.stop();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == retryButton) {
                running = true;

                // reset everything again
                mango1Y = 0; mango2Y = 0; bananaY = 0;
                bananasCollected = 0;

                retryButton.setVisible(false);
                startGame();
            }

            if (e.getSource() == giveButton) {
                foodFrame.setVisible(false);
                mainFrame.setVisible(true);
            }

            // lets set bounds
            fruitsMove();
            checkFruits();
            penguinMove();
            checkCollisions();
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a':
                    direction = 'L';
                    break;
                case 'd':
                    direction = 'R';
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}