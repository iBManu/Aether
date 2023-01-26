/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Bodies.Galaxy;
import Model.ColorPalettes;
import Model.Variables;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Manu
 */
public class GalaxyCanvas extends Canvas{
    
    Random r;
    Image img;
    private Galaxy galaxy;
    ColorPalettes c;
    int selectedColumn = 5, selectedRow = 5;
    
    public GalaxyCanvas()
    {
        c = new ColorPalettes();
        r = new Random(Variables.SEED);
        
        galaxy = new Galaxy(Variables.GalaxyType.SPIRAL);
        
        this.setBackground(Color.black);
        this.setSize(Variables.CANVAS_WIDTH, Variables.CANVAS_HEIGHT);
        galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
    }
    
    public void update()
    {
        paint(this.getGraphics());
    }
    
    @Override
    public void paint(Graphics g)
    {
        img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
        Graphics2D g2d = (Graphics2D) og.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        float dash[] = {4.0f};
        BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        g2d.setStroke(dashed);
        
        g2d.setColor(Variables.CONCENTRIC_CIRCLES_COLOR);
        for(int i = 0; i < 5; i++)
        {
            g2d.drawLine((180 * i + 180), 0, (180 * i + 180), 899);
            g2d.drawLine(0, (180 * i + 180), 899, (180 * i + 180));
        }
        
        int count = 0;
        /*while(count < 300) //CENTER
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) < 150)
            {
                int size = r.nextInt(3) + 1;
                g2d.fillOval(x, y, size, size);
                count++;
            }
            
        }*/
        
        drawSpiral(g2d);
        //drawLenticular(g2d);
        

            g2d.setColor(new Color(134,255,100));
            g.fillRect(selectedColumn * 180, selectedRow * 180, 180, 180);
        
        g2d.dispose();
        g.drawImage(img, 0, 0, null);
    }
    
    void drawSpiral(Graphics2D g2d) {

        int n = r.nextInt(4);
        int xc = 450;
        int yc = 450;
        
        if(n == 0)
        {
            int branchsize = r.nextInt(40) + 40;
            int radius = 0;
            galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
            for (int i = 1; i <= 450; i++) {
                double angle = i * Math.PI / 200;
                int x1 = (int) (xc - radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                int y1 = (int) (yc + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                int size = r.nextInt(5) + 1;
                g2d.fillOval(x1, y1, size, size);
                radius++;
            }

            radius = 0;
            galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
            for (int i = 1; i <= 450; i++) {
                double angle = -i * Math.PI / 200;
                int x1 = (int) (xc + radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                int y1 = (int) (yc + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                int size = r.nextInt(5) + 1;
                g2d.fillOval(x1, y1, size, size);
                radius++;
            }
        }
        else if(n == 1)
        {
            int branchsize = r.nextInt(40) + 20;
            int radius = 0;
            for (int j = 0; j < 3; j++) {
                galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
                for (int i = 1; i <= 450; i++) {
                    double angle = (i * Math.PI / 200) + (j * 2 * Math.PI / 3);
                    int x1 = (int) (xc + radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                    int y1 = (int) (yc + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                    g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                    int size = r.nextInt(5) + 1;
                    g2d.fillOval(x1, y1, size, size);
                    radius++;
                }
                radius = 0;
            }

        }
        else if(n == 2)
        {
            int branchsize = r.nextInt(40) + 20;
            int radius = 0;
            for (int j = 0; j < 4; j++) {
                galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
                for (int i = 1; i <= 450; i++) {
                    double angle = (i * Math.PI / 200) + (j * 2 * Math.PI / 4);
                    int x1 = (int) (xc + radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                    int y1 = (int) (yc + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                    g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                    int size = r.nextInt(5) + 1;
                    g2d.fillOval(x1, y1, size, size);
                    radius++;
                }
                radius = 0;
            }

        }
        else if(n == 3)
        {
            int branchsize = r.nextInt(40) + 20;
            int radius = 0;
            for (int j = 0; j < 5; j++) {
                galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
                for (int i = 1; i <= 450; i++) {
                    double angle = (i * Math.PI / 200) + (j * 2 * Math.PI / 5);
                    int x1 = (int) (xc + radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                    int y1 = (int) (yc + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                    g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                    int size = r.nextInt(5) + 1;
                    g2d.fillOval(x1, y1, size, size);
                    radius++;
                }
                radius = 0;
            }

        }
        
        int count = 0;
        while(count < 500) //BORDER
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) < 300)
            {
                int size = r.nextInt(3) + 1;
                g2d.fillOval(x, y, size, size);
                count++;
            }
            
        }
        
        count = 0;
        while(count < 10000) //DUST
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            Color c = galaxy.getGalaxyColorPalette().get(r.nextInt(4));
            Color col = new Color(c.getRed(),c.getGreen(),c.getBlue(),50);
            g2d.setColor(col);
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) < 450)
            {
                g2d.fillOval(x, y, 2, 2);
                count++;
            }
            
        }
        
    }
    
    void drawLenticular(Graphics2D g2d)
    {
        int count = 0;
        while(count < 1000)
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) >= 375 && Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) <= 425)
            {
                g2d.fillOval(x, y, 2, 2);
                count++;
            }
            
        }
        
        if(r.nextInt() < 10)
        {
        galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
        count = 0;
        while(count < 2000)
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) <= 425)
            {
                g2d.fillOval(x, y, 2, 2);
                count++;
            }
            
        }
        }
        
        
        if(r.nextInt() < 20)
        {
            galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
            count = 0;
            while(count < 750)
            {
                int x = r.nextInt(900);
                int y = r.nextInt(900);
    
                
                g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) <= 200)
                {
                    g2d.fillOval(x, y, 2, 2);
                    count++;
                }

            }
        }
        
        
        galaxy.setGalaxyColorPalette(c.returnRandColorPalette());
        count = 0;
        while(count < 250)
        {
            int x = r.nextInt(900);
            int y = r.nextInt(900);
            
            g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
            if(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) <= 50)
            {
                g2d.fillOval(x, y, 2, 2);
                count++;
            }
            
        }
        
        int branchsize = r.nextInt(40) + 20;
            int radius = 0;
            for (int j = 0; j < 3; j++) {
                for (int i = 1; i <= 450; i++) {
                    double angle = (i * Math.PI / 200) + (j * 2 * Math.PI / 3);
                    int x1 = (int) (450 + radius * Math.cos(angle) + r.nextInt(branchsize) - 10);
                    int y1 = (int) (450 + radius * Math.sin(angle) + r.nextInt(branchsize) - 10);
                    g2d.setColor(galaxy.getGalaxyColorPalette().get(r.nextInt(4)));
                    int size = r.nextInt(5) + 1;
                    g2d.fillOval(x1, y1, size, size);
                    radius++;
                }
                radius = 0;
            }
    }
    
    public void selectGridPiece(int x, int y)
    {
        this.selectedColumn = x;
        this.selectedRow = y;
        
        update();
    }
    
}
