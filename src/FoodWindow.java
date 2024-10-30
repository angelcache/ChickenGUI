
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FoodWindow extends JFrame implements ActionListener{
    @SuppressWarnings("rawtypes")
    ChickenGui mainFrame;
    JComboBox foodOptions;
    JButton foodButton;
    JLabel objective;
    int sucessful = 0;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FoodWindow (ChickenGui frame) {
        mainFrame = frame;
        String[] pantry = {"egg", "squash", "banana", "mango", "seeds"};
        foodOptions = new JComboBox(pantry);
        foodOptions.addActionListener(this);
        foodOptions.setBounds(140, 70, 77, 20);
        foodOptions.setForeground(Color.BLACK);
        foodOptions.setBackground(Color.WHITE);

        objective = new JLabel("Chicken wants something yellow, curvy, and sweet !!");
        objective.setBounds(54, 0, 7777, 100);
        objective.setFont(new Font("Calibri", Font.BOLD, 12));
        objective.setForeground(new Color(0x9E6B1D));
        
        foodButton = new JButton("Give");
        foodButton.setForeground(Color.white);
        foodButton.setBackground(new Color(0xD3C163));
        foodButton.setBounds(134,110, 90, 20);
        this.add(foodButton);
        foodButton.addActionListener(this);

        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("The Kitchen");
        this.setSize(400, 200);
        this.setIconImage(new ImageIcon("chickicon.png").getImage());
        this.setResizable(false);

        ImageIcon image = new ImageIcon("chickicon.png");
        this.setIconImage(image.getImage()); // change icon of frame
        this.getContentPane().setBackground(new Color(0xCCE8B8));
        
        this.add(foodOptions);
        this.add(objective);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == foodButton) {
                if (foodOptions.getSelectedItem().equals("banana")) {
                    this.setVisible(false);
                    FoodCatchGame foodGame = new FoodCatchGame(mainFrame);
                    foodGame.setVisible(true);
                } else if (foodOptions.getSelectedItem().equals("mango")) {
                    objective.setText("Tha- that is yellow, curvy, and sweet but not it. Try again.");
                } else if(foodOptions.getSelectedItem().equals("egg")) {
                    objective.setText("Did you seriously try to feed a chicken an egg?! :(");
                } else {
                    objective.setText("That isn't what chicken likes. Hint: its a fruit. Try again.");
                }
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
