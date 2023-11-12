package application;

import java.util.List;

public class Admin {
	private final String username = "admin"; //no password for simplicity
	private List<User> listOfUsers;
	//there can be only one admin
	
	public Admin() {
		//not sure what to add in constructor with username set to final
	}
	
	public List<User> getUsers() {
		return listOfUsers;
	}
	
	public void addUser(String name) {
		listOfUsers.add(new User(name));
	}
	
	public void rmUser(String name) {
		for (int i = 0; i < listOfUsers.size(); i++) {
			if (listOfUsers.get(i).getUsername().equals(name)) {
				listOfUsers.remove(i);
				break;
			}
        }
		//error msg if not found
	}
}
