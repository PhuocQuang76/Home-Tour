package fixtures;

import exceptions.DoorIsClosedException;
import exceptions.NoDoorInDirectionException;
import exceptions.NoExitInDirectionException;
import game.Exit;
import helpers.RoomDirections;
import java.util.HashMap;

public class Room extends Fixture{
    private HashMap<RoomDirections, Exit> exits; //variables are enum & which room

    //Constructors
    public Room(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
        this.exits = new HashMap<>();
    }

    //Methods`
    public HashMap<RoomDirections, Exit> getExits() {
        return exits;
    }

    //Set direction and room set into The exits map
    public void setExit(RoomDirections direction, Exit exit) {
        exits.put(direction, exit);
    }

    public void openDoor(String direction) throws Exception {
        try {
            Exit exit = getExitFromDirection(direction);
            exit.getDoor().setOpen(true);
        } catch (NoExitInDirectionException err) {
            throw new NoDoorInDirectionException("There are no doors there.");
        }
    }

    private Exit getExitFromDirection(String direction) throws NoExitInDirectionException {
        try {
            RoomDirections d = RoomDirections.valueOf(direction.toUpperCase());
            Exit exit = exits.get(d);
            if (exit == null) {
                throw new NoExitInDirectionException("There are no exits there.");
            }
            return exit;
        } catch (IllegalArgumentException e) {
            throw new NoExitInDirectionException("There are no exits in that direction.");
        }
    }

    //Get the exit when you put the direction command
    public Room getExit(String direction) throws Exception {
        Room room = this; //stay in the current room;
//        try {
        //Room nextRoom = exits.get(d).getRoom(); //Get the value of map at key:"d".Value is room
        Exit exit = getExitFromDirection(direction);
        if (exit == null) {
            throw new NoExitInDirectionException("\n***No exits in that direction.***");
        } else {
            if (exit.getDoor().isOpen()) {
                room = exit.getRoom();
            } else {
                throw new DoorIsClosedException("The door is closed.");
            }
        }
//            if (nextRoom == null) {
//                throw new Exception("\n***No exits in that direction.***");
//            } else {
//                exit = nextRoom;

                //Add door code here
//                HashMap<RoomDirections,Exist> exitGates = nextRoom.getExits();
//                for(Map.Entry<RoomDirections, Exist> exitGate : exitGates.entrySet()) {
//                    if(!exitGate.getValue().getDoor().isOpen()){
//                        String answer;
//                        System.out.println("Do you want to open the door? ");
//                        Scanner input = new Scanner(System.in);
//                        answer = input.nextLine().toUpperCase();
//                        if(answer.equals("YES") || answer.equals("Y")){
//                            exitGate.getValue().getDoor().setOpen(!exitGate.getValue().getDoor().isOpen());
//                            System.out.println(exitGate.getValue().getDoor().isOpen());
//                            System.out.println("The door is opened !!!");
//                        }
//                    }
//                }



            //}
//        } catch (Exception err) {
//            System.out.println("\n***You cannot go there, there are no exits there.***");
//        }
        return room;
    }
}
