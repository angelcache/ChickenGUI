import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HugWindow extends JFrame implements MouseListener, ActionListener {
    ChickenGui mainFrame;
    JLabel label;
    JButton endingButton;
    ImageIcon friendVisits;
    ImageIcon wavingChicken;
    ImageIcon happyEnding;
    
    public HugWindow (ChickenGui frame) {
        mainFrame = frame;

        // customizing the hug window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 500);
        this.setIconImage(new ImageIcon("chickicon.png").getImage());
        this.setLayout(null);

        
        // instantiating the image icons
        friendVisits = new ImageIcon("wavingfriend.gif");
        wavingChicken = new ImageIcon("wavingChicken.gif");
        happyEnding = new ImageIcon("friend.gif");

        
        // customizing label that contains the image icon
        label = new JLabel("      Hold and Release to Hug!");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP); 
        label.setBounds(7, 0, 333, 440);
        label.setIcon(friendVisits);
        label.addMouseListener(this);
        
        // making button to go back to the main frame
        endingButton = new JButton("Success! Click me :)");
        endingButton.setFont(new Font("Mali", Font.BOLD, 12));
        endingButton.setFocusable(false);
        endingButton.setForeground(new Color(0x9E6B1D));
        endingButton.setBackground(new Color(0xCCE8B8));
        endingButton.setBorder(BorderFactory.createEtchedBorder());
        endingButton.setBounds(110, 350, 180, 50);
        endingButton.setVisible(false);

        endingButton.addActionListener(this);

        this.add(label);
        this.add(endingButton);
        this.getContentPane().setBackground(new Color(0xEEE7D0));
        this.setLocationRelativeTo(null); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setIcon(wavingChicken);
        label.setText("");
        label.setBounds(27, 0, 366, 440);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setIcon(happyEnding);
        label.setText("");
        label.setBounds(20, 0, 366, 440);
        endingButton.setVisible(true);
        label.removeMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == endingButton) {
            this.setVisible(false);
                    mainFrame.setVisible(true);
        }
    }

}
