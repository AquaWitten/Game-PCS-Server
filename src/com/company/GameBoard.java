package com.company;

import Cards.CityCard;
import Cards.EpidemicCard;
import Cards.InfectionCard;
import Cards.PlayerCard;
import Markers.CureMarker;
import Markers.InfectionMarker;
import Markers.OutbreakMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameBoard {

    //Create all relevant variables
    public ArrayList<City> allCities;
    public ArrayList<Player> players;
    public ArrayList<PlayerCard> playerDeck;
    public ArrayList<PlayerCard> playerDiscard;
    public ArrayList<InfectionCard> infectionDeck;
    public ArrayList<InfectionCard> infectionDiscard;

    public OutbreakMarker outbreakMarker;
    public InfectionMarker infectionMarker;

    CureMarker blueCureMarker;
    CureMarker yellowCureMarker;
    CureMarker blackCureMarker;
    CureMarker redCureMarker;

    Message gameBoardContent;

    int researchStationsLeft;
    int blueCubesLeft;
    int yellowCubesLeft;
    int blackCubesLeft;
    int redCubesLeft;

    int playerWithIDsTurn;

    boolean gameWon;
    boolean gameLost;

    //Creates a static instance and makes it possible to refer to the variable through: GameBoard.gameBoard
    public static GameBoard gameBoard;


    GameBoard(){

        //Instantiate different variables
        outbreakMarker   = new OutbreakMarker();
        infectionMarker  = new InfectionMarker();

        blueCureMarker   = new CureMarker();
        yellowCureMarker = new CureMarker();
        blackCureMarker  = new CureMarker();
        redCureMarker    = new CureMarker();

        allCities        = new ArrayList<>();
        players          = new ArrayList<>();
        playerDeck       = new ArrayList<>();
        playerDiscard    = new ArrayList<>();
        infectionDeck    = new ArrayList<>();
        infectionDiscard = new ArrayList<>();

        gameBoardContent = new Message();

        researchStationsLeft = 6;
        blueCubesLeft = 24;
        yellowCubesLeft = 24;
        blackCubesLeft = 24;
        redCubesLeft = 24;
        instantiateCities(); //run the method creating cities and adding them to the allCities array
        instantiateDecks(); //run method creating cards and placing them in the decks

        GameBoard.gameBoard = this;

    }

    //Activate upon the draw of an epidemic card
    public void activateEpidemicCard(){
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            GameBoard.gameBoard.allCities.get(i).resetRecentOutbreak();
        }

        //Increase
        GameBoard.gameBoard.infectionMarker.increaseInfectionRate();

        //Infect
        int lastCardNumber = GameBoard.gameBoard.infectionDeck.size() - 1;
        String targetName = GameBoard.gameBoard.infectionDeck.get(lastCardNumber).getName();
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            if(targetName.equals(GameBoard.gameBoard.allCities.get(i).getName())){
                GameBoard.gameBoard.allCities.get(i).addCube(GameBoard.gameBoard.infectionDeck.get(lastCardNumber).getColor(), 3);
                break;
            }
        }
        GameBoard.gameBoard.infectionDiscard.add(GameBoard.gameBoard.infectionDeck.get(lastCardNumber));
        GameBoard.gameBoard.infectionDeck.remove(lastCardNumber);

        //Intensify
        Collections.shuffle(GameBoard.gameBoard.infectionDiscard);
        for(int i = 0; i < GameBoard.gameBoard.infectionDiscard.size(); i++){
            GameBoard.gameBoard.infectionDeck.add(0,GameBoard.gameBoard.infectionDiscard.get(0));
            GameBoard.gameBoard.infectionDiscard.remove(0);
        }
    }

    //Activate upon the draw of an infection card
    public void drawInfectionCard(int cubeAmount){
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            GameBoard.gameBoard.allCities.get(i).resetRecentOutbreak();
        }

        //DO INFECTION
        String target = GameBoard.gameBoard.infectionDeck.get(0).getName();
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            if(target.equals(GameBoard.gameBoard.allCities.get(i).getName())){
                GameBoard.gameBoard.allCities.get(i).addCube(GameBoard.gameBoard.infectionDeck.get(0).getColor(), cubeAmount);
                break;
            }
        }

        //Move top card in the deck to the discard pile
        GameBoard.gameBoard.infectionDiscard.add(GameBoard.gameBoard.infectionDeck.get(0));
        GameBoard.gameBoard.infectionDeck.remove(0);

    }


    public void checkWin(){

        //Check win condition
        if(blueCureMarker.getHasCure() && yellowCureMarker.getHasCure() && blackCureMarker.getHasCure() && redCureMarker.getHasCure()){
            System.out.println("Game is won! Congratulations");
            gameWon = true;
        }

    }

    public void checkLose() {

        //Check lose condition with cubes
        if (blueCubesLeft <= 0 || yellowCubesLeft <= 0 || blackCubesLeft <= 0 || redCubesLeft <= 0) {
            System.out.println("Game is lost! You ran out of disease cubes");
            this.gameLost = true;
        }
    }

    public void checkLose(OutbreakMarker outbreaks) {

        //Check lose condition with outbreakMarker
        if (outbreaks.getOutbreakCounter() >= 8) {
            System.out.println("Game is lost! There have been too many outbreaks");
            gameLost = true;
        }
    }

    public void checkLose(InfectionMarker infections) {

        //Check lose condition with infectionMarker
        if (infections.getInfectionRate() >= 10) {
            System.out.println("Game is lost! The infection rate of the disease is too high");
            gameLost = true;
        }
    }

    public void checkLose(ArrayList<PlayerCard> playerDeckCardsLeft){

        //Check lose condition with player deck
        if(playerDeckCardsLeft.isEmpty()){
            System.out.println("Game is lost! There are no more cards in the player deck");
            gameLost = true;
        }
    }

    public void setLose()
    {
        gameLost = true;
    }

    public void instantiateDecks(){ //Method used to instantiate the two decks of cards
        //PlayerDeck without epidemic cards
        for(int i = 0; i < allCities.size(); i++){
            CityCard temp = new CityCard(allCities.get(i).getName(), allCities.get(i).getColor());
            playerDeck.add(temp);
        }
        Collections.shuffle(playerDeck);
        //Infection deck
        for(int i = 0; i < allCities.size(); i++){
            InfectionCard temp = new InfectionCard(allCities.get(i).getName(), allCities.get(i).getColor());
            infectionDeck.add(temp);
        }
        Collections.shuffle(infectionDeck);
    }

    //Returns a city variable with the inserted name
    public City getCity(String city) {
        City returnCity = null;
        for (int i = 0; i < allCities.size(); i++) {
            if (allCities.get(i).getName().equals(city)) {
                returnCity = allCities.get(i);
                break;
            }
        }
        return returnCity;
    }

    public void increasePlayerWithIDsTurn()
    {
        if(this.playerWithIDsTurn < 3)
            this.playerWithIDsTurn += 1;
        else
            this.playerWithIDsTurn = 0;
    }

    public int getPlayerWithIDsTurn() {
        return playerWithIDsTurn;
    }

    public CureMarker getBlueCureMarker() {
        return blueCureMarker;
    }

    public CureMarker getYellowCureMarker() {
        return yellowCureMarker;
    }

    public CureMarker getBlackCureMarker() {
        return blackCureMarker;
    }

    public CureMarker getRedCureMarker() {
        return redCureMarker;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public void instantiateCities(){ //Method used to instantiate all the cities and adding them to the allCities array
        //All blue cities
        City sanFrancisco = new City("sanfrancisco", "blue",new ArrayList<>(Arrays.asList("chicago", "losangeles", "tokyo", "manila")));
        allCities.add(sanFrancisco);
        City chicago = new City("chicago", "blue",new ArrayList<>(Arrays.asList("sanfrancisco", "losangeles", "montreal", "atlanta", "mexicocity")));
        allCities.add(chicago);
        City montreal = new City("montreal", "blue",new ArrayList<>(Arrays.asList("chicago", "newyork", "washington")));
        allCities.add(montreal);
        City newYork = new City("newyork", "blue",new ArrayList<>(Arrays.asList("montreal", "london", "washington", "madrid")));
        allCities.add(newYork);
        City atlanta = new City("atlanta", "blue",new ArrayList<>(Arrays.asList("chicago", "miami", "washington")));
        allCities.add(atlanta);
        City washington = new City("washington", "blue",new ArrayList<>(Arrays.asList("montreal", "newyork", "miami", "atlanta")));
        allCities.add(washington);
        City london = new City("london", "blue",new ArrayList<>(Arrays.asList("madrid", "newyork", "essen", "paris")));
        allCities.add(london);
        City madrid = new City("madrid", "blue",new ArrayList<>(Arrays.asList("london", "newyork", "paris", "algiers")));
        allCities.add(madrid);
        City paris = new City("paris", "blue",new ArrayList<>(Arrays.asList("london", "essen", "madrid", "algiers", "milan")));
        allCities.add(paris);
        City essen = new City("essen", "blue",new ArrayList<>(Arrays.asList("london", "paris", "stpetersburg", "milan")));
        allCities.add(essen);
        City stPetersburg = new City("stpetersburg", "blue",new ArrayList<>(Arrays.asList("essen", "istanbul", "moscow")));
        allCities.add(stPetersburg);
        City milan = new City("milan", "blue",new ArrayList<>(Arrays.asList("essen", "paris", "istanbul")));
        allCities.add(milan);
        //All yellow cities
        City losAngeles = new City("losangeles", "yellow",new ArrayList<>(Arrays.asList("sydney", "sanfrancisco", "chicago", "mexicocity")));
        allCities.add(losAngeles);
        City mexicoCity = new City("mexicocity", "yellow",new ArrayList<>(Arrays.asList("losangeles", "chicago", "miami", "bogota", "lima")));
        allCities.add(mexicoCity);
        City miami = new City("miami", "yellow",new ArrayList<>(Arrays.asList("atlanta", "washington", "mexicocity", "bogota")));
        allCities.add(miami);
        City bogota = new City("bogota", "yellow",new ArrayList<>(Arrays.asList("miami", "lima", "mexicocity", "buenosaires", "saopaulo")));
        allCities.add(bogota);
        City lima = new City("lima", "yellow",new ArrayList<>(Arrays.asList("bogota", "santiago", "mexicocity")));
        allCities.add(lima);
        City santiago = new City("santiago", "yellow",new ArrayList<>(Arrays.asList("lima")));
        allCities.add(santiago);
        City buenosAires = new City("buenosaires", "yellow",new ArrayList<>(Arrays.asList("bogota", "saopaulo")));
        allCities.add(buenosAires);
        City saoPaulo = new City("saopaulo", "yellow",new ArrayList<>(Arrays.asList("bogota", "buenosaires", "madrid", "lagos")));
        allCities.add(saoPaulo);
        City lagos = new City("lagos", "yellow",new ArrayList<>(Arrays.asList("saopaulo", "kinshasa", "khartoum")));
        allCities.add(lagos);
        City kinshasa = new City("kinshasa", "yellow",new ArrayList<>(Arrays.asList("lagos", "johannesburg", "khartoum")));
        allCities.add(kinshasa);
        City khartoum = new City("khartoum", "yellow",new ArrayList<>(Arrays.asList("lagos", "johannesburg", "kinshasa", "cairo")));
        allCities.add(khartoum);
        City johannesburg = new City("johannesburg", "yellow",new ArrayList<>(Arrays.asList("khartoum", "kinshasa")));
        allCities.add(johannesburg);
        //All black cities
        City algiers = new City("algiers", "black",new ArrayList<>(Arrays.asList("madrid", "paris", "istanbul", "cairo")));
        allCities.add(algiers);
        City istanbul = new City("istanbul", "black",new ArrayList<>(Arrays.asList("milan", "algiers", "stpetersburg", "moscow", "baghdad", "cairo")));
        allCities.add(istanbul);
        City cairo = new City("cairo", "black",new ArrayList<>(Arrays.asList("algiers", "istanbul", "baghdad", "riyadh")));
        allCities.add(cairo);
        City riyadh = new City("riyadh", "black",new ArrayList<>(Arrays.asList("cairo", "karachi", "baghdad")));
        allCities.add(riyadh);
        City baghdad = new City("baghdad", "black",new ArrayList<>(Arrays.asList("cairo", "karachi", "riyadh", "istanbul", "tehran")));
        allCities.add(baghdad);
        City moscow = new City("moscow", "black",new ArrayList<>(Arrays.asList("stpetersburg", "istanbul", "tehran")));
        allCities.add(moscow);
        City tehran = new City("tehran", "black",new ArrayList<>(Arrays.asList("moscow", "baghdad", "karachi", "delhi")));
        allCities.add(tehran);
        City karachi = new City("karachi", "black",new ArrayList<>(Arrays.asList("tehran", "baghdad", "riyadh", "mumbai", "delhi")));
        allCities.add(karachi);
        City delhi = new City("delhi", "black",new ArrayList<>(Arrays.asList("tehran", "karachi", "kolkata", "mumbai", "chennai")));
        allCities.add(delhi);
        City mumbai = new City("mumbai", "black",new ArrayList<>(Arrays.asList("karachi", "delhi", "chennai")));
        allCities.add(mumbai);
        City kolkata = new City("kolkata", "black",new ArrayList<>(Arrays.asList("hongkong", "bangkok", "delhi", "chennai")));
        allCities.add(kolkata);
        City chennai = new City("chennai", "black",new ArrayList<>(Arrays.asList("kolkata", "bangkok", "jakarta", "mumbai", "delhi")));
        allCities.add(chennai);
        //All red cities
        City bangkok = new City("bangkok", "red",new ArrayList<>(Arrays.asList("kolkata", "hongkong", "hochiminhcity", "jakarta", "chennai")));
        allCities.add(bangkok);
        City jakarta = new City("jakarta", "red",new ArrayList<>(Arrays.asList("chennai", "bangkok", "hochiminhcity", "sydney")));
        allCities.add(jakarta);
        City sydney = new City("sydney", "red",new ArrayList<>(Arrays.asList("jakarta", "manila", "losangeles")));
        allCities.add(sydney);
        City manila = new City("manila", "red",new ArrayList<>(Arrays.asList("sydney", "hochiminhcity", "hongkong", "taipei", "sanfrancisco")));
        allCities.add(manila);
        City hoChiMinhCity = new City("hochiminhcity", "red",new ArrayList<>(Arrays.asList("jakarta", "bangkok", "hongkong", "manila")));
        allCities.add(hoChiMinhCity);
        City hongKong = new City("hongkong", "red",new ArrayList<>(Arrays.asList("hochiminhcity", "bangkok", "kolkata", "shanghai", "taipei", "manila")));
        allCities.add(hongKong);
        City taipei = new City("taipei", "red",new ArrayList<>(Arrays.asList("shanghai", "osaka", "hongkong", "manila")));
        allCities.add(taipei);
        City shanghai = new City("shanghai", "red",new ArrayList<>(Arrays.asList("taipei", "beijing", "hongkong", "seoul", "tokyo")));
        allCities.add(shanghai);
        City beijing = new City("beijing", "red",new ArrayList<>(Arrays.asList("shanghai", "seoul")));
        allCities.add(beijing);
        City seoul = new City("seoul", "red",new ArrayList<>(Arrays.asList("shanghai", "beijing", "tokyo")));
        allCities.add(seoul);
        City tokyo = new City("tokyo", "red",new ArrayList<>(Arrays.asList("shanghai", "seoul", "osaka")));
        allCities.add(tokyo);
        City osaka = new City("osaka", "red",new ArrayList<>(Arrays.asList("taipei", "tokyo")));
        allCities.add(osaka);

    }

    public void setupPhase(){

        //Place research station on Atlanta
        for(int i = 0; i < GameBoard.gameBoard.allCities.size(); i++){
            if(GameBoard.gameBoard.allCities.get(i).getName().equals("atlanta")){
                GameBoard.gameBoard.allCities.get(i).placeResearchStation();
               return;
            }
        }

        //Initial infection
        GameBoard.gameBoard.drawInfectionCard(3);
        GameBoard.gameBoard.drawInfectionCard(3);
        GameBoard.gameBoard.drawInfectionCard(3);
        GameBoard.gameBoard.drawInfectionCard(2);
        GameBoard.gameBoard.drawInfectionCard(2);
        GameBoard.gameBoard.drawInfectionCard(2);
        GameBoard.gameBoard.drawInfectionCard(1);
        GameBoard.gameBoard.drawInfectionCard(1);
        GameBoard.gameBoard.drawInfectionCard(1);

        //Players draw cards
        for(int i = 0; i < GameBoard.gameBoard.players.size(); i++) {
            GameBoard.gameBoard.players.get(i).drawCard();
            GameBoard.gameBoard.players.get(i).drawCard();
        }

        //Add epidemic cards and shuffle player deck
        for(int i = 0; i < 5; i++){
            EpidemicCard temp = new EpidemicCard();
            GameBoard.gameBoard.playerDeck.add(temp);
            Collections.shuffle(playerDeck);
        }

    }
//-----------------------------Setter methods for Message class------------------------------------

    /**
     * For player1, player2, player3 and player4
     * array space;
     * 0: String ID of player
     * 1: String describing if it is the players turn
     * 2: String name of the city the player is currently on
     * 3-?: for-loop adds the name of the cards of the players hand
     */
    public void messageSetPlayer1() {
        gameBoardContent.player1 = new ArrayList<>();

        gameBoardContent.player1.add(Integer.toString(GameBoard.gameBoard.players.get(0).getID()));
        gameBoardContent.player1.add(GameBoard.gameBoard.players.get(0).getIsTurnString());
        gameBoardContent.player1.add(GameBoard.gameBoard.players.get(0).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(0).cardHand.size(); i++)
        {
            gameBoardContent.player1.add(GameBoard.gameBoard.players.get(0).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void messageSetPlayer2() {
        gameBoardContent.player2 = new ArrayList<>();

        gameBoardContent.player2.add(Integer.toString(GameBoard.gameBoard.players.get(1).getID()));
        gameBoardContent.player2.add(GameBoard.gameBoard.players.get(1).getIsTurnString());
        gameBoardContent.player2.add(GameBoard.gameBoard.players.get(1).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(1).cardHand.size(); i++)
        {
            gameBoardContent.player2.add(GameBoard.gameBoard.players.get(1).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void messageSetPlayer3() {
        gameBoardContent.player3 = new ArrayList<>();

        gameBoardContent.player3.add(Integer.toString(GameBoard.gameBoard.players.get(2).getID()));
        gameBoardContent.player3.add(GameBoard.gameBoard.players.get(2).getIsTurnString());
        gameBoardContent.player3.add(GameBoard.gameBoard.players.get(2).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(2).cardHand.size(); i++)
        {
            gameBoardContent.player3.add(GameBoard.gameBoard.players.get(2).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    public void messageSetPlayer4() {
        gameBoardContent.player4 = new ArrayList<>();

        gameBoardContent.player4.add(Integer.toString(GameBoard.gameBoard.players.get(3).getID()));
        gameBoardContent.player4.add(GameBoard.gameBoard.players.get(3).getIsTurnString());
        gameBoardContent.player4.add(GameBoard.gameBoard.players.get(3).getCurrentCityName());

        for(int i = 0; i < GameBoard.gameBoard.players.get(3).cardHand.size(); i++)
        {
            gameBoardContent.player4.add(GameBoard.gameBoard.players.get(3).cardHand.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * Each city on the board is added to the string array with, containing important values
     * size of array [6][48]
     * [6] is the number of different kinds of information
     * [48] si the number of different cities on the gameboard
     * [0][i]: string name of the city
     * [1][i]: string describing if the city has a research station
     * [2][i]: string value of the amount of BLUE cubes on the city
     * [3][i]: string value of the amount of YELLOW cubes on the city
     * [4][i]: string value of the amount of BLACK cubes on the city
     * [5][i]: string value of the amount of RED cubes on the city
     */
    public void messageSetCities() {
        gameBoardContent.cities = new String[6][48];

        for(int i=0; i < GameBoard.gameBoard.allCities.size(); i++)
        {
            gameBoardContent.cities[0][i] = GameBoard.gameBoard.allCities.get(i).getName().toLowerCase();
            gameBoardContent.cities[1][i] = Boolean.toString(GameBoard.gameBoard.allCities.get(i).researchStation);
            gameBoardContent.cities[2][i] = Integer.toString(GameBoard.gameBoard.allCities.get(i).getBlueCubes());
            gameBoardContent.cities[3][i] = Integer.toString(GameBoard.gameBoard.allCities.get(i).getYellowCubes());
            gameBoardContent.cities[4][i] = Integer.toString(GameBoard.gameBoard.allCities.get(i).getBlackCubes());
            gameBoardContent.cities[5][i] = Integer.toString(GameBoard.gameBoard.allCities.get(i).getRedCubes());
        }
    }

    /**
     * For loop adds the name of each card left in the playerDeck to the array
     */
    public void messageSetPlayerDeck() {
        gameBoardContent.playerDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDeck.size(); i++)
        {
            gameBoardContent.playerDeck.add(GameBoard.gameBoard.playerDeck.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * For loop adds the name of each card in the playerDiscard to the array
     */
    public void messageSetPlayerDiscard() {
        gameBoardContent.playerDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.playerDiscard.size(); i++)
        {
            gameBoardContent.playerDiscard.add(GameBoard.gameBoard.playerDiscard.get(i).getNameOfCard().toLowerCase());
        }
    }

    /**
     * For loop adds the name of each card left in the infectionDeck to the array
     */
    public void messageSetInfectionDeck() {
        gameBoardContent.infectionDeck = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDeck.size(); i++)
            gameBoardContent.infectionDeck.add(GameBoard.gameBoard.infectionDeck.get(i).getName().toLowerCase());
    }

    /**
     * For loop adds the name of each card in the infectionDiscard to the array
     */
    public void messageSetInfectionDiscard() {
        gameBoardContent.infectionDiscard = new ArrayList<>();

        for(int i = 0; i < GameBoard.gameBoard.infectionDiscard.size(); i++)
            gameBoardContent.infectionDiscard.add(GameBoard.gameBoard.infectionDiscard.get(i).getName());
    }

    /**
     * copies the amount of blue cubes on the city
     */
    public void messageSetBlueCubesLeft() {
        gameBoardContent.blueCubesLeft = GameBoard.gameBoard.blueCubesLeft;
    }

    /**
     * copies the amount of red cubes on the city
     */
    public void messageSetRedCubesLeft() {
        redCubesLeft = GameBoard.gameBoard.redCubesLeft;
    }

    /**
     * copies the amount of yellow cubes on the city
     */
    public void messageSetYellowCubesLeft() {
        gameBoardContent.yellowCubesLeft = GameBoard.gameBoard.yellowCubesLeft;
    }

    /**
     * copies the amount of black cubes on the city
     */
    public void messageSetBlackCubesLeft() {
        gameBoardContent.blackCubesLeft = GameBoard.gameBoard.blackCubesLeft;
    }

    /**
     * copies the value of the outbreak marker
     */
    public void messageSetOutbreakMarker() {
        gameBoardContent.outbreakMarker = GameBoard.gameBoard.outbreakMarker.getOutbreakCounter();
    }

    /**
     * copies the amount of times the infection marker has been moved
     */
    public void messageSetInfectionMarker() {
        gameBoardContent.infectionMarker = GameBoard.gameBoard.infectionMarker.getInfectionDegree();
    }

    /**
     * copies the gamewon boolean from the gameboard
     */
    public void messageSetGameWon() {
        gameBoardContent.gameWon = GameBoard.gameBoard.gameWon;
    }

    /**
     * copies the gamelost boolean from the gameboard
     */
    public void messageSetGameLost() {
        gameBoardContent.gameLost = GameBoard.gameBoard.gameLost;
    }

    public Message setMessageContent(){
        messageSetPlayer1();
        messageSetPlayer2();
        messageSetPlayer3();
        messageSetPlayer4();

        messageSetCities();
        messageSetPlayerDeck();
        messageSetPlayerDiscard();
        messageSetInfectionDeck();
        messageSetInfectionDiscard();

        messageSetBlackCubesLeft();
        messageSetBlueCubesLeft();
        messageSetYellowCubesLeft();
        messageSetRedCubesLeft();

        messageSetInfectionMarker();
        messageSetOutbreakMarker();
        messageSetGameLost();
        messageSetGameWon();

        return gameBoardContent;
    }
}
