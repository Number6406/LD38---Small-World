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
public class Farm extends Building {
    
    public Farm(int x, int y) {
        super(x, y);
        this.color = Color.orange;
        this.log_cost = 12;
        this.rock_cost = 15;
        this.population_capability = 5;
        this.food_production = 50;
    }
    
    
}
