package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Observers (users) are being iterated by Subject with
 * the update method - Observer pattern
 */

public interface Observer {

    public void update(String userTweet);
	
}
