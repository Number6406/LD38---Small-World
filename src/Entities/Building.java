/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import ld38.EscapistGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Number6406
 */
public abstract class Building {
    
    protected Vector2f position;
    protected Color color;
    
    protected int log_cost = 0;
    protected int rock_cost = 0;
    protected int requiered_workers = 0;
    
    protected int population_capability = 0;
    
    protected int food_production = 0;
    protected int rock_production = 0;
    protected int log_production = 0;
    
    public Building(int x, int y) {
        position = new Vector2f(x,y);
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
    
    public int getLog_production() {
        return log_production;
    }
    
    public int getRock_production() {
        return rock_production;
    }
    
    public int getRequiered_workers() {
        return requiered_workers;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x*EscapistGame.tile_size, position.y*EscapistGame.tile_size, EscapistGame.tile_size, EscapistGame.tile_size);
    }
    
}
