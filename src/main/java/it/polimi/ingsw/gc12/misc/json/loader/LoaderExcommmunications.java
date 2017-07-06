package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.Match;
import it.polimi.ingsw.gc12.model.board.excommunication.ExcommunicationTile;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by feder on 2017-06-22.
 */
public class LoaderExcommmunications extends Loader<List<ExcommunicationTile>> {

    public LoaderExcommmunications(){
        super("excommunications");
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<ExcommunicationTile>>() {}.getType();
    }

    @Override
    protected List<ExcommunicationTile> adapt(List<ExcommunicationTile> content, Match match) {
        return content;
    }
}
