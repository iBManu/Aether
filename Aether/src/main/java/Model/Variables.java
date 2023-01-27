/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Manu
 */
public class Variables {
    public static Random r = new Random(System.currentTimeMillis());
    public static int MAX_STAR_SIZE = 6;
    public static int MIN_STAR_SIZE = 2;
    public static int MAX_PATHS = 6;
    public static int MIN_PATHS = 3;
    public static int MAX_DISTANCE = 100;
    public static int MAX_PLANETS = 6;
    public static int MIN_PLANETS = 1;
    public static int STAR_NUM = 500;
    public static int CONSTELLATIONS_NUM = 3;
    public static int CANVAS_HEIGHT = 900;
    public static int CANVAS_WIDTH = 900;
    public static Color DEFAULT_STAR_COLOR = Color.gray; //new Color(231, 246, 242);
    public static Color CONCENTRIC_CIRCLES_COLOR = new Color(44, 51, 51);
    public static int SEED = 115710;//(int) System.currentTimeMillis();//115710//-301158681;
    public static int GID = r.nextInt(115710) + 1;
    public static enum StarType {
        HYPERGIANT, 
        SUPERGIANT, 
        GIANT,
        //SUBGIANT,
        SUBDWARF,
        REDDWARF,
        WHITEDWARF
    }
    public static enum PlanetType {
        ROCKY, 
        GASGIANT, 
        DWARF  
    }
    
    public static enum GalaxyType {
        ELLIPTICAL, 
        SPIRAL, 
        IRREGULAR,
        LENTICULAR
    }

    public static void setMAX_PATHS(int MAX_PATHS) {
        Variables.MAX_PATHS = MAX_PATHS;
    }

    public static void setMIN_PATHS(int MIN_PATHS) {
        Variables.MIN_PATHS = MIN_PATHS;
    }

    public static void setMAX_DISTANCE(int MAX_DISTANCE) {
        Variables.MAX_DISTANCE = MAX_DISTANCE;
    }

    public static void setMAX_PLANETS(int MAX_PLANETS) {
        Variables.MAX_PLANETS = MAX_PLANETS;
    }

    public static void setMIN_PLANETS(int MIN_PLANETS) {
        Variables.MIN_PLANETS = MIN_PLANETS;
    }

    public static void setSTAR_NUM(int STAR_NUM) {
        Variables.STAR_NUM = STAR_NUM;
    }

    public static void setCONSTELLATIONS_NUM(int CONSTELLATIONS_NUM) {
        Variables.CONSTELLATIONS_NUM = CONSTELLATIONS_NUM;
    }

    public static void setSEED(int SEED) {
        Variables.SEED = SEED;
    }

    public static void setGID(int GID) {
        Variables.GID = GID;
    }
}
