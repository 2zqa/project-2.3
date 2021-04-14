package project23.framework;

public class ChallengeRequest {
    private final String opponentName;
    private final GameType gameType;
    private final int challengeNr;

    public ChallengeRequest(String opponentName, GameType gameType, int challengeNr) {
        this.opponentName = opponentName;
        this.gameType = gameType;
        this.challengeNr = challengeNr;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public GameType getGameType() {
        return gameType;
    }

    public int getChallengeNr() {
        return challengeNr;
    }
}