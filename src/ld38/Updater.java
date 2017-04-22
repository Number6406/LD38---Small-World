/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import world.World;

/**
 *
 * @author Number6406
 */
public class Updater {
    
    private int total_timer = 0;
    
    private int water_updater = 200;
    private int water_timer = 0;
    
    private int population_feeding_updater = 2000;
    private int population_feeding_timer = 0;
    
    private int population_growing_updater = 5000;
    private int population_growing_timer = 0;
    
    private int production_updater = 3000;
    private int production_timer = 0;
    
    private static Updater INSTANCE = new Updater();

    private Updater() {

    }

    public static Updater getInstance() {
        return INSTANCE;
    }
    
    public void update(World world, int delta) {
        
        total_timer+=delta;
        population_feeding_timer+=delta;
        population_growing_timer+=delta;
        
        if(updateAtomicWater(delta)) {
            world.upWater(0.001);
            world.destroyBuildings();
        }
        
        if(updateAtomicFood(delta)) {
            Ressources.getInstance().consumeFood();
        }
        
        if(updateAtomicPop(delta)) {
            if(world.getTotalCapability() > Ressources.getInstance().getPopulation()) {
                if(Ressources.getInstance().getFood() > 0) {
                    Ressources.getInstance().updatePopulation(1);
                } else {
                    Ressources.getInstance().updatePopulation(-1);
                }
            } if (world.getTotalCapability() < Ressources.getInstance().getPopulation()) {
                Ressources.getInstance().updatePopulation(-1);
            }
        }
        
        if(updateAtomicProd(delta)) {
            Ressources.getInstance().updateFood(world.getTotalFoodProduction());
            Ressources.getInstance().updateLog(world.getTotalLogProduction());
            Ressources.getInstance().updateRock(world.getTotalRockProduction());
        }
        
    }
    
    protected boolean updateAtomicWater(int delta) {
        water_timer += delta;
        if(water_timer > water_updater) {    
            water_timer -= water_updater;
            return true;
        }
        return false;
    }
    
    protected boolean updateAtomicFood(int delta) {
        population_feeding_timer += delta;
        if(population_feeding_timer > population_feeding_updater) {    
            population_feeding_timer -= population_feeding_updater;
            return true;
        }
        return false;
    }
    
    protected boolean updateAtomicPop(int delta) {
        population_growing_timer += delta;
        if(population_growing_timer > population_growing_updater) {
            population_growing_timer -= population_growing_updater;
            return true;
        }
        return false;
    }
    
    protected boolean updateAtomicProd(int delta) {
        production_timer += delta;
        if(production_timer > production_updater) {    
            production_timer -= production_updater;
            return true;
        }
        return false;
    }
    
}
