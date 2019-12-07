package com.shixinpeng;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

/**
 * @author shixinpeng
 * @description 简单模式地图绘制
 * @ClassName: MapGraphicDemo
 * @date 2019/11/25
 *
 */


public class Rectangle extends JComponent {

    public static void main(String[] args) {
        MyWindowListener windowListener = new MyWindowListener();
        Rectangle rectangle = new Rectangle();

        JFrame fr = new JFrame("Test…");

        fr.addWindowListener(windowListener);
        fr.getContentPane().add(rectangle,BorderLayout.CENTER);

        fr.pack();
        fr.setSize(400,400);
        fr.setLocation(400,400);
        fr.setVisible(true);


    }

    public Rectangle() {
        setDoubleBuffered(true);
        this.repaint();
    }

    @Override
    public void printComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Arc2D arc;

        float[] solid = {12.0f,0.0f};
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension size = getSize();

        g2.setColor(Color.white);

        g2.fill(new Rectangle2D.Double(0,0,size.width,size.height));

        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, solid, 0.0f);
        g2.setStroke(stroke);
        arc = new Arc2D.Double(20,40,100,150,0,360, Arc2D.OPEN);

        g2.setColor(Color.orange);
        g2.fill(arc);
        g2.setColor(Color.red);
        g2.draw(arc);

        arc = new Arc2D.Double(200,200,200,100,-30,-300,Arc2D.PIE);
        g2.setColor(Color.orange);g2.fill(arc);g2.setColor(Color.red);
        g2.draw(arc);
    }
}

class MyWindowListener extends WindowAdapter {


    @Override
    public void windowClosing(WindowEvent e) {

        System.exit(0);
    }
}
