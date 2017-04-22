/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

/**
 *
 * @author Number6406
 */
public class Ressources {
    
    private static Ressources INSTANCE = new Ressources();
    
    private int population = 10;
    private int food = 100;
    private int log = 75;
    private int rock = 50;
    
    private Ressources() {
        
    }
    
    public void init() {
        population = 10;
        food = 100;
        log = 50;
        rock = 50;
    }
    
    public static Ressources getInstance() {
        return INSTANCE;
    }
    
    public void consumeFood() {
        food = Math.max(0, food-population);
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
