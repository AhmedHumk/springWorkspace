package com.addo.basicserver.house;

public class Rooms {
	private String RoomName;
	private int RoomNumber;
	
	public Rooms(String roomName, int roomNumber) {
		super();
		RoomName = roomName;
		RoomNumber = roomNumber;
	}
	
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public int getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}
	
	
}
