package it.polimi.ingsw.gc12.misc.json.loader;

import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gc12.model.match.Match;

import java.lang.reflect.Type;
import java.util.List;

public class LoaderTrackFaithPointsValues extends Loader<List<Integer>> {

    public LoaderTrackFaithPointsValues() {
        super("faithPointValues");
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<Integer>>() {}.getType();
    }

    @Override
    protected List<Integer> adapt(List<Integer> content, Match match) {
        return content;
    }
}
