/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.newdawn.slick.Graphics;

/**
 *
 * @author Number6406
 */
public class Notifier {
    
    private String message = "";
    
    public Notifier() {
        
    }
    
    public void draw(Graphics g) {
        g.drawString(message, 200, 10);
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
}
