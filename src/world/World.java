/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import Entities.Building;
import com.sun.javafx.geom.Vec2f;
import static java.lang.Math.sqrt;
import ld38.Main;
import ld38.Ressources;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Number6406
 */
public class World {
    
    private SimplexNoise simplexNoise=new SimplexNoise(1000,0.5,5000);
    
    private double xStart=0;
    private double XEnd=500;
    private double yStart=0;
    private double yEnd=500;

    private int world_diameter=50;
    private int island_radius = 20;
    
    private int tile_size = Main.tile_size;
    private double water_level = 0.4;

    private double[][] result = new double[world_diameter][world_diameter];
    private Rectangle[][] tiles = new Rectangle[world_diameter][world_diameter];
    
    private Building[][] buildings = new Building[world_diameter][world_diameter];
    
    int nb_farm, nb_mine, nb_woodmanhut;
    
    public World() {
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                int x=(int)(xStart+i*((XEnd-xStart)/world_diameter));
                int y=(int)(yStart+j*((yEnd-yStart)/world_diameter));
                double includingIsland = 0;
                if(distance(i - (world_diameter/2), j - (world_diameter / 2)) <= island_radius) {
                    includingIsland = 1;
                }
                result[i][j]= 0.5*(1+simplexNoise.getNoise(x,y)) * includingIsland;
                tiles[i][j] = new Rectangle(i*tile_size, j*tile_size, tile_size, tile_size);
            }
        }
            
    }
    
    public double distance(double x, double y) {
        
        return sqrt(x*x + y*y);
        
    }
    
    public void draw(Graphics g) {
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(water_level < result[i][j]) {
                    g.setColor(new Color((int)(result[i][j]*255), (int)(result[i][j]*255), (int)(result[i][j]*255), 100));
                } else {
                    g.setColor(Color.black);
                }
                g.fill(tiles[i][j]);
                
                if(buildings[i][j] != null) {
                    buildings[i][j].draw(g);
                }
            }
        }
    }
    
    public int destroyBuildings() {
        int destroy_counter = 0;
        
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null && water_level >= result[i][j]) {
                    buildings[i][j] = null;
                    destroy_counter++;
                }
            }
        }
        
        return destroy_counter;
    }
    
    public int countBuildings() {
        int count = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public boolean isAccessible(Vec2f pos) {
        return water_level < result[(int)pos.x][(int)pos.y];
    }
    
    public void upWater(double increment) {
        
        water_level += increment;
        
    }

    public void addBuilding(Building building, int x, int y) {
        buildings[x][y] = building;
    }
    
    public int getTotalCapability() {
        int space = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    space += buildings[i][j].getPopulation_capability();
                }
            }
        }
        return space;
    }
    
    public int getTotalWorkers() {
        int workers = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    workers += buildings[i][j].getRequiered_workers();
                }
            }
        }
        return Math.min(workers, Ressources.getInstance().getPopulation());
    }
    
    public int getTotalBuilding(Object o) {
        int building = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null && (buildings[i][j].getClass().equals(o.getClass()))) {
                    building++;
                }
            }
        }
        return building;
    }
    
    public int getTotalProdBuildings() {
        return nb_farm + nb_mine + nb_woodmanhut;
    }
    
    public int getTotalFoodProduction() {
        int prod = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    prod += buildings[i][j].getFood_production();
                }
            }
        }
        return prod;
    }
    
    public int getTotalLogProduction() {
        int prod = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    prod += buildings[i][j].getLog_production();
                }
            }
        }
        return prod;
    }
    
    public int getTotalRockProduction() {
        int prod = 0;
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(buildings[i][j] != null) {
                    prod += buildings[i][j].getRock_production();
                }
            }
        }
        
        return prod;
    }
    
    public boolean isSubmerged() {
        for(int i=0;i<world_diameter;i++){
            for(int j=0;j<world_diameter;j++){
                if(water_level < result[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
