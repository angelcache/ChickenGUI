import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.Border;

/*-----------------------------------------------------------------------------------------------------------------
 * Purpose: Penguin goes to the library to learn more about how to compliment chicken. This codes the library
 *          game where penguin must collect 3 books that will help him learn how to be a cheer up a friend 
 *          who is not in the best mood.
 * Images: Penguin walking, book gifs, and bad ending image were made by me in Piskel and in Clip Studio Paint
 /*-----------------------------------------------------------------------------------------------------------------*/

class ComplimentLibraryGame extends JFrame {
    private ChickenGui mainFrame;
    private ComplimentLibraryGame thisFrame;
    private final int SCREEN_WIDTH = 616;
    private final int SCREEN_HEIGHT = 640;

    private boolean running = false;
    private boolean success = false;

    public ComplimentLibraryGame(ChickenGui frame) {
        /**
        * Library Game constructor generates the frame and customizes it
        */
        this.setTitle("Learn about complimenting");
        this.add(new LibraryDialogue());
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/chickicon.png");
        this.setIconImage(icon.getImage());

        mainFrame = frame;
        thisFrame = this;
    }

    
    public void gameOver() {
        /**
         * ends the game and starts the complimenting window
         */
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
            mainFrame.setGameFinished();
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

    public class LibraryDialogue extends JPanel implements ActionListener{
        private final String[] dialogue = {"I'm really bad at cheering people up T-T.", 
                                        "I should stop by at the library first to run errands.",
                                        "And maybe read a book or two about complimenting!",
                                        "I'll have to be careful though, those librarians aren't so fond of me..."};
        private JButton dialogueButton;
        private ImageIcon libraryImage = new ImageIcon("img/library.png");
        private ImageIcon penguingImage = new ImageIcon("img/penguinFrontWalking.gif");
        private JLabel libraryLabel;
        private int dialogueClicked = -1;

        public LibraryDialogue() {
            /**
            * Library Dialogue constructor generates the dialogue label and customizes it
            */
            this.setBackground(new Color(0xEEE7D0));
            this.setFocusable(true);
            this.setLayout(null);
            libraryLabel = new JLabel();
            libraryLabel.setBounds(65, 0, 450, 424); 
            
            // setting up the dialogue
            dialogueButton = new JButton(dialogue[0]);
            dialogueButton.setForeground(Color.BLACK);
            dialogueButton.setBackground(new Color(0xCCE8B8));
            dialogueButton.setFocusable(false);
            dialogueButton.addActionListener(this); 
            dialogueButton.setVisible(true);
            dialogueButton.setBounds(20, 392, 560, 190); 
            dialogueButton.setIcon(penguingImage);
            dialogueButton.addActionListener(this);
            // setting up a border
            Border border = BorderFactory.createLineBorder(new Color(0xD3C163), 20);
            this.setBorder(border);
            this.add(dialogueButton);
            this.add(libraryLabel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /**
            * changes the dialogue and dialogue image when the button is clicked
            */
            if (e.getSource() == dialogueButton) { // check if there is still dialogue
                dialogueClicked++;
                if (dialogueClicked < dialogue.length && dialogueClicked == 1) {
                    libraryLabel.setIcon(libraryImage);
                    dialogueButton.setText(dialogue[dialogueClicked]);
                } else if (dialogueClicked < dialogue.length) {
                    dialogueButton.setText(dialogue[dialogueClicked]);
                } else {
                    LibraryPanel libraryPanel = new LibraryPanel();

                    thisFrame.getContentPane().removeAll();  // Remove all existing components
                    thisFrame.getContentPane().add(libraryPanel); 
                    libraryPanel.setFocusable(true);
                    libraryPanel.requestFocusInWindow(); // ensures game panel receives input properly
                    thisFrame.revalidate();
                    thisFrame.repaint();
                }
            } 
        }
        
    }

/*-----------------------------------------------------------------------------------------------------------------*/

    public class LibraryPanel extends JPanel implements ActionListener {
        private final int MAZE_UNITS = 40;
        private final int PANEL_MAZE = 15;

        private int penguinX = 1;
        private int penguinY = 1;

        // penguin
        private String penguinDirection = "front";
        private Image penguinFront = new ImageIcon("img/penguinFrontWalking.gif").getImage();
        private Image penguinBack = new ImageIcon("img/penguinBackWalking.gif").getImage();
        private Image penguinLeft = new ImageIcon("img/penguinLeftWalking.gif").getImage();
        private Image penguinRight = new ImageIcon("img/penguinRightWalking.gif").getImage();

        // 3 librarian npc's / enemies

        private int[] librarian1XY = {3, 5};
        private int[] librarian2XY = {11, 3};
        private int[] librarian3XY = {7, 11};

        private Image librarian1Left = new ImageIcon("img/librarianLeftWalking.gif").getImage();
        private Image librarian1Right = new ImageIcon("img/librarianRightWalking.gif").getImage();
        private String librarian1Position = "right";
        private int librarianXVelocity1 = 1;

        private Image librarian2Left = new ImageIcon("img/librarianLeftWalking.gif").getImage();
        private Image librarian2Right = new ImageIcon("img/librarianRightWalking.gif").getImage();
        private String librarian2Position = "right";
        private int librarianXVelocity2 = 1;

        private Image librarian3Left = new ImageIcon("img/librarianLeftWalking.gif").getImage();
        private Image librarian3Right = new ImageIcon("img/librarianRightWalking.gif").getImage();
        private String librarian3Position = "left";
        private int librarianXVelocity3 = 1;

        // Penguins space (bound box)
        Rectangle penguinBounds = new Rectangle(penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);
        
        // Librarians space (bound box)
        Rectangle librarian1Bounds = new Rectangle(librarian1XY[0] * MAZE_UNITS, librarian1XY[1] * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);
        Rectangle librarian2Bounds = new Rectangle(librarian2XY[0] * MAZE_UNITS, librarian2XY[1] * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);
        Rectangle librarian3Bounds = new Rectangle(librarian3XY[0] * MAZE_UNITS, librarian3XY[1] * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);

        // kicked out of library image
        private Image badEnding = new ImageIcon("img/badEnding.png").getImage();

        int turn1 = 0; // even is right, odd is left
        int turn2 = 0;
        int turn3 = 0;

        private int[] book1XY = {0, 0};
        private int[] book2XY = {0, 0};
        private int[] book3XY = {0, 0};
        private Image book = new ImageIcon("img/book.gif").getImage();
        private Image book2 = new ImageIcon("img/book.gif").getImage();
        private Image book3 = new ImageIcon("img/book.gif").getImage();
        private Random random;

        private final int[][] maze = new int[MAZE_UNITS][MAZE_UNITS];

        private static final int DELAY = 160;
        private Timer timer;
        private int booksCollected = 0;
        private JButton successButton;
        private JButton retryButton;

        /**
         * Library Panel constructor creates Panel, customizes it + starts the game by initializing the maze
         */
        public LibraryPanel() {
            this.setBackground(new Color(0xD3C163));
            this.setPreferredSize(new Dimension(PANEL_MAZE * MAZE_UNITS, PANEL_MAZE * MAZE_UNITS));
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

            retryButton = new JButton();
            retryButton.setBounds(150, 350, 100, 50);
            retryButton.setText("Retry");
            retryButton.setFont(new Font("Mali", Font.BOLD, 12));
            retryButton.setFocusable(false);
            retryButton.setForeground(Color.white);
            retryButton.setBackground(new Color(0xD3C163));
            retryButton.setBorder(BorderFactory.createEtchedBorder());
            
            this.add(retryButton);
            retryButton.setVisible(false);
            retryButton.addActionListener(this);
            
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
            // First book placed in the middle
            book1XY[0] += PANEL_MAZE - 8;
            book1XY[1] += PANEL_MAZE - 6;

            // Second book is place in bottom left, ensures its placed on path (0)
            book2XY[0] += 1;
            book2XY[1] = PANEL_MAZE - 10;
  
            // 3rd book top right: ensures its placed on path (0)
            book3XY[0] = PANEL_MAZE - 2;
            book3XY[1] += 1;
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

                /*  shuffle directions to ensure randomness of path - ensures DFS explores creates diff pathways each restart
                for (int i = 1; i < directions.length; i++) {
                    int[] temp = directions[i];
                    int randIndex = random.nextInt(directions.length);
                    directions[i] = directions[randIndex];
                    directions[randIndex] = temp;
                }
                */

                // shuffles a directions once to create a more dynamic looking maze
                for (int i = 1; i < directions.length; i++) {
                    int[] temp = directions[i];
                    directions[i] = directions[i - 1];
                    directions[i - 1] = temp;
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

        /**
         * Checks to see if penguin has collided with a book or a librarian
         */
        public void checkCollisions() {
            
            // check if penguin has collided with a book
            checkBookCollision(penguinBounds, book1XY);
            checkBookCollision(penguinBounds, book2XY);
            checkBookCollision(penguinBounds, book3XY);

            if(booksCollected >= 3) {
                running = false;
                success = true;
                System.out.println("Success!");
            }

            // check if penguin has collided with a librarian
            checkLibrarianCollision(penguinBounds, librarian1Bounds);
            checkLibrarianCollision(penguinBounds, librarian2Bounds);
            checkLibrarianCollision(penguinBounds, librarian3Bounds);
        }

        /**
         * Checks if penguin collided with a book
         */
        private void checkBookCollision(Rectangle penguinBounds, int[] books) {
            if (penguinX == book1XY[0] && penguinY == book1XY[1]) {
                booksCollected += 1;
                book1XY[0]= -1;
            }
            if (penguinX == book2XY[0] && penguinY == book2XY[1]) {
                booksCollected += 1;
                book2XY[0] = -1;
            }
            if (penguinX == book3XY[0] && penguinY == book3XY[1]) {
                booksCollected += 1;
                book3XY[0] = -1;
            }

        }

        /**
         * Checks if penguin collided with a librarian
         */
        private void checkLibrarianCollision(Rectangle penguinBounds, Rectangle librarianBounds) {
            if (penguinBounds.intersects(librarianBounds)) {
                    running = false;
                    success = false;
            }
        }

    
        /**
         * Creates the drawing component and calls all the other drawing methods 
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!running && !success) {
                g.drawImage(badEnding, 100,160, this);
                g.setColor(Color.black);
                g.setFont(new Font("Calibri", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(g.getFont()); 
                g.drawString("Librarian kicked you out \n for running!", ((SCREEN_WIDTH) - metrics.stringWidth("Librarian kicked you out \n for running!")) / 2, 120);
                retryButton.setVisible(true);

            } else if (success) {
                g.setColor(new Color(0xCCE8B8));
                g.setFont(new Font("Calibri", Font.BOLD, 34));
                FontMetrics metrics = getFontMetrics(g.getFont()); 
                g.drawString("Success! Go compliment chicken :)", ((SCREEN_WIDTH) - metrics.stringWidth("Success! Go compliment chicken :)")) / 2, 240);
                successButton.setVisible(true);

            } else {
                retryButton.setVisible(false); 
                drawMaze(g);
                drawPenguin(g);
                drawLibrarians(g);
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
         * Drawing Librarians, placing them in the correct coordinates
         * @param g
         */
        private void drawLibrarians(Graphics g) {
            switch (librarian1Position) {
                case "right":
                    g.drawImage(librarian1Right, librarian1XY[0] * MAZE_UNITS, librarian1XY[1] * MAZE_UNITS, this);
                    break;
                case "left":
                    g.drawImage(librarian1Left, librarian1XY[0] * MAZE_UNITS, librarian1XY[1] * MAZE_UNITS, this);
                    break;
            }

            switch (librarian2Position) {
                case "right":
                    g.drawImage(librarian2Right, librarian2XY[0] * MAZE_UNITS, librarian2XY[1] * MAZE_UNITS, this);
                    break;
                case "left":
                    g.drawImage(librarian2Left, librarian2XY[0] * MAZE_UNITS, librarian2XY[1] * MAZE_UNITS, this);
                    break;
            }

            switch (librarian3Position) {
                case "right":
                    g.drawImage(librarian3Right, librarian3XY[0] * MAZE_UNITS, librarian3XY[1] * MAZE_UNITS, this);
                    break;
                case "left":
                    g.drawImage(librarian3Left, librarian3XY[0] * MAZE_UNITS, librarian3XY[1] * MAZE_UNITS, this);
                    break;
            }
        }

        /**
         * Draws the 3 Books and places in the maze
         */
        private void drawBooks(Graphics g) {
            g.drawImage(book, book1XY[0] * MAZE_UNITS, book1XY[1] * MAZE_UNITS, this);
            g.drawImage(book2, book2XY[0] * MAZE_UNITS, book2XY[1] * MAZE_UNITS, this);
            g.drawImage(book3, book3XY[0] * MAZE_UNITS, book3XY[1] * MAZE_UNITS, this);
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
                mainFrame.setGameFinished();
                gameOver();
            }

            if (e.getSource() == retryButton) {
                penguinX = 1; penguinY = 1;
                running = true;
            }

            // Penguin Movement UPdate
            penguinBounds.setBounds(penguinX * MAZE_UNITS, penguinY * MAZE_UNITS, MAZE_UNITS, MAZE_UNITS);


            // Librarian movements

            // Librarian 1
            int newX = librarian1XY[0] + librarianXVelocity1;

            if (newX >= 0 && librarian1XY[1] >= 0 && librarian1XY[1] < PANEL_MAZE && librarian1XY[1] < PANEL_MAZE && maze[librarian1XY[1]][newX] == 0) {
                librarian1XY[0] = newX;
            } else {
                turn1++;
                librarianXVelocity1 = librarianXVelocity1 * -1;
                librarian1XY[0] += librarianXVelocity1;
            }
            librarian1Bounds.setLocation(librarian1XY[0] * MAZE_UNITS, librarian1XY[1] * MAZE_UNITS);

            if (turn1 % 2 == 1) {
                librarian1Position = "left";
            } else {
                librarian1Position = "right";
            }

            // Librarian 2
            newX = librarian2XY[0] + librarianXVelocity2;

            if (newX >= 0 && librarian2XY[1] >= 0 && librarian2XY[1] < PANEL_MAZE && librarian2XY[1] < PANEL_MAZE && maze[librarian2XY[1]][newX] == 0) {
                librarian2XY[0] = newX;
            } else {
                turn2++;
                librarianXVelocity2 = librarianXVelocity2 * -1;
                librarian2XY[0] += librarianXVelocity2;
            }
            librarian2Bounds.setLocation(librarian2XY[0] * MAZE_UNITS, librarian2XY[1] * MAZE_UNITS);
            
            if (turn2 % 2 == 1) {
                librarian2Position = "left";
            } else {
                librarian2Position = "right";
            }

            // Librarian 3
            newX = librarian3XY[0] + librarianXVelocity3;
 
            if (newX >= 0 && librarian3XY[1] >= 0 && librarian3XY[1] < PANEL_MAZE && librarian3XY[1] < PANEL_MAZE && maze[librarian3XY[1]][newX] == 0) {
                librarian3XY[0] = newX;
            } else {
                turn3++;
                librarianXVelocity3 = librarianXVelocity3 * -1;
                librarian3XY[0] += librarianXVelocity3;
            }
            librarian3Bounds.setLocation(librarian3XY[0] * MAZE_UNITS, librarian3XY[1] * MAZE_UNITS);
            
            if (turn3 % 2 == 1) {
                librarian3Position = "left";
            } else {
                librarian3Position = "right";
            }
            
            repaint();
        }

        /*-----------------------------------------------------------------------------------------------------------------*/
        
        /**
         * When keys are pressed, the penguin will move accordingly
        */
        private class TAdapter extends KeyAdapter {

            @Override
            public void keyReleased(KeyEvent e) {
                // Movement input will work for arrow pad
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        movePenguin(-1, 0);
                        penguinDirection = "left";
                        break;
                    case KeyEvent.VK_RIGHT:
                        movePenguin(1, 0);
                        penguinDirection = "right";
                        break;
                    case KeyEvent.VK_UP:
                        movePenguin(0, -1);
                        penguinDirection = "back";
                        break;
                    case KeyEvent.VK_DOWN:
                        movePenguin(0,1);
                        penguinDirection = "front";
                        break;
                }

                // Movement input will work for a, w, s, d
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
