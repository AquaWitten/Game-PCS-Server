package Markers;


public class CureMarker {

    String type;
    boolean hasCure;
    boolean isExterminated;

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

}
