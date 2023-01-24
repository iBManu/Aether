/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import Model.Variables.StarType;
import java.awt.Color;

/**
 *
 * @author Manu
 */
public class Star {
    private int size;
    private int x;
    private int y;
    private Color color;
    private StarType type;
    private float mass;
    private float temp;
    private float metallicity;
    private float magneticfield;
    private float radius;
    private float lightColor;
    private float brightness;
    private String starName;
    private boolean selected;

    public Star(int size, int x, int y, Color color, StarType type, float mass, float temp, String starName) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
        this.mass = mass;
        this.temp = temp;
        this.starName = starName;
        selected = false;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }  

    public StarType getType() {
        return type;
    }

    public float getMass() {
        return mass;
    }

    public float getTemp() {
        return temp;
    }

    public String getStarName() {
        return starName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Star{" + "size=" + size + ", x=" + x + ", y=" + y + ", color=" + color + ", type=" + type + ", mass=" + mass + ", temp=" + temp + '}';
    }
}
