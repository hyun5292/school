import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Point 
{
    int x;
    int y;
    Point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}
class MouseThread extends Thread
{
    Point pp;
    JPanel panel;
    boolean mygame = false;
 
    MouseThread(Point pp,JPanel panel)
    {
        this.pp = pp;
        this.panel = panel;
  
        Thread th = new Thread(this);
        th.start();
    }
    synchronized void waitForRun()
    {
        if(!mygame)
        {
            try
            {
            	this.wait();
            }
           catch(InterruptedException e)
           {
        	   return;
           }
        }
    }
    synchronized void toRun()
    {
        mygame = true;
        this.notify();
    }
 
    public void run()
    {
        while(true)
        {
            waitForRun();
            pp.x = (int)(Math.random()*1000);
            pp.y = (int)(Math.random()*1000);
            panel.repaint();
            try
            {
            	Thread.sleep(300);
            }
            catch(InterruptedException e)
            {
            	return;
            }
        }
    }
}

public class num10 extends JFrame
{
    Container contentPane;
    public num10()
    {
        setTitle("Make Drawing to Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = getContentPane();
  
        MyPanel panel = new MyPanel();
        contentPane.add(panel,BorderLayout.CENTER);
  
        setSize(500,500);
        setVisible(true);
    }
    class MyPanel extends JPanel
    {
        Point pp = new Point(100,100);
  
        MyPanel()
        {
            MouseThread th = new MouseThread(pp,this);
            addMouseListener(new MyMouse(th));
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.fillOval(pp.x, pp.y, pp.x, pp.y);
        }
        class MyMouse extends MouseAdapter
        {
            MouseThread th;
   
            MyMouse(MouseThread th)
            {
                this.th = th;
            }
            public void mouseEntered(MouseEvent e)
            {
                th.toRun();
            }
            public void mouseExited(MouseEvent e)
            {
                th.mygame=false;
            }
        }
    }
    public static void main(String[] args)
    {
        new num10();
    }
}