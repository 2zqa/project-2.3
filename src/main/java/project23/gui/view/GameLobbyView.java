package project23.gui.view;

import javafx.css.Match;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import project23.framework.ChallengeRequest;
import project23.gui.controller.Controller;
import project23.gui.model.GameLobbyModel;

import java.util.List;

public class GameLobbyView extends View<GameLobbyModel> {

    public GameLobbyView(Parent parent, Controller controller, int windowWidth, int windowHeight) {
        super(parent, controller, windowWidth, windowHeight);
    }

    @Override
    public void update(GameLobbyModel model) {
        showDialog(model.getDialogMessage(), model.getDialogTitle());
        showChallengeDialog(model);
        showInfoText(model.getInfoMessage(), model.getLabelNode());

        // Update lobby list
        updateGameChallengeList(model.getLobbyPlayers());
    }

    public void showChallengeDialog(GameLobbyModel model) {
        ChallengeRequest challengeRequest = model.getLastChallengeRequest();
        if(challengeRequest == null) {
            return;
        }

        //creating dialog
        Dialog<String> dialog = new Dialog<>();
        //add button
        ButtonType accept = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType ignore = new ButtonType("Ignore", ButtonBar.ButtonData.CANCEL_CLOSE);

        //setting dialog content
        dialog.setContentText(challengeRequest.getOpponentName() + " is challenging you to a game of " + challengeRequest.getGameType().displayName + "! Do you accept?");
        //add button to dialogpane
        dialog.getDialogPane().getButtonTypes().add(accept);
        dialog.getDialogPane().getButtonTypes().add(ignore);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/dialogStyle.css").toExternalForm());
        dialog.setTitle("A new foe has appeared!");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));

        // Listen for answer
        Button button1 = (Button) dialog.getDialogPane().lookupButton(accept);
        button1.setOnAction(actionEvent -> model.acceptMatch(challengeRequest));

        //show dialog
        dialog.show();
    }

    private void updateGameChallengeList(List<String> playerNames) {
        //als lookup null teruggeeft, gebruik parameter net zoals showInfoText
        ListView challengerList = (ListView) lookup("#challengerList");
        // als dit null is: rip lol
        challengerList.getItems().clear();

        for (String playerName : playerNames) {
            challengerList.getItems().add(playerName);
        }
    }
}
