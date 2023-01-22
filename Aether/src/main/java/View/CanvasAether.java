/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Path;
import Model.Star;
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
    private ArrayList<Path> Paths;
    
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
        
        if(Paths != null)
        {
            og.setColor(Variables.DEFAULT_STAR_COLOR);
            for(int i = 0; i < Paths.size(); i++)
            {
                og.drawLine(Paths.get(i).getOrigin().getX(), Paths.get(i).getOrigin().getY(), Paths.get(i).getEnd().getX(), Paths.get(i).getEnd().getY());
                System.out.println("xd?");
            }
        }
        
        g2d.dispose();
        g.drawImage(img, 0, 0, null);
        
        try {
            ImageIO.write((RenderedImage) img, "png", new File("Foto.png"));
        } catch (IOException e) {
            e.printStackTrace();
}

    }

    public void drawStars(ArrayList<Star> Stars)
    {
        this.Stars = Stars;
        update();
    }
    
    public void drawPaths(ArrayList<Path> Paths)
    {
        this.Paths = Paths;
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
