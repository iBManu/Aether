/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Bodies.Constellation;
import Model.Bodies.Planet;
import Model.Bodies.Star;
import Model.Bodies.Path;
import Model.Variables.StarType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Model.Bodies.StarSystem;

/**
 *
 * @author Manu
 */
public class Generator {
    
    private ArrayList<Star> Stars;
    private ArrayList<Path> Paths;
    private ArrayList<Planet> Planets;
    private ArrayList<Constellation> Constellations;
    private ArrayList<StarSystem> StarSystems;
    private Random r;
    
    public Generator()
    {
        r = new Random(Variables.SEED);
        StarSystems = new ArrayList<>();
    }
    
    public ArrayList<Star> starGenerator()
    {
        int sd = 0, rd = 0, wd = 0, g = 0, sg = 0, hg = 0;
        Stars = new ArrayList<>();
        
        int x, y;
        
        for(int i = 0; i < Variables.STAR_NUM; i++)
        {
            x = r.nextInt(899);
            y = r.nextInt(899);
            while(Math.sqrt((x - 450) * (x - 450) + (y - 450) * (y - 450)) > 450) //GENERATES IN THE CIRCLE
            {
                x = r.nextInt(899);
                y = r.nextInt(899);
            }
            //int size = r.nextInt(Variables.MAX_STAR_SIZE) + Variables.MIN_STAR_SIZE;
            
            int percent = r.nextInt(100);
            StarType type;
            
            System.out.println("porcentage: " + percent);
            
            if(percent < 15)
            {
                type = Variables.StarType.SUBDWARF;
                sd++;
            }
            else if(percent > 15 && percent < 45)
            {
                type = Variables.StarType.WHITEDWARF;
                wd++;
            }
            else if(percent > 45 && percent < 90)
            {
                type = Variables.StarType.REDDWARF;
                rd++;
            }
            else if(percent > 90 && percent < 95)
            {
                type = Variables.StarType.GIANT;
                g++;
            }
            else if(percent > 95 && percent < 98)
            {
                type = Variables.StarType.SUPERGIANT;
                sg++;
            }
            else
            {
                type = Variables.StarType.HYPERGIANT;
                hg++;
            }
            
            Stars.add(generateStarCharacteristics(type, x, y));
        }
        
        System.out.println(sd + "," + rd + "," + wd + "," + g + "," + sg + "," + hg);
        
        return Stars;
    }
    
    public ArrayList<Path> pathGenerator()
    {
        Paths = new ArrayList<>();
        
        int pathnum = r.nextInt(Variables.MAX_PATHS) + Variables.MIN_PATHS;
        
        Star actual = Stars.get(r.nextInt(Variables.STAR_NUM));
        
        for(int i = 0; i < pathnum; i++)
        {
            boolean find = false;
            int j = 0;
            while(!find || Stars.size() == j)
            {
                Star end = Stars.get(r.nextInt(Variables.STAR_NUM));
                double distance = Math.sqrt(Math.pow(end.getX() - actual.getX(), 2) + Math.pow(end.getY() - actual.getY(), 2));
                if(distance <= Variables.MAX_DISTANCE)
                {
                    find = true;
                    Paths.add(new Path(actual,end));
                    actual = end;
                }
                j++;
            }
        }
        
        return Paths;
    }
    
    public ArrayList<Constellation> constellationGenerator()
    {
        Constellations = new ArrayList<>();
        
        for(int i = 0; i < Variables.CONSTELLATIONS_NUM; i++)
        {
            Constellations.add(new Constellation(pathGenerator()));
        }
        
       return Constellations; 
    }
    
