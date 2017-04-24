/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import Entities.House;
import com.sun.javafx.geom.Vec2f;
import java.util.List;
import ld38.Main;
import ld38.Resources;
import org.newdawn.slick.Color;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewHouse extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public NewHouse(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position)) {
            House b = new House((int)mouse_position.x, (int)mouse_position.y);
            if( Resources.getInstance().getLog() >= b.getLog_cost() && Resources.getInstance().getRock() >= b.getRock_cost() ) {
                world.addBuilding(b, (int)mouse_position.x, (int)mouse_position.y);
                Resources.getInstance().updateLog(-b.getLog_cost());
                Resources.getInstance().updateRock(-b.getRock_cost());
                
                Main.notifier.setMessage("Created house !", Color.green, 500);
            } else {
                Main.notifier.setMessage("Not enough ressources", Color.red, 1000);
            }
        } else {
            Main.notifier.setMessage("Missplaced building", Color.red, 1000);
        }
        
    }
    
}
