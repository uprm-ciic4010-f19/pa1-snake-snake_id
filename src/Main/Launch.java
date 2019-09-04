package Main;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Launch {

    public static void main(String[] args) {
    	//I've shrunken the window just so that its more easily viewable.
        GameSetUp game = new GameSetUp("Snake", 600, 600);
        game.start();
    }
}
