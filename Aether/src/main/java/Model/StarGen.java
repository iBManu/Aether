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
public class StarGen {
    
    private ArrayList<Star> Stars;
    
    public StarGen()
    {
        
    }
    
    public ArrayList<Star> starGenerator(int starNum)
    {
        Random r = new Random(System.currentTimeMillis());
        Stars = new ArrayList<>();
        
        for(int i = 0; i < starNum; i++)
        {
            Stars.add(new Star(r.nextInt(6) + 1, r.nextInt(899), r.nextInt(899), new Color(231, 246, 242)));
        }
        
        return Stars;
    }
}
