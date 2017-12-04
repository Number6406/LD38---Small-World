/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Number6406
 */
public class Tiles {
    
    public static Image tile_water;
    public static Image tile_sand;
    public static Image tile_grass;
    public static Image tile_mountain;
    public static Image tile_snow;
    
    public Tiles() {
        try {
            tile_water = new Image("res/tile_water.png");
            tile_sand = new Image("res/tile_sand.png");
            tile_grass = new Image("res/tile_grass.png");
            tile_mountain = new Image("res/tile_mountain.png");
            tile_snow = new Image("res/tile_snow.png");
        } catch (SlickException ex) {
            Logger.getLogger(Tiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
