/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import Entities.Farm;
import Entities.Mine;
import com.sun.javafx.geom.Vec2f;
import java.util.List;
import ld38.Ressources;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewMine extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public NewMine(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position)) {
            Mine b = new Mine((int)mouse_position.x, (int)mouse_position.y);
            if( Ressources.getInstance().getLog() >= b.getLog_cost() && Ressources.getInstance().getRock() >= b.getRock_cost() ) {
                world.addBuilding(b, (int)mouse_position.x, (int)mouse_position.y);
                Ressources.getInstance().updateLog(-b.getLog_cost());
                Ressources.getInstance().updateRock(-b.getRock_cost());
            }
        }
        
    }
    
}
