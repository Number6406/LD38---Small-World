/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Level;
import java.util.logging.Logger;
import ld38.EscapistGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Number6406
 */
public class House extends Building {
    
    private static Image image;
    
    public House(int x, int y) {
        super(x, y);
        try {
            this.image = new Image("res/build_house.png");
        } catch (SlickException ex) {
            Logger.getLogger(House.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.color = Color.blue;
        this.log_cost = 10;
        this.rock_cost = 10;
        this.population_capability = 10;
        this.requiered_workers = 0;
        this.food_production = 0;
    }
    
    @Override
    public void draw(Graphics g) {
        if(image != null) {
            g.drawImage(image, position.x*EscapistGame.tile_size, position.y*EscapistGame.tile_size);
        } else {
            super.draw(g);
        }
    }
    
}
