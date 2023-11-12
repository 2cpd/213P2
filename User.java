package application;

import java.util.List;

public class User {
	private String username; //no password for simplicity
	private List<Album> createdAlbums;
	
	public User (String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
