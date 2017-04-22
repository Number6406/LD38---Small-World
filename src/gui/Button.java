/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.javafx.geom.Vec2f;
import functions.Callable;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Number6406
 */
public class Button {
    
    private Color background;
    private Color color;
    private String text;
    
    private Vec2f pos;
    private int width;
    private int height;
    
    private Callable function;
    
    public Button(Vec2f pos, int width, int height, Color background, Color color, String text, Callable function) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.background = background;
        this.color = color;
        this.text = text;
        this.function = function;
    }
    
    public void Draw(Graphics g) {
        
        g.setColor(background);
        g.fillRect(pos.x, pos.y, width, height);
        g.setColor(color);
        g.drawString(text, pos.x+10, pos.y+10);
        
    }
    
    public boolean isHovering() {
        return ((Mouse.getX() >= pos.x && Mouse.getX() <= pos.x+width) && (500-Mouse.getY() >= pos.y && 500-Mouse.getY() <= pos.y + height));
    }
    
    public void setBackground(Color background) {
        this.background = background;
    }
    
    public void clicked() {
        function.call();
    }
    
    
}
