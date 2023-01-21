/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Star;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Manu
 */
public class CanvasAether extends Canvas{
    
    private ArrayList<Star> Stars;
    
    public CanvasAether()
    {
        this.setBackground(Color.black);
        this.setSize(900, 900);
        
        listener();
    }
    
    public void update()
    {
        paint(this.getGraphics());
    }
    
    @Override
    public void paint(Graphics g)
    {
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
        Graphics2D g2d = (Graphics2D) og.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color(123, 143, 161));
        
        g2d.drawOval(0, 0, 900, 900);
        
        if(Stars != null)
        {
            g2d.setColor(new Color(231, 246, 242));
            for(int i = 0; i < Stars.size(); i++)
            {
                g2d.setColor(Stars.get(i).getColor());
                g2d.fillOval(Stars.get(i).getX(), Stars.get(i).getY(), Stars.get(i).getSize(), Stars.get(i).getSize());
            }
        }
        
        g2d.dispose();
        g.drawImage(img, 0, 0, null);
    }

    public void drawStars(ArrayList<Star> Stars)
    {
        this.Stars = Stars;
        
        update();
    }
    
    public void listener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for (Star s : Stars) {
                    if (s.getX() <= x && x <= s.getX() + s.getSize() && s.getY() <= y && y <= s.getY() + s.getSize()) {
                        s.setColor(Color.red);
                        repaint();
                        break;
                    }
                }
            }
        });

    }
}
