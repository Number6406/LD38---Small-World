/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import world.World;

/**
 *
 * @author Number6406
 */
public class Main extends BasicGame {
    
    private World world;
    private int timer;
    
    public Main(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        world = new World();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        timer += delta;
        if(timer > 2000) {
            world.upWater(0.05);
            timer -= 2000;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        world.draw(grphcs);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Small World"));
            
            appgc.setDisplayMode(700, 500, false);
            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
