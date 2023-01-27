/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Bodies.Galaxy;
import Model.Bodies.Galaxy.gstar;
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
    
    public GalaxyCanvas(Galaxy galaxy)
    {
        c = new ColorPalettes();
        r = new Random(Variables.SEED);
        
        this.galaxy = galaxy;
        
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
        
        /*if(galaxy.getType() == Variables.GalaxyType.SPIRAL)
            drawSpiral(g2d, galaxy.getBranches());
        else if(galaxy.getType() == Variables.GalaxyType.LENTICULAR)
            drawLenticular(g2d);*/
        
        ArrayList<gstar> gstars = galaxy.getgstars();
        
        for(int i = 0; i < gstars.size(); i++)
        {
            g2d.setColor(gstars.get(i).getC());
            g2d.fillOval(gstars.get(i).getX(), gstars.get(i).getY(), gstars.get(i).getSize(), gstars.get(i).getSize());
        }

        g2d.setColor(new Color(134,255,100, 50));
        g2d.fillRect(selectedColumn * 180, selectedRow * 180, 180, 180);
        
        g2d.dispose();
        g.drawImage(img, 0, 0, null);
    }
    
    void drawSpiral(Graphics2D g2d, int branches) {

        
        
    }
    
    void drawLenticular(Graphics2D g2d)
    {
        
    }
    
    public void selectGridPiece(int x, int y)
    {
        this.selectedColumn = x;
        this.selectedRow = y;
    }

    public Image getImg() {
        return img;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public int getSelectedRow() {
        return selectedRow;
    }
}
