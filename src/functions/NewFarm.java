/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import Entities.Building;
import Entities.Farm;
import com.sun.javafx.geom.Vec2f;
import java.util.List;
import ld38.Main;
import ld38.Ressources;
import org.newdawn.slick.Color;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewFarm extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public NewFarm(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position)) {
            Farm b = new Farm((int)mouse_position.x, (int)mouse_position.y);
            if( Ressources.getInstance().getLog() >= b.getLog_cost() && Ressources.getInstance().getRock() >= b.getRock_cost()) {
                world.addBuilding(b, (int)mouse_position.x, (int)mouse_position.y);
                Ressources.getInstance().updateLog(-b.getLog_cost());
                Ressources.getInstance().updateRock(-b.getRock_cost());
                
                Main.notifier.setMessage("Created farm !", Color.green, 500);
            } else {
                Main.notifier.setMessage("Not enough ressources", Color.red, 1000);
            }
        } else {
            Main.notifier.setMessage("Missplaced building", Color.red, 1000);
        }
        
    }
    
}
