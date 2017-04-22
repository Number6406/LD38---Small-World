/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import com.sun.javafx.geom.Vec2f;
import java.util.List;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewBuilding extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public NewBuilding(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position)) {
            world.addBuilding(new Building((int)mouse_position.x, (int)mouse_position.y), (int)mouse_position.x, (int)mouse_position.y);
        }
        
    }
    
}
