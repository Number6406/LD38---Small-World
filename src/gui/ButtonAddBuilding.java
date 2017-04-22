/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
    
    protected int log_needed;
    protected int rock_needed;
    
    private static Font awtFont = new Font("", Font.BOLD, 12);
    private static TrueTypeFont font = new TrueTypeFont(awtFont, false);
    
    public ButtonAddBuilding(Vec2f pos, int width, int height, Color background, Color hover, Color color, String text, Callable function, int log_needed, int rock_needed) {
        super(pos, width, height, background, hover, color, text, function);
        this.log_needed = log_needed;
        this.rock_needed = rock_needed;
    }
    
     public void draw(Graphics g) {
        
        super.draw(g);
        
        font.drawString(pos.x + 10, pos.y + 25, "Log : " + log_needed);
        font.drawString(pos.x + 70, pos.y + 25, "Rock : " + rock_needed);
        
    }
    
}
