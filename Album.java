package application;

import java.util.List;

public class Album {
	
	private User creator;
	private List<Photo> photosInAlbum;
	
	public Album(User creator) {
		this.creator = creator;
	}
	
	public void addPhoto(Photo newPhoto) {
		photosInAlbum.add(newPhoto);
	}
	
	public void rmPhoto(int index) {
		photosInAlbum.remove(index);
	}
	
	public void caption() {
		
	}
	
	public List<Photo> search(int date) {
		return null;
	}
	
	public List<Photo> search(String tag) {
		return null;
	}
}
