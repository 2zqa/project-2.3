package connection;

public interface ServerPlayerCommunicationListener {
    void turnReceive(String whoPlayer, String move);

    void finalizeTurn();
}