import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class num6 extends JFrame {
	public num6() {
		setTitle("버블 게임");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      BubblePanel p = new BubblePanel();
	      setContentPane(p);
	      
	      setSize(700,700);   
	      setVisible(true);
	}

	public static void main(String[] args) {
		new num6();
	}
}

class BubblePanel extends JPanel {
	   public BubblePanel() {
	      setLayout(null);
	      addMouseListener(new MouseAdapter() {
	         public void mousePressed(MouseEvent e) {
	            BubbleThread bubbleThread = new BubbleThread(e.getX(), e.getY());
	            bubbleThread.start();
	         }
	      });
	   }
	   
	   class BubbleThread extends Thread {
	      JLabel bubble;
	      public BubbleThread(int loc_x, int loc_y) {
	         ImageIcon img = new ImageIcon("images/bubble.jpg");
	         bubble = new JLabel(img);
	         bubble.setSize(100, 100);
	         bubble.setLocation(loc_x, loc_y);
	         add(bubble);
	         repaint();
	      }
	      
	      public void run() {
	         while(true) {
	            int x = bubble.getX() ;
	            int y = bubble.getY() - 5;
	            
	            if(y < 0) {
	               remove(bubble);
	               repaint();
	               return;
	            }
	            
	            bubble.setLocation(x, y);
	            repaint();
	            
	            try {
	               sleep(20);
	            }
	            catch(InterruptedException e) {
	            	return;
	            }
	         }
	      }
	   }
	}