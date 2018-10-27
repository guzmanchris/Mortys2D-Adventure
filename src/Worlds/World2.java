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

    public World2(Handler handler, String path, Player player){
        super(handler,path,player);
        this.handler = handler;

        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 450));
        entityManager.addEntity(new Tree(handler, 533, 276));
        entityManager.addEntity(new Rock(handler, 684, 1370));
        entityManager.addEntity(new Tree(handler, 765, 888));
        entityManager.addEntity(new Rock(handler, 88, 1345));
        entityManager.addEntity(new Tree(handler, 77, 700));
        entityManager.addEntity(new Rock(handler, 700, 83));
        entityManager.addEntity(new CoinBlock(handler, 800, 1320));
        entityManager.addEntity(new CoinBlock(handler, 1400, 200));
        entityManager.addEntity(new CoinBlock(handler, 320, 858));
        entityManager.addEntity(new Door(handler, 100, 0,caveWorld));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        //entityManager.addEntity(new InjuredMorty(handler, 360, 0));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

}