/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import Model.Variables.GalaxyType;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Manu
 */
public class Galaxy {
    private Sector galaxyGrid[][] = new Sector [8][8];
    private GalaxyType type;
    private ArrayList<Color> galaxyColorPalette;
    private int branches;
    private int branchsize;
    private ArrayList<gstar> gstars;
    private BlackHole centerBlackHole;
    
    public static class gstar
    {
        int x;
        int y;
        Color c;
        int size;

        public gstar(int x, int y, Color c, int size) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.size = size;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getC() {
            return c;
        }

        public int getSize() {
            return size;
        }
 
    }

    public Galaxy(GalaxyType type, ArrayList<Color> galaxyColorPalette, int branches, int branchsize) {
        this.type = type;
        this.galaxyColorPalette = galaxyColorPalette;
        this.branches = branches; 
        this.branchsize = branchsize;
        gstars = new ArrayList<>();
    }
    
    public void setGalaxyGrid(int i, int j, Sector sector)
    {
        this.galaxyGrid[i][j] = sector;
    }
    
    public Sector getGalaxySector(int x, int y)
    {
        return galaxyGrid[x][y];
    }

    public void setGalaxyColorPalette(ArrayList<Color> galaxyColorPalette) {
        this.galaxyColorPalette = galaxyColorPalette;
    }

    public ArrayList<Color> getGalaxyColorPalette() {
        return galaxyColorPalette;
    }

    public Sector[][] getGalaxyGrid() {
        return galaxyGrid;
    }

    public GalaxyType getType() {
        return type;
    }

    public int getBranches() {
        return branches;
    }

    public int getBranchsize() {
        return branchsize;
    }
    
    public void addgstar(gstar g)
    {
        gstars.add(g);
    }
    
    public ArrayList<gstar> getgstars()
    {
        return gstars;
    }

    public BlackHole getCenterBlackHole() {
        return centerBlackHole;
    }

    public void setCenterBlackHole(BlackHole centerBlackHole) {
        this.centerBlackHole = centerBlackHole;
    }
}
