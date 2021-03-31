package ttt;

import Connection.Connection;
import framework.GameManager;
import framework.Match;
import ttt.factory.TTTBoardFactory;

public class TTTGameManager extends GameManager {
    public TTTGameManager(Connection connection) {
        super(connection, new TTTBoardFactory());
    }

    @Override
    public int getMinPlayers() {
        return 2;
    }

    @Override
    public int getMaxPlayers() {
        return 2;
    }

    @Override
    public void ourTurn() {

    }
}

