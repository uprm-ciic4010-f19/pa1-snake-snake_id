package Game.Entities.Static;

import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    @SuppressWarnings("unused")
	private Handler handler;
    private boolean Good=true;
    //unsure as to why handler is here and is unused.

    
    ///Para verificar que la manzana esta pudrida todavia o no,  crea una variable int "health" mientras el player se mueve, el health baja, cuando llegue a un cierto punto, cambia 
    /// el booleano de is good a false, pudriendo la manzana, entonces si la manzana esta podrida pierdes puntos basado en la ecuacion basada en las specs 
    
    public int xCoord;
    public int yCoord;
   
    
    public Apple(Handler handler,int x, int y){
        
      
       
       this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
        
        //I'm assuming that here is where the isGood() property goes.
    }
    
    public boolean isGood() {return Good;}
    public void SetGood(boolean Goodness) {Good=Goodness;}
    
}
