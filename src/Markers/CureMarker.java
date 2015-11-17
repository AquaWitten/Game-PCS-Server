package Markers;


import com.company.GameBoard;

public class CureMarker {

    //has a cure been found?
    boolean hasCure;
    //are all cubes removed from the gameboard and do we have a cure?
    boolean isExterminated;

    //Initializing variables
    public CureMarker(){

        hasCure = false;
        isExterminated = false;
    }

    public void SetHasCure(){

        //Add some if statement here
        hasCure = true;
        GameBoard.gameBoard.checkWin();
    }

    public void SetIsExterminated(){

        //Add some if statement here
        isExterminated = true;
    }

    public boolean getHasCure() {
        return hasCure;
    }
}
