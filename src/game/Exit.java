package game;

import fixtures.Door;
import fixtures.Room;
import helpers.RoomDirections;

import java.util.HashMap;

public class Exit {
    private Room room;
    private Door door;
    public Exit(Room room, Door door){
        this.room = room;
        this.door = door;
    }

    public Room getRoom() {
        return room;
    }

    public Door getDoor() {
        return door;
    }
}
