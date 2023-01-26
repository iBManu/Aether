/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Manu
 */
public class ColorPalettes {
    
    Random r;
    private ArrayList<ArrayList<Color>> ColorPalettes;
    private ArrayList<Color> PINKY;
    private ArrayList<Color> COLDBLUE;
    private ArrayList<Color> ERAGREEN;
    private ArrayList<Color> ANCIENT;
    private ArrayList<Color> ABYSS;
    private ArrayList<Color> MINT;
    private ArrayList<Color> LOVE;
    
    public ColorPalettes()
    {
        r = new Random(Variables.SEED);
        ColorPalettes = new ArrayList<>();
        
        PINKY = new ArrayList<>();
        PINKY.add(new Color(255,89,123));
        PINKY.add(new Color(255,142,158));
        PINKY.add(new Color(249,181,208));
        PINKY.add(new Color(238,238,238));
        ColorPalettes.add(PINKY);
        
        COLDBLUE = new ArrayList<>();
        COLDBLUE.add(new Color(207,245,231));
        COLDBLUE.add(new Color(160,228,203));
        COLDBLUE.add(new Color(89,193,189));
        COLDBLUE.add(new Color(13,76,146));
        ColorPalettes.add(COLDBLUE);
        
        ERAGREEN = new ArrayList<>();
        ERAGREEN.add(new Color(85,113,83));
        ERAGREEN.add(new Color(125,143,105));
        ERAGREEN.add(new Color(169,175,126));
        ERAGREEN.add(new Color(230,229,169));
        ColorPalettes.add(ERAGREEN);
        
        ANCIENT = new ArrayList<>();
        ANCIENT.add(new Color(167,93,93));
        ANCIENT.add(new Color(211,117,107));
        ANCIENT.add(new Color(240,153,125));
        ANCIENT.add(new Color(255,195,161));
        ColorPalettes.add(ANCIENT);
        
        ABYSS = new ArrayList<>();
        ABYSS.add(new Color(10,38,71));
        ABYSS.add(new Color(20,66,114));
        ABYSS.add(new Color(32,82,149));
        ABYSS.add(new Color(44,116,179));
        ColorPalettes.add(ABYSS);
        
        MINT = new ArrayList<>();
        MINT.add(new Color(67, 154, 151));
        MINT.add(new Color(98, 182, 183));
        MINT.add(new Color(151, 222, 206));
        MINT.add(new Color(203, 237, 213));
        ColorPalettes.add(MINT);
        
        LOVE = new ArrayList<>();
        LOVE.add(new Color(54, 22, 99));
        LOVE.add(new Color(224, 20, 76));
        LOVE.add(new Color(255, 88, 88));
        LOVE.add(new Color(255, 151, 193));
        ColorPalettes.add(LOVE);
    }
    
    public ArrayList<Color> returnRandColorPalette()
    {
        return ColorPalettes.get(r.nextInt(ColorPalettes.size()));
    }
}
