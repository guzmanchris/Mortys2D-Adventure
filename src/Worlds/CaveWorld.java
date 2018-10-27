package Worlds;
import Game.Entities.Creatures.MortyAlly;
import Game.Entities.Creatures.MortyEnemy;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Statics.CoinBlock;
import Game.Entities.Statics.Door;
import Game.Entities.Statics.GuardMorty;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class CaveWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    private BaseWorld world2;

    public CaveWorld(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
        world2 = new World2(handler,"res/Maps/map2.map",player);
        
        entityManager.addEntity(new MortyEnemy(handler, 100, 800));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        entityManager.addEntity(new SkelyEnemy(handler, 1000, 500));
        entityManager.addEntity(new MortyAlly(handler,0,0));
        entityManager.addEntity(new Door(handler, 100, 0,world2));
        entityManager.addEntity(new CoinBlock(handler, 1255, 256));
        entityManager.addEntity(new CoinBlock(handler, 250, 761));
        entityManager.addEntity(new CoinBlock(handler, 1439, 888));
        entityManager.addEntity(new GuardMorty(handler, 250, 10));
        

    }


}