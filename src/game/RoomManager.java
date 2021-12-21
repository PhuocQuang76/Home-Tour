package game;

import fixtures.Door;
import fixtures.Room;
import helpers.RoomDirections;

public class RoomManager {

    static {
        init();
    }

    public static Room getStartingRoom() {
        return startingRoom;
    }

    //Variables
    private static Room startingRoom;  //the starting room for the program.
    private static Room[] rooms;

    // private static Map <Integer, String> rooms = new HashMap<>();

    //methods
    public static void init(){
        rooms = new Room[5];   //all the room in the house

        //Create 5 rooms and set them into the array.
        Room foyer = new Room(
                "The Foyer",
                "A colorful small foyer",
                "A foyer often has a coat closet or adequate space \n" +
                        "to store all of your guests' belongings."
        );

        Room livingRoom = new Room(
                "The Living Room",
                "A harmonious and joyful living room",
                "Shapely furniture in warm hues is layered alongside a classic sofa \n" +
                        "in chalky white linen, punctuated with accessories in jewel brights."
        );
        Room kitchen = new Room(
                "The Kitchen",
                "This kitchen was designed with the ceramic collection",
                "the blue mid century serve ware and tableware to anchor the entire space,\n" +
                        "so they mixed in open cubes and added splashes of red for a vibrant \n" +
                        "yet straightforward color palette."
        );
        Room masterBedroom = new Room(
                "The Master Bedroom",
                "My favorite colors, feelings, and collections master bedroom.",
                "soothing shades and a restful palette of monochromatic tones.\n" +
                        "Remember color theory: gentle hues of blue, lavender, or green are \n" +
                        "considered calm and serene. Rich jewel-toned hues help set the mood \n" +
                        "of coziness and comfort   q   "
        );
        Room kidBedroom = new Room(
                "The Kid Bedroom",
                "an alive kid bedroom with the colourful beds, tables.",
                "The colors of the room are green, linen, white, fossil.\n " +
                        "An industrial desk and some books on it. A plant and a pen cup look nice \n" +
                        "on the desk. Add a toy robot and a race car. A carpet on the floor and \n" +
                        "a bookshelf on the wall made the room look smart."
        );

        rooms[0] = foyer;
        rooms[1] = livingRoom;
        rooms[2] = kitchen;
        rooms[3] = masterBedroom;
        rooms[4] = kidBedroom;

        Door doorFromFoyerAndLivingRoom = new Door();
        Door doorLivingRoomAndKitchen = new Door();
        Door doorLivingRoomAndMasterBedroom = new Door();
        Door doorKitchenAndKidsBedroom = new Door();
        Door doorMasterBedroomAndKidsBedroom = new Door();

        //Set exits for foyer room to living room
        foyer.setExit(RoomDirections.EAST, new Exit(livingRoom, doorFromFoyerAndLivingRoom));

        //Set exits for living Room to kitchen, master bedroom, foyer
        livingRoom.setExit(RoomDirections.SOUTH, new Exit(kitchen, doorLivingRoomAndKitchen));
        livingRoom.setExit(RoomDirections.WEST, new Exit(masterBedroom, doorLivingRoomAndMasterBedroom));
        livingRoom.setExit(RoomDirections.EAST, new Exit(foyer, doorFromFoyerAndLivingRoom));

        //Set exist for Kitchen to livinng room, kid bedroom
        kitchen.setExit(RoomDirections.NORTH, new Exit(livingRoom, doorLivingRoomAndKitchen));
        kitchen.setExit(RoomDirections.EAST, new Exit(kidBedroom, doorKitchenAndKidsBedroom));

        //Set exits for master Bedroom to living room, kifd bedroom
        masterBedroom.setExit(RoomDirections.WEST, new Exit(livingRoom, doorLivingRoomAndMasterBedroom));
        masterBedroom.setExit(RoomDirections.SOUTH, new Exit(kidBedroom, doorMasterBedroomAndKidsBedroom));

        //Set exits for kid Bedroom to master bedroom , kitcheen.
        kidBedroom.setExit(RoomDirections.NORTH, new Exit(masterBedroom, doorMasterBedroomAndKidsBedroom));
        kidBedroom.setExit(RoomDirections.WEST, new Exit(kitchen, doorKitchenAndKidsBedroom));

        startingRoom = foyer;
    }

}
