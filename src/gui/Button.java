/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import functions.Callable;
import ld38.EscapistGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Number6406
 */
public class Button {
    
    protected Color background;
    protected Color hover;
    protected Color color;
    protected String text;
    protected boolean hovered;
    private int cast_key;
    
    protected Vector2f pos;
    protected int width;
    protected int height;
    
    protected Callable function;
    
    public Button(Vector2f pos, int width, int height, Color background, Color hover, Color color, String text, Callable function, int cast_key) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.background = background;
        this.hover = hover;
        this.color = color;
        this.text = text;
        this.function = function;
        this.cast_key = cast_key;
    }
    
    public int getCastKey() {
        return cast_key;
    }
    
    public void draw(Graphics g) {
        
        if(hovered) {
            g.setColor(hover);
        } else {
            g.setColor(background);
        }
        g.fillRect(pos.x, pos.y, width, height);
        g.setColor(color);
        g.drawString(text, pos.x+10, pos.y+10);
        
    }
    
    public boolean isHovering() {
        hovered = ((Mouse.getX() >= pos.x && Mouse.getX() < pos.x+width) && (EscapistGame.windowY-Mouse.getY() >= pos.y && EscapistGame.windowY-Mouse.getY() < pos.y + height));
        return hovered;
    }
    
    public void setBackground(Color background) {
        this.background = background;
    }
    
    public void clicked() {
        function.call();
    }
    
    
}
