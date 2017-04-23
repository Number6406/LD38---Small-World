/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Number6406
 */
public class Notifier {
    
    private String message = "";
    private int initial_timer = 0;
    private int timer = 0;
    private Color color;
    
    public Notifier() {
        color = Color.white;
    }
    
    public void draw(Graphics g) {
        if(timer > 0) {
            if(initial_timer - timer > 100) {
                g.setColor(color);  
            } else {
                g.setColor(Color.white);
            }
            g.drawString(message, 200, 10);
        }
    }
    
    public void updateTimer(int delta) {
        if(timer > 0) {
            timer -= delta;
        }
    }
    
    public void setMessage(String message, Color color, int timer) {
        this.color = color;
        this.initial_timer = timer;
        this.timer = timer;
        this.message = message;
    }
    
}
