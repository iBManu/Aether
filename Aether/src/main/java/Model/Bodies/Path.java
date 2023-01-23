/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bodies;

/**
 *
 * @author Manu
 */
public class Path {
    private Star origin;
    private Star end;

    public Path(Star origin, Star end) {
        this.origin = origin;
        this.end = end;
    }

    public Star getOrigin() {
        return origin;
    }

    public Star getEnd() {
        return end;
    } 
}
