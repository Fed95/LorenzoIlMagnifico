package it.polimi.ingsw.gc_12.java_fx;

import it.polimi.ingsw.gc_12.FamilyMemberColor;
import it.polimi.ingsw.gc_12.PlayerColor;
import it.polimi.ingsw.gc_12.WorkType;
import it.polimi.ingsw.gc_12.card.CardType;
import it.polimi.ingsw.gc_12.resource.ResourceType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class GUIInitializer extends MainBoardController {

	private void initializeAllMapsAndLists(){





		turnOrderTrack.add(firstPlayerTurn);
		turnOrderTrack.add(secondPlayerTurn);
		turnOrderTrack.add(thirdPlayerTurn);
		turnOrderTrack.add(fourthPlayerTurn);


		mapPeriodImageViewTile.add(excomTile1);
		mapPeriodImageViewTile.add(excomTile2);
		mapPeriodImageViewTile.add(excomTile3);

		mapPlayerColorButton.put(PlayerColor.BLUE, passTurnPl1);
		mapPlayerColorButton.put(PlayerColor.GREEN, passTurnPl2);
		mapPlayerColorButton.put(PlayerColor.RED, passTurnPl3);
		mapPlayerColorButton.put(PlayerColor.YELLOW, passTurnPl4);


		markets.add(market0);
		markets.add(market1);
		markets.add(market2);
		markets.add(market3);

		List<ImageView> productions = new ArrayList<>();
		productions.add(production);
		productions.add(productionBig);
		List<ImageView> harvests = new ArrayList<>();
		harvests.add(harvest);
		harvests.add(harvestBig);
		workplaces.put(WorkType.PRODUCTION, productions);
		workplaces.put(WorkType.HARVEST, harvests);

		//all card of player 1
		territoryPlayer1.add(territory0CardPlayer1);
		territoryPlayer1.add(territory1CardPlayer1);
		territoryPlayer1.add(territory2CardPlayer1);
		territoryPlayer1.add(territory3CardPlayer1);
		territoryPlayer1.add(territory4CardPlayer1);
		territoryPlayer1.add(territory5CardPlayer1);

		venturePlayer1.add(venture0CardPlayer1);
		venturePlayer1.add(venture1CardPlayer1);
		venturePlayer1.add(venture2CardPlayer1);
		venturePlayer1.add(venture3CardPlayer1);
		venturePlayer1.add(venture4CardPlayer1);
		venturePlayer1.add(venture5CardPlayer1);

		buildingPlayer1.add(building0CardPlayer1);
		buildingPlayer1.add(building1CardPlayer1);
		buildingPlayer1.add(building2CardPlayer1);
		buildingPlayer1.add(building3CardPlayer1);
		buildingPlayer1.add(building4CardPlayer1);
		buildingPlayer1.add(building5CardPlayer1);

		characterPlayer1.add(character0CardPlayer1);
		characterPlayer1.add(character1CardPlayer1);
		characterPlayer1.add(character2CardPlayer1);
		characterPlayer1.add(character3CardPlayer1);
		characterPlayer1.add(character4CardPlayer1);
		characterPlayer1.add(character5CardPlayer1);

		mapCardTypeImageViewPlayerCardPl1.put(CardType.TERRITORY, territoryPlayer1);
		mapCardTypeImageViewPlayerCardPl1.put(CardType.BUILDING, buildingPlayer1);
		mapCardTypeImageViewPlayerCardPl1.put(CardType.VENTURE, venturePlayer1);
		mapCardTypeImageViewPlayerCardPl1.put(CardType.CHARACTER, characterPlayer1);

		//all card of player 2
		territoryPlayer2.add(territory0CardPlayer2);
		territoryPlayer2.add(territory1CardPlayer2);
		territoryPlayer2.add(territory2CardPlayer2);
		territoryPlayer2.add(territory3CardPlayer2);
		territoryPlayer2.add(territory4CardPlayer2);
		territoryPlayer2.add(territory5CardPlayer2);

		venturePlayer2.add(venture0CardPlayer2);
		venturePlayer2.add(venture1CardPlayer2);
		venturePlayer2.add(venture2CardPlayer2);
		venturePlayer2.add(venture3CardPlayer2);
		venturePlayer2.add(venture4CardPlayer2);
		venturePlayer2.add(venture5CardPlayer2);

		buildingPlayer2.add(building0CardPlayer2);
		buildingPlayer2.add(building1CardPlayer2);
		buildingPlayer2.add(building2CardPlayer2);
		buildingPlayer2.add(building3CardPlayer2);
		buildingPlayer2.add(building4CardPlayer2);
		buildingPlayer2.add(building5CardPlayer2);

		characterPlayer2.add(character0CardPlayer2);
		characterPlayer2.add(character1CardPlayer2);
		characterPlayer2.add(character2CardPlayer2);
		characterPlayer2.add(character3CardPlayer2);
		characterPlayer2.add(character4CardPlayer2);
		characterPlayer2.add(character5CardPlayer2);

		mapCardTypeImageViewPlayerCardPl2.put(CardType.TERRITORY, territoryPlayer2);
		mapCardTypeImageViewPlayerCardPl2.put(CardType.BUILDING, buildingPlayer2);
		mapCardTypeImageViewPlayerCardPl2.put(CardType.VENTURE, venturePlayer2);
		mapCardTypeImageViewPlayerCardPl2.put(CardType.CHARACTER, characterPlayer2);

		//all card of player 3
		territoryPlayer3.add(territory0CardPlayer3);
		territoryPlayer3.add(territory1CardPlayer3);
		territoryPlayer3.add(territory2CardPlayer3);
		territoryPlayer3.add(territory3CardPlayer3);
		territoryPlayer3.add(territory4CardPlayer3);
		territoryPlayer3.add(territory5CardPlayer3);

		venturePlayer3.add(venture0CardPlayer3);
		venturePlayer3.add(venture1CardPlayer3);
		venturePlayer3.add(venture2CardPlayer3);
		venturePlayer3.add(venture3CardPlayer3);
		venturePlayer3.add(venture4CardPlayer3);
		venturePlayer3.add(venture5CardPlayer3);

		buildingPlayer3.add(building0CardPlayer3);
		buildingPlayer3.add(building1CardPlayer3);
		buildingPlayer3.add(building2CardPlayer3);
		buildingPlayer3.add(building3CardPlayer3);
		buildingPlayer3.add(building4CardPlayer3);
		buildingPlayer3.add(building5CardPlayer3);

		characterPlayer3.add(character0CardPlayer3);
		characterPlayer3.add(character1CardPlayer3);
		characterPlayer3.add(character2CardPlayer3);
		characterPlayer3.add(character3CardPlayer3);
		characterPlayer3.add(character4CardPlayer3);
		characterPlayer3.add(character5CardPlayer3);

		mapCardTypeImageViewPlayerCardPl3.put(CardType.TERRITORY, territoryPlayer3);
		mapCardTypeImageViewPlayerCardPl3.put(CardType.BUILDING, buildingPlayer3);
		mapCardTypeImageViewPlayerCardPl3.put(CardType.VENTURE, venturePlayer3);
		mapCardTypeImageViewPlayerCardPl3.put(CardType.CHARACTER, characterPlayer3);

		//all card of player 4
		territoryPlayer4.add(territory0CardPlayer4);
		territoryPlayer4.add(territory1CardPlayer4);
		territoryPlayer4.add(territory2CardPlayer4);
		territoryPlayer4.add(territory3CardPlayer4);
		territoryPlayer4.add(territory4CardPlayer4);
		territoryPlayer4.add(territory5CardPlayer4);

		venturePlayer4.add(venture0CardPlayer4);
		venturePlayer4.add(venture1CardPlayer4);
		venturePlayer4.add(venture2CardPlayer4);
		venturePlayer4.add(venture3CardPlayer4);
		venturePlayer4.add(venture4CardPlayer4);
		venturePlayer4.add(venture5CardPlayer4);

		buildingPlayer4.add(building0CardPlayer4);
		buildingPlayer4.add(building1CardPlayer4);
		buildingPlayer4.add(building2CardPlayer4);
		buildingPlayer4.add(building3CardPlayer4);
		buildingPlayer4.add(building4CardPlayer4);
		buildingPlayer4.add(building5CardPlayer4);

		characterPlayer4.add(character0CardPlayer4);
		characterPlayer4.add(character1CardPlayer4);
		characterPlayer4.add(character2CardPlayer4);
		characterPlayer4.add(character3CardPlayer4);
		characterPlayer4.add(character4CardPlayer4);
		characterPlayer4.add(character5CardPlayer4);

		mapCardTypeImageViewPlayerCardPl4.put(CardType.TERRITORY, territoryPlayer4);
		mapCardTypeImageViewPlayerCardPl4.put(CardType.BUILDING, buildingPlayer4);
		mapCardTypeImageViewPlayerCardPl4.put(CardType.VENTURE, venturePlayer4);
		mapCardTypeImageViewPlayerCardPl4.put(CardType.CHARACTER, characterPlayer4);

		mapPlayerColorCardTypeListImageView.put(PlayerColor.BLUE, mapCardTypeImageViewPlayerCardPl1);
		mapPlayerColorCardTypeListImageView.put(PlayerColor.GREEN, mapCardTypeImageViewPlayerCardPl2);
		mapPlayerColorCardTypeListImageView.put(PlayerColor.RED, mapCardTypeImageViewPlayerCardPl3);
		mapPlayerColorCardTypeListImageView.put(PlayerColor.YELLOW, mapCardTypeImageViewPlayerCardPl4);
	}

	private void initializeFamilyMembers() {
		familyMembers.put(PlayerColor.BLUE, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, blueBlack);
			put(FamilyMemberColor.WHITE, blueWhite);
			put(FamilyMemberColor.ORANGE, blueOrange);
			put(FamilyMemberColor.NEUTRAL, blueNeutral);
		}});

		familyMembers.put(PlayerColor.GREEN, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, greenBlack);
			put(FamilyMemberColor.WHITE, greenWhite);
			put(FamilyMemberColor.ORANGE, greenOrange);
			put(FamilyMemberColor.NEUTRAL, greenNeutral);
		}});

		familyMembers.put(PlayerColor.RED, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, redBlack);
			put(FamilyMemberColor.WHITE, redWhite);
			put(FamilyMemberColor.ORANGE, redOrange);
			put(FamilyMemberColor.NEUTRAL, redNeutral);
		}});

		familyMembers.put(PlayerColor.YELLOW, new HashMap<FamilyMemberColor, ImageView>() {{
			put(FamilyMemberColor.BLACK, yellowBlack);
			put(FamilyMemberColor.WHITE, yellowWhite);
			put(FamilyMemberColor.ORANGE, yellowOrange);
			put(FamilyMemberColor.NEUTRAL, yellowNeutral);
		}});
	}

	private void initializePlayerLabels() {
		//creating map player list of label player
		familyMemberLabels.put(PlayerColor.BLUE, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl1);
			put(FamilyMemberColor.WHITE, whiteValuePl1);
			put(FamilyMemberColor.ORANGE, orangeValuePl1);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl1);
		}});

		familyMemberLabels.put(PlayerColor.GREEN, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl2);
			put(FamilyMemberColor.WHITE, whiteValuePl2);
			put(FamilyMemberColor.ORANGE, orangeValuePl2);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl2);
		}});

		familyMemberLabels.put(PlayerColor.RED, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl3);
			put(FamilyMemberColor.WHITE, whiteValuePl3);
			put(FamilyMemberColor.ORANGE, orangeValuePl3);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl3);
		}});

		familyMemberLabels.put(PlayerColor.YELLOW, new HashMap<FamilyMemberColor, Label>() {{
			put(FamilyMemberColor.BLACK, blackValuePl4);
			put(FamilyMemberColor.WHITE, whiteValuePl4);
			put(FamilyMemberColor.ORANGE, orangeValuePl4);
			put(FamilyMemberColor.NEUTRAL, neutralValuePl4);
		}});
	}

	private void initializePlayerTabs() {
		//associating tab with colo player
		mapPlayerColorTab.put(PlayerColor.BLUE, bluePlayer);
		mapPlayerColorTab.put(PlayerColor.GREEN, greenPlayer);
		mapPlayerColorTab.put(PlayerColor.RED, redPlayer);
		mapPlayerColorTab.put(PlayerColor.YELLOW, yellowPlayer);
	}

	private void initializeCardFloor() {
		//associating floor number to id
		mapCardTypeMapIntegerCardFloors.put(CardType.TERRITORY, new ArrayList<>(Arrays.asList(cardFloor3, cardFloor2, cardFloor1, cardFloor0)));
		mapCardTypeMapIntegerCardFloors.put(CardType.BUILDING, new ArrayList<>(Arrays.asList(cardFloor11, cardFloor10, cardFloor9, cardFloor8)));
		mapCardTypeMapIntegerCardFloors.put(CardType.CHARACTER, new ArrayList<>(Arrays.asList(cardFloor7, cardFloor6, cardFloor5, cardFloor4)));
		mapCardTypeMapIntegerCardFloors.put(CardType.VENTURE, new ArrayList<>(Arrays.asList(cardFloor15, cardFloor14, cardFloor13, cardFloor12)));
	}

	private void initializeResourceLabels() {
		//creating the list and map resources
		resourcePlayer1.put(ResourceType.MONEY, moneyValuePl1);
		resourcePlayer1.put(ResourceType.STONE, stoneValuePl1);
		resourcePlayer1.put(ResourceType.WOOD, woodValuePl1);
		resourcePlayer1.put(ResourceType.SERVANT, servantValuePl1);

		resourcePlayer2.put(ResourceType.MONEY, moneyValuePl2);
		resourcePlayer2.put(ResourceType.STONE, stoneValuePl2);
		resourcePlayer2.put(ResourceType.WOOD, woodValuePl2);
		resourcePlayer2.put(ResourceType.SERVANT, servantValuePl2);

		resourcePlayer3.put(ResourceType.MONEY, moneyValuePl3);
		resourcePlayer3.put(ResourceType.STONE, stoneValuePl3);
		resourcePlayer3.put(ResourceType.WOOD, woodValuePl3);
		resourcePlayer3.put(ResourceType.SERVANT, servantValuePl3);

		resourcePlayer4.put(ResourceType.MONEY, moneyValuePl4);
		resourcePlayer4.put(ResourceType.STONE, stoneValuePl4);
		resourcePlayer4.put(ResourceType.WOOD, woodValuePl4);
		resourcePlayer4.put(ResourceType.SERVANT, servantValuePl4);

		mapPlayerColorResourceLabel.put(PlayerColor.BLUE, resourcePlayer1);
		mapPlayerColorResourceLabel.put(PlayerColor.GREEN, resourcePlayer2);
		mapPlayerColorResourceLabel.put(PlayerColor.RED, resourcePlayer3);
		mapPlayerColorResourceLabel.put(PlayerColor.YELLOW, resourcePlayer4);
	}

	private void initializeFloors() {
		//associating floor number to id
		towerFloors.put(CardType.TERRITORY, new ArrayList<>(Arrays.asList(floorToBeClicked3, floorToBeClicked2, floorToBeClicked1, floorToBeClicked0)));
		towerFloors.put(CardType.BUILDING, new ArrayList<>(Arrays.asList(floorToBeClicked11, floorToBeClicked10, floorToBeClicked9, floorToBeClicked8)));
		towerFloors.put(CardType.CHARACTER, new ArrayList<>(Arrays.asList(floorToBeClicked7, floorToBeClicked6, floorToBeClicked5, floorToBeClicked4)));
		towerFloors.put(CardType.VENTURE, new ArrayList<>(Arrays.asList(floorToBeClicked15, floorToBeClicked14, floorToBeClicked13, floorToBeClicked12)));
	}
}
