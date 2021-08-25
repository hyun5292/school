import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class num8 extends JFrame {
   public num8(){
      super("눈 내리는 마을");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setContentPane(new SnowPanel());
      setSize(500,500);
      setVisible(true);
   }

   static public void main(String[] args) {
      new num8();
   }
}
   
   class SnowPanel extends JPanel {
      ImageIcon icon = new ImageIcon("images/village.jpg");
      Image img = icon.getImage();
      final int SNOWS = 50;
      final int SNOW_SIZE = 20;
      Vector<Point> snowVector = new Vector<Point>();
      
      public SnowPanel() {
         this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
               addSnow();
               new SnowThread().start();
               SnowPanel.this.removeComponentListener(this);
            }            
         });
      }
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // 배경 이미지를 그린다.
         drawSnow(g);
      }
      
      void addSnow() {
         for(int i=0; i<SNOWS; i++) {
            Point p = new Point((int)(Math.random()*getWidth()), 
                  (int)(Math.random()*getHeight()));
            snowVector.add(p);
         }
      }
      void drawSnow(Graphics g) {
         g.setColor(Color.WHITE);         
         for(int i=0; i<snowVector.size(); i++) {
            Point p = snowVector.get(i);
            g.fillOval(p.x, p.y, SNOW_SIZE, SNOW_SIZE);         
         }
      }
      
      void changeSnowPosition() {
         for(int i=0; i<SNOWS; i++) {
            Point p = snowVector.get(i);
            int xDir = Math.random() > 0.5 ? 1 : -1;
            int offsetX = (int)(Math.random()*3)*xDir;
            int offsetY = (int)(Math.random()*7);
            p.x += offsetX;
            if(p.x < 0) p.x = 0;
            p.y += offsetY;
            if(p.y > getHeight()) {
               p.x = (int)(Math.random()*getWidth());
               p.y = 5;
            }
         }      
      }
      class SnowThread extends Thread {
         public void run() {
            while(true) {
               try {
                  sleep(100);
               }
               catch(InterruptedException e) 
               { 
            	   return; 
               }
               changeSnowPosition();
               repaint();
            }
         }
      }
   }