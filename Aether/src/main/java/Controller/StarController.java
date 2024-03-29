/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bodies.Constellation;
import Model.Bodies.Galaxy;
import Model.Generator;
import Model.Bodies.Planet;
import Model.Bodies.Sector;
import Model.Bodies.Star;
import Model.Bodies.StarSystem;
import Model.Variables;
import View.CanvasAether;
import View.ConstantsDialog;
import View.GalaxyCanvas;
import View.StarSystemCanvas;
import View.Window;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Manu
 */
public class StarController {
    
    private Window w;
    private CanvasAether cv;
    private StarSystemCanvas scv;
    private GalaxyCanvas gcv;
    private Generator sg;
    private StarSystem currentStarSystem;
    private ConstantsDialog cdl;
    private boolean inserted = true;
    private Star lastStar;
    private Galaxy galaxy;
    private Sector sector;
    private int column;
    private int row;
    private ArrayList<Star> stars;
    private ArrayList<Constellation> constellations;
    
    public StarController(Window w, GalaxyCanvas gcv, Sector sector)
    {
        this.w = w;
        cv = new CanvasAether(w);
        scv = new StarSystemCanvas();
        cdl = new ConstantsDialog();
        this.gcv = gcv;
        this.sector = sector;
        
        sg = new Generator();
        
        w.CanvasContainer.setLayout(new GridLayout());
        w.CanvasContainer.add(cv);
        
        //gen();
        
        stars = sector.getStars();
        constellations = sector.getConstellations();
        
        cv.drawStars(stars);
        cv.drawConstellations(constellations);
        
        if(sector.getCenterBlackHole() != null)
        {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            cv.drawCenterBlackHole(sector.getCenterBlackHole());
        }
        else
            cv.undrawCenterBlackHole();
        
        System.out.println("SECTOR: " + sector);
        
        listener();
    }
    
