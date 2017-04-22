/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

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

    private int xResolution=50;
    private int yResolution=50;
    
    private int tile_size = 10;

    private double[][] result=new double[xResolution][yResolution];
    private Rectangle[][] tiles = new Rectangle[xResolution][yResolution];
    
    
    public World() {
        for(int i=0;i<xResolution;i++){
            for(int j=0;j<yResolution;j++){
                int x=(int)(xStart+i*((XEnd-xStart)/xResolution));
                int y=(int)(yStart+j*((yEnd-yStart)/yResolution));
                result[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
                tiles[i][j] = new Rectangle(i*tile_size, j*tile_size, tile_size, tile_size);
            }
        }
            
    }
    
    public void draw(Graphics g) {
        for(int i=0;i<xResolution;i++){
            for(int j=0;j<yResolution;j++){
                if((int)(result[i][j]*255) < 150) {
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
    
}
