package com.company;


public class LobbyStatus {

    boolean p1, p2, p3, p4;
    boolean changeInStatus;
    boolean allReady;

    LobbyStatus()
    {

    }

    public void changePlayerStatus(String message)
    {
        if(message.equals("1_True")){
            p1 = true;
            changeInStatus = true;
        }

        else if(message.equals("1_False")){
            p1 = false;
            changeInStatus = true;
        }

        else if(message.equals("2_True")){
            p2 = true;
            changeInStatus = true;
        }

        else if(message.equals("2_False")){
            p2 = false;
            changeInStatus = true;
        }

        else if(message.equals("3_True")){
            p3 = true;
            changeInStatus = true;
        }

        else if(message.equals("3_False")){
            p3 = false;
            changeInStatus = true;
        }

        else if(message.equals("4_True")){
            p4 = true;
            changeInStatus = true;
        }

        else if(message.equals("4_False")){
            p4 = false;
            changeInStatus = true;
        }

        //Check if all players are ready
        if(p1 && p2 && p3 && p4)
            allReady=true;
        else
            allReady = false;
    }

    public String getPlayerStatus(String player)
    {
        if(player.equals("p1"))
            return String.valueOf(p1);

        else if(player.equals("p2"))
            return String.valueOf(p2);

        else if(player.equals("p3"))
            return String.valueOf(p1);

        else if(player.equals("p4"))
            return String.valueOf(p1);

        else
        {
            System.out.println("no player match the requested string in getPlayerStatus");
            return "null";
        }
    }
}
