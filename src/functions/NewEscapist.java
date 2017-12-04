/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import Entities.Escapist;
import Entities.Farm;
import java.util.List;
import ld38.EscapistGame;
import ld38.Resources;
import ld38.SoundBoard;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewEscapist extends Callable {
    
    private Vector2f mouse_position;
    private World world;
    
    public NewEscapist(Vector2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position)) {
            Escapist b = new Escapist((int)mouse_position.x, (int)mouse_position.y);
            if( Resources.getInstance().getLog() >= b.getLog_cost() && Resources.getInstance().getRock() >= b.getRock_cost()) {
                if(Resources.getInstance().getPopulation() >= world.getTotalWorkers() + EscapistGame.model_mine.getRequiered_workers()) {
                    world.addBuilding(b, (int)mouse_position.x, (int)mouse_position.y);
                    Resources.getInstance().updateLog(-b.getLog_cost());
                    Resources.getInstance().updateRock(-b.getRock_cost());
                    SoundBoard.getInstance().play("build");

                    EscapistGame.notifier.setMessage("Escape way in creation !", Color.green, 500);
                } else {
                    EscapistGame.notifier.setMessage("Not enough population", Color.red, 1000);
                    SoundBoard.getInstance().play("error");
                }
            } else {
                EscapistGame.notifier.setMessage("Not enough ressources", Color.red, 1000);
                SoundBoard.getInstance().play("error");
            }
        } else {
            EscapistGame.notifier.setMessage("Missplaced building", Color.red, 1000);
            SoundBoard.getInstance().play("error");
        }
        
    }
    
}