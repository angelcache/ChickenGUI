/*
 * Purpose: Penguin goes to the library to learn more about how to compliment chicken. The game will be a maze
 *          through the library where penguin must collect 3 books that will help him learn how to be a good
 *          comforter when a friend is not in the best mood.
 * Images: penguin walking and book gifs were made by me on Piskel
 */

/*-----------------------------------------------------------------------------------------------------------------*/

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;

/*-----------------------------------------------------------------------------------------------------------------*/

class LibraryGame extends JFrame {
    private ChickenGui mainFrame;
    private LibraryGame thisFrame;
    private final int SCREEN_WIDTH = 616;
    private final int SCREEN_HEIGHT = 640;

    private boolean running = false;
    private boolean success = false;

    /**
     * Library Game constructor generates the frame and customizes it
     */
    public LibraryGame(ChickenGui frame) {
        this.setTitle("Learn about complimenting");
        this.add(new LibraryPanel());
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        mainFrame = frame;
        thisFrame = this;
    }

    /**
         * ends the game and starts the complimenting window
         */
        public void gameOver() {
            ComplimentWindow window = new ComplimentWindow();
            int complimentResult = window.Result();
                
    
            // complimented chicken
            if (complimentResult == 1) { 
                mainFrame.friendButton.setEnabled(false);
                mainFrame.badEnding.setVisible(false);
                mainFrame.complimentFailLabel.setVisible(false);
                mainFrame.complimentSuccessLabel.setVisible(true);
                mainFrame.objective.setText(null);
                mainFrame.objective.setIcon(null);
                mainFrame.goodEnding.setVisible(true);
                mainFrame.setVisible(true);
                mainFrame.setTitle("Happy Chicken");
                mainFrame.complimentButton.setEnabled(false); // got to be at end, doesn't work at front
            }
    
            // insulted chicken
            if (complimentResult == 0 ||  complimentResult == 2) { 
                mainFrame.friendButton.setVisible(true);
                mainFrame.complimentButton.setVisible(true);
                mainFrame.foodButton.setVisible(true);
                mainFrame.badEnding.setVisible(true);
                mainFrame.complimentFailLabel.setVisible(true);
                mainFrame.objective.setText(null);
                mainFrame.objective.setIcon(null);
                mainFrame.setVisible(true);
            }     
        }

/*-----------------------------------------------------------------------------------------------------------------*/

    public class LibraryPanel extends JPanel implements ActionListener {
        private final int MAZE_UNITS = 40;
        private final int PANEL_MAZE = 15;

        private int penguinX = 1;
        private int penguinY = 1;

        private String penguinDirection = "front";
        private Image penguinFront = new ImageIcon("penguinFrontWalking.gif").getImage();
        private Image penguinBack = new ImageIcon("penguinBackWalking.gif").getImage();
        private Image penguinLeft = new ImageIcon("penguinLeftWalking.gif").getImage();
        private Image penguinRight = new ImageIcon("penguinRightWalking.gif").getImage();

        private int book1X = 0; 
        private int book1Y = 0;
        private int book2X = 0; 
        private int book2Y = 0;
        private int book3X = 0; 
        private int book3Y = 0;
        private Image book = new ImageIcon("book.gif").getImage();
        private Image book2 = new ImageIcon("book.gif").getImage();
        private Image book3 = new ImageIcon("book.gif").getImage();
        private Random random;

        private final int[][] maze = new int[MAZE_UNITS][MAZE_UNITS];

        private static final int DELAY = 75;
        private Timer timer;
        private int booksCollected = 0;
        private JButton successButton;

        /**
         * Library Panel constructor creates Panel, customizes it + starts the game by initializing the maze
         */
        public LibraryPanel() {
            this.setBackground(new Color(0xD3C163));
            this.setPreferredSize(new Dimension(PANEL_MAZE * MAZE_UNITS, PANEL_MAZE * MAZE_UNITS));
            this.setBackground(new Color(0xD3C163));
            this.setFocusable(true);
            this.addKeyListener(new TAdapter());

            successButton = new JButton();
            successButton.setBounds(150, 350, 100, 50);
            successButton.setText("Go Compliment Chicken!");
            successButton.setFont(new Font("Mali", Font.BOLD, 12));
            successButton.setFocusable(false);
            successButton.setForeground(Color.white);
            successButton.setBackground(new Color(0xD3C163));
            successButton.setBorder(BorderFactory.createEtchedBorder());
            
            this.add(successButton);
            successButton.setVisible(false);
            successButton.addActionListener(this);
            startGame();
        }

        /**
         * Calls the maze method and method for creation of books
         */
        private void startGame() {
            random = new Random();
            running = true;
            createMaze();
            newBooks();
            timer = new Timer(DELAY, this);
            timer.start();
        }

        /**
         * Initializes the books 
        */
        private void newBooks() {
            // First book always bottom right
            book1X = PANEL_MAZE - 2;
            book1Y = PANEL_MAZE - 2;

            // Second book is place in bottom left, ensures its placed on path (0)
           do { 
                book2X += 1;
                book2Y = PANEL_MAZE - 4;
            } while (maze[book2Y][book2X] != 0);

            // 3rd book top right: ensures its placed on path (0)
            do { 
                book3X = PANEL_MAZE - 2;
                book3Y += 1;
            } while (maze[book3Y][book3X] != 0);
        }

