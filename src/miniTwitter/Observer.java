package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Observers (users) are being iterated by Subject with
 * the update method - Observer pattern
 */

public interface Observer {

    // takes in arguments of tweet and time tweet was posted
    public void update(String tweet, Long tweetTime);
	
}
