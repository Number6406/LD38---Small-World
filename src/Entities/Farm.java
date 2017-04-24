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
public class Farm extends Building {
    
    private static Image image;
    
    public Farm(int x, int y) {
        super(x, y);
        try {
            image = new Image("res/build_farm.png");
        } catch (SlickException ex) {
            Logger.getLogger(Farm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.color = Color.orange;
        this.log_cost = 12;
        this.rock_cost = 15;
        this.population_capability = 5;
        this.requiered_workers = 5;
        this.food_production = 50;
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
