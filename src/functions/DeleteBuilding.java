/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import Entities.Escapist;
import Entities.Farm;
import com.sun.javafx.geom.Vec2f;
import java.util.List;
import ld38.EscapistGame;
import ld38.Resources;
import ld38.SoundBoard;
import org.newdawn.slick.Color;
import world.World;

/**
 *
 * @author Number6406
 */
public class DeleteBuilding extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public DeleteBuilding(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isBuilding(mouse_position)) {
            if(world.destroyBuilding(mouse_position)) {
                EscapistGame.notifier.setMessage("Deleted building successfully", Color.green, 1000);
            } else {
                EscapistGame.notifier.setMessage("Error occured", Color.red, 1000);
                SoundBoard.getInstance().play("error");
            }
        } else {
            EscapistGame.notifier.setMessage("Can't delete nothing", Color.red, 1000);
            SoundBoard.getInstance().play("error");
        }
        
    }
    
}
