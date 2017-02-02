package Game.Entities;

import Game.Entities.Creatures.Player;
import Main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Elemental on 1/1/2017.
 */
public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<EntityBase> entities;
    private Comparator<EntityBase> renderSorter = new Comparator<EntityBase>(){
        @Override
        public int compare(EntityBase a, EntityBase b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<EntityBase>();
        addEntity(player);
    }

    public void tick(){
        Iterator<EntityBase> it = entities.iterator();

        while(it.hasNext()){
            EntityBase e = it.next();
            e.tick();
            if(!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(EntityBase e : entities){
            e.render(g);
        }
    }

    public void addEntity(EntityBase e){
        entities.add(e);
    }

    //GETTERS SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<EntityBase> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<EntityBase> entities) {
        this.entities = entities;
    }

}