package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * Objects (users) being observed - Observer pattern
 */

public interface Subject {
	
	// method that adds new user to following list
	public void attach(String userName);
	// method to notify users of new tweets 
    public void notifyObservers(String userTweet);

}
