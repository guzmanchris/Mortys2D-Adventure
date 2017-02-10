package Game.GameStates;

import Main.Handler;
import Worlds.BaseWorld;
import Worlds.World;

import java.awt.*;

/**
 * Created by Elemental on 12/10/2016.
 */
public class GameState extends State {

    private BaseWorld world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/Maps/map1.map");
        handler.setWorld(world);
    }


    @Override
    public void tick() {
        handler.getWorld().tick();

    }

    @Override
    public void render(Graphics g) {
        handler.getWorld().render(g);

    }

}
