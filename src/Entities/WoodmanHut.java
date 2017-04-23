/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.newdawn.slick.Color;

/**
 *
 * @author Number6406
 */
public class WoodmanHut extends Building {
    
    public WoodmanHut(int x, int y) {
        super(x, y);
        this.color = Color.magenta;
        this.log_cost = 10;
        this.rock_cost = 0;
        this.population_capability = 0;
        this.requiered_workers = 6;
        this.food_production = 0;
        this.rock_production = 0;
        this.log_production = 30;
    }
    
    
}
