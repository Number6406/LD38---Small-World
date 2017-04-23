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
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Number6406
 */
public class ButtonAddBuilding extends Button {
    
    private static Font awtFont = new Font("", Font.BOLD, 12);
    private static TrueTypeFont font = new TrueTypeFont(awtFont, false);
    
    private Building building;
    
    public ButtonAddBuilding(Vec2f pos, int width, int height, Color background, Color hover, Color color, String text, Callable function, Building building) {
        super(pos, width, height, background, hover, color, text, function);
        this.building = building;
    }
    
     public void draw(Graphics g) {
        
        super.draw(g);
        
        font.drawString(pos.x + 10, pos.y + 25, "Log : " + building.getLog_cost());
        font.drawString(pos.x + 60, pos.y + 25, "Rock : " + building.getRock_cost());
        font.drawString(pos.x + 120, pos.y + 25, "Pop : " + building.getRequiered_workers());
        
    }
    
}
