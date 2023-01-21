/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StarGen;
import View.CanvasAether;
import View.Window;
import java.awt.GridLayout;

/**
 *
 * @author Manu
 */
public class StarController {
    
    public StarController()
    {
        Window w = new Window();
        w.setTitle("Aether");
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        CanvasAether cv = new CanvasAether();
        
        StarGen sg = new StarGen();
        
        w.CanvasContainer.setLayout(new GridLayout());
        w.CanvasContainer.add(cv);
        
        cv.drawStars(sg.starGenerator(10000));
    }
}
