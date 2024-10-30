import javax.swing.*;

public class HugClothesCutscene extends JFrame{
    public HugClothesGame gameFrame;

    public void HugClothesCutscene(HugClothesGame frame) {
        gameFrame = frame;

        // customizing the hug frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 500);
        this.setIconImage(new ImageIcon("chickicon.png").getImage());
        this.setLayout(null);

    }
}
