/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import Entities.Farm;
import Entities.House;
import Entities.Mine;
import Entities.WoodmanHut;
import com.sun.javafx.geom.Vec2f;
import functions.NewHouse;
import functions.NewFarm;
import functions.NewMine;
import functions.NewWoodmanHut;
import gui.Button;
import gui.ButtonAddBuilding;
import gui.Notifier;
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
    
    private boolean game_running = true;
    
    private World world;
    
    private Vec2f mouse_select;
    
    private List<Button> buttons;
    
    public static House model_house;
    public static Farm model_farm;
    public static Mine model_mine;
    public static WoodmanHut model_woodmanhut;
    
    private int score_final = 0;
    
    private Notifier notifier;
    
    public Main(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
        buttons = new LinkedList<Button>();
        notifier = new Notifier();
        
        world = new World();
        Ressources.getInstance().init();
        Updater.getInstance().init();
        
        mouse_select = new Vec2f(25,25);
        
        model_house = new House(50, 0);
        model_farm = new Farm(50, 5);
        model_mine = new Mine(50, 10);
        model_woodmanhut = new WoodmanHut(50, 15);
        
        buttons.add(new ButtonAddBuilding(new Vec2f(500,0), 200, 50, Color.darkGray, Color.lightGray, Color.white, "Build House",
                new NewHouse(mouse_select, world), model_house.getLog_cost(), model_house.getRock_cost()));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,50), 200, 50, Color.darkGray, Color.lightGray, Color.white, "Build Farm",
                new NewFarm(mouse_select, world), model_farm.getLog_cost(), model_farm.getRock_cost()));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,100), 200, 50, Color.darkGray, Color.lightGray, Color.white, "Build Mine",
                new NewMine(mouse_select, world), model_mine.getLog_cost(),model_mine.getRock_cost()));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,150), 200, 50, Color.darkGray, Color.lightGray, Color.white, "Build Woodman Hut",
                new NewWoodmanHut(mouse_select, world), model_woodmanhut.getLog_cost(), model_woodmanhut.getRock_cost()));
        
        game_running = true;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
            
        if(input.isKeyPressed(Input.KEY_R)) {
            init(gc);
        }
        
        if(Ressources.getInstance().getPopulation() <= 0 || world.isSubmerged()) {
            score_final = Updater.getInstance().getTimerScore();
            game_running = false;
        }
        
        if(game_running) {
            Updater.getInstance().update(world, delta);
            
            if(input.isMousePressed(0)) {
                if(Mouse.getX() < 500) {
                    mouse_select.x = (int) (Mouse.getX() / tile_size);
                    mouse_select.y = (int) ((500-Mouse.getY()) / tile_size);
                }
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
        if(game_running) {
            world.draw(grphcs);
            if(world.isAccessible(mouse_select)) {
                grphcs.setColor(Color.green);
            } else {
                grphcs.setColor(Color.red);
            }
            grphcs.setLineWidth(3);
            //grphcs.drawString("Selection : " + mouse_select.x + ";" + mouse_select.y, 10, 30);
            grphcs.drawRect(mouse_select.x * tile_size, mouse_select.y * tile_size, tile_size, tile_size);

            for (Button button : buttons) {
                button.isHovering();
                button.draw(grphcs);
            }
            
            model_house.draw(grphcs);
            model_farm.draw(grphcs);
            model_mine.draw(grphcs);
            model_woodmanhut.draw(grphcs);

            grphcs.setColor(Color.white);
            grphcs.drawString("Pop : " + Ressources.getInstance().getPopulation() + "/" + world.getTotalCapability(), 10, 470);
            grphcs.drawString("Food : " + Ressources.getInstance().getFood(), 160, 470);
            grphcs.drawString("Log : " + Ressources.getInstance().getLog(), 310, 470);
            grphcs.drawString("Rock : " + Ressources.getInstance().getRock(), 460, 470);
            
            grphcs.drawString("Timer Score : " + Updater.getInstance().getTimerScore(), 10, 30);
        } else {
            grphcs.setColor(Color.red);
            grphcs.drawString("Game lost | [R] to restart", 250, 200);
            grphcs.setColor(Color.yellow);
            grphcs.drawString("Score : " + score_final, 275, 250);
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
