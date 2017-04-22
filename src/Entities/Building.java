/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.sun.javafx.geom.Vec2f;
import ld38.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Number6406
 */
public class Building {
    
    private Vec2f position;
    private Color color;
    
    public Building(int x, int y) {
        position = new Vec2f(x,y);
        color = Color.blue;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x*Main.tile_size, position.y*Main.tile_size, Main.tile_size, Main.tile_size);
    }
    
}
