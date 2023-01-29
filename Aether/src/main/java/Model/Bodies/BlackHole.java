/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

import java.math.BigDecimal;

/**
 *
 * @author Manu
 */
public class BlackHole {
    private BigDecimal mass;
    int x;
    int y;

    public BlackHole(BigDecimal mass, int x, int y) {
        this.mass = mass;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
