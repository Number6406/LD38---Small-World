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
public class Mine extends Building {
    
    public Mine(int x, int y) {
        super(x, y);
        this.color = Color.pink;
        this.log_cost = 25;
        this.rock_cost = 5;
        this.population_capability = 0;
        this.food_production = 0;
        this.rock_production = 20;
        this.log_production = 0;
    }
    
    
}
