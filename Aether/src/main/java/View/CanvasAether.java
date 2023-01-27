/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Bodies.Constellation;
import Model.Bodies.Path;
import Model.Bodies.Star;
import Model.Variables;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Manu
 */
public class CanvasAether extends Canvas{
    
    private ArrayList<Star> Stars;
    private ArrayList<Constellation> Constellations;
    private Window w;
    Image img;
    
    public CanvasAether(Window w)
    {
        this.setBackground(Color.black);
        this.setSize(900, 900);
        this.w = w;
    }
    
    public void update()
    {
        paint(this.getGraphics());
    }
    
    @Override
    public void paint(Graphics g)
    {
        img = createImage(this.getWidth(), this.getHeight());
        System.out.println("w: " + this.getWidth() + " , h: " + this.getHeight());
        Graphics og = img.getGraphics();
        Graphics2D g2d = (Graphics2D) og.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color(123, 143, 161));
        
        g2d.drawOval(0, 0, 900, 900);
        
        float dash[] = {4.0f};
        BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        g2d.setStroke(dashed);

        g2d.setColor(Variables.CONCENTRIC_CIRCLES_COLOR);
        
        // Dibuja el primer círculo concentrico
        g2d.drawOval(125, 125, 650, 650);

        // Dibuja el segundo círculo concentrico
        g2d.drawOval(250, 250, 400, 400);

        // Dibuja el tercer círculo concentrico
        g2d.drawOval(375, 375, 150, 150);

        g2d.drawLine(0, 450, 899, 450);
        g2d.drawLine(450, 0, 450, 899);
        //g2d.drawRect(125, 125, 775, 775);
        g2d.drawLine(135, 135, 765, 765);
        g2d.drawLine(135, 765, 765, 135);
        
        if(Stars != null)
        {
            for(int i = 0; i < Stars.size(); i++)
            {
                g2d.setColor(Stars.get(i).getColor());
                g2d.fillOval(Stars.get(i).getX(), Stars.get(i).getY(), Stars.get(i).getSize(), Stars.get(i).getSize());
            }
        }
        
        if(Constellations != null)
        {
            g2d.setStroke(new BasicStroke());
            g2d.setColor(Variables.DEFAULT_STAR_COLOR);
            for(int i = 0; i < Constellations.size(); i++)
            {
                for(int j = 0; j < Constellations.get(i).getPaths().size(); j++)
                {
                    g2d.drawLine(Constellations.get(i).getPaths().get(j).getOrigin().getX(), Constellations.get(i).getPaths().get(j).getOrigin().getY(), Constellations.get(i).getPaths().get(j).getEnd().getX(), Constellations.get(i).getPaths().get(j).getEnd().getY());
                }
                
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
    
    public void drawConstellations(ArrayList<Constellation> Constellations)
    {
        this.Constellations = Constellations;
        update();
    }
    
    /*public Star findStar(ArrayList<Star> stars, int x, int y) {
        for(int i = 0; i < Stars.size(); i++)
        {
            if(Stars.get(i).getX() <= x && Stars.get(i).getY() <= y)
                return Stars.get(i);
        }
        return null;
    }*/

    public Image getImg() {
        return img;
    }
}
