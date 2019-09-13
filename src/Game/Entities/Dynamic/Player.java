package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

    public int lenght;
    public boolean justAte;
    private Handler handler;

    public double speed = 20;
    
    public int xCoord;
    public int yCoord;

    public int moveCounter;
    public int AppleHealth=1800;
    public Color SnakeColor=Color.red;
    
    public double score;
    public double currentScore;
    
    
    public String direction;
	public int yCoord2;
	public int xCoord2;

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;
        
        currentScore=1;
       
        

    }

    public void tick(){
        moveCounter++;
        
        
        switch (AppleHealth) {
		case 0:
			//Set the apple to bad
			handler.getWorld().getApple().SetGood(false);
			System.out.println("THE APPLE HAS GONE BAD O HNO");

			//subtract
			AppleHealth--;
			break;
		case -1:
			//do nothing
			break;
		default:
			AppleHealth--;
			System.out.println("APPLE HEALTH: " + AppleHealth);
			break;
		}
        
        if(moveCounter>=speed) {
            checkCollisionAndMove();
            moveCounter=0;
        }
      
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
        	speed = speed + 0.6;
        	System.out.println(speed);
        }
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
        	speed = speed - 0.6;
        	System.out.println(speed);
        }
        
       
        
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
        	State.setState(handler.getGame().pauseState);
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
        	try {
        		this.add_Tail();	
			} catch (Exception e) {
			}
        	
        	
        }
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && direction != "Down" ){
        	direction="Up";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
            if(direction=="Up") {} else {direction="Down";}
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
        	if(direction=="Right") {} else {direction="Left";}
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
          if(direction=="Left") {} else {direction="Right";{}
        	
        }
       
        }
        
        	//if for example you press up, and the previous direction was down, do not go in that direction so you dont go into yourself and so forth


    }

    public void checkCollisionAndMove(){
    	
    	  	
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        switch (direction){
            case "Left":
                if(xCoord==0){
                	this.xCoord = handler.getWorld().GridWidthHeightPixelCount-1;
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){ //around here checks if youre beyond the border 
                	this.xCoord = 0; //kill();
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                	this.yCoord = handler.getWorld().GridWidthHeightPixelCount-1;//kill();
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	this.yCoord = 0;//kill();
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;
        
        //How can we check if there is a tail in front of the player?
        
        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }
        
        for (int i = 0; i < handler.getWorld().body.size(); i++ ) {
        	if (handler.getWorld().player.xCoord == handler.getWorld().body.get(i).x && handler.getWorld().player.yCoord == handler.getWorld().body.get(i).y){
        	kill();
        	}
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }
    }

    public void render(Graphics g,Boolean[][] playeLocation){
        
//    	@SuppressWarnings("unused") 
    	
    	//this random variable may be declared to make a random color snake. 
		
    	
    	Graphics2D scoreDisplay = (Graphics2D) g;
       	String scoreNumber = Double.toString(score);
       	
       	scoreDisplay.setFont ( new Font ("Chalkboard" , Font.PLAIN, 15));
       	scoreDisplay.setColor(Color.BLACK);
       	scoreDisplay.drawString( "Score: " + scoreNumber, 0, 15);		
       	
    	
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
//<<<<<<< HEAD  
              

                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
//=======
                
            	//This renders all the entities on screen (Player, Apple, and Tail)
            	
            	//If its a player (or tail) that's located in I,J do esto:
                if(playeLocation[i][j]){
                	//BTW looks like that two dimensional array represents if the player or tail is there
                	//I think you can use it to program the collission detection of the snake on itself.
                	
                	//This sets the color to the shade of green we need
//                	ColorCounter++;
//                	if (ColorCounter==100) {
                    	Random r = new Random();
                    	switch (r.nextInt(5)) {
    					case 0:
    						SnakeColor=Color.GREEN;
    						break;
    					case 1:
    						SnakeColor=Color.CYAN;
    						break;
    					case 2:
    						SnakeColor=Color.RED;
    						break;
    					case 3:
    						SnakeColor=Color.PINK;
    						break;
    					case 4:
    						SnakeColor=Color.YELLOW;
    						break;

    					default:
    						SnakeColor=Color.ORANGE;
    						break;}
//                    	ColorCounter=0;
//                	}                	
                	
                g.setColor(SnakeColor);
                	
                g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }
                
                //If its an apple do esto:
                if(handler.getWorld().appleLocation[i][j]){
                	
                	if (handler.getWorld().getApple().isGood()){
                		//The color if its good
                		g.setColor(new Color(127, 0, 0));
                	}
                	else
                	{
//                		the color if its bad
                		g.setColor(new Color(0, 0, 0));
                	}
                	
//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa1-snake-snake_id.git
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }
                    
                
                

                }
            }
        }
    }
    public void add_Tail() {
    	lenght++;
        
     	Tail tail= null;
         switch (direction){
             case "Left":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail = new Tail(this.xCoord+1,this.yCoord,handler);
                     }else{
                         if(this.yCoord!=0){
                             tail = new Tail(this.xCoord,this.yCoord-1,handler);
                         }else{
                             tail =new Tail(this.xCoord,this.yCoord+1,handler);
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                     }else{
                         if(handler.getWorld().body.getLast().y!=0){
                             tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                         }else{
                             tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                         }
                     }

                 }
                 break;
             case "Right":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.xCoord!=0){
                         tail=new Tail(this.xCoord-1,this.yCoord,handler);
                     }else{
                         if(this.yCoord!=0){
                             tail=new Tail(this.xCoord,this.yCoord-1,handler);
                         }else{
                             tail=new Tail(this.xCoord,this.yCoord+1,handler);
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().x!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                     }else{
                         if(handler.getWorld().body.getLast().y!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                         }
                     }

                 }
                 break;
             case "Up":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                     }else{
                         if(this.xCoord!=0){
                             tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                         }else{
                             tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                     }else{
                         if(handler.getWorld().body.getLast().x!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                         }
                     }

                 }
                 break;
             case "Down":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.yCoord!=0){
                         tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                     }else{
                         if(this.xCoord!=0){
                             tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                         }else{
                             tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                         } System.out.println("Tu biscochito");
                     }
                 }else{
                     if(handler.getWorld().body.getLast().y!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                     }else{
                         if(handler.getWorld().body.getLast().x!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                         }
                     }

                 }
                 break;
         }
         handler.getWorld().body.addLast(tail);
         handler.getWorld().playerLocation[tail.x][tail.y] = true;
     }
    

    public void Eat(){
        
    	lenght++;
    	
    	speed += -0.6;
    	
    	if (handler.getWorld().getApple().isGood()) {
    		score += Math.sqrt((2*currentScore) + 1);
    	}
    	else {
    		score -= Math.sqrt((2*currentScore) + 1);
    	}
    	AppleHealth=1800;
       
       System.out.println(score);
    	Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }


    
   
    
    public void kill(){
    	
    	
    	lenght = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
        
        handler.getMouseManager().setUimanager(null);
        handler.getGame().reStart();
        State.setState(handler.getGame().GameOverState);
    	
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }
}
