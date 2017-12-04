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
import functions.DeleteBuilding;
import functions.NewEscapist;
import functions.NewHouse;
import functions.NewFarm;
import functions.NewMine;
import functions.NewWoodmanHut;
import functions.PauseGame;
import functions.ShowInfo;
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
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import world.World;

/**
 *
 * @author Number6406
 */
public class EscapistGame extends BasicGame {

    public static final int windowX = 700, windowY = 530;
    public static final int tile_size = 10;

    private World world;

    private Vector2f mouse_select;

    private List<Button> buttons;

    public static House model_house;
    public static Farm model_farm;
    public static Mine model_mine;
    public static WoodmanHut model_woodmanhut;
    public static Escapist model_escapist;

    public boolean escapistReady = false;

    private int score_final = 0;

    public static Notifier notifier;

    private boolean game_lost = false;
    private boolean game_won = false;
    private static boolean game_info = false;
    private static boolean game_pause = false;

    private Image info_image;
    private Image pause_image;

    public EscapistGame(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        info_image = new Image("res/info.png");
        pause_image = new Image("res/pause.png");

        buttons = new LinkedList<Button>();
        notifier = new Notifier();

        world = new World();
        Resources.getInstance().init();
        Updater.getInstance().init(world);

        mouse_select = new Vector2f(25, 25);

        model_house = new House(50, 0);
        model_farm = new Farm(50, 5);
        model_mine = new Mine(50, 10);
        model_woodmanhut = new WoodmanHut(50, 15);
        model_escapist = new Escapist(50, 22);

        buttons.add(new ButtonAddBuilding(new Vector2f(500, 0), 200, 50, Color.darkGray, new Color(85, 85, 85), Color.white, "[1]House",
                new NewHouse(mouse_select, world), Input.KEY_1, model_house));
        buttons.add(new ButtonAddBuilding(new Vector2f(500, 50), 200, 50, Color.darkGray, new Color(85, 85, 85), Color.white, "[2]Farm",
                new NewFarm(mouse_select, world), Input.KEY_2, model_farm));
        buttons.add(new ButtonAddBuilding(new Vector2f(500, 100), 200, 50, Color.darkGray, new Color(85, 85, 85), Color.white, "[3]Mine",
                new NewMine(mouse_select, world), Input.KEY_3, model_mine));
        buttons.add(new ButtonAddBuilding(new Vector2f(500, 150), 200, 50, Color.darkGray, new Color(85, 85, 85), Color.white, "[4]Wood. Hut",
                new NewWoodmanHut(mouse_select, world), Input.KEY_4, model_woodmanhut));
        buttons.add(new ButtonAddBuilding(new Vector2f(500, 200), 200, 50, Color.darkGray, new Color(85, 85, 85), Color.white, "[5]Escapist",
                new NewEscapist(mouse_select, world), Input.KEY_5, model_escapist));

        buttons.add(new Button(new Vector2f(500, 250), 200, 40, new Color(180, 0, 0), new Color(240, 36, 36), Color.white, "Delete Building",
                new DeleteBuilding(mouse_select, world), Input.KEY_DELETE));
        buttons.add(new Button(new Vector2f(500, 450), 200, 40, Color.darkGray, new Color(85, 85, 85), Color.white, "[I] Info / Help",
                new ShowInfo(), Input.KEY_I));
        buttons.add(new Button(new Vector2f(500, 410), 200, 40, Color.darkGray, new Color(85, 85, 85), Color.white, "[P] Pause Game",
                new PauseGame(), Input.KEY_P));

        game_info = false;
        game_lost = false;
        game_won = false;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_P) && !game_info) {
            game_pause = !game_pause;
        }
        
        if(game_pause) {
            input.clearControlPressedRecord();
            input.clearKeyPressedRecord();
            input.clearMousePressedRecord();
            return;
        }
        
        if (game_info) {
            if (input.isKeyDown(Input.KEY_ESCAPE)) {
                setInfoPause(false);
            }
            input.clearControlPressedRecord();
            input.clearKeyPressedRecord();
            input.clearMousePressedRecord();
            return;
        }

        if (input.isKeyPressed(Input.KEY_R) && !game_info && !game_pause) {
            init(gc);
        }

        if (!game_won && !game_lost && !game_pause && !game_info) {
            
            if (escapistReady = world.hasEscapist()) {
                if (Updater.getInstance().updateAtomicEscapist(delta)) {
                    game_won = true;
                    SoundBoard.getInstance().play("won");
                    score_final = finalScore();
                    return;
                }
            }
            if (Resources.getInstance().getPopulation() <= 0 || world.isSubmerged()) {
                game_lost = true;
                SoundBoard.getInstance().play("lost");
                score_final = finalScore();
                return;
            }

            Updater.getInstance().update(delta);
            notifier.updateTimer(delta);

            if (Updater.getInstance().updateAtomicMove(delta)) {
                if (input.isKeyDown(Input.KEY_UP) && mouse_select.y > 0) {
                    mouse_select.y--;
                    Updater.getInstance().resetMoveTimer();
                }
                if (input.isKeyDown(Input.KEY_DOWN) && mouse_select.y < world.getWorldSize() - 1) {
                    mouse_select.y++;
                    Updater.getInstance().resetMoveTimer();
                }
                if (input.isKeyDown(Input.KEY_LEFT) && mouse_select.x > 0) {
                    mouse_select.x--;
                    Updater.getInstance().resetMoveTimer();
                }
                if (input.isKeyDown(Input.KEY_RIGHT) && mouse_select.x < world.getWorldSize() - 1) {
                    mouse_select.x++;
                    Updater.getInstance().resetMoveTimer();
                }
            }

            for (Button button : buttons) {
                if (input.isKeyPressed(button.getCastKey())) {
                    button.clicked();
                }
            }

            if (input.isMousePressed(0)) {
                if (Mouse.getX() < 500 && EscapistGame.windowY - Mouse.getY() < 500) {
                    mouse_select.x = (int) (Mouse.getX() / tile_size);
                    mouse_select.y = (int) ((EscapistGame.windowY - Mouse.getY()) / tile_size);
                }
                for (Button button : buttons) {
                    if (button.isHovering()) {
                        button.clicked();
                    }
                }
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        if (game_won) {
            grphcs.setColor(Color.green);
            grphcs.drawString("Game won ! | [R] to restart", 250, 200);
            grphcs.setColor(Color.yellow);
            grphcs.drawString("Score : " + score_final, 275, 250);
            grphcs.setColor(Color.gray);
            grphcs.drawString("Feel free to share the game and challenge your friends ! :)", 100, 280);
        } else if (!game_lost) {
            world.draw(grphcs);
            if (world.isAccessible(mouse_select)) {
                grphcs.setColor(Color.green);
            } else {
                grphcs.setColor(Color.red);
            }
            //grphcs.drawString("Selection : " + mouse_select.x + ";" + mouse_select.y, 10, 50);
            grphcs.setLineWidth(3);
            grphcs.drawRect(mouse_select.x * tile_size, mouse_select.y * tile_size, tile_size, tile_size);

            for (Button button : buttons) {
                if (!game_info && !game_pause) {
                    button.isHovering();
                }
                button.draw(grphcs);
            }

            grphcs.setColor(Color.white);
            grphcs.drawString("Timer Score : " + Updater.getInstance().getTimerScore(), 10, 10);

            grphcs.setColor(Color.darkGray);
            grphcs.fillRect(0, 490, 700, 40);

            grphcs.setColor(Color.white);
            grphcs.drawImage(Resources.getInstance().icon_pop, 10, 505);
            grphcs.drawString("" + Resources.getInstance().getPopulation() + "/" + world.getTotalCapability(), 20, 500);
            grphcs.drawImage(Resources.getInstance().icon_food, 185, 505);
            grphcs.drawString("" + Resources.getInstance().getFood(), 195, 500);
            grphcs.drawImage(Resources.getInstance().icon_log, 360, 505);
            grphcs.drawString("" + Resources.getInstance().getLog(), 370, 500);
            grphcs.drawImage(Resources.getInstance().icon_rock, 535, 505);
            grphcs.drawString("" + Resources.getInstance().getRock(), 545, 500);

            grphcs.setColor(Color.green);
            grphcs.drawImage(Resources.getInstance().icon_workers, 90, 505);
            grphcs.drawString("(" + Updater.getInstance().getAvailablePop() + ")", 100, 500);

            if ((Updater.getInstance().differenceFood()) > 0) {
                grphcs.setColor(Color.green);
            } else {
                grphcs.setColor(Color.red);
            }
            grphcs.drawString("(" + (Updater.getInstance().differenceFood()) + ")", 245, 500);
            grphcs.setColor(Color.green);
            grphcs.drawString("(" + world.getTotalLogProduction() + ")", 420, 500);
            grphcs.drawString("(" + world.getTotalRockProduction() + ")", 595, 500);

            notifier.draw(grphcs);

            if (escapistReady) {
                grphcs.setColor(Color.green);
                grphcs.drawString("Escaping : " + Updater.getInstance().getEscapistProgressString(), 520, 350);
            }
            
            if (game_info) {
                grphcs.drawImage(info_image, 0, 0);
                grphcs.setColor(Color.white);
                grphcs.drawString("[ESC] to close", 550, 10);
            }
            
            if(game_pause) {
                grphcs.drawImage(pause_image, 0, 0);
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
            appgc = new AppGameContainer(new EscapistGame("Escapist !"));
            appgc.setIcon("res/icon_sm.png");
            appgc.setDisplayMode(windowX, windowY, false);
            appgc.setVSync(true);
            appgc.setVerbose(false);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Escapist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int finalScore() {
        int timer = Updater.getInstance().getTimerScore();
        if (game_won) {
            return 1000 - timer - world.getTotalBuilding(model_house);
        } else {
            return timer + world.countBuildings();
        }
    }

    public static void setInfoPause(boolean pause) {
        game_info = pause;
    }
    
    public static void setPause(boolean pause) {
        game_pause = pause;
    }
    
}