    public void listener() {
        cv.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //boolean found = false;
                for (Star s : stars) {
                    if (s.getX() <= x && x <= s.getX() + s.getSize() && s.getY() <= y && y <= s.getY() + s.getSize()) {
                        //found = true;
                        if(lastStar != null && s != lastStar)
                        {
                            lastStar.setColor(Variables.DEFAULT_STAR_COLOR);
                            lastStar.setSelected(false);
                        }
                            
                            
                        if(s.isSelected() == true)
                        {
                            w.CanvasContainer.remove(cv);
                            w.CanvasContainer.add(scv);
                            scv.setSystem(currentStarSystem);
                            s.setSelected(false);
                            s.setColor(Variables.DEFAULT_STAR_COLOR);
                        }
                        else
                        {
                            StarSystem ss = sg.getSystem(s);

                            if(ss == null)
                                ss = sg.generateStarSystem(s);

                            currentStarSystem = ss;

                            s.setColor(Color.red);
                            System.out.println(s.toString());

                            setConsoleStarInfo(s);
                            cv.update();
                            s.setSelected(true);
                        }
                        
                        lastStar = s;
                        
                        break;
                    }        
                }
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    for(Star s : stars)
                        s.setColor(Variables.DEFAULT_STAR_COLOR);
                    
                    w.CanvasContainer.remove(cv);
                    w.CanvasContainer.add(gcv);
                }
                /*if(found == false)
                    {
                        lastStar.setSelected(false);
                        lastStar.setColor(Variables.DEFAULT_STAR_COLOR);
                        lastStar = null;
                        w.console.setText("");
                        cv.update();
                    }*/
            }
        });

        scv.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    w.CanvasContainer.remove(scv);
                    w.CanvasContainer.add(cv);
                }
            }
        });
        
        w.viewStarSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                w.CanvasContainer.remove(cv);
                w.CanvasContainer.add(scv);
                scv.setSystem(currentStarSystem);
            }
        });
        
        w.viewStarChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                w.CanvasContainer.remove(scv);
                w.CanvasContainer.add(cv);
            }
        });
        
        w.editConstantsButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cdl.setLocationRelativeTo(null);
                cdl.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                
                cdl.MIN_CONST_PATHS_TF.setText(Integer.toString(Variables.MIN_PATHS));
                cdl.MAX_CONST_PATHS_TF.setText(Integer.toString(Variables.MAX_PATHS));
                cdl.MAX_DIST_PATHS_TF.setText(Integer.toString(Variables.MAX_DISTANCE));
                cdl.CONST_NUM_TF.setText(Integer.toString(Variables.CONSTELLATIONS_NUM));
                cdl.STAR_NUM_TF.setText(Integer.toString(Variables.STAR_NUM));
                cdl.MIN_PLANETS_TF.setText(Integer.toString(Variables.MIN_PLANETS));
                cdl.MAX_PLANETS_TF.setText(Integer.toString(Variables.MAX_PLANETS));
                cdl.SEED_TF.setText(Integer.toString(Variables.SEED));
                
                cdl.setVisible(true);
            }
        });
        
        cdl.setConstantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Variables.MIN_PATHS != Integer.valueOf(cdl.MIN_CONST_PATHS_TF.getText()) || Variables.MAX_PATHS != Integer.valueOf(cdl.MAX_CONST_PATHS_TF.getText()) || Variables.MAX_DISTANCE != Integer.valueOf(cdl.MAX_DIST_PATHS_TF.getText()) || Variables.CONSTELLATIONS_NUM != Integer.valueOf(cdl.CONST_NUM_TF.getText()) || Variables.STAR_NUM != Integer.valueOf(cdl.STAR_NUM_TF.getText()) || Variables.MIN_PLANETS != Integer.valueOf(cdl.MIN_PLANETS_TF.getText()) || Variables.MAX_PLANETS != Integer.valueOf(cdl.MAX_PLANETS_TF.getText()) || Variables.SEED != Integer.valueOf(cdl.SEED_TF.getText()))
                {
                    Variables.setMIN_PATHS(Integer.valueOf(cdl.MIN_CONST_PATHS_TF.getText()));
                    Variables.setMAX_PATHS(Integer.valueOf(cdl.MAX_CONST_PATHS_TF.getText()));
                    Variables.setMAX_DISTANCE(Integer.valueOf(cdl.MAX_DIST_PATHS_TF.getText()));
                    Variables.setCONSTELLATIONS_NUM(Integer.valueOf(cdl.CONST_NUM_TF.getText()));
                    Variables.setSTAR_NUM(Integer.valueOf(cdl.STAR_NUM_TF.getText()));
                    Variables.setMIN_PLANETS(Integer.valueOf(cdl.MIN_PLANETS_TF.getText()));
                    Variables.setMAX_PLANETS(Integer.valueOf(cdl.MAX_PLANETS_TF.getText()));
                    Variables.setSEED(Integer.valueOf(cdl.SEED_TF.getText()));
                    
                    inserted = true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: can't enter same constants as actuals", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        
        w.exportImgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().endsWith(".png")) {
                        selectedFile = new File(selectedFile.getAbsolutePath() + ".png");
                    }
                    try {
                        ImageIO.write((RenderedImage) cv.getImg(), "png", selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        w.goBackToGalaxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                w.CanvasContainer.remove(cv);
                w.CanvasContainer.remove(scv);
                w.CanvasContainer.add(gcv);
            }
        });
        
        /*Variables.STAR_NUM = w.starCountSlider.getValue();
        w.starCountSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                Variables.STAR_NUM = w.starCountSlider.getValue();   
                cv.drawStars(sg.starGenerator());
            }
});*/
        
    }
    
    public void setConsoleStarInfo(Star st)
    {
        w.console.setText("");
        w.console.append("STAR " + st.getStarName());
        w.console.append("\nStar type: " + st.getType());
        w.console.append("\nMass: " + st.getMass() + " solar masses");
        w.console.append("\nCoordinates: (" + st.getX() + "," + st.getY() + ")");
        w.console.append("\nSurface temperature: " + st.getTemp() + " K");
        
        w.console.append("\n\nPlanets of this star");
        
        StarSystem ss = sg.getSystem(st);
        
        for(Planet pl : ss.getPlanets())
        {
            w.console.append("\n\n Type: " + pl.getType());
            w.console.append("\n Mass: " + pl.getMass() + " Earth masses");
            w.console.append("\n Radius: " + pl.getRadius() + " Earth radius");
            w.console.append("\n Density: " + pl.getDensity() + " kg/m³");
            w.console.append("\n Gravity: " + pl.getGravity() + " Earth gravities");
            System.out.println(pl.toString());
        }
    }
    
    public void gen()
    {
        //if(inserted == true)
        //{
            //System.out.println("row: " + row + " , column: " + column);
            ArrayList<Star> stars = sector.getStars();
            ArrayList<Constellation> constellations = sector.getConstellations();
            //sector = new Sector(stars,constellations);
            //cv.drawStars(sg.starGenerator());
            cv.drawConstellations(sg.constellationGenerator());

            BigInteger prob = new BigInteger(String.valueOf((long)(Math.pow(Variables.CANVAS_HEIGHT * Variables.CANVAS_WIDTH, Variables.STAR_NUM) * Math.pow(6, Variables.STAR_NUM))));

            System.out.println("La probabilidad de que este cielo exista es de 1 entre " + prob + " otros cielos");
            inserted = false;
        //}
        //else
        //{
        //    JOptionPane.showMessageDialog(null, "Error: can't regenerate with same constants as actual scenario, please consider changing costants", "Error", JOptionPane.ERROR_MESSAGE);
        //}
        
    }

    public void setSector(Sector sector) {
        this.sector = sector;
        
        w.CanvasContainer.add(cv);
        lastStar = null;
        
        this.stars = sector.getStars();
        this.constellations = sector.getConstellations();
        
        cv.drawStars(sector.getStars());
        cv.drawConstellations(sector.getConstellations());
        cv.drawCenterBlackHole(sector.getCenterBlackHole());
    }
}
