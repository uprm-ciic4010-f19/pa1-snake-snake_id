package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Main.Handler;

import java.awt.*;
import java.util.LinkedList;


/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {

    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;
    public Player player2;

    protected Handler handler;


    public Boolean appleOnBoard;
    private Apple apple;
    public Boolean[][] appleLocation;


    public Boolean[][] playerLocation;
    public Boolean[][] player2Location;

    public LinkedList<Tail> body = new LinkedList<>();


    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;


    }
    public void tick(){
    	
    }
    
    public void render(Graphics g){
    	//This sets the background color
    	Color purple = new Color(150, 53, 139);
    	g.setColor(purple);
    	
    	//This actually fills the background
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());

    }
	public Apple getApple() {
		return apple;
	}
	public void setApple(Apple apple) {
		this.apple = apple;
	}
    
    
}
