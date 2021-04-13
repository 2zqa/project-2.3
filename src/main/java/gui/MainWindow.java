package gui;

import gui.controller.*;
import gui.model.*;
import gui.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainWindow extends Stage {
    private GenericGameConfigurationView ggcView;
    private GameView ggView;
    private GenericGameMenuView ggmView;
    private MainMenuView mmView;
    private GameLobbyView glView;

    public static final int WINDOW_WIDTH = 650;
    public static final int WINDOW_HEIGHT = 750;

    public enum viewEnum {
        GAME_LOBBY,
        MAINMENU,
        GAME_MENU,
        GAME_CONFIGURATION,
        GAME
    }

    public MainWindow(Stage primaryStage) throws IOException {
        // primaryStage word niet gebruikt?!
        setupMVC();

        //stage = primaryStage;
        this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/icon.png")).toExternalForm()));
        this.setTitle("C4Games");
        switchView(viewEnum.MAINMENU);
    }

    private void setupMVC() throws IOException {
        // Game settings
        GenericGameConfigurationModel ggcModel = new GenericGameConfigurationModel();
        GenericGameConfigurationController ggcController = new GenericGameConfigurationController();
        ggcController.setMainWindow(this);
        ggcController.setModel(ggcModel);
        ggcView = new GenericGameConfigurationView(
                getFXMLParent("GenericGameConfiguration.fxml", ggcController), ggcController, WINDOW_WIDTH, WINDOW_HEIGHT);
        ggcModel.registerView(ggcView);

        // Gameboard
        GenericGameModel ggModel = new GenericGameModel();
        GenericGameController ggController = new GenericGameController();
        ggController.setMainWindow(this);
        ggController.setModel(ggModel);
        ggView = new GameView(
                getFXMLParent("GenericGame.fxml", ggController),
                ggController,
                WINDOW_WIDTH,
                WINDOW_HEIGHT
        );
        ggModel.registerView(ggView);

        // Game Menu
        GenericGameMenuModel ggmModel = new GenericGameMenuModel(ggModel);
        GenericGameMenuController ggmController = new GenericGameMenuController();
        ggmController.setMainWindow(this);
        ggmController.setModel(ggmModel);
        ggmView = new GenericGameMenuView(
                getFXMLParent("GenericGameMenu.fxml", ggmController), ggmController, WINDOW_WIDTH, WINDOW_HEIGHT);
        ggmModel.registerView(ggmView);

        // Main Menu
        //MainMenuModel mmModel = new MainMenuModel();
        MainMenuController mmController = new MainMenuController();
        mmController.setMainWindow(this);
        mmView = new MainMenuView(
                getFXMLParent("MainMenu.fxml", mmController), mmController, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Game lobby
        GameLobbyModel glModel = new GameLobbyModel();
        GameLobbyController glController = new GameLobbyController();
        glController.setMainWindow(this);
        glController.setModel(glModel);
        glView = new GameLobbyView(
                getFXMLParent("GameLobby.fxml", glController), glController, WINDOW_WIDTH, WINDOW_HEIGHT);
        glModel.registerView(glView);
        //TODO: moet dit hier? controller.initialize is te vroeg!! Moet na de register.
        glModel.updateView();
    }

    /**
     * Returns a Parent object with specified controller set
     * @param fxmlFile the fxml file
     * @param controller the controller
     * @return Parent object of the fxml file
     * @throws IOException when the fxml file isn't found
     */
    private Parent getFXMLParent(String fxmlFile, Controller controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/"+fxmlFile));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    /**
     * Sets the view to one of the specified views
     * @param view which view to show
     */
    public void switchView(viewEnum view) {
        switch (view) {
            case MAINMENU:
                this.setScene(mmView);
                break;
            case GAME_MENU:
                this.setScene(ggmView);
                break;
            case GAME_CONFIGURATION:
                this.setScene(ggcView);
                break;
            case GAME:
                this.setScene(ggView);
                break;
            case GAME_LOBBY:
                this.setScene(glView);
                break;
            default:
                throw new IllegalArgumentException();
        }
        show();
    }
}