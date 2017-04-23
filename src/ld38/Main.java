/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import Entities.Escapist;
import Entities.Farm;
import Entities.House;
import Entities.Mine;
import Entities.WoodmanHut;
import com.sun.javafx.geom.Vec2f;
import functions.DeleteBuilding;
import functions.NewEscapist;
import functions.NewHouse;
import functions.NewFarm;
import functions.NewMine;
import functions.NewWoodmanHut;
import gui.Button;
import gui.ButtonAddBuilding;
import gui.Notifier;
import gui.Tiles;
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
import org.newdawn.slick.geom.Rectangle;

import world.World;

/**
 *
 * @author Number6406
 */
public class Main extends BasicGame {
    
    public static final int windowX = 700, windowY = 530;
    public static final int tile_size = 10;
    
    private boolean game_running = true;
    
    private World world;
    
    private Vec2f mouse_select;
    
    private List<Button> buttons;
    
    public static House model_house;
    public static Farm model_farm;
    public static Mine model_mine;
    public static WoodmanHut model_woodmanhut;
    public static Escapist model_escapist;
    
    public boolean escapistReady = false;
        
    private int score_final = 0;
    
    public static Notifier notifier;
    private boolean game_won = false;
    
    public Main(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
        buttons = new LinkedList<Button>();
        notifier = new Notifier();
        
        world = new World();
        Ressources.getInstance().init();
        Updater.getInstance().init(world);
        
        mouse_select = new Vec2f(25,25);
        
        model_house = new House(50, 0);
        model_farm = new Farm(50, 5);
        model_mine = new Mine(50, 10);
        model_woodmanhut = new WoodmanHut(50, 15);
        model_escapist = new Escapist(50, 20);
        
        buttons.add(new ButtonAddBuilding(new Vec2f(500,0), 200, 50, Color.darkGray, new Color(85,85,85), Color.white, "+House",
                new NewHouse(mouse_select, world), Input.KEY_1, model_house ));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,50), 200, 50, Color.darkGray, new Color(85,85,85), Color.white, "+Farm",
                new NewFarm(mouse_select, world), Input.KEY_2, model_farm));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,100), 200, 50, Color.darkGray, new Color(85,85,85), Color.white, "+Mine",
                new NewMine(mouse_select, world), Input.KEY_3, model_mine));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,150), 200, 50, Color.darkGray, new Color(85,85,85), Color.white, "+Wood. Hut",
                new NewWoodmanHut(mouse_select, world), Input.KEY_4, model_woodmanhut));
        buttons.add(new ButtonAddBuilding(new Vec2f(500,200), 200, 50, Color.darkGray,new Color(85,85,85), Color.white, "+Escapist",
                new NewEscapist(mouse_select, world), Input.KEY_5, model_escapist));
        
        buttons.add(new Button(new Vec2f(500,250), 200, 40, new Color(180,0,0), new Color(240,36,36), Color.white, "Delete Building", 
                new DeleteBuilding(mouse_select, world), Input.KEY_DELETE));
        
        game_running = true;
        game_won = false;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        
        if(escapistReady = world.hasEscapist()) {
            if(Updater.getInstance().updateAtomicEscapist(delta)) {
                score_final = Updater.getInstance().getTimerScore();
                game_won = true;
            }    
        }
        
        if(input.isKeyPressed(Input.KEY_R)) {
            init(gc);
        }
        
        if(!game_won) {
            

            if(Ressources.getInstance().getPopulation() <= 0 || world.isSubmerged()) {
                score_final = Updater.getInstance().getTimerScore();
                game_running = false;
            }

            if(game_running) {
                Updater.getInstance().update(delta);
                notifier.updateTimer(delta);

                if(Updater.getInstance().updateAtomicMove(delta)) {
                    if(input.isKeyDown(Input.KEY_UP) && mouse_select.y > 0) {
                        mouse_select.y--;
                        Updater.getInstance().resetMoveTimer();
                    }
                    if(input.isKeyDown(Input.KEY_DOWN) && mouse_select.y < world.getWorldSize()-1) {
                        mouse_select.y++;
                        Updater.getInstance().resetMoveTimer();
                    }
                    if(input.isKeyDown(Input.KEY_LEFT) && mouse_select.x > 0) {
                        mouse_select.x--;
                        Updater.getInstance().resetMoveTimer();
                    }
                    if(input.isKeyDown(Input.KEY_RIGHT) && mouse_select.x < world.getWorldSize()-1) {
                        mouse_select.x++;
                        Updater.getInstance().resetMoveTimer();
                    }
                }

                for (Button button : buttons) {
                    if(input.isKeyPressed(button.getCastKey())) {
                        button.clicked();
                    }
                }

                if(input.isMousePressed(0)) {
                    if(Mouse.getX() < 500) {
                        mouse_select.x = (int) (Mouse.getX() / tile_size);
                        mouse_select.y = (int) ((Main.windowY-Mouse.getY()) / tile_size);
                    }
                    for (Button button : buttons) {
                        if(button.isHovering()) {
                           button.clicked();
                        }
                    }
                }
            }
        }   
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        if(game_won) {
            grphcs.setColor(Color.green);
            grphcs.drawString("Game won ! | [R] to restart", 250, 200);
            grphcs.setColor(Color.yellow);
            grphcs.drawString("Score : " + score_final, 275, 250);
        } else if(game_running) {
            world.draw(grphcs);
            if(world.isAccessible(mouse_select)) {
                grphcs.setColor(Color.green);
            } else {
                grphcs.setColor(Color.red);
            }
            //grphcs.drawString("Selection : " + mouse_select.x + ";" + mouse_select.y, 10, 50);
            grphcs.setLineWidth(3);
            grphcs.drawRect(mouse_select.x * tile_size, mouse_select.y * tile_size, tile_size, tile_size);

            for (Button button : buttons) {
                button.isHovering();
                button.draw(grphcs);
            }
            
            grphcs.setColor(Color.white);
            grphcs.drawString("Timer Score : " + Updater.getInstance().getTimerScore(), 10, 10);
            
            grphcs.setColor(Color.darkGray);
            grphcs.fillRect(0, 490, 700, 40);
            
            grphcs.setColor(Color.white);
            grphcs.drawImage(Ressources.getInstance().icon_pop, 10, 505);
            grphcs.drawString("" + Ressources.getInstance().getPopulation() + "/" + world.getTotalCapability(), 20, 500);
            grphcs.drawImage(Ressources.getInstance().icon_food, 185, 505);
            grphcs.drawString("" + Ressources.getInstance().getFood(), 195, 500);
            grphcs.drawImage(Ressources.getInstance().icon_log, 360, 505);
            grphcs.drawString("" + Ressources.getInstance().getLog(), 370, 500);
            grphcs.drawImage(Ressources.getInstance().icon_rock, 535, 505);
            grphcs.drawString("" + Ressources.getInstance().getRock(), 545, 500);
            
            grphcs.setColor(Color.green);
            grphcs.drawImage(Ressources.getInstance().icon_workers, 90, 505);
            grphcs.drawString("(" + Updater.getInstance().getAvailablePop() + ")", 100, 500);
            
            if((Updater.getInstance().differenceFood()) > 0) {
                grphcs.setColor(Color.green);
            } else {
                grphcs.setColor(Color.red);
            }
            grphcs.drawString("(" + (Updater.getInstance().differenceFood()) + ")", 245, 500);
            grphcs.setColor(Color.green);
            grphcs.drawString("(" + world.getTotalLogProduction() + ")", 420, 500);
            grphcs.drawString("(" + world.getTotalRockProduction() + ")", 595, 500);
            
            notifier.draw(grphcs);
            
            if(escapistReady) {
                grphcs.drawString("Escaping : " + Updater.getInstance().getEscapistProgress(), 520, 350);
            }
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
            
            appgc.setDisplayMode(windowX, windowY, false);
            appgc.setVSync(true);
            appgc.setVerbose(false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
