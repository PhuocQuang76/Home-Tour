package game;


import exceptions.DoorIsClosedException;
import exceptions.NoExitInDirectionException;
import fixtures.Room;
import helpers.AllowedCommands;


public class Player {
    private Room currentRoom = RoomManager.getStartingRoom();

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void handleCommand(String ...command) throws DoorIsClosedException {
        try {
            AllowedCommands playerCommand = AllowedCommands.valueOf(command[0].toUpperCase());
            if (playerCommand == AllowedCommands.GO) {
                move(command[1]);
            } else if (playerCommand == AllowedCommands.OPEN) {
                // open the door
                openDoor(command[1]);
            }
        } catch (IllegalArgumentException err) {
            System.out.println("That command is not valid.  It has to be \"open\" or \"go.\"\n");
        }
    }

    public boolean openDoor(String direction) {
        try {
            currentRoom.openDoor(direction);
            String nextRoomName = currentRoom.getExit(direction).getName();
            System.out.println("Creek! The " + direction + " door to " + nextRoomName + " is opened.");
            System.out.println("---------------------");
            return true;
        } catch (Exception e) {
            System.out.println("There are no doors in that direction.\n");
            return false;
        }
    }

    //Move to the room user want to go in.
    public Room move(String direction) throws DoorIsClosedException {
        try {
            currentRoom = currentRoom.getExit(direction);
        } catch (DoorIsClosedException e) {
            throw e;
        } catch (NoExitInDirectionException e) {
            System.out.println("There are no exits in that direction.\n");
        } catch (Exception e) {
            System.out.println("You did something I don't know how to handle. Try again?\n");
        }
        return currentRoom;
    }
}
