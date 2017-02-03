package Main;

import Game.GameStates.GameState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Inputs.KeyManager;
import Inputs.MouseManager;
import Resources.GameCamera;
import Resources.Images;
import Display.DisplayScreen;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Elemental on 12/10/2016.
 */
public class Game implements Runnable {
    private DisplayScreen display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;
    public State pauseState;


    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height){

        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init(){
        display = new DisplayScreen(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Images img = new Images();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        pauseState = new PauseState(handler);

        State.setState(menuState);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    private void tick(){
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        if(State.getState() != null)
            State.getState().render(g);


        //End Drawing!
        bs.show();
        g.dispose();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
