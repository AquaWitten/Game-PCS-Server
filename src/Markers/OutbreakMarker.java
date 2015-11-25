package Markers;


import com.company.GameBoard;

public class OutbreakMarker {

    //create variable, shows outbreak markers position
    int outbreakCounter;

    public OutbreakMarker(){

        //give default value to variable and print status
        outbreakCounter = 0;
        System.out.println("outbreakCounter initialized, value is: " + outbreakCounter);
    }

    //method to increase the the marker, called when condition is met
    public void increaseOutbreakMarker(){

        //since the game is lost if reaches 8, there is no need to increase it beyond this value as it might cause the game to continue if the lose statement is badly constructed
        if(outbreakCounter < 8) {
            outbreakCounter++;
            System.out.println("OutbreakCounter increased to: " + outbreakCounter);
            GameBoard.gameBoard.checkLose(this);
        }
        else
            System.out.println("Outbreak marker tried to go above 8, if game not lost, find the bug");
    }

    //getter
    public int getOutbreakCounter() {
        return outbreakCounter;
    }
}
