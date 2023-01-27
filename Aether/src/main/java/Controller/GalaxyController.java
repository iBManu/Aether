/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bodies.Galaxy;
import Model.Bodies.Sector;
import Model.Bodies.Star;
import Model.Bodies.StarSystem;
import Model.Generator;
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
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Manu
 */
public class GalaxyController {
    private Window w;
    private CanvasAether cv;
    private StarSystemCanvas scv;
    private GalaxyCanvas gcv;
    private Generator sg;
    private ConstantsDialog cdl;
    private Random r;
    private Galaxy galaxy;
    private StarController s;
    
    public GalaxyController()
    {
        w = new Window();
        w.setTitle("Aether");
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        
        r = new Random(Variables.SEED);
        
        sg = new Generator();
        galaxy = sg.generateGalaxy();
        
        cv = new CanvasAether(w);
        gcv = new GalaxyCanvas(sg.generateGalaxy());
        cdl = new ConstantsDialog();
        
        w.CanvasContainer.setLayout(new GridLayout());
        w.CanvasContainer.add(gcv);
        
        setConsole();
        
        gen();
        
        listener();
    }
    
    public void listener() {
        
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
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: can't enter same constants as actuals", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        
        w.regenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Variables.setSEED((int) System.currentTimeMillis());
               galaxy = sg.generateGalaxy();
               gcv.setGalaxy(galaxy);
               gcv.update();
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
                        ImageIO.write((RenderedImage) gcv.getImg(), "png", selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    
        w.goToGalaxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                int GID = Integer.valueOf(w.moveToTextField.getText());
                if(GID < 1)
                    JOptionPane.showMessageDialog(null, "Error: there are no negative galaxies, please enter a valid galaxy id", "Error", JOptionPane.ERROR_MESSAGE);
                else if(GID > 115710)
                    JOptionPane.showMessageDialog(null, "Error: there are only 115710 galaxies, please enter a valid galaxy id", "Error", JOptionPane.ERROR_MESSAGE);
                else
                {
                    Variables.setGID(GID);
                    galaxy = sg.generateGalaxy();
                    gcv.setGalaxy(galaxy);
                    gcv.update();
                }
            }
        });
        
        gcv.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int column = x / 180;
                int row = y / 180;
                
                setConsole();
                
                if(column == gcv.getSelectedColumn() && row == gcv.getSelectedRow())
                {
                    gcv.selectGridPiece(5, 5); //CLEARS
                    w.CanvasContainer.remove(gcv);
                    if(s == null)
                    {
                        s = new StarController(w, gcv,galaxy.getGalaxySector(row, column));
                    }
                        
                    else
                        s.setSector(galaxy.getGalaxySector(row, column));
                }
                else
                {
                    gcv.selectGridPiece(column, row);
                    gcv.update();
                }
                
                
            }
});
}

    private void gen() {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                galaxy.setGalaxyGrid(i, j, sg.generateSector());
            }
        }
        System.out.println("e iguá?: " + (galaxy.getGalaxySector(0, 0) == galaxy.getGalaxySector(0, 1)));
    }
    
    private void setConsole()
    {
        w.console.setText("");
        w.console.append("GALAXY TYPE: " + galaxy.getType());
        w.console.append("\nGALAXY ID: " + Variables.GID);
    }
}
