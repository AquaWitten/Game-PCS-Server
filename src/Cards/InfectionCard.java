package Cards;


public class InfectionCard {

    //variables created
    String name;
    String color;

    public InfectionCard(String name, String color){

        //gives this instance of card name and color of city associated
        this.name = name;
        this.color = color;

    }

    //getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}
