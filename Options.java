import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Raze on 05/04/17.
 */

public class Options {

    public void optWindow(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game Options");
        window.setMinWidth(250);

        Label label = new Label("Options");
        Label textSpeed = new Label("Text speed:");

        ChoiceBox<String> textSpeedBox = new ChoiceBox<>();
        textSpeedBox.getItems().addAll("Slow", "Normal", "Fast");
        switch(Player.getAnimateSpeed()) {
            case 2000:
                textSpeedBox.getSelectionModel().select(0);
                break;
            case 1000:
                textSpeedBox.getSelectionModel().select(1);
                break;
            case 500:
                textSpeedBox.getSelectionModel().select(2);
                break;
        }

        Button returnBtn = new Button("Return to Game");
        returnBtn.setOnAction(e -> {
            String speed = textSpeedBox.getValue();
            Player.setAnimateSpeed(speed);
            window.close();
        }
        );

        VBox layout = new VBox();
        layout.getChildren().addAll(label, textSpeed, textSpeedBox, returnBtn);
        layout.setMargin(label, new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add("main.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
