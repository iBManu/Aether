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
public class Sector {
    private ArrayList<Star> stars;
    private ArrayList<StarSystem> starSystems;
    private ArrayList<Constellation> constellations;

    public Sector(ArrayList<Star> stars, ArrayList<Constellation> constellations) {
        this.stars = stars;
        this.constellations = constellations;
        starSystems = new ArrayList<>();
    }
    
    public void addStarSystem(StarSystem starsystem)
    {
        starSystems.add(starsystem);
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public ArrayList<Constellation> getConstellations() {
        return constellations;
    }
}
