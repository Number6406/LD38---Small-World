/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import static java.lang.Math.sqrt;
import ld38.Main;
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
    
    private int tile_size = Main.tile_size;
    
    private int island_radius = 20;
    
    private double water_level = 0.1;

    private double[][] result = new double[world_diameter][world_diameter];
    private Rectangle[][] tiles = new Rectangle[world_diameter][world_diameter];
    
    
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
                    g.setColor(new Color((int)(result[i][j]*255), (int)(result[i][j]*255), (int)(result[i][j]*255)));
                } else {
                    g.setColor(Color.black);
                }
                g.fill(tiles[i][j]);
                g.setColor(Color.white);
                //g.drawString(""+(result[i][j])+"", i*tile_size, j*tile_size);
            }
        }
    }
    
    public void upWater(double increment) {
        
        water_level += increment;
        
    }
    
}
