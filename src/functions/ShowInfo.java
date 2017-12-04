/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import ld38.EscapistGame;
import org.newdawn.slick.Color;

/**
 *
 * @author Number6406
 */
public class ShowInfo extends Callable {
    
    public ShowInfo() {
        super();
    }
    
    @Override
    public void call() {
        EscapistGame.setInfoPause(true);
    }
    
}
