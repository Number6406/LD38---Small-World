/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import Entities.Building;
import com.sun.javafx.geom.Vec2f;
import functions.NewBuilding;
import gui.Button;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.World;

/**
 *
 * @author Number6406
 */
public class Main extends BasicGame {
    
    public static final int tile_size = 10;
    
    private World world;
    private int timer;
    
    private Vec2f mouse_select;
    
    private List<Button> buttons;
    private int destruction;
    
    public Main(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
        buttons = new LinkedList<Button>();
        
        world = new World();
        
        mouse_select = new Vec2f();
        
        buttons.add(new Button(new Vec2f(520,100), 80, 30, Color.darkGray, Color.white, "Bonjour", new NewBuilding(mouse_select, world)));
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        timer += delta;
        if(timer > 200) {
            world.upWater(0.001);
            if((destruction = world.destroyBuildings()) > 0) {
                //System.out.println(destruction + " destroyed");
            }
            timer -= 200;
        }
        
        Input input = gc.getInput();
        if(input.isMousePressed(0)) {
            if(Mouse.getX() < 500) {
                mouse_select.x = (int) (Mouse.getX() / tile_size);
                mouse_select.y = (int) ((500-Mouse.getY()) / tile_size);
        
            }
            if(Mouse.getX()>= 500) {
                for (Button button : buttons) {
                    if(button.isHovering()) {
                        button.clicked();
                    }
                }
            }
        }
            
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        world.draw(grphcs);
        if(world.isAccessible(mouse_select)) {
            grphcs.setColor(Color.green);
        } else {
            grphcs.setColor(Color.red);
        }
        grphcs.setLineWidth(3);
        grphcs.drawString("Selection : " + mouse_select.x + ";" + mouse_select.y, 10, 30);
        grphcs.drawRect(mouse_select.x * tile_size, mouse_select.y * tile_size, tile_size, tile_size);
    
        for (Button button : buttons) {
            button.Draw(grphcs);
        }
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
