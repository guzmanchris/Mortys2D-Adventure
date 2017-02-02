package Game.inventory;

import Game.GameStates.State;
import Game.items.Item;
import Resources.Images;
import UI.UIImageButton;
import UI.UIInventory;
import UI.UIManager;
import main.Handler;
import org.omg.PortableInterceptor.ACTIVE;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Elemental on 1/3/2017.
 */
public class Inventory {

    private Handler handler;
    private boolean active = false;
    private UIManager uiManager;
    private ArrayList<Item> inventoryItems;
    public static Boolean[] Slots;

    public static int currSlot = 0;
    public static int rendSlot = 0;


    public Inventory(Handler handler){
        this.handler=handler;
        inventoryItems = new ArrayList<Item>();
        uiManager = new UIManager(handler);
        uiManager.addObjects(new UIInventory(0,0, 329, 265, Images.inventory,() -> {

        }));


        Slots= new Boolean[2];
        Slots[0]=false;
        Slots[1]=false;
    }

    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
            active=!active;


        }
        if(!active){
            return;
        }

        //System.out.println("Inventory: ");
        //for(Item i : inventoryItems){
        //    System.out.println(i.getName() + "   "+ i.getCount());
        //}

    }


    public void render(Graphics g) {

        if(!active){

            uiManager.isActive(uiManager.getObjects(),false);
            return;
        }

        uiManager.isActive(uiManager.getObjects(),true);
        uiManager.Render(g);
        g.setColor(Color.white);

        if (inventoryItems.size() == 1) {
            g.drawImage(inventoryItems.get(0).getTexture(), 19, 17, 32, 32, null);
            g.drawString(String.valueOf(inventoryItems.get(0).getCount()), 19+33,17+35);
        }else if(inventoryItems.size() == 2) {
            g.drawImage(inventoryItems.get(0).getTexture(), 19, 17, 32, 32, null);
            g.drawString(String.valueOf(inventoryItems.get(0).getCount()), 19+33,17+35);
            g.drawImage(inventoryItems.get(1).getTexture(), 80, 17, 32, 32, null);
            g.drawString(String.valueOf(inventoryItems.get(1).getCount()), 80+33,17+35);
                }



    }
    //Inventory Methods

    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){

                i.setCount(i.getCount()+item.getCount());
                return;
            }

        }

        inventoryItems.add(item);
        if(currSlot == 2){
            currSlot=0;
            return;
        }
        Slots[currSlot]=true;
        currSlot++;
    }



    //GET SET
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
