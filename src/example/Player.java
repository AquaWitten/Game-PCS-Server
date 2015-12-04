package example;


import Cards.*;
import Markers.CureMarker;
import java.util.ArrayList;

public class Player {

    RoleCard role;

    int ID;
    int actionsLeft;

    City currentCity;
    ArrayList<PlayerCard> cardHand;

    Boolean isTurn;
    Boolean startOfTurn;
    Boolean turnIsDone;

    Player(RoleCard role, int ID, City startCity){

        this.role = role;
        this.ID = ID;
        this.currentCity = startCity;

        cardHand = new ArrayList<>();
        actionsLeft=0;
        isTurn=false;

        startOfTurn = true;
        turnIsDone = false;
    }

    /**
     * If its the players turn
     * Checks if a city is neighbor city and moves the player there
     * @param targetCity city the player wants to move to
     */
    public void moveToNeighbor(City targetCity) {
        if (isTurn) {
            for (int i = 0; i < currentCity.getNeighborCities().size(); i++) {

                if (targetCity.getName().toLowerCase().equals( currentCity.neighborCities.get(i).toLowerCase()) ) {
                    currentCity = targetCity;
                    actionsLeft--;
                    break;
                } else
                    System.out.println("City is not neighbor");
            }
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * move to target city if player has the target city's cityCard on hand
     * @param targetCity city the player wants to move to
     * @param paymentCard place in array, of the card that the player wishes to use as payment
     */
    public void moveToCardOnHand(City targetCity, int paymentCard){
        if (isTurn) {

                if (targetCity.name.toLowerCase().equals(cardHand.get(paymentCard).getNameOfCard().toLowerCase()) ) {
                    currentCity = targetCity;
                    discardCard(paymentCard);
                    actionsLeft--;
                }

                else
                    System.out.println("Payment card is not matching target city");
            }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city is a card on players hand
     * @param targetCity city that the player wants to move to
     * @param paymentCard place in array, of the card that the player wishes to use as payment
     */
    public void moveUsingCurrentCityCard(City targetCity, int paymentCard){
        if (isTurn) {
            if (currentCity.name.toLowerCase().equals(cardHand.get(paymentCard).getNameOfCard().toLowerCase()) ) {
                currentCity = targetCity;
                discardCard(paymentCard);
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city has a research stations and target city has a research station
     * @param targetCity the city that the player wants to move to
     */
    public void moveBetweenResearchStations(City targetCity){
        if (isTurn) {
            if (currentCity.getResearchStation() && targetCity.getResearchStation()) {
                currentCity = targetCity;
                actionsLeft--;
            }
            else
                System.out.println("Payment card is not matching current city");
        }
        else
            System.out.println("Not your turn " + ID);
    }

    /**
     * If its the players turn
     * if current city has a research station
     * checks if payment cards are on the players hand and adds them to a temporary arrayList
     * if temporary arrayList has more or the same as numbOfPayCards, then make cure for selected cureMarker
     * remove temp cards from hand
     * @param cureMarker the selected type of cureMarker that the cure is made for
     * @param payCards array of cards that are used as payment
     */
    public void CreateCure(CureMarker cureMarker, CityCard[] payCards){

        if(isTurn) {
            if (currentCity.researchStation) {
                int numbOfPayCards = 5;
                ArrayList<PlayerCard> tempCards = new ArrayList<>();

                for (int i = 0; i < cardHand.size(); i++)
                {
                    for(int j = 0; j < payCards.length; j++)
                    {
                        if (cardHand.get(i).getNameOfCard().equals( payCards[j].getNameOfCard() ))
                            tempCards.add(cardHand.get(i));
                    }
                }

                if (tempCards.size() >= numbOfPayCards)
                {
                    cureMarker.SetHasCure();
                    actionsLeft--;

                    for(int i=0; i < cardHand.size(); i++) {
                        for(int j=0; j < tempCards.size(); j++) {
                            if (cardHand.get(i).getNameOfCard().equals( payCards[j].getNameOfCard() ))
                                discardCard(i);
                        }
                    }
                }
                else
                    System.out.println("Player: "+ID+", you do not have the correct cards to make a cure");
            }
        }
    }

    /**
     * Discards card from players hand and adds it to discard pile
     * @param i place in array of the cards that is to be discarded
     */
    public void discardCard(int i)
    {
        GameBoard.gameBoard.playerDiscard.add(cardHand.get(i));
        cardHand.remove(i);
    }

    /**
     * If its the players turn
     * if currentCity does not have a research station
     * if player has cityCard matching the current city, place research station in current city
     * @param paymentCard place in array of card used for payment
     */
    public void buildResearchStation(int paymentCard){
        if(isTurn){
            if(GameBoard.gameBoard.researchStationsLeft > 0){
                if(!currentCity.researchStation)
                {
                    if (currentCity.getName().equals(cardHand.get(paymentCard).getNameOfCard()) )
                    {
                        currentCity.placeResearchStation();
                        GameBoard.gameBoard.researchStationsLeft--;
                        actionsLeft--;
                    }

                    else
                        System.out.println("current city:"+currentCity.getName()+ " does not match paymentcard");
                }
                else
                    System.out.println("city has a research station");
            }
            else
                System.out.println("No more spare research stations");
        }
        else
            System.out.println("Not your turn player " + ID);
    }

    /**
     * If its the players turn
     * if player has actions left, remove cube from current city
     * @param color color of the type of cube the is to be removed
     */
    public void removeCube(String color)
    {
        if(isTurn)
            if(actionsLeft > 0)
            {
                currentCity.removeCube(color);
                actionsLeft--;
            }
    }

    /**
     * Draw a card from the player deck
     * if the card is an Epidemic card, call activeEpidemic method in the gameboard
     * if the card is not an Epidemic card, add it to players hand
     * remove card from player deck
     * Check if there are more cards in player deck
     */
    public void drawCard(){

        //Checks if the deck is empty
        if(GameBoard.gameBoard.playerDeck.isEmpty())
        {
            GameBoard.gameBoard.setLose();
            System.out.println("Game is lost! There are no more cards in the player deck");
        }

        //If first card is an epidemic card
        else if(GameBoard.gameBoard.playerDeck.get(0).getNameOfCard().equals("epidemic")){
            GameBoard.gameBoard.activateEpidemicCard();
            GameBoard.gameBoard.playerDiscard.add(GameBoard.gameBoard.playerDeck.get(0));
            GameBoard.gameBoard.playerDeck.remove(0);
        }
        //else the card is normal player card
        else
        {
            cardHand.add(GameBoard.gameBoard.playerDeck.get(0));
            GameBoard.gameBoard.playerDeck.remove(0);
        }
    }

    /**-------------- Setters ---------------- **/

    public void setIsTurn(boolean stateOfTurn) {

        if(stateOfTurn){
            isTurn = true;
            turnIsDone = false;
            if(startOfTurn)
            {
                actionsLeft = 4;
                startOfTurn=false;
            }
        }

        else
        {
            isTurn = false;
            startOfTurn = true;
        }
    }

    public void setTurnIsDone()
    {
        turnIsDone = true;
    }

    /**
     * @param cityName name of the city that the index is wanted for
     * @return the index of the card with cityName in the players hand
     */
    public int getCardOnHandIndex(String cityName)
    {
        int returnIndex = 0;
        for(int i=0; i<cardHand.size(); i++)
        {
            if(cardHand.get(i).getNameOfCard().equals(cityName)) {
                returnIndex = i;
                break;
            }
        }
        return returnIndex;
    }

    /**-------------- Getters ---------------- **/

    /**
     * @param cityName name of the city that the CityCard is wanted for
     * @return the CityCard with a name matching the cityName Sting.
     */
    public CityCard getCityCardFromHand(String cityName)
    {
        CityCard tmpCard = null;

        for(int i=0; i<cardHand.size(); i++)
        {
            if(cardHand.get(i).getNameOfCard().equals(cityName.toLowerCase())){
                tmpCard = (CityCard) cardHand.get(i);
                break;
            }
        }
        return tmpCard;
    }

    public int getID() {
        return ID;
    }

    public String getCurrentCityName() {
        return currentCity.getName().toLowerCase();
    }

    public String getIsTurnString() {
        return Boolean.toString(isTurn);
    }

    public boolean getIsTurn(){return isTurn;}

    public int getRoleID()
    {
        return role.getRoleID();
    }

    public int getActionsLeft(){return actionsLeft;}

    public void setCurrentCity(City newCity)
    {
        currentCity = newCity;
    }

    public boolean getTurnIsDone()
    {
        return turnIsDone;
    }
}
