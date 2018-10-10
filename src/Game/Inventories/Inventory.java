package Game.Inventories;

import Game.Items.Item;
import Game.SpellCast.FireBallSpell;
import Resources.Images;
import UI.UIInventory;
import UI.UIManager;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Elemental on 1/3/2017.
 */
public class Inventory {

    private Handler handler;
    private boolean active = false;
    private UIManager uiManager;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler){

        this.handler=handler;
        inventoryItems = new ArrayList<>();

        uiManager = new UIManager(handler);

        uiManager.addObjects(new UIInventory(0,0, 329, 265, Images.inventory,() -> {
        }));
    }

    public void tick() {

        for(Item i : inventoryItems){
            if(i.getCount()==0){
                inventoryItems.remove(inventoryItems.indexOf(i));
                return;
            }
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
            active=!active;
            handler.getWorld().getEntityManager().getPlayer().getSpellGUI().setActive(false);

        }
        
        //Debugging Tool
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_X)) {
        	for(int i=0; i<Item.totalItems; i++) {
        		addItem(Item.items[i]);
        	}
        }

        if(!active){
            return;
        }

        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();



    }

    public void render(Graphics g) {

        if(!active){
            uiManager.isActive(uiManager.getObjects(),false);
            return;
        }



        uiManager.isActive(uiManager.getObjects(),true);
        uiManager.Render(g);
        g.setColor(Color.white);
        renderItems(g);


    }

    //Inventory Methods
    private void renderItems(Graphics g) {
    	int xPos=25;
    	int yPos =24;
    	for(int i=0; i<inventoryItems.size(); i++) {
    		//Reset x position and increment y position each time 5 iterations have been completed.
    		if(i==5 || i==10 || i==15) {
    			xPos = 25;
    			yPos +=60;
    			} 
    		//Only draw image if inventory not full (Maximum of 20 items)
    		if(!(i>20)) {
    		g.drawImage(inventoryItems.get(i).getTexture(), xPos, yPos, inventoryItems.get(i).getWidth(), inventoryItems.get(i).getHeight(), null);
    		g.drawString(String.valueOf(inventoryItems.get(i).getCount()), xPos+33,yPos+35);
    		//Increment x position
    		xPos+=61;
    		}
    	}

    }

    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount()+item.getCount());
                return;
            }
        }
        if(item.getId()==2){
            handler.getWorld().getEntityManager().getPlayer().getSpellGUI().addSpell(new FireBallSpell(handler));
        }
        inventoryItems.add(item);

    }

    //GET SET
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getInventoryItems(){
        return inventoryItems;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
