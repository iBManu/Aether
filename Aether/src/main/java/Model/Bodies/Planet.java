/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import Model.Variables.PlanetType;
import java.awt.Color;

/**
 *
 * @author Manu
 */
public class Planet {
    private float mass;
    private float radius;
    private PlanetType type;
    private Color planetColor;
    private float gravity;
    private float density;

    public Planet(float mass, float radius, PlanetType type, Color planetColor, float gravity, float density) {
        this.mass = mass;
        this.radius = radius;
        this.type = type;
        this.planetColor = planetColor;
        this.gravity = gravity;
        this.density = density;
    }

    public float getMass() {
        return mass;
    }

    public float getRadius() {
        return radius;
    }

    public PlanetType getType() {
        return type;
    }

    public Color getPlanetColor() {
        return planetColor;
    }

    public float getGravity() {
        return gravity;
    }

    public float getDensity() {
        return density;
    }
}
