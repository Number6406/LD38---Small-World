/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;


import Entities.WoodmanHut;
import com.sun.javafx.geom.Vec2f;
import ld38.Main;
import ld38.Ressources;
import org.newdawn.slick.Color;
import world.World;

/**
 *
 * @author Number6406
 */
public class NewWoodmanHut extends Callable {
    
    private Vec2f mouse_position;
    private World world;
    
    public NewWoodmanHut(Vec2f mouse_position, World world) {
        super();
        this.mouse_position = mouse_position;
        this.world = world;
    }
    
    @Override
    public void call() {
        
        if(world.isAccessible(mouse_position) && world.canPlaceWoodmansHut(mouse_position)) {
            WoodmanHut b = new WoodmanHut((int)mouse_position.x, (int)mouse_position.y);
            if( Ressources.getInstance().getLog() >= b.getLog_cost() && Ressources.getInstance().getRock() >= b.getRock_cost()) {
                if(Ressources.getInstance().getPopulation() >= world.getTotalWorkers() + Main.model_woodmanhut.getRequiered_workers()) {
                    world.addBuilding(b, (int)mouse_position.x, (int)mouse_position.y);
                    Ressources.getInstance().updateLog(-b.getLog_cost());
                    Ressources.getInstance().updateRock(-b.getRock_cost());
                    Main.notifier.setMessage("Created woodman's hut !", Color.green, 500);
                } else {
                     Main.notifier.setMessage("Not enough population", Color.red, 1000);
                }
            } else {
                Main.notifier.setMessage("Not enough ressources", Color.red, 1000);
            }
        } else {
            Main.notifier.setMessage("Missplaced building", Color.red, 1000);
        }
        
    }
    
}
