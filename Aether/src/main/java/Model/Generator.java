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
    private ArrayList<Path> Paths;
    
    public StarGen()
    {
        
    }
    
    public ArrayList<Star> starGenerator()
    {
        Random r = new Random(System.currentTimeMillis());
        Stars = new ArrayList<>();
        
        int x, y;
        
        for(int i = 0; i < Variables.STAR_NUM; i++)
        {
            x = r.nextInt(899);
            y = r.nextInt(899);
            while(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) > 450)
            {
                x = r.nextInt(899);
                y = r.nextInt(899);
            }
            Stars.add(new Star(r.nextInt(Variables.MAX_STAR_SIZE) + Variables.MIN_STAR_SIZE, x, y, Variables.DEFAULT_STAR_COLOR));
        }
        
        return Stars;
    }
    
    public ArrayList<Path> pathGenerator()
    {
        Random r = new Random(System.currentTimeMillis());
        Paths = new ArrayList<>();
        
        for(int i = 0; i < Variables.PATH_NUM; i++)
        {
            Paths.add(new Path(Stars.get(r.nextInt(Variables.STAR_NUM)),Stars.get(r.nextInt(Variables.STAR_NUM))));
        }
        
        return Paths;
    }
}
