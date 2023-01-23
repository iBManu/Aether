/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import java.util.ArrayList;

/**
 *
 * @author Manu
 */
public class Constellation {
    private ArrayList<Star> Stars;
    private ArrayList<Path> Paths;
    private String name;

    public Constellation(ArrayList<Path> Paths) {
        this.Paths = Paths;
    }

    public ArrayList<Star> getStars() {
        return Stars;
    }

    public ArrayList<Path> getPaths() {
        return Paths;
    }

    public void setName(String name) {
        this.name = name;
    }
}
