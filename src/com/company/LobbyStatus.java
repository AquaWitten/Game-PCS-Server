package com.company;

import java.util.Random;

/**
 * Used to keep track of the clients ready status during the lobby fase
 */
public class LobbyStatus {

    boolean p1, p2, p3, p4;
    int p1Role, p2Role, p3Role, p4Role;
    boolean changeInStatus;
    boolean ani1, ani2, ani3, ani4;
    boolean animation;

    LobbyStatus()
    {

    }

    /**
     * Depending on the message string, sets the players status to true or false
     * @param message string comprised of player ID and string from client
     */
    public void changePlayerStatus(String message)
    {
        if(message.equals("0_True")){
            p1 = true;
            changeInStatus = true;
        }

        else if(message.equals("0_False")){
            p1 = false;
            changeInStatus = true;
        }

        else if(message.equals("1_True")){
            p2 = true;
            changeInStatus = true;
        }

        else if(message.equals("1_False")){
            p2 = false;
            changeInStatus = true;
        }

        else if(message.equals("2_True")){
            p3 = true;
            changeInStatus = true;
        }

        else if(message.equals("2_False")){
            p3 = false;
            changeInStatus = true;
        }

        else if(message.equals("3_True")){
            p4 = true;
            changeInStatus = true;
        }

        else if(message.equals("3_False")){
            p4 = false;
            changeInStatus = true;
        }
    }

//    public void setAllReady()
//    {
//        if(p1 && p2 && p3 && p4)
//            allReady = true;
//    }

    /**
     * Get the status of a player
     * @param player name of the local variable for the player
     * @return returns a string of the boolean value
     */
    public String getPlayerStatus(String player)
    {
        if(player.equals("p1"))
            return String.valueOf(p1);

        else if(player.equals("p2"))
            return String.valueOf(p2);

        else if(player.equals("p3"))
            return String.valueOf(p3);

        else if(player.equals("p4"))
            return String.valueOf(p4);

        else
        {
            System.out.println("no player match the requested string in getPlayerStatus");
            return "null";
        }
    }

    public void setPlayerRole(int playerID, int playerRoleID)
    {
        switch (playerID){
            case 1: p1Role = playerRoleID;
                break;
            case 2: p2Role = playerRoleID;
                break;
            case 3: p3Role = playerRoleID;
                break;
            case 4: p4Role = playerRoleID;
                break;
        }
    }

    public int getPlayerRole(int playerID)
    {
        switch (playerID) {
            case 1: return p1Role;

            case 2: return p2Role;

            case 3: return p3Role;

            case 4: return p4Role;

            default: return 4;

        }
    }

    public void setAnimation()
    {
        animation = true;
    }

    public boolean isAnimation() {
        return animation;
    }
}
