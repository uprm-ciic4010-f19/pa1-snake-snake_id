package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player2 {

    public int lenght2;
    public boolean justAte2;
    private Handler handler;

    public double speed = 20;
    
    public int xCoord2;
    public int yCoord2;

    public int moveCounter2;
    
    public double score;
    public double currentScore;
    
    
    public String direction2;

    public Player2(Handler handler){
        this.handler = handler;
        xCoord2 = 0;
        yCoord2 = 0;
        moveCounter2 = 0;
        direction2= "Down";
        justAte2 = false;
        lenght2= 1;
        
        currentScore=1;
       
        

    }

    public void tick(){
        moveCounter2++;
        
        if(moveCounter2>=speed) {
            checkCollisionAndMove();
            moveCounter2=0;
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
        	this.add_Tail();
        }
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && direction2 != "Down" ){
        	direction2="Up";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
            if(direction2=="Up") {} else {direction2="Down";}
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
        	if(direction2=="Right") {} else {direction2="Left";}
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
          if(direction2=="Left") {} else {direction2="Right";{}
        	
        }
       
        }
        
        	//if for example you press up, and the previous direction2 was down, do not go in that direction2 so you dont go into yourself and so forth


    }

    public void checkCollisionAndMove(){
    	
    	  	
        handler.getWorld().player2Location[xCoord2][yCoord2]=false;
        int x = xCoord2;
        int y = yCoord2;
        switch (direction2){
            case "Left":
                if(xCoord2==0){
                	this.xCoord2 = handler.getWorld().GridWidthHeightPixelCount-1;
                }else{
                    xCoord2--;
                }
                break;
            case "Right":
                if(xCoord2==handler.getWorld().GridWidthHeightPixelCount-1){ //around here checks if youre beyond the border 
                	this.xCoord2 = 0; //kill();
                }else{
                    xCoord2++;
                }
                break;
            case "Up":
                if(yCoord2==0){
                	this.yCoord2 = handler.getWorld().GridWidthHeightPixelCount-1;//kill();
                }else{
                    yCoord2--;
                }
                break;
            case "Down":
                if(yCoord2==handler.getWorld().GridWidthHeightPixelCount-1){
                	this.yCoord2 = 0;//kill();
                }else{
                    yCoord2++;
                }
                break;
        }
        handler.getWorld().player2Location[xCoord2][yCoord2]=true;
        
        //How can we check if there is a tail in front of the player2?
        
        if(handler.getWorld().appleLocation[xCoord2][yCoord2]){
            Eat();
        }
        
        for (int i = 0; i < handler.getWorld().body.size(); i++ ) {
        	if (handler.getWorld().player2.xCoord2 == handler.getWorld().body.get(i).x && handler.getWorld().player2.yCoord2 == handler.getWorld().body.get(i).y){
        	kill();
        	}
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().player2Location[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }
    }

    public void render(Graphics g,Boolean[][] playeLocation){
        
//    	@SuppressWarnings("unused") 
    	
    	//this random variable may be declared to make a random color snake. 
		
    	
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
//<<<<<<< HEAD  
              

                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
//=======
                
            	//This renders all the entities on screen (player2, Apple, and Tail)
            	
            	//If its a player2 (or tail) that's located in I,J do esto:
                if(playeLocation[i][j]){
                	//BTW looks like that two dimensional array represents if the player2 or tail is there
                	//I think you can use it to program the collission detection of the snake on itself.
                	
                	//This sets the color to the shade of green we need
                	Random r = new Random();
                	int color = r.nextInt(5);	
                	if(color == 0) {
                	g.setColor(Color.GREEN);}
                	if(color == 1) {
                    	g.setColor(Color.CYAN);}
                	if(color == 2) {
                    	g.setColor(Color.RED);}
                	if(color == 3) {
                    	g.setColor(Color.PINK);}
                	if(color == 4) {
                    	g.setColor(Color.YELLOW);}
                	
                	
                g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }
                
                //If its an apple do esto:
                if(handler.getWorld().appleLocation[i][j]){
                	
                	//Consider writing a test to see if the apple is bad, and if it is, use a darker shade of red.
                	//You can specify that by decreasing the red value (The integers below on new Color() represent
                	//Red, green, and blue respectively)
                	g.setColor(new Color(127, 0, 0));
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
    	lenght2++;
        
     	Tail tail= null;
         switch (direction2){
             case "Left":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.xCoord2!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail = new Tail(this.xCoord2+1,this.yCoord2,handler);
                     }else{
                         if(this.yCoord2!=0){
                             tail = new Tail(this.xCoord2,this.yCoord2-1,handler);
                         }else{
                             tail =new Tail(this.xCoord2,this.yCoord2+1,handler);
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler);
                     }else{
                         if(handler.getWorld().body.getLast().y!=0){
                             tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler);
                         }else{
                             tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler);

                         }
                     }

                 }
                 break;
             case "Right":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.xCoord2!=0){
                         tail=new Tail(this.xCoord2-1,this.yCoord2,handler);
                     }else{
                         if(this.yCoord2!=0){
                             tail=new Tail(this.xCoord2,this.yCoord2-1,handler);
                         }else{
                             tail=new Tail(this.xCoord2,this.yCoord2+1,handler);
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().x!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                     }else{
                         if(handler.getWorld().body.getLast().y!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler));
                         }
                     }

                 }
                 break;
             case "Up":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.yCoord2!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=(new Tail(this.xCoord2,this.yCoord2+1,handler));
                     }else{
                         if(this.xCoord2!=0){
                             tail=(new Tail(this.xCoord2-1,this.yCoord2,handler));
                         }else{
                             tail=(new Tail(this.xCoord2+1,this.yCoord2,handler));
                         }
                     }
                 }else{
                     if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler));
                     }else{
                         if(handler.getWorld().body.getLast().x!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler));
                         }
                     }

                 }
                 break;
             case "Down":
                 if( handler.getWorld().body.isEmpty()){
                     if(this.yCoord2!=0){
                         tail=(new Tail(this.xCoord2,this.yCoord2-1,handler));
                     }else{
                         if(this.xCoord2!=0){
                             tail=(new Tail(this.xCoord2-1,this.yCoord2,handler));
                         }else{
                             tail=(new Tail(this.xCoord2+1,this.yCoord2,handler));
                         } System.out.println("Tu biscochito");
                     }
                 }else{
                     if(handler.getWorld().body.getLast().y!=0){
                         tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler));
                     }else{
                         if(handler.getWorld().body.getLast().x!=0){
                             tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                         }else{
                             tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler));
                         }
                     }

                 }
                 break;
         }
         handler.getWorld().body.addLast(tail);
         handler.getWorld().player2Location[tail.x][tail.y] = true;
     }
    

    public void Eat(){
        
    	lenght2++;
    	
    	speed += -0.6;
    	
       score += Math.sqrt((2*currentScore) + 1);
       System.out.println(score);
    	Tail tail= null;
        handler.getWorld().appleLocation[xCoord2][yCoord2]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction2){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord2!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord2+1,this.yCoord2,handler);
                    }else{
                        if(this.yCoord2!=0){
                            tail = new Tail(this.xCoord2,this.yCoord2-1,handler);
                        }else{
                            tail =new Tail(this.xCoord2,this.yCoord2+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord2!=0){
                        tail=new Tail(this.xCoord2-1,this.yCoord2,handler);
                    }else{
                        if(this.yCoord2!=0){
                            tail=new Tail(this.xCoord2,this.yCoord2-1,handler);
                        }else{
                            tail=new Tail(this.xCoord2,this.yCoord2+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord2!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord2,this.yCoord2+1,handler));
                    }else{
                        if(this.xCoord2!=0){
                            tail=(new Tail(this.xCoord2-1,this.yCoord2,handler));
                        }else{
                            tail=(new Tail(this.xCoord2+1,this.yCoord2,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord2!=0){
                        tail=(new Tail(this.xCoord2,this.yCoord2-1,handler));
                    }else{
                        if(this.xCoord2!=0){
                            tail=(new Tail(this.xCoord2-1,this.yCoord2,handler));
                        }else{
                            tail=(new Tail(this.xCoord2+1,this.yCoord2,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord2-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord2,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord2,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().player2Location[tail.x][tail.y] = true;
    }


    
   
    
    public void kill(){
    	
    	
    	lenght2 = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().player2Location[i][j]=false;

            }
        }
        
        handler.getMouseManager().setUimanager(null);
        handler.getGame().reStart();
        State.setState(handler.getGame().GameOverState);
    	
    }

    public boolean isjustAte2() {
        return justAte2;
    }

    public void setjustAte2(boolean justAte2) {
        this.justAte2 = justAte2;
    }
}
