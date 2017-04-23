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
public class Escapist extends Building {
    
    public Escapist(int x, int y) {
        super(x, y);
        this.color = Color.red;
        this.log_cost = 5000;
        this.rock_cost = 3000;
        this.population_capability = 200;
        this.requiered_workers = 50;
        this.food_production = 0;
    }
    
}
