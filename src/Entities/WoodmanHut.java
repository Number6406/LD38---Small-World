/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Level;
import java.util.logging.Logger;
import ld38.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Number6406
 */
public class WoodmanHut extends Building {
    
    private static Image image;
    
    public WoodmanHut(int x, int y) {
        super(x, y);
        try {
            image = new Image("res/build_woodman.png");
        } catch (SlickException ex) {
            Logger.getLogger(WoodmanHut.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.color = Color.magenta;
        this.log_cost = 10;
        this.rock_cost = 0;
        this.population_capability = 0;
        this.requiered_workers = 6;
        this.food_production = 0;
        this.rock_production = 0;
        this.log_production = 30;
    }
    
    @Override
    public void draw(Graphics g) {
        if(image != null) {
            g.drawImage(image, position.x*Main.tile_size, position.y*Main.tile_size);
        } else {
            super.draw(g);
        }
    }
    
}
