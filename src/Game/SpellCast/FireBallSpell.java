package Game.SpellCast;

import Main.Handler;
import Resources.Images;

/**
 * Created by Elemental on 2/11/2017.
 */
public class FireBallSpell extends Spells {
    private Handler handler;

    public FireBallSpell(Handler handler) {
        super(handler,Images.Runes[2],2);
        this.handler=handler;
    }

    @Override
    public void Fire(){
        handler.getWorld().getEntityManager().getPlayer().readyFireAttack();
        handler.getWorld().getEntityManager().getPlayer().fireAttack();
    }
}
