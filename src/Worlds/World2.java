package Worlds;

import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.*;
import Main.Handler;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World2 extends BaseWorld{

    private Handler handler;
    private BaseWorld caveWorld;
    int base = 32;
    
    public World2(Handler handler, String path, Player player){
        super(handler,path,player);
        this.handler = handler;
        
        

        entityManager.addEntity(new Tree(handler, 1330, base*12));        
        entityManager.addEntity(new Tree(handler, 1320, base*14));
        entityManager.addEntity(new Tree(handler, 1310, base*16));
        entityManager.addEntity(new Tree(handler, 1300, base*18));
        entityManager.addEntity(new Tree(handler, 1290, base*20));
        entityManager.addEntity(new Tree(handler, 1280, base*22));
        entityManager.addEntity(new Tree(handler, 1270, base*24));
        entityManager.addEntity(new Tree(handler, 1260, base*26));        
        entityManager.addEntity(new Tree(handler, 1250, base*28));
        entityManager.addEntity(new Tree(handler, 1240, base*30));        
        entityManager.addEntity(new Tree(handler, 1230, base*32));
        entityManager.addEntity(new Door(handler, 100, 0,caveWorld));
        entityManager.addEntity(new WizardHumanoid(handler, 250, 0,caveWorld));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

}