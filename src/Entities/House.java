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
public class House extends Building {
    
    public House(int x, int y) {
        super(x, y);
        this.color = Color.blue;
        this.log_cost = 10;
        this.rock_cost = 10;
        this.population_capability = 10;
        this.food_production = 0;
    }    
    
}
