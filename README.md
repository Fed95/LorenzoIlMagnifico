# Lorenzo il Magnifico
## How to run it
In order to play the game you have to start the Server class that manages the client requests for the game.
After that for each player you can start ClientGUI or ClientCLI 
depending on if you want to play with the graphical interface (ClientGUI) or the command line (ClientCLI).
The type of the connection is asked directly from CLI or GUI and you can choose between RMI or SOCKET.<br>
So now you are ready to play the game!
  ```
  Server          --> run it.polimi.ingsw.gc12.Server
  client with GUI --> run it.polimi.ingsw.gc12.view.client.gui.ClientGUI
  client with CLI --> run it.polimi.ingsw.gc12.view.client.cli.ClientCLI
  ```
  
## How To Play
###CLI
When you run clientCLI the CLI asks you to perform an action through integer inputs, so just follow the instructions on the command line. 
### GUI
#### Components
- MainBoard Area
- PersonalBoardArea for each player
    + where you can find the cards, family members, leader cards and the resources for each player.
- Details Area
    + where you can see cards zoomed in
- Points Area
    + Where you can find the ranking of the players for victory, military and faith points.
- Notifications
    + In this area you get notifications from the server about what happened to the game,
    like new turn, player connection/disconnection, player exclusion.
- Controls Area
    + Here you can find some buttons to perform actions
#### Perform Actions
+ Zoom cards in
   * To see the card (Excommunication, Leader, Development) in the details area you can click once on the card once
+ Choose family member:<br>
   * just click once on it<br><br>
+ Choose a place (Towers Floor, Council palace, WorkSpaces, Markets...):<br>
   * after clicking on a family member, click once where you want to put it, like in the real game.<br><br>
+ Play/Discard Leader Card:<br>
   * To play or discard a leader card click twice on it, it will ask you if you want to play or discard it.
+ Pass Turn
   * Click on the button Pass Turn on the Controls Area. You can pass a turn only after placing a family member.
+ See Other players' statistics
   * Click on the other players' tab
    



## Details
### The Server
- Must be run once.
- Can handle more game at the same time.
- Since both RMI and SOCKET are implemented the server can handle different games with different type of connections.

### The Client
- One for each player.
- ClientGUI uses javaFX as graphical interface
- Since both RMI and SOCKET are implemented when you start the client it asks the tecnology to use.
- Both GUI and CLI are implemented, so you have to start the right client.

### Configurations
- Main Board configurations
    + The bonus on the game board associated to the floor can be configured from the json file `towers.json` 
    + The bonus associated to the personal board can be configured from the json file `cardsSpace.json`
    + The bonus on the Faith points track can be configured from the json file `faithPointValues.json`, but you can only change the value of the victory points associated to the track.
    + The number of market spaces and the presence of multiple work spaces (harvest and production) based on the number of players ca be configure from the file `configPlayers.json`.
    The change of these parameters are reflected in the GUI too. 
- Cards
    + The cards can be configured from the file json `cards.json`
    + The excommunication cards can be configured from the json file `excommunication.json`
    + The leader cards can be configured in the json file `cards.json` (last 20 cards of the file).
- Timeout
    + The timeout before the start of the match after reaching the minimum amount of player is present and can be configurated from the json file `config` (now 20s).
    + The timeout for a player move is present and can be configured from the json file `config.json` (now 2 minutes).

### Start of the Match
- If there are no startup games, a new game is created, otherwise the user automatically enters the game at startup.
- The match starts after reaching 4 players. When two players connect to a game a timeout starts, if other two player do not connect before the end of timeout the match starts with the current players.
- As will be described later, the possibility to reconnect after a disconnection of a player is based on the player's name.
For this reason there cannot be active players with the same name. Hence, for both CLI and GUI if you try to connect with an already taken name, you will receive an error and you will have to choose another name.

### During the Match
- Each player has a timeout to make a move and the server waits that period. After that the player will be suspended. 
- A player can disconnect from the match and his/her turns will be skipped. 
The player can rejoin the match by writing the same name of the previous session. 
The connection technology doesn't have to be the same of the previous session.
- If the number of players is less than 2, the match is suspended. 
- A suspended match can start from when it stopped if the number of players reaches 2 again, 
skipping the turn of the suspended/disconnected players.
- All the players will be notified if a player disconnected or has been excluded for the timeout's expiration.
- The suspended player can rejoin the match and continue the game writing something on the cli or clicking any element on the GUI.


#### Graphical User Interface
- If using the GUI, it's still possible to see the actions that can be currently performed from the command line, 
in a way similar to what happens when you are using the CLI.
- Every time the action you rae trying to perform is notified as invalid, 
you have to make a new action entirely, starting by clicking on the family member or the leader card. 
This fact is also visible from the fact that the family member previously selected is not highlighted anymore.
- If you receive multiple council privileges (e.g placing a family member on the market space) a popup is shown 
and after selecting the first exchange to do, the second popup will appear without the exchange already performed. 
As specified in the game rules.
- The reconnection mechanism works correctly with GUI too, but after the reconnection the interface is not updated
completely based on the match state of the server.


## UML
The UML diagrams are inside the folder resources/uml.
They are divided into multiple files because a unique UML would have been too big. 
The division is based on the packages.   
There is a file called model.png that shows at a higher level the dependencies between among the packages and the main classes.

 
