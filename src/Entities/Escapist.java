/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Level;
import java.util.logging.Logger;
import ld38.Updater;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Number6406
 */
public class Escapist extends Building {
    
    private static SpriteSheet image;
    
    public Escapist(int x, int y) {
        super(x, y);
        try {
            image = new SpriteSheet("res/build_escapist.png", 10, 30);
        } catch (SlickException ex) {
            Logger.getLogger(WoodmanHut.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.color = Color.red;
        this.log_cost = 5000;
        this.rock_cost = 3000;
        this.population_capability = 0;
        this.requiered_workers = 50;
        this.food_production = 0;
    }
    
    @Override
    public void draw(Graphics g) {
        if(image != null) {
            g.drawImage(image.getSprite((int)((Updater.getInstance().getEscapistProgress()/100)*image.getHorizontalCount()), 0),
                    position.x*ld38.EscapistGame.tile_size, (position.y-2)*ld38.EscapistGame.tile_size
            );
        } else {
            super.draw(g);
        }
    }
    
}
