/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ld38;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 *
 * @author Number6406
 */
public class SoundBoard {
    
    private static final SoundBoard INSTANCE = new SoundBoard();
    
    private Map<String, Sound> sounds;
    
    private SoundBoard() {
        sounds = new HashMap<String, Sound>();
        
        try {
            sounds.put("submerge", new Sound("sound/submerging.wav"));
            sounds.put("destroy", new Sound("sound/destroy.wav"));
            sounds.put("build", new Sound("sound/build.wav"));
            sounds.put("error", new Sound("sound/error.wav"));
            sounds.put("won", new Sound("sound/won.wav"));
            sounds.put("lost", new Sound("sound/lost.wav"));
        } catch (SlickException ex) {
            Logger.getLogger(SoundBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SoundBoard getInstance() {
        return INSTANCE;
    }
    
    public void play(String name) {
        sounds.get(name).play();
    }
    
}
