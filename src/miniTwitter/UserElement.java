package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Base component of Composite pattern
 */

public interface UserElement {
	
    // returns current user's ID
    public String getUserID();
    // get the creation time of user or group
    public Long getCreationTime();
    public void setCreationTime(Long updateTime);
	
}
