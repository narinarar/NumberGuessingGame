package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Random;

public class Controller {
    private static final int GUESS_RANGE = 101;
    private static final int NUM_CHANCES = 10;

    private Random random;
    private int numberGuess, chanceCounter;


    private double xOffset, yOffset;

    @FXML
    private Button minBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private GridPane titleBar;

    @FXML
    private GridPane gameDisplay;

    @FXML
    private GridPane mainMenu;

    @FXML
    private Label chanceCounterLabel;

    @FXML
    private TextField userInputField;

    @FXML
    private ImageView backgroundImg;

    @FXML
    private Button tryAgainBtn;

    @FXML
    private Label hintLabel;

    public void initialize() {
        random = new Random();

        numberGuess = random.nextInt(GUESS_RANGE);

        chanceCounter = NUM_CHANCES;

        chanceCounterLabel.setText("You have " + chanceCounter + " guesses!");
    }

    public void handleSubmitAction() {
        int userGuess;
        try {
            userGuess = Integer.parseInt(userInputField.getText().replace(" ", ""));

            if (userGuess == numberGuess){
                backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/success.png")));

                gameDisplay.setVisible(false);

                tryAgainBtn.setVisible(true);
            } else{
                chanceCounter --;
                chanceCounterLabel.setText("You have " + chanceCounter + " guess" + (chanceCounter == 1 ? "" : "es ") + "left");

                if (chanceCounter <= 0){
                    backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/game_over.png")));

                    gameDisplay.setVisible(false);

                    tryAgainBtn.setVisible(true);
                }
            }

            if (userGuess < numberGuess){
                hintLabel.setText("Alien: Too Low");
            }else {
                hintLabel.setText("Alien: Too High");
            }

        }catch (Exception e){
            hintLabel.setText("Alien: That isn't a number!");
        }
    }
    public void handleTryAgainAction(){
        backgroundImg.setImage(new Image(getClass().getResourceAsStream("/resources/background.png")));

        gameDisplay.setVisible(true);

        numberGuess = random.nextInt(GUESS_RANGE);

        chanceCounter = NUM_CHANCES;

        chanceCounter = NUM_CHANCES;

        tryAgainBtn.setVisible(false);

        hintLabel.setText("");

        userInputField.setText("");
    }


    public void handleStartAction() {
        mainMenu.setVisible(false);
        gameDisplay.setVisible(true);
    }

    public void handleExitAction() {
        Platform.exit();
    }

    // close window
    public void handleCloseAction() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    //minimize window
    public void handleMinimizeAction() {
        Stage stage = (Stage) minBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleTitleBarClickAction(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
        event.consume();
    }

    public void handleTitleBarMovementAction(MouseEvent event){
        Scene scene = titleBar.getScene();
        Window window = scene.getWindow();

        window.setX(event.getScreenX() - xOffset);
        window.setY(event.getScreenY() - yOffset);
        event.consume();
    }
}
