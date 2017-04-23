/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Building;
import com.sun.javafx.geom.Vec2f;
import functions.Callable;
import java.awt.Font;
import ld38.Ressources;
import ld38.Updater;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Number6406
 */
public class ButtonAddBuilding extends Button {
    
    private static Font awtFont = new Font("", Font.BOLD, 12);
    private static TrueTypeFont font = new TrueTypeFont(awtFont, false);
    
    
    private Color current_color = Color.white;
    
    private Building building;
    
    public ButtonAddBuilding(Vec2f pos, int width, int height, Color background, Color hover, Color color, String text, Callable function, int cast_key, Building building) {
        super(pos, width, height, background, hover, color, text, function, cast_key);
        this.building = building;
    }
    
    public void draw(Graphics g) {
        
        super.draw(g);
        
        current_color = Color.white;
        if(building.getLog_cost() > Ressources.getInstance().getLog()) {
            current_color = Color.red;
        }
        g.drawImage(Ressources.getInstance().icon_log, pos.x+10, pos.y+28);
        font.drawString(pos.x + 20, pos.y + 25, ""+building.getLog_cost(), current_color);
        
        current_color = Color.white;
        if(building.getRock_cost() > Ressources.getInstance().getRock()) {
            current_color = Color.red;
        }
        g.drawImage(Ressources.getInstance().icon_rock, pos.x+40, pos.y+28);
        font.drawString(pos.x + 50, pos.y + 25, "" + building.getRock_cost(), current_color);
        
        current_color = Color.white;
        if(building.getRequiered_workers() > Updater.getInstance().getAvailablePop()) {
            current_color = Color.red;
        }
        g.drawImage(Ressources.getInstance().icon_workers, pos.x+70, pos.y+28);
        font.drawString(pos.x + 80, pos.y + 25, "" + building.getRequiered_workers(), current_color);
        
        current_color = Color.green;
        g.drawImage(Ressources.getInstance().icon_pop, pos.x+120, pos.y+18);
        font.drawString(pos.x + 132, pos.y + 15, "+" + building.getPopulation_capability());
        g.drawImage(Ressources.getInstance().icon_rock, pos.x+120, pos.y+33);
        font.drawString(pos.x + 132, pos.y + 30, "+" + building.getRock_production());
        
        g.drawImage(Ressources.getInstance().icon_log, pos.x+160, pos.y+18);
        font.drawString(pos.x + 172, pos.y + 15, "+" + building.getLog_production());
        g.drawImage(Ressources.getInstance().icon_log, pos.x+160, pos.y+33);
        font.drawString(pos.x + 172, pos.y + 30, "+" + building.getFood_production());
        
    }
    
}