        /**
         * Creating the random path for the maze: we must use DFS to create a random pathway, forming the maze
         * 0 - pathway, 1 - walls
         */ 
        private void createMaze() {
            
            Stack<int[]> stack = new Stack<>();

            // Generate the matrix with just walls
            for (int y = 0; y < PANEL_MAZE; y++) {
                for (int x = 0; x < PANEL_MAZE; x++) {
                    maze[y][x] = 1;
                }
            }

            // Depth First Search (DFS) using a stack to track current location and randomizing directions of DFS
            maze[1][1] = 0;
            stack.push(new int[]{1,1});
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            while (!stack.isEmpty()) {
                int[] cell = stack.pop();
                int currentX = cell[0];
                int currentY = cell[1];

                // shuffle directions to ensure randomness of path - ensures DFS explores creates diff pathways each restart
                for (int i = 1; i < directions.length; i++) {
                    int[] temp = directions[i];
                    int randIndex = random.nextInt(directions.length);
                    directions[i] = directions[randIndex];
                    directions[randIndex] = temp;
                }

                for (int[]dir: directions) {
                    int newX = currentX + dir[0] * 2;
                    int newY = currentY + dir[1] * 2;

                    if (newX > 0 && newY > 0 && newX < PANEL_MAZE -1 && newY < PANEL_MAZE - 1 && maze[newY][newX] == 1) {
                        maze[newY][newX] = 0; // Create path at new position
                        maze[newY - dir[1]][newX - dir[0]] = 0; // maze array structured as row and then col so y first
                        stack.push(new int[]{newX, newY});
                    }
                }
            }

        }

        public void checkCollisions() {
            if (penguinX == book1X && penguinY == book1Y) {
                booksCollected += 1;
                book1X = -1;
            }
            if (penguinX == book2X && penguinY == book2Y) {
                booksCollected += 1;
                book2X = -1;
            }
            if (penguinX == book3X && penguinY == book3Y) {
                booksCollected += 1;
                book3X = -1;
            }

            if (booksCollected >= 3) {
                success = true;
            }
        }
    
        /**
         * Creates the drawing component and calls all the other drawing methods 
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!running && !success) {

            } else if (success) {
                g.setColor(new Color(0xCCE8B8));
                g.setFont(new Font("Calibri", Font.BOLD, 34));
                FontMetrics metrics = getFontMetrics(g.getFont()); 
                g.drawString("Success! Go compliment chicken :)", ((SCREEN_WIDTH) - metrics.stringWidth("Success! Go compliment chicken :)")) / 2, 240);
                successButton.setVisible(true);
            } else {
                drawMaze(g);
                drawPenguin(g);
                drawBooks(g);
            }
            
        }

        /**
         * Draws the Maze: checks if its a wall or path and colors accordingly
         */
        public void drawMaze(Graphics g) {

            for (int y = 0; y < PANEL_MAZE; y++) {
                for (int x = 0; x < PANEL_MAZE; x++) {
                    if (maze[y][x] == 1) {
                        g.setColor(new Color(0xBCA86D));
                        g.fillRect(x * MAZE_UNITS, y * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);
                    }

                    if (maze[y][x] == 0) {
                        g.setColor(new Color(0xEEE7D0));
                        g.fillRect(x * MAZE_UNITS, y * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);
                    }
                }
            }
        }

        /**
         * Draws the Penguin using our penguin image icon: will change icon depending on angle
         */
        private void drawPenguin(Graphics g) {
            switch (penguinDirection) {
                case "front":
                    g.drawImage(penguinFront, penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, this);
                    break;
                case "back":
                    g.drawImage(penguinBack, penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, this);
                    break;
                case "right":
                    g.drawImage(penguinRight, penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, this);
                    break;
                case "left":
                    g.drawImage(penguinLeft, penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, this);
                    break;
            }
        }

        /**
         * Draws the 3 Books and places in the maze
         */
        private void drawBooks(Graphics g) {
            g.drawImage(book, book1X * MAZE_UNITS, book1Y * MAZE_UNITS, this);
            g.drawImage(book2, book2X * MAZE_UNITS, book2Y * MAZE_UNITS, this);
            g.drawImage(book3, book3X * MAZE_UNITS, book3Y * MAZE_UNITS, this);
        }

        /**
         * Makes penguin move to given direction if new spot is a path and not a wall
         */
        private void movePenguin(int xDirection, int yDirection) {
            int newX = penguinX + xDirection;
            int newY = penguinY + yDirection;

            if (newX >= 0 && newY >= 0 && newX < PANEL_MAZE && newY < PANEL_MAZE && maze[newY][newX] == 0) {
                penguinX = newX;
                penguinY = newY;
            }
            repaint();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (running) {
                checkCollisions();
                repaint();
            }

            if (e.getSource() == successButton) {
                mainFrame.complimenting = true;
                thisFrame.setVisible(false);
                gameOver();
            }
        }

        /*-----------------------------------------------------------------------------------------------------------------*/
        
        /**
         * When keys are pressed, the penguin will move accordingly
        */
        private class TAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        movePenguin(-1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        movePenguin(1, 0);
                        break;
                    case KeyEvent.VK_UP:
                        movePenguin(0, -1);
                        break;
                    case KeyEvent.VK_DOWN:
                        movePenguin(0,1);
                        break;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'a':
                        movePenguin(-1, 0);
                        penguinDirection = "left";
                        break;
                    case 'd':
                        movePenguin(1, 0);
                        penguinDirection = "right";
                        break;
                    case 'w':
                        movePenguin(0, -1);
                        penguinDirection = "back";
                        break;
                    case 's':
                        movePenguin(0,1);
                        penguinDirection = "front";
                        break;
                }
            }
        }
    }
}
