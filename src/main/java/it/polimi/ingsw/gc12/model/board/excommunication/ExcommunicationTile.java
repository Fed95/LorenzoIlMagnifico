package it.polimi.ingsw.gc12.model.board.excommunication;

import it.polimi.ingsw.gc12.model.effect.Effect;
import it.polimi.ingsw.gc12.model.effect.EffectProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExcommunicationTile implements EffectProvider, Serializable {

	private final int id;
	private int period;
	private List<Effect> effects = new ArrayList<>();

	public ExcommunicationTile(int id, int period, List<Effect> effects){
		this.id = id;
		this.period = period;
		this.effects = effects;
	}

	public int getId() {
		return id;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public int getPeriod() {
		return period;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("		ExcommunicationTile (Period " + period + ")").append(System.getProperty("line.separator"));
		sb.append("		Effects: ").append(System.getProperty("line.separator"));
		for(Effect effect : effects)
			sb.append("         - " + effect).append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
