package Worlds;

import Game.Entities.Creatures.MortyEnemy2;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Creatures.SkelyEnemy2;
import Game.Entities.Statics.*;
import Main.Handler;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World2 extends BaseWorld{

    private Handler handler;
    private BaseWorld caveWorld;
    private int base = 32;

    public World2(Handler handler, String path, Player player){
        super(handler,path,player);
        this.handler = handler;

        entityManager.addEntity(new Tree(handler, 1320, base*12));
        entityManager.addEntity(new Tree(handler, 1320, base*14));
        entityManager.addEntity(new Tree(handler, 1320, base*16));
        entityManager.addEntity(new Tree(handler, 1320, base*18));
        entityManager.addEntity(new Tree(handler, 1320, base*20));
        entityManager.addEntity(new Tree(handler, 1320, base*22));
        entityManager.addEntity(new Tree(handler, 1320, base*24));
        entityManager.addEntity(new Tree(handler, 1320, base*26));
        entityManager.addEntity(new Tree(handler, 1320, base*28));
        entityManager.addEntity(new Tree(handler, 1320, base*30));
        entityManager.addEntity(new Tree(handler, 1320, base*32));
        entityManager.addEntity(new Crate(handler, 1600, base*28));
        entityManager.addEntity(new Crate(handler, 1536, base*24));
        entityManager.addEntity(new Crate(handler, 1464, base*26));
        entityManager.addEntity(new Door(handler, 100, 0,caveWorld));
        entityManager.addEntity(new SkelyEnemy2(handler, 1364, (64*30)-350));
        entityManager.addEntity(new SkelyEnemy2(handler, 1300, (64*30)-200));
        entityManager.addEntity(new SkelyEnemy2(handler, 1236, (64*30)-264));
        entityManager.addEntity(new MortyEnemy2(handler, 256, (64*30)-320));
        entityManager.addEntity(new MortyEnemy2(handler, 192, (64*30)-256));
        entityManager.addEntity(new InjuredMorty(handler, 360, 0));
        entityManager.addEntity(new KalaxianCrystalBlock(handler, 250, 64*30-440));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

}