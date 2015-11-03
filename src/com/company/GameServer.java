package com.company;

import Cards.*;
import Markers.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServer {

    //Global variables
    int port;
    static ArrayList<Socket> connectionArray;
    static ArrayList<String> currentUsers;
    ArrayList<City> allCities;
//--------------------------------------------------------------

    public static void main(String[] args) {
        OutbreakMarker out = new OutbreakMarker();
        for(int i=0; i < 10; i++)
            out.IncreaseOutbreakMarker();
    }

    public void Receive(){

    }

    public void Send(){

    }

    public void InstantiateCities(){ //Method used to instantiate all the cities
        //All blue cities
        City sanFrancisco = new City("san francisco", "blue",new ArrayList<>(Arrays.asList("chicago", "los angeles", "tokyo", "manila")));
        allCities.add(sanFrancisco);
        City chicago = new City("chicago", "blue",new ArrayList<>(Arrays.asList("san francisco", "los angeles", "montreal", "atlanta", "mexico city")));
        allCities.add(chicago);
        City montreal = new City("montreal", "blue",new ArrayList<>(Arrays.asList("chicago", "new york", "washington")));
        allCities.add(montreal);
        City newYork = new City("new york", "blue",new ArrayList<>(Arrays.asList("montreal", "london", "washington", "madrid")));
        allCities.add(newYork);
        City atlanta = new City("atlanta", "blue",new ArrayList<>(Arrays.asList("chicago", "miami", "washington")));
        allCities.add(atlanta);
        City washington = new City("washington", "blue",new ArrayList<>(Arrays.asList("montreal", "new york", "miami", "atlanta")));
        allCities.add(washington);
        City london = new City("london", "blue",new ArrayList<>(Arrays.asList("madrid", "new york", "essen", "paris")));
        allCities.add(london);
        City madrid = new City("madrid", "blue",new ArrayList<>(Arrays.asList("london", "new york", "paris", "algiers")));
        allCities.add(madrid);
        City paris = new City("paris", "blue",new ArrayList<>(Arrays.asList("london", "essen", "madrid", "algiers", "milan")));
        allCities.add(paris);
        City essen = new City("essen", "blue",new ArrayList<>(Arrays.asList("london", "paris", "st petersburg", "milan")));
        allCities.add(essen);
        City stPetersburg = new City("st petersburg", "blue",new ArrayList<>(Arrays.asList("essen", "istanbul", "moscow")));
        allCities.add(stPetersburg);
        City milan = new City("milan", "blue",new ArrayList<>(Arrays.asList("essen", "paris", "istanbul")));
        allCities.add(milan);
        //All yellow cities
        City losAngeles = new City("los angeles", "yellow",new ArrayList<>(Arrays.asList("sydney", "san francisco", "chicago", "mexico city")));
        allCities.add(losAngeles);
        City mexicoCity = new City("mexico city", "yellow",new ArrayList<>(Arrays.asList("los angeles", "chicago", "miami", "bogota", "lima")));
        allCities.add(mexicoCity);
        City miami = new City("miami", "yellow",new ArrayList<>(Arrays.asList("atlanta", "washington", "mexico city", "bogota")));
        allCities.add(miami);
        City bogota = new City("bogota", "yellow",new ArrayList<>(Arrays.asList("miami", "lima", "mexico city", "buenos aires", "sao paulo")));
        allCities.add(bogota);
        City lima = new City("lima", "yellow",new ArrayList<>(Arrays.asList("bogota", "santiago", "mexico city")));
        allCities.add(lima);
        City santiago = new City("santiago", "yellow",new ArrayList<>(Arrays.asList("lima")));
        allCities.add(santiago);
        City buenosAires = new City("buenos aires", "yellow",new ArrayList<>(Arrays.asList("bogota", "sao paulo")));
        allCities.add(buenosAires);
        City saoPaulo = new City("sao paulo", "yellow",new ArrayList<>(Arrays.asList("bogota", "buenos aires", "madrid", "lagos")));
        allCities.add(saoPaulo);
        City lagos = new City("lagos", "yellow",new ArrayList<>(Arrays.asList("sao paulo", "kinshasa", "khartoum")));
        allCities.add(lagos);
        City kinshasa = new City("kinshasa", "yellow",new ArrayList<>(Arrays.asList("lagos", "johannesburg", "khartoum")));
        allCities.add(kinshasa);
        City khartoum = new City("khartoum", "yellow",new ArrayList<>(Arrays.asList("lagos", "johannesburg", "kinshasa", "cairo")));
        allCities.add(khartoum);
        City johannesburg = new City("johannesburg", "yellow",new ArrayList<>(Arrays.asList("khartoum", "kinshasa")));
        allCities.add(johannesburg);
        //All black cities

        //All red cities

    }
}
