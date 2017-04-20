/**
 * Created by Raze on 18/04/17.
 */
public class Player {
    //Global player variables
    private static int loc = 0;
    //0 = Bedroom
    //1 = Cabinet room
    //2 = Outside

    //Area variables
    private static boolean[] areaState = {false, false, false};
    //0 = isDrawerOpen
    //1 = isCabinetOpen
    //2 = isDoorOpen

    //Player inventory variable
    private static boolean[] inventoryItem = {false, false};
    //0 = hasCrowbar
    //1 = hasDoorKey

    //Text animation delay
    public static int animateDelay = 1000;
    //500 = Slow
    //1000 = Normal
    //2000 = Fast

    //Current image displayed on the screen
    public static String imageDisplayed = "pics/blank.png";

    //Methods to access and modify global variables

    //Method to set text animation speed
    public static String getImage(){
        return imageDisplayed;
    }
    public static int getAnimateSpeed(){
        return animateDelay;
    }
    public static void setAnimateSpeed(String speed){
        switch (speed){
            case "Slow":
                animateDelay = 2000;
                break;
            case "Normal":
                animateDelay = 1000;
                break;
            case "Fast":
                animateDelay = 500;
                break;
        }
    }
    public static int getLoc(){
        return loc;
    }
    public static void setLoc(int newLoc){
        loc = newLoc;
    }
    public static boolean getAreaState(int stateId){
        return areaState[stateId];
    }
    public static void setAreaState(int stateId, boolean state){
        if(state) {
            areaState[stateId] = true;
        }else{
            areaState[stateId] = false;
        }
    }
    public static boolean hasInventoryItem(int itemId){
        return inventoryItem[itemId];
    }
    public static void setInventoryItem(int itemId, boolean state){
        if(state) {
            inventoryItem[itemId] = true;
        }else{
            inventoryItem[itemId] = false;
        }
    }
    public static String viewInventory(){
        String inv = "Your inventory contains: ";
        int amount = 0;
        for(int i = 0; i < inventoryItem.length; i++){
            if(inventoryItem[i]){
                amount++;
            }
        }
        if(!(amount == 0)){
            String[] invList = new String[amount];
            if(hasInventoryItem(0)){
                invList[0] = "Crowbar";
            }
            if(hasInventoryItem(1)){
                invList[1] = "Key";
            }
            String invItems = String.join(", ", invList);
            inv += invItems;
        }else{
            inv = "Your inventory is empty.";
        }
        return inv;
    }
}
