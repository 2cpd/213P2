package application;

public class Tag {
	private String tagType;
	private String tagValue;
	
	public Tag(String tagName, String tagValue) {
		this.tagType = tagName; this.tagValue = tagValue;
	}
	
	public String getTagType() {
		return tagType;
	}
	
	public String getTagValue() {
		return tagValue;
	}
}
