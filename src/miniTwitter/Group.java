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
	
    // list of UserElement type entries
    private List<UserElement> userEntries;
    private String groupID;
	
    public Group(String groupName) {
	groupID = groupName;
    }
	
    public List<UserElement> getUserEntries() {
	return userEntries;
    }
	
    @Override
    public String getUserID() {
	return groupID;
    }
	
}
