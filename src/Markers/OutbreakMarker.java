package Markers;


public class OutbreakMarker {

    int outbreakCounter;

    public OutbreakMarker(){

        outbreakCounter = 0;
        System.out.println("outbreakCounter initialized, value is: " + outbreakCounter);
    }

    public void IncreaseOutbreakMarker(){

        //since the game is lost if reaches 8, there is no need to increase it beyond this value as it might cause the game to continue if the lose statement is badly constructed
        if(outbreakCounter < 8) {
            outbreakCounter++;
            System.out.println("OutbreakCounter increased to: "+outbreakCounter);
        }
        else
            System.out.println("Outbreak marker tried to go above 8, if game not lost, find the bug");
    }

    public int getOutbreakCounter() {
        return outbreakCounter;
    }
}
