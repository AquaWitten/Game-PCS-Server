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
    public static ArrayList<City> allCities;
//--------------------------------------------------------------
//UPON PLAYER TURN CHANGE RESET CITY VARIABLE recentOutbreak TO FALSE
    public static void main(String[] args) {
        allCities = new ArrayList<>();
        InstantiateCities(); //run the method creating cities and adding them to the allCities array

        //testing city class and methods
        allCities.get(1).redCubes = 3;
        allCities.get(2).redCubes = 2;
        allCities.get(12).redCubes = 2;
        allCities.get(0).Outbreak("red");
        System.out.println(allCities.get(1).redCubes);
        System.out.println(allCities.get(12).name + allCities.get(12).redCubes);

        OutbreakMarker out = new OutbreakMarker();
        for(int i=0; i < 10; i++)
            out.IncreaseOutbreakMarker();
    }

    public void Receive(){

    }

    public void Send(){

    }

    public static void InstantiateCities(){ //Method used to instantiate all the cities and adding them to the allCities array
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
        City algiers = new City("algiers", "black",new ArrayList<>(Arrays.asList("madrid", "paris", "istanbul", "cairo")));
        allCities.add(algiers);
        City istanbul = new City("istanbul", "black",new ArrayList<>(Arrays.asList("milan", "algiers", "st petersburg", "moscow", "baghdad", "cairo")));
        allCities.add(istanbul);
        City cairo = new City("cairo", "black",new ArrayList<>(Arrays.asList("algiers", "istanbul", "baghdad", "riyadh")));
        allCities.add(cairo);
        City riyadh = new City("riyadh", "black",new ArrayList<>(Arrays.asList("cairo", "karachi", "baghdad")));
        allCities.add(riyadh);
        City baghdad = new City("baghdad", "black",new ArrayList<>(Arrays.asList("cairo", "karachi", "riyadh", "istanbul", "tehran")));
        allCities.add(baghdad);
        City moscow = new City("moscow", "black",new ArrayList<>(Arrays.asList("st petersburg", "istanbul", "tehran")));
        allCities.add(moscow);
        City tehran = new City("tehran", "black",new ArrayList<>(Arrays.asList("moscow", "baghdad", "karachi", "delhi")));
        allCities.add(tehran);
        City karachi = new City("karachi", "black",new ArrayList<>(Arrays.asList("tehran", "baghdad", "riyadh", "mumbai", "delhi")));
        allCities.add(karachi);
        City delhi = new City("delhi", "black",new ArrayList<>(Arrays.asList("tehran", "karachi", "kolkata", "mumbai", "chennai")));
        allCities.add(delhi);
        City mumbai = new City("mumbai", "black",new ArrayList<>(Arrays.asList("karachi", "delhi", "chennai")));
        allCities.add(mumbai);
        City kolkata = new City("kolkata", "black",new ArrayList<>(Arrays.asList("hong kong", "bangkok", "delhi", "chennai")));
        allCities.add(kolkata);
        City chennai = new City("chennai", "black",new ArrayList<>(Arrays.asList("kolkata", "bangkok", "jakarta", "mumbai", "delhi")));
        allCities.add(chennai);
        //All red cities
        City bangkok = new City("bangkok", "red",new ArrayList<>(Arrays.asList("kolkata", "hong kong", "ho chi minh city", "jakarta", "chennai")));
        allCities.add(bangkok);
        City jakarta = new City("jakarta", "red",new ArrayList<>(Arrays.asList("chennai", "bangkok", "ho chi minh city", "sydney")));
        allCities.add(jakarta);
        City sydney = new City("sydney", "red",new ArrayList<>(Arrays.asList("jakarta", "manila", "los angeles")));
        allCities.add(sydney);
        City manila = new City("manila", "red",new ArrayList<>(Arrays.asList("sydney", "ho chi minh city", "hong kong", "taipei", "san francisco")));
        allCities.add(manila);
        City hoChiMinhCity = new City("ho chi minh city", "red",new ArrayList<>(Arrays.asList("jakarta", "bangkok", "hong kong", "manila")));
        allCities.add(hoChiMinhCity);
        City hongKong = new City("hong kong", "red",new ArrayList<>(Arrays.asList("ho chi minh city", "bangkok", "kolkata", "shanghai", "taipei", "manila")));
        allCities.add(hongKong);
        City taipei = new City("taipei", "red",new ArrayList<>(Arrays.asList("shanghai", "osaka", "hong kong", "manila")));
        allCities.add(taipei);
        City shanghai = new City("shanghai", "red",new ArrayList<>(Arrays.asList("taipei", "beijing", "hong kong", "seoul", "tokyo")));
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
}
