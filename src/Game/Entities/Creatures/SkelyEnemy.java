package Game.Entities.Creatures;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;

/**
 * Created by Elemental on 2/7/2017.
 */
public class SkelyEnemy extends CreatureBase  {


    private Animation animsDown, animsUp, animsLeft, animsRight;

    Boolean attacking=false;

    private int animWalkingSpeed = 150;
    private Inventory Skelyinventory;
    private Rectangle SkelyCam;

    private int healthcounter =0;

    private Random randint;
    private int moveCount=0;
    private int direction;

    public SkelyEnemy(Handler handler, float x, float y) {
        super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);
        bounds.x=8*2;
        bounds.y=18*2;
        bounds.width=16*2;
        bounds.height=14*2;
        speed=1.5f;

        SkelyCam= new Rectangle();



        randint = new Random();
        direction = randint.nextInt(4) + 1;

        animsDown = new Animation(animWalkingSpeed, Images.SkelyEnemy_front);
        animsLeft = new Animation(animWalkingSpeed,Images.SkelyEnemy_left);
        animsRight = new Animation(animWalkingSpeed,Images.SkelyEnemy_right);
        animsUp = new Animation(animWalkingSpeed,Images.SkelyEnemy_back);

        Skelyinventory= new Inventory(handler);
    }

    @Override
    public void tick() {
        animsDown.tick();
        animsUp.tick();
        animsRight.tick();
        animsLeft.tick();

        moveCount ++;
        if(moveCount>=60){
            moveCount=0;
            direction = randint.nextInt(4) + 1;
        }
        checkIfMove();

        move();


        if(isBeinghurt()){
            healthcounter++;
            if(healthcounter>=120){
                setBeinghurt(false);
                System.out.print(isBeinghurt());
            }
        }
        if(healthcounter>=120&& !isBeinghurt()){
            healthcounter=0;
        }


        Skelyinventory.tick();


    }


    private void checkIfMove() {
        xMove = 0;
        yMove = 0;

        SkelyCam.x = (int) (x - handler.getGameCamera().getxOffset() - (64 * 3));
        SkelyCam.y = (int) (y - handler.getGameCamera().getyOffset() - (64 * 3));
        SkelyCam.width = 64 * 7;
        SkelyCam.height = 64 * 7;

        if (SkelyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset())
                || SkelyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getPlayer().getWidth(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getPlayer().getHeight())) {

            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle ar = new Rectangle();
            int arSize = 13;
            ar.width = arSize;
            ar.height = arSize;

            if (lu) {
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
            } else if (ld) {
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
            } else if (ll) {
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else if (lr) {
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            }

            for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.equals(this))
                    continue;
                if (e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getPlayer())) {

                    checkAttacks();
                    return;
                }
            }


            if (x >= handler.getWorld().getEntityManager().getPlayer().getX() - 8 && x <= handler.getWorld().getEntityManager().getPlayer().getX() + 8) {//nada

                xMove = 0;
            } else if (x < handler.getWorld().getEntityManager().getPlayer().getX()) {//move right

                xMove = speed;

            } else if (x > handler.getWorld().getEntityManager().getPlayer().getX()) {//move left

                xMove = -speed;
            }

            if (y >= handler.getWorld().getEntityManager().getPlayer().getY() - 8 && y <= handler.getWorld().getEntityManager().getPlayer().getY() + 8) {//nada
                yMove = 0;
            } else if (y < handler.getWorld().getEntityManager().getPlayer().getY()) {//move down
                yMove = speed;

            } else if (y > handler.getWorld().getEntityManager().getPlayer().getY()) {//move up
                yMove = -speed;
            }


        } else {


            switch (direction) {
                case 1://up
                    yMove = -speed;
                    break;
                case 2://down
                    yMove = speed;
                    break;
                case 3://left
                    xMove = -speed;
                    break;
                case 4://right
                    xMove = speed;
                    break;

            }
        }
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if(isBeinghurt() && healthcounter<=120){
            g.setColor(Color.white);
            g.drawString("SkelyHealth: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()-20));
        }
    }

    @SuppressWarnings("Duplicates")
    private Image getCurrentAnimationFrame() {
        if(xMove < 0){
            ll=true;
            ld=false;
            lr=false;
            lu=false;
            return animsLeft.getCurrentFrame();
        }else if(xMove > 0){
            ll=false;
            ld=false;
            lr=true;
            lu=false;
            return animsRight.getCurrentFrame();
        }else if(yMove < 0){
            ll=false;
            ld=false;
            lr=false;
            lu=true;
            return animsUp.getCurrentFrame();
        }else if(yMove > 0){
            ll=false;
            ld=true;
            lr=false;
            lu=false;
            return animsDown.getCurrentFrame();
        }else{
            if(ld){
                return Images.SkelyEnemy_front[0];
            }else if(lu){
                return Images.SkelyEnemy_back[0];
            }else if(ll){
                return Images.SkelyEnemy_left[0];
            }else if(lr){
                return Images.SkelyEnemy_right[0];
            }else{
                return Images.SkelyEnemy_front[0];
            }
        }
    }

    @Override
    public void die() {

    }
}
