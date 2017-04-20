import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Raze on 05/04/17.
 */

public class Input {
    public static void InputHandler(String input, Label main, TextField inputField, Stage window) {
        if(!input.contains(" ")) {
            switch (input) {
                case "quit":
                case "close":
                    window.close();
                    break;
                case "options":
                case "opt":
                case "settings":
                    Options opt = new Options();
                    opt.optWindow();
                    break;
                case "hello":
                    AnimateText("Hello world!");
                    break;
                case "examine":
                    AnimateText("What are you examining?");
                    break;
                case "move":
                    AnimateText("Where are you moving?");
                     break;
                case "take":
                    AnimateText("What are you taking?");
                    break;
                case "open":
                    AnimateText("What are you opening?");
                    break;
                case "inventory":
                case "inv":
                case "bag":
                    AnimateText(Player.viewInventory());
                    break;
                default:
                    AnimateText("Invalid command, type \"help\" for a list of commands.");
            }
        } else {
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case "examine":
                case "lookat":
                    Action examine = new Action();
                    AnimateText(examine.examine(inputArray[1]));
                    break;
                case "move":
                case "go":
                case "walk":
                    Action move = new Action();
                    AnimateText(move.move(inputArray[1]));
                    break;
                case "take":
                case "pickup":
                    Action take = new Action();
                    AnimateText(take.take(inputArray[1]));
                    break;
                case "open":
                    Action open = new Action();
                    AnimateText(open.open(inputArray[1]));
                    break;
                default:
                    AnimateText("Invalid command, type \"help\" for a list of commands.");
            }
        }
        Main.saveCommand(0);
        inputField.setText("");
    }
    public static void AnimateText(String string) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(Player.getAnimateSpeed()));
            }

            protected void interpolate(double frac) {
                final int length = string.length();
                final int n = Math.round(length * (float) frac);
                Main.getLabel().setText(string.substring(0, n));
            }
        };
        animation.play();
    }
}
