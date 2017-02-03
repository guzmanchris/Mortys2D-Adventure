package Game.Entities.Creatures;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Resources.Animation;
import Resources.Images;
import Main.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 1/1/2017.
 */

public class Player extends CreatureBase {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    private boolean ld=true,ll=false,lr=false,lu=false;
    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;



    private int animWalkingSpeed = 150;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);

        bounds.x=8*2;
        bounds.y=18*2;
        bounds.width=16*2;
        bounds.height=14*2;


        animDown = new Animation(animWalkingSpeed,Images.player_front);
        animLeft = new Animation(animWalkingSpeed,Images.player_left);
        animRight = new Animation(animWalkingSpeed,Images.player_right);
        animUp = new Animation(animWalkingSpeed,Images.player_back);

        inventory= new Inventory(handler);

    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(lu && handler.getKeyManager().attbut){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if(ld && handler.getKeyManager().attbut){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(ll && handler.getKeyManager().attbut){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else if(lr && handler.getKeyManager().attbut){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            return;
        }

        attackTimer = 0;

        for(EntityBase e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(2);
                System.out.println(e + " has " + e.getHealth() + " lives.");
                return;
            }
        }

    }

    @Override
    public void die(){
        System.out.println("You lose");
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            ll=true;
            ld=false;
            lr=false;
            lu=false;
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            ll=false;
            ld=false;
            lr=true;
            lu=false;
            return animRight.getCurrentFrame();
        }else if(yMove < 0){
            ll=false;
            ld=false;
            lr=false;
            lu=true;
            return animUp.getCurrentFrame();
        }else if(yMove > 0){
            ll=false;
            ld=true;
            lr=false;
            lu=false;
            return animDown.getCurrentFrame();
        }else{
            if(ld){
                return Images.player_front[0];
            }else if(lu){
                return Images.player_back[0];
            }else if(ll){
                return Images.player_left[0];
            }else if(lr){
                return Images.player_right[0];
            }else{
                return Images.player_front[0];
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}