import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Raze on 05/04/17.
 */

public class Main extends Application {

    private Stage window;
    private Scene gameScene;
    private static Label main;
    private static ImageView image;
    private static TextField inputField;

    private static String lastCommand;
    private static String currentCommand;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Shitty game");

        main = new Label();
        main.setText("");
        main.setWrapText(true);

        image = new ImageView(new Image(Player.getImage()));

        BorderPane gameLayout = new BorderPane();
        VBox gameText = new VBox();
        StackPane gameScreen = new StackPane();

        inputField = new TextField();
        inputField.setPrefWidth(300);
        inputField.getStyleClass().add("text-input");

        inputField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                if(!(inputField.getText().equals(""))) {
                    getImageView().setImage(new Image("pics/blank.png"));
                    saveCommand(0);
                    Input.InputHandler(inputField.getText(), main, inputField, window);
                }
            /* }else if(e.getCode() == KeyCode.UP){
                  if(!inputField.getText().equals(currentCommand) || !inputField.getText().equals(lastCommand)) {
                    saveCommand(1);
                    inputField.setText(lastCommand);
                    inputField.positionCaret(lastCommand.length());
                }
            }else if(e.getCode() == KeyCode.DOWN){
                if(!inputField.getText().equals(lastCommand) || !inputField.getText().equals(currentCommand)) {
                    saveCommand(0);
                    inputField.setText(currentCommand);
                    inputField.positionCaret(currentCommand.length());
                } */

            }
        });

        gameText.setPadding(new Insets(15));
        gameText.setSpacing(10);

        gameText.getChildren().addAll(main, inputField);

        gameLayout.setBottom(gameText);
        gameLayout.setTop(gameScreen);
        gameScene = new Scene(gameLayout, 500, 400);
        gameScene.getStylesheets().add("main.css");

        gameScreen.getChildren().add(image);

        window.setScene(gameScene);
        window.show();
        Input.AnimateText("You find yourself in a room. Use 'examine' to examine your surroundings.");
    }
    public static Label getLabel(){
        return main;
    }
    public static ImageView getImageView(){
        return image;
    }
    public static void saveCommand(int cmd){
        switch (cmd) {
            case 0:
                lastCommand = inputField.getText();
                break;
            case 1:
                currentCommand = inputField.getText();
                break;
        }
    }
}
