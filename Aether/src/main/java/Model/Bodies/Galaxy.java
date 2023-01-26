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

    public Galaxy(GalaxyType type) {
        this.type = type;
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
}
