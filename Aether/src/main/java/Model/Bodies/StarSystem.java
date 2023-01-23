/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import Model.Bodies.Planet;
import Model.Bodies.Star;
import java.util.ArrayList;

/**
 *
 * @author Manu
 */
public class StarSystem {
    private Star star;
    private ArrayList<Planet> planets;

    public StarSystem(Star star, ArrayList<Planet> planets) {
        this.star = star;
        this.planets = planets;
    }

    public Star getStar() {
        return star;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    @Override
    public String toString() {
        return "StarSystem{" + "star=" + star + ", planets=" + planets + '}';
    }
}
