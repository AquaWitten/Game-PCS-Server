package Markers;

import com.company.GameBoard;

/**
 * Class to track if cure for specific color has been found
 */
public class CureMarker {

    //Global class variables
    boolean hasCure;
    boolean isExterminated;

    //Initializing variables
    public CureMarker(){

        hasCure = false;
        isExterminated = false;
    }

    /**
     * Used in the Player class when creating cure for a color
     * Checks if the game is won
     */
    public void SetHasCure(){
        hasCure = true;
        GameBoard.gameBoard.checkWin();
    }

    /**
     * Sets isExterminated to true if there are no more cubes of a color and if the cure is made
     */
    public void SetIsExterminated(){
        isExterminated = true;
    }

    /**
     * @return the state of the isExterminated boolean
     */
    public boolean getIsExterminated() {
        return isExterminated;
    }

    /**
     * @return the state of the hasCure boolean
     */
    public boolean getHasCure() {
        return hasCure;
    }
}
