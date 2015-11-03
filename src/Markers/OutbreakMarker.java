package Markers;


public class OutbreakMarker {

    int outbreakCounter;

    public OutbreakMarker(){

        outbreakCounter = 0;
        System.out.println("outbreakCounter initialized, value is: " + outbreakCounter);
    }

    public void IncreaseOutbreakMarker(){

        if(outbreakCounter < 8) {
            outbreakCounter++;
            System.out.println("OutbreakCounter increased to: "+outbreakCounter);
        }
        else
            System.out.println("Outbreak marker tried to go above 8, if game not lost, find the bug");
    }
}
