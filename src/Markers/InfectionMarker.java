package Markers;


public class InfectionMarker {

    //Keeps track of how many times has the marker been moved
    int infectionDegree;

    //Stores the values of the infection rate
    public int[] infectionRate;

    public InfectionMarker(){

        //max value is 6
        infectionDegree = 0;

        infectionRate = new int[]{2,2,2,3,3,4,4};
    }

    public void IncreaseInfectionRate(){

        //to avoid going beyond size of the array
        if(infectionDegree < infectionRate.length - 1)
            infectionDegree++;
    }

    public int GetInfectionRate(){
        //return the current infection rate from the array based on infection degree.
        return infectionRate[infectionDegree];
    }
}
