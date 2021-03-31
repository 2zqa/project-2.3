package ttt;

import framework.GameManager;
import framework.board.Board;
import framework.board.BoardObserver;
import framework.board.piece.BoardPiece;
import framework.player.Player;
import ttt.player.TTTRandomAIPlayer;

public class TTTConsoleGame implements BoardObserver {
    public static void main(String[] args) {
        new TTTConsoleGame();
    }

    private final Board board;
    private final Player playerCross, playerCircle;

    public TTTConsoleGame() {
        GameManager gameManager = new TTTGameManager();
        board = gameManager.getBoard();

        board.registerObserver(this);

        gameManager.setPlayer(0, (playerCross  = new TTTRandomAIPlayer(board)));
        gameManager.setPlayer(1, (playerCircle = new TTTRandomAIPlayer(board)));

        gameManager.start();
    }

    @Override
    public void boardUpdated() {
        System.out.println();
        System.out.println("Current board:");
        for(int y = 0; y < board.getHeight(); y++) {
            for(int x = 0; x < board.getWidth(); x++) {
                BoardPiece piece = board.getBoardPiece(x, y);
                System.out.print(getPlayerChar(piece.getOwner()));
            }
            System.out.println();
        }

        if(board.isGameOver()) {
            System.out.println();
            System.out.println("---");
            System.out.println("GAME OVER! Winner: " + getPlayerChar(board.getWinner()));
        }
    }

    private char getPlayerChar(Player player) {
        if(player == playerCross) {
            return 'X';
        }else if(player == playerCircle) {
            return 'O';
        }else{
            return ' ';
        }
    }
}