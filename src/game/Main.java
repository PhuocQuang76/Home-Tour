package game;

import exceptions.DoorIsClosedException;
import fixtures.Door;
import helpers.RoomDirections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static String collectInput;

    public static void main(String[] args) throws Exception {
        Player player = new Player();
        printRoom(player);  //Print out the initial room to the console.
        System.out.println("\n             ******* INSTRUCTION *******");
        System.out.println("You can follow by one of these below commands:");
        System.out.println("1) Enter 'go' and exits direction to move to another room.\n" +
                           "   Ex:'go north','go east','go west','go south',");
        System.out.println("2) Enter 'open' and exit direction to open the exit door you want to open.\n" +
                           "   Ex:\"open north\",'open east','open west','open south',");
        System.out.println("3) Enter 'quit' or 'q' to quit the tour !");

        loopCommand(player);
    }

    private static void loopCommand(Player player) {
        String[] command;
        //printRoom(player);
        do {
            command = collectInput();
            try {
                player.handleCommand(command);
                printRoom(player);
                System.out.println("\n             ******* INSTRUCTION *******");
                System.out.println("You can follow by one of these below commands:");
                System.out.println("1) Enter 'go' and exits direction to move to another room.\n" +
                        "   Ex:'go north','go east','go west','go south',");
                System.out.println("2) Enter 'open' and exit direction to open the exit door you want to open.\n" +
                        "   Ex:\"open north\",'open east','open west','open south',");
                System.out.println("3) Enter 'quit' or 'q' to quit the tour !");
                System.out.println("Type your command here ⇊⇊⇊_ ");
                loopCommand(player);
            } catch (DoorIsClosedException e) {
                System.out.println("The door is closed to that room. Do you want to open it (Y/N)");
                String[] openDoor = collectInput();
                if (openDoor.length == 1 && (openDoor[0].toUpperCase().equals("Y")) || openDoor[0].toUpperCase().equals("YES") ) {
                    if (player.openDoor(command[1])) {
                        try {
                            player.move(command[1]);
                            printRoom(player);
                        } catch (DoorIsClosedException ex) {
                            //Do nothing, dispose of the error, player doesn't move.
                        }
                    }
                }
            }
        } while (command.length > 1 && !command[0].toUpperCase().equals("q") || !command[0].toUpperCase().equals("quit"));
    }

    public static String[] collectInput() {
        System.out.println("************************************************************************\n");
        Scanner input = new Scanner(System.in);
        String[] command = input.nextLine().split(" ");
        return command;
    }


    private static void printRoom(Player player) {
        //Print out the room and long description.
        System.out.println(player.getCurrentRoom().getName());
        System.out.println(player.getCurrentRoom().getLongDescription() + "\n");

        System.out.println("Exits:");
        HashMap<RoomDirections, Exit> exitGates = player.getCurrentRoom().getExits();
        for(Map.Entry<RoomDirections, Exit> exitGate : exitGates.entrySet()) {
                System.out.print(exitGate.getKey().toString() + ": ");
                if (exitGate.getValue().getDoor().isOpen()) {
                    System.out.println(exitGate.getValue().getRoom().getShortDescription());
                } else {
                    System.out.println("The door is closed.");

                }
        }
    }


    private static void parse(String[] command, Player player) throws Exception {
        //player.setCurrentRoom() = player.getCurrentRoom().getExit(command[1]);
        player.move(command[1]);
    }
}
