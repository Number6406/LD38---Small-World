/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Number6406
 */
public class Ressources {
    
    private static Ressources INSTANCE = new Ressources();
    
    private int population = 10;
    private int food = 200;
    private int log = 90;
    private int rock = 50;
    
    public Image icon_pop;
    public Image icon_workers;
    public Image icon_log;
    public Image icon_rock;
    public Image icon_food;
    
    private Ressources() {
        
    }
    
    public void init() throws SlickException {
        icon_pop = new Image("res/pop.png");
        icon_workers = new Image("res/workers.png");
        icon_log = new Image("res/log.png");
        icon_rock = new Image("res/rock.png");
        icon_food = new Image("res/food.png");
        population = 10;
        food = 200;
        log = 90;
        rock = 50;
    }
    
    public static Ressources getInstance() {
        return INSTANCE;
    }
    
    public int foodConsumeValue() {
        return population;
    }
    
    public void consumeFood() {
        food = Math.max(0, food-foodConsumeValue());
    }
    
    public void setPopulation(int population) {
        this.population = population;
    }

    public void updatePopulation(int population) {
        this.population += population;
    }

    public void updateFood(int food) {
        this.food += food;
    }

    public void updateLog(int log) {
        this.log += log;
    }

    public void updateRock(int rock) {
        this.rock += rock;
    }

    public int getPopulation() {
        return population;
    }

    public int getFood() {
        return food;
    }

    public int getLog() {
        return log;
    }

    public int getRock() {
        return rock;
    }
    
}
