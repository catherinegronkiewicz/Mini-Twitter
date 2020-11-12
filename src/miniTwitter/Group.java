package miniTwitter;

import java.util.List;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Serves as a Composite and Root - Composite pattern
 */

public class Group implements UserElement {
	
	// list of UserElement entries
	private List<UserElement> entries;
	private String groupID;
	
	public Group(String groupName) {
		groupID = groupName;
	}
	
	@Override
	public String getUserID() {
		return groupID;
	}
}