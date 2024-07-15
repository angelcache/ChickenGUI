import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DragPanel extends JPanel {
    private ImageIcon image = new ImageIcon("wavingFriend.gif");
    final int WIDTH = image.getIconWidth(); // final because width will not change
    final int HEIGHT = image.getIconHeight();
    Point imageCorner;
    Point prevPoint;

    public DragPanel() {
        imageCorner = new Point(0, 0); // at the very top corner of the image
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
    }

    private class ClickListener extends MouseAdapter{
        // waits until we click the mouse
        public void mousePressed(MouseEvent e) {
            prevPoint = e.getPoint(); // it is updating the rpevious point to where we clicked
        }
        
    }

    private class DragListener extends MouseMotionAdapter {
        // going to move our image as we move mouse around
        public void mouseDragged(MouseEvent e) {
            Point currentPoint = e.getPoint();
            imageCorner.translate(
                                (int) (currentPoint.getX() - prevPoint.getX()), 
                                (int) (currentPoint.getY() - prevPoint.getY()) 
            );
            prevPoint = currentPoint;
            repaint();
        }
    }
}
