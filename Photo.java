package application;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Calendar;
import java.util.List;

public class Photo {
	private BufferedImage imageFile;
	private String caption;
	private Calendar date;
	private List<Tag> addedTags;
	
	public void addTag() {
		//add location tag
		//add peoples tag
		//add custom tag(s)
		//if name and type match: error msg, tag already exists
	}

	public void rmTag(Tag tagToBeRemoved) {
		//if name and type match: delete
		//if no name and type match: error msg, not found
	}
}

	