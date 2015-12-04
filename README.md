# Game-PCS-Server

As the game is not functioning proprerly, it is neccesarry to download the project fold and open it using a Java IDE.
When running the server it will print the LAN IP to the console, this have to match the the IP that the Client tries to connect to.

##Testing the Lobby
We have a working lobby, but we have unresolved errors that happen when we exit the lobby and enter the main game it self.
Therefore, the main game and lobby must be tested individually.
To test the lobby simply run the Server without changing any code. But be prepared that when the “Start Game” button is pressed in the lobby, the clients will crash.

##Testing the main game
In order to skip the lobby phase of the game do the following:
1: Open the ClientConnections Class 
2: Comment out line 57-62
3. Comment out line 177

This will skip the lobby phase on the server and go straight to the main game commands.

##Current State of main game phase
In the main game it is possible to perform all implemented actions (not implemented is, Trade and EventCard) but the changes on one client will not be updated on the others and it is only possible to play one turn with each client.


