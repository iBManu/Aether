/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Bodies.Planet;
import Model.Bodies.Star;
import Model.Bodies.StarSystem;
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
public class StarSystemCanvas extends Canvas{
    
    private StarSystem starsystem;
    private Random r;
    
    public StarSystemCanvas()
    {
        this.setSize(Variables.CANVAS_WIDTH, Variables.CANVAS_HEIGHT);
        r = new Random(Variables.SEED);
        setBackground(Color.black);
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

        g2d.setColor(Color.gray);
        for(int i = 0; i < 500; i++)
        {
            int size = r.nextInt(5) + 1;
            g2d.fillOval(r.nextInt(899), r.nextInt(899), size, size);
        }
            
            
        if(starsystem != null)
        {
            paintStar(g2d);
            paintPlanets(g2d, starsystem.getPlanets());
        }
         
        g2d.dispose();
        g.drawImage(img, 0, 0, null);
    }
    
    public void setSystem(StarSystem starsystem)
    {
        this.starsystem = starsystem;
        update();
    }
    
    public void paintStar(Graphics2D g2d)
    {   
        if(starsystem.getStar().getType() == Variables.StarType.SUBDWARF)
        {
            g2d.setColor(Color.orange);
            g2d.fillOval(-50, 400, 100, 100);
        }
        else if(starsystem.getStar().getType() == Variables.StarType.REDDWARF)
        {
            g2d.setColor(Color.red);
            g2d.fillOval(-65, 385, 130, 130);
        }
        else if(starsystem.getStar().getType() == Variables.StarType.WHITEDWARF)
        {
            g2d.setColor(Color.white);
            g2d.fillOval(-60, 390, 120, 120);
        }
        else if(starsystem.getStar().getType() == Variables.StarType.GIANT)
        {
            g2d.setColor(Color.yellow);
            g2d.fillOval(-100, 350, 200, 200);
        }
        else if(starsystem.getStar().getType() == Variables.StarType.SUPERGIANT)
        {
            g2d.setColor(Color.yellow);
            g2d.fillOval(-150, 300, 300, 300);
        }
        else if(starsystem.getStar().getType() == Variables.StarType.HYPERGIANT)
        {
            g2d.setColor(Color.orange);
            g2d.fillOval(-200, 250, 400, 400);
        }
    }
    
    public void paintPlanets(Graphics2D g2d, ArrayList<Planet> planets)
    {
        float dash[] = {4.0f};
        BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        g2d.setStroke(dashed);
        g2d.setColor(Variables.CONCENTRIC_CIRCLES_COLOR);
        
        int divisionsize = 650 / planets.size();
        
        for(int i = 0; i < planets.size(); i++)
        {
            g2d.setColor(Variables.CONCENTRIC_CIRCLES_COLOR);
            g2d.setStroke(dashed);
            g2d.drawOval(-1000 + i * divisionsize, -200, 1300, 1300);
            
            if(planets.get(i).getType() == Variables.PlanetType.ROCKY)
            {
                g2d.setColor(planets.get(i).getPlanetColor());
                g2d.fillOval(275 + divisionsize * i, 425, 50, 50);
            }
            else if(planets.get(i).getType() == Variables.PlanetType.GASGIANT)
            {
                g2d.setColor(planets.get(i).getPlanetColor());
                g2d.fillOval(250 + divisionsize * i, 400, 100, 100);
                if(planets.get(i).isHasrings())
                {
                    g2d.setStroke(new BasicStroke(10f));
                    g2d.drawOval(230 + divisionsize * i, 380, 140, 140);
                }
                    
            }
            else if(planets.get(i).getType() == Variables.PlanetType.DWARF)
            {
                g2d.setColor(planets.get(i).getPlanetColor());
                g2d.fillOval(290 + divisionsize * i, 440, 20, 20);
            }
        }
        
        
    }
}