    public Star generateStarCharacteristics(StarType type, int x, int y)
    {
        if(type == Variables.StarType.SUBDWARF)
        {
            int size = 2;
            float mass = (float) (0.08 + (0.25 - 0.08) * r.nextFloat());
            float temp = (float) (2000 + (2800 - 2000)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        else if(type == Variables.StarType.REDDWARF)
        {
            int size = 4;
            float mass = (float) (0.08 + (0.5 - 0.08) * r.nextFloat());
            float temp = (float) (2500 + (4500 - 2500)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        else if(type == Variables.StarType.WHITEDWARF)
        {
            int size = 3;
            float mass = (float) (0.5 + (8 - 0.5) * r.nextFloat());
            float temp = (float) (4500 + (100000 - 4500)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        else if(type == Variables.StarType.GIANT)
        {
            int size = 5;
            float mass = (float) (1 + (20 - 1) * r.nextFloat());
            float temp = (float) (3000 + (60000 - 3000)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        else if(type == Variables.StarType.SUPERGIANT)
        {
            int size = 6;
            float mass = (float) (20 + (100 - 20) * r.nextFloat());
            float temp = (float) (3000 + (50000 - 3000)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        else if(type == Variables.StarType.HYPERGIANT)
        {
            int size = 7;
            float mass = (float) (100 + (150 - 100) * r.nextFloat());
            float temp = (float) (70000 + (100000 - 70000)) * r.nextFloat();
            
            return new Star(size, x, y ,Variables.DEFAULT_STAR_COLOR, type, mass, temp);
        }
        
        return null;
    }
    
    public ArrayList<Planet> generatePlanets(Star s)
    {
        //r.setSeed(Variables.SEED * s.getX() * s.getY());
        Planets = new ArrayList<>();
        
        int planetsnum = r.nextInt(Variables.MAX_PLANETS) + Variables.MIN_PLANETS;
        double EarthMass = 5.972E24;
        
        for(int i = 0; i < planetsnum; i++)
        {
            int percent = r.nextInt(100);
            
            if(percent < 33)
            {
                Color planetColor;
                if(percent < 25)
                    planetColor = new Color(248, 237, 227);
                else if(percent > 25 && percent < 50)
                    planetColor = new Color(133, 88, 111);
                else if(percent > 50 && percent < 75)
                    planetColor = new Color(173, 142, 112);
                else
                    planetColor = new Color(62, 42, 33);
                
                float density = 3f + (10f - 3f) * r.nextFloat();
                float mass = 0.05f + (15 - 0.05f) * r.nextFloat();
                float radius = (float) Math.cbrt((3 * mass) / (4 * Math.PI * density));
                Planets.add(new Planet(mass, radius,Variables.PlanetType.ROCKY, planetColor, (float) ((Math.pow(6.67 * 10, -11) * mass) / Math.pow(radius, 2)), density));
            }
            else if(percent > 33 && percent < 66)
            {
                Color planetColor;
                if(percent < 25)
                    planetColor = new Color(254, 222, 255);
                else if(percent > 25 && percent < 50)
                    planetColor = new Color(176, 226, 255);
                else if(percent > 50 && percent < 75)
                    planetColor = new Color(253, 138, 138);
                else
                    planetColor = new Color(241, 247, 181);
                
                float density = 0.1f + (0.68f - 0.1f) * r.nextFloat();
                float mass = 10 + (318 - 10) * r.nextFloat();
                float radius = (float) Math.cbrt((3 * mass) / (4 * Math.PI * density));
                Planets.add(new Planet(mass, radius,Variables.PlanetType.GASGIANT, planetColor, (float) ((Math.pow(6.67 * 10, -11) * mass) / Math.pow(radius, 2)), density));
            }
            else
            {
                Color planetColor;
                if(percent < 25)
                    planetColor = new Color(250, 248, 241);
                else if(percent > 25 && percent < 50)
                    planetColor = new Color(250, 234, 177);
                else if(percent > 50 && percent < 75)
                    planetColor = new Color(229, 186, 115);
                else
                    planetColor = new Color(197, 137, 64);
                
                float density = 1f + (2.1f - 1f) * r.nextFloat();
                float mass = 0.000001f + (0.002f - 0.000001f) * r.nextFloat();
                float radius = (float) Math.cbrt((3 * mass) / (4 * Math.PI * density));
                Planets.add(new Planet(mass, radius,Variables.PlanetType.DWARF, planetColor, (float) ((Math.pow(6.67 * 10, -11) * mass) / Math.pow(radius, 2)), density));
            }
        }
        
        return Planets;
    }
    
    public StarSystem generateStarSystem(Star s)
    {
        StarSystem sys = new StarSystem(s, generatePlanets(s));
        StarSystems.add(sys);
        return sys;    
    }

    public ArrayList<StarSystem> generateStarSystems(ArrayList<Star> stars)
    {
        return null;  
    }
    
    public StarSystem getSystem(Star st)
    {
        if(StarSystems.size() == 0)
            return null;
        
        for(StarSystem s : StarSystems)
        {
            if(s.getStar() == st)
                return s;
        }
        return null;
    }
    
    public ArrayList<Star> getStars() {
        return Stars;
    }

    public ArrayList<Path> getPaths() {
        return Paths;
    }

    public ArrayList<Planet> getPlanets() {
        return Planets;
    }

    public ArrayList<Constellation> getConstellations() {
        return Constellations;
    }
}
