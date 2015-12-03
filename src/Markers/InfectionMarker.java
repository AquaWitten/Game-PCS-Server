package Markers;


import com.company.GameBoard;

public class InfectionMarker {

    //Keeps track of how many times has the marker been moved
    int infectionDegree;

    //Stores the values of the infection rate
    public int[] infectionRate;

    public InfectionMarker(){

        //max value is 7
        infectionDegree = 0;

        //When infectionRate hits 10 the lose condition is met
        infectionRate = new int[]{2,2,2,3,3,4,4};
    }

    public void increaseInfectionRate(){

        //to avoid going beyond size of the array
        if(infectionDegree < infectionRate.length - 1) {
            infectionDegree++;
        }
        else
            GameBoard.gameBoard.setLose();
    }

    /**-------------- Getters ---------------- **/

    public int getInfectionRate()
    {
        //return the current infection rate from the array based on infection degree.
        return infectionRate[infectionDegree];
    }

    public int getInfectionDegree() {
        return infectionDegree;
    }
}
