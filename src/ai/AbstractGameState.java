package ai;

import java.util.ArrayList;
import java.util.List;
import level.Level;
import player.Player;
import app.RPGGame;

public abstract class AbstractGameState
    implements Cloneable, Comparable<AbstractGameState> {

    List<AbstractGameState> successors = new ArrayList<AbstractGameState>();
    public Player player;
    public Level level;

    public AbstractGameState (RPGGame game) {
        extractGameInfo(game);
    }

    public void extractGameInfo (RPGGame game) {
        //game.copyGameState(this);
    }

    public abstract int getStateValue ();

    public void addSuccessor (AbstractGameState ags) {
        successors.add(ags);
    }

    public int compareTo (AbstractGameState ags) {
        return getStateValue() - ags.getStateValue();
    }

}
