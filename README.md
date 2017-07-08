# Project Template - Prova Finale (Ingegneria del Software)

## Project Setup
In order to set up your project, follow these steps
### Clone and push the template to your repo
Using the git command line client for your OS, type the following commands:
```bash
 # clone the repo on your current folder, naming the remote as 'template'
 git clone https://github.com/deib-polimi/prova-finale-template --origin template
 # move to the cloned repo
 cd prova-finale-template/
 # add your repository as 'origin' (default) remote
 git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME
 # push the template project to your github repository, setting 
 git push --set-upstream origin master
 # alternatively, if you already have some content in your repo (e.g., a README)
 # and YOU WANT TO OVERWRITE IT, force the push
 git push --force --set-upstream origin master
 
```
then, you can safely remove the 'template' remote by typing `git remote rm template`.

### Customize your project files and Import them in Eclipse
- Open the `pom.xml` file in a text editor and substitute the two occurrences of **pcXX** with your assigned **team_code**.
- Import it in Eclipse as Maven Project:
  * from Eclipse, select `File > Import... > Existing Maven Project`
  * click `Browse...` and select the directory where you cloned the project
  * make sure the project is listed and selected under `Projects`
  * select `Finish`
  * you should now see the project **team_code** listed in the Package Explorer view of Eclipse
- from the Package Explorer view, rename packages under `src/main/java` and `src/test/java` substituting **pcXX** with your assigned **team_code**
- customize the `README.md`
- in order to check that everything worked fine, try to build with Maven:
  + from Eclipse (Package Explorer view):
    * right-click on the project
    * select `Run as > Maven build...`
    * type `clean package` into the `Goal` field
    * click `Run`
  + from command line:
    * move to your project directory (you should be in the same folder as `pom.xml` file)
    * type `mvn clean package`
  + wait for the build to complete and make sure you have a build success

### Commit and push your changes:
  ```
  git commit -am "customize project"
  git push origin master
  ```
# Game Implementation
##How to run it
In oreder to play the game you have to start the server class that manage the client request for a game.
After that for each player you can start clientGUI or clientCLI depending if you want to play with the graphic interface(clientGUI) or the command line(clientCLI).
The type of the connection is asked from CLI or GUI and you can choose between RMI or SOCKET.<br>
So now you are ready to play the game!
  ```
  Server          --> run it.polimi.ingsw.gc12.Server
  client with GUI --> run it.polimi.ingsw.gc12.view.client.gui.ClientGUI
  client with CLI --> run it.polimi.ingsw.gc12.view.client.cli.ClientCLI
  ```
  
##How To Play
###CLI
When you run clientCLI the CLI ask you to perform an action through integers input, so just follow the instruction on the command line. 
###GUI
####Components
- MainBoard Area
- PersonalBoardArea for each player
    + where you can find the cards, family member, cards leader and the resources for each player.
- Details Area
    + where you can se cards zoomed in
- Points Area
    + Where you can find the ranking of the players for the victory, military and faith points.
- ChatArea
    + In this area you get response from the server a real chat is not implemented.
- Controls Area
    +Here you can find some button to perform actions
####Perform Actions
+ Zoom cards in
   * For see the card (Excommunication, Leader, Development) in the details area you can click once on the card
+ Choose family member:<br>
   * just click once on it<br><br>
+ Choose a place (Towers Floor, Council palace, WorkSpaces, Markets...):<br>
   * after click on  a family member, click once where you want to put it, like on te real game.<br><br>
+ Play/Discard Leader Card:<br>
   * For play or discard leader card  do a double click on it, it will ask you if you want to play or discard it.
+ Pass Turn
   * Click on the button Pass Turn on the Controls Area
+ See Other player Statistics
   * Just click on the other player tab
    



##Details
###The Server
- must be run once.
- can handle more game at the same time.
- since both RMI and SOCKET are implemented the server can handle different games with different type of connections.

###The Client
- Instantiate it for each plyer.
- clientGUI it use javaFX as graphical interface
- since both RMI and SOCKET are implemented when you start the client it asks the tecnology to use.
- For GUI or CLI you have to start the right client, but both are implemented.

###Configurations
- Main Board configurations
    + The bonus on the game board associated to the floor can be configurated from the json file `towers` 
    + The bonus associated to the personal board can be configurated from the json file `configPlayers`
    + The bonus on the Faith points track can be configurated from the json file `faithPointValues`, but you can only change the value of the victory points associated to the track.
- Cards
    + The cards can be configurated from the file json `cards`
    + The excommunication cards can be configurated from the json file `excommunication`
    + The leader cards can be configurated in the json file `cards` (last 20 cards of the file).
- Timeout
    + The timeout before the start of the match after reaching the minimum amount of player is present and can be configurated from the json file `config` (now 1s).
    + The timeout for a player move is present and can be configurated from the json file `config` (now 60s).

###Start of the Match
- If there are no startup games, a new game is created, otherwise the user automatically enters the game at startup.
- The match start after reaching 4 players. When two players connect to a game a timeout start, if other two player doesn't connect before the end of timeout the match start with the player connected.

###During the Match
- Each player has a timeout for make a move, the server wait that period, after that the player will be suspended. If the number of player is less than 2 the match is suspended. But can be continued if the player reconnect.
- All the player will be notified if a player is missing.
- The game continue if the player are 2 or more, without the turn of suspended player.
- The player suspended can reconnect and continue the game, writing something on the cli or clicking any element on the GUI.



 
