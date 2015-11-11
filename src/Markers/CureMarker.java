package Markers;


public class CureMarker {

    //type = color of of the cure, ex blue, yellow
    String type;
    //has a cure been found?
    boolean hasCure;
    //are all cubes removed from the gameboard and do we have a cure?
    boolean isExterminated;

    //Initializing variables
    public CureMarker(String type){

        this.type = type;
        hasCure = false;
        isExterminated = false;
    }

    public void SetHasCure(){

        //Add some if statement here
        hasCure = true;
    }

    public void SetIsExterminated(){

        //Add some if statement here
        isExterminated = true;
    }

    public boolean GetHasCure() {
        return hasCure;
    }
}
