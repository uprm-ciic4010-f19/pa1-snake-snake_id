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
    protected Apple apple;
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
    
   public void render1(Graphics g) {
   	Graphics2D score = (Graphics2D) g;
   	String scoreNumber = Double.toString(player.score);
   	
   	for (int i = 0 ; i <= 800; i = i + GridPixelsize) {
   		
   		
    		g.setColor(new Color(128,0,128));
    		g.drawLine(0, i, handler.getWidth(), i);
    		g.drawLine(i, 0, i, handler.getHeight());
    		score.setColor(Color.BLACK);
    		score.setFont ( new Font ("Chalkboard" , Font.PLAIN, 30));
    		score.drawString( "Score: " + scoreNumber, 600, 50);
    		
    		
    	}
    }

    public void render(Graphics g){
    	//This sets the background color
    	Color purple = new Color(224, 191, 238);
    	g.setColor(purple);
    	
    	//This actually fills the background
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());

        //rendergrid(g);
    }
    
    /**
     * Render the grid, comment me out if you don't need me
     * @param g
     */
    public void rendergrid(Graphics g) {
    	for (int i = 0; i <= 800; i = i + GridPixelsize) {

            g.setColor(Color.black);
            g.drawLine(0, i, handler.getWidth() , i);
            g.drawLine(i,0,i,handler.getHeight());

        }

    }

}
