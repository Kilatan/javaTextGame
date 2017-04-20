import javafx.scene.image.Image;

/**
 * Created by Raze on 15/04/17.
 */
public class Action {
    String room1 = "You're in a small room with a bed. There is another room to your right.";
    String room2 = "This room contains a cabinet, a chair and a table. There is a closed door opposite where you came in.";

    String bed = "It's a plain, not very comfortable bed.";

    String chair = "It's a chair.";
    String table = "It's a table.";
    String closedCabinet = "It's a plain wood-colored cabinet with a drawer. Through the glass you see a silver key on a shelf.";
    String cabinet = "It's a plain wood-colored cabinet with a drawer. The cabinet door is open and you see a silver key on a shelf.";
    String key = "It's a silvery door key lying on the cabinet shelf.";
    String emptyCabinet = "It's a plain wood-colored cabinet with a drawer. The shelf is empty.";
    String drawer = "The drawer is closed.";
    String drawerWithCrowbar = "A crowbar is lying in the drawer.";
    String crowbar = "This might be able to open the cabinet.";
    String emptyDrawer = "The drawer is open. It's empty.";
    String none = "You can't see anything like that.";

    String moveLeft = "You entered the room to your left.";
    String moveRight = "You entered the room to your right.";

    String takeCrowbar = "You took the crowbar from the drawer.";
    String haveCrowbar = "You already have the crowbar.";
    String takeKey = "You took the key from the cabinet.";
    String haveKey = "You already have the key.";

    String openDrawer = "You open the drawer. Inside you see a crowbar.";
    String openCabinet = "You break the cabinet door open with the crowbar.";
    String lockedCabinet = "The cabinet door is locked.";

    String openDoor = "The key fits, and you open the door.";
    String lockedDoor = "The door is locked.";

    String invalidArg = "That is not a valid argument.";

    String nothing = "Nothing happened.";
    String cantOpen = "You can't examine that.";
    String cantTake = "You can't take that.";
    String cantMove = "There is nowhere to go this way.";

    public String examine(String input) {
        String examineMsg = "";
        switch (Player.getLoc()) {
            case 0:
                switch (input) {
                    case "room":
                        examineMsg = room1;
                        break;
                    case "bed":
                        examineMsg = bed;
                        break;
                    default:
                        examineMsg = none;
                }
                break;
            case 1:
                switch (input) {
                    case "room":
                        examineMsg = room2;
                        break;
                    case "chair":
                        Main.getImageView().setImage(new Image("pics/chair.png"));
                        examineMsg = chair;
                        break;
                    case "table":
                        Main.getImageView().setImage(new Image("pics/table.png"));
                        examineMsg = table;
                        break;
                    case "cabinet":
                        if(!Player.getAreaState(1)){
                            Main.getImageView().setImage(new Image("pics/cabinet.png"));
                            examineMsg = closedCabinet;
                        } else {
                            if(!Player.hasInventoryItem(1)){
                                examineMsg = cabinet;
                            } else {
                                examineMsg = emptyCabinet;
                            }
                        }
                        break;
                    case "crowbar":
                        if(Player.getAreaState(0) && !Player.hasInventoryItem(0)){
                            Main.getImageView().setImage(new Image("pics/crowbar.png"));
                            examineMsg = crowbar;
                        }else{
                            examineMsg = none;
                        }
                        break;
                    case "key":
                        if(!Player.hasInventoryItem(1)) {
                            Main.getImageView().setImage(new Image("pics/key.png"));
                            examineMsg = key;
                        }else{
                            examineMsg = none;
                        }
                        break;
                    case "drawer":
                        if(!Player.getAreaState(0)) {
                            Main.getImageView().setImage(new Image("pics/drawer.png"));
                            examineMsg = drawer;
                        } else {
                            if (!Player.hasInventoryItem(0)) {
                                Main.getImageView().setImage(new Image("pics/drawerWithCrowbar.png"));
                                examineMsg = drawerWithCrowbar;
                            } else {
                                Main.getImageView().setImage(new Image("pics/emptyDrawer.png"));
                                examineMsg = emptyDrawer;
                            }
                        }
                        break;
                    case "door":
                        examineMsg = lockedDoor;
                        break;
                    default:
                        examineMsg = none;
                }
                break;
            case 2:
        }
        return examineMsg;
    }
    public String move(String input){
        String moveMsg = "";
        switch (Player.getLoc()) {
            case 0:
                switch (input) {
                    case "left":
                        moveMsg = cantMove;
                        break;
                    case "right":
                        moveMsg = moveRight;
                        Player.setLoc(1);
                        break;
                    default:
                        moveMsg = invalidArg;
                }
                break;
            case 1:
                switch (input) {
                    case "left":
                        moveMsg = moveLeft;
                        Player.setLoc(0);
                        break;
                    case "right":
                        moveMsg = cantMove;
                        break;
                    default:
                        moveMsg = invalidArg;
                }
                break;
        }
        return moveMsg;
    }
    public String take(String input){
        String takeMsg = "";
        switch (Player.getLoc()){
            case 0:
                takeMsg = cantTake;
                break;
            case 1:
                switch(input) {
                    case "crowbar":
                        if(Player.getAreaState(0)) {
                            if (!Player.hasInventoryItem(0)) {
                                Player.setInventoryItem(0, true);
                                Main.getImageView().setImage(new Image("pics/crowbar.png"));
                                takeMsg = takeCrowbar;
                            } else {
                                takeMsg = haveCrowbar;
                            }
                        }else{
                            takeMsg = nothing;
                        }
                        break;
                    case "key":
                        if(Player.getAreaState(1)){
                            if(!Player.hasInventoryItem(1)){
                                Player.setInventoryItem(1, true);
                                Main.getImageView().setImage(new Image("pics/key.png"));
                                takeMsg = takeKey;
                            } else {
                                takeMsg = haveKey;
                            }
                        }else{
                            takeMsg = nothing;
                        }
                        break;
                    default:
                        takeMsg = cantTake;
                }
        }
        return takeMsg;
    }
    public String open(String input){
        String openMsg = "";
        switch (Player.getLoc()){
            case 0:
                openMsg = cantOpen;
                break;
            case 1:
            switch (input) {
                case "drawer":
                    if(!Player.hasInventoryItem(0)) {
                        Player.setAreaState(0, true);
                        Main.getImageView().setImage(new Image("pics/drawerWithCrowbar.png"));
                        openMsg = openDrawer;
                    }else{
                        Main.getImageView().setImage(new Image("pics/emptyDrawer.png"));
                        openMsg = emptyDrawer;
                    }
                    break;
                case "cabinet":
                    if(!Player.getAreaState(1)){
                        if(Player.hasInventoryItem(0)){
                            Player.setAreaState(1, true);
                            openMsg = openCabinet;
                        }else{
                            openMsg = lockedCabinet;
                        }
                    }else{
                        openMsg = nothing;
                    }
                    break;
                case "door":
                    if(!Player.getAreaState(2)){
                        if(Player.hasInventoryItem(1)){
                            Player.setAreaState(2, true);
                            openMsg = openDoor;
                        }else{
                            openMsg = lockedDoor;
                        }
                    }else{
                        openMsg = nothing;
                    }
                    break;
            }
        }
        return openMsg;
    }
}
