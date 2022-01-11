package game;

import exceptions.DoorIsClosedException;
import helpers.AllowedCommands;
import helpers.RoomDirections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Player player = new Player();
        printRoom(player);  //Print out the initial room to the console.
        System.out.println("\n             ******* INSTRUCTION *******\n" +
        "You can follow by one of these below commands:\n" +
        "1) Enter 'go' and exits direction to move to another room.\n" +
                "   Ex:'go north','go east','go west','go south',\n" +
        "2) Enter 'open' and exit direction to open the exit door you want to open.\n" +
                           "   Ex:\"open north\",'open east','open west','open south',\n" +
        "3) Enter 'quit' or 'q' to quit the tour !");

        String[] command = {} ;
        //printRoom(player);
        AllowedCommands allowedCommands = null;
        do {
            try {
                command = collectInput();
                allowedCommands = AllowedCommands.valueOf(command[0].toUpperCase());


                if (command.length == 1 && allowedCommands == AllowedCommands.QUIT || allowedCommands == AllowedCommands.Q) {
                    System.out.println("Command: " + command[0]);
                    return;
                }

                try {
                    //command= collectInput();
                    player.handleCommand(command);
                    printRoom(player);
                    System.out.println("\n             ******* INSTRUCTION *******\n" +
                            "You can follow by one of these below commands:\n" +
                            "1) Enter 'go' and exits direction to move to another room.\n" +
                            "   Ex:'go north','go east','go west','go south',\n" +
                            "2) Enter 'open' and exit direction to open the exit door you want to open.\n" +
                            "   Ex:\"open north\",'open east','open west','open south',\n" +
                            "3) Enter 'quit' or 'q' to quit the tour !");

                    //loopCommand(player);
                } catch (DoorIsClosedException e) {
                    System.out.println("The door is closed to that room. Do you want to open it (Y/N)");
                    String[] openDoor = collectInput();
                    if (openDoor.length == 1 && (openDoor[0].toUpperCase() == ("Y")) || openDoor[0].toUpperCase().equals("YES")) {
                        if (player.openDoorAtPlayer(command[1])) {
                            try {
                                player.move(command[1]);
                                printRoom(player);
                            } catch (DoorIsClosedException ex) {
                                //Do nothing, dispose of the error, player doesn't move.
                            }
                        }
                        printRoom(player);
                    }else {
                        printRoom(player);
                    }

                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid command.");
                printRoom(player);
            }catch (IllegalArgumentException e){
                printRoom(player);
            }
                //} while (!command[0].toUpperCase().equals(AllowedCommands.Q) || !command[0].toUpperCase().equals(AllowedCommands.QUIT));
        } while (command.length!=1 && allowedCommands != AllowedCommands.QUIT || allowedCommands != AllowedCommands.Q);

    }


    public static String[] collectInput() {
        System.out.println("************************************************************************\n");
        Scanner input = new Scanner(System.in);
        String[] command = input.nextLine().split(" ");

        return command;
    }

    private static void printRoom(Player player) {
        //Print out the room and long description.
        System.out.println("\n-------------------------------------------------------");
        System.out.println(player.getCurrentRoom().getName());
        System.out.println(player.getCurrentRoom().getLongDescription() + "\n");

        System.out.println("Exits:");
        HashMap<RoomDirections, Exit> exitGates = player.getCurrentRoom().getExits();
        for(Map.Entry<RoomDirections, Exit> exitGate : exitGates.entrySet()) {
            System.out.print(exitGate.getKey().toString() + ": ");
            if (exitGate.getValue().getDoor().isOpen()) {
                System.out.println(exitGate.getValue().getRoom().getShortDescription());
            } else {
                System.out.println( "-> to " + exitGate.getValue().getRoom().getName() + " : (The door is closed.)");

            }
        }
    }


    private static void parse(String[] command, Player player) throws Exception {
        //player.setCurrentRoom() = player.getCurrentRoom().getExit(command[1]);
        player.move(command[1]);
    }
}
