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
public abstract class Building {
    
    protected Vec2f position;
    protected Color color;
    
    protected int log_cost = 0;
    protected int rock_cost = 0;
    protected int population_capability = 0;
    
    protected int food_production = 0;
    protected int rock_production = 0;
    protected int log_production = 0;
    
    public Building(int x, int y) {
        position = new Vec2f(x,y);
    }
    
    public int getPopulation_capability() {
        return population_capability;
    }
    
    public int getLog_cost() {
        return log_cost;
    }

    public int getRock_cost() {
        return rock_cost;
    }
    
    public int getFood_production() {
        return food_production;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x*Main.tile_size, position.y*Main.tile_size, Main.tile_size, Main.tile_size);
    }
    
}
