package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Leaf of Composite pattern, as well as
 * implementing the Observer pattern
 */

import java.util.ArrayList;
import java.util.HashMap;

public class User implements UserElement, Observer, Subject {

	private String uniqueID;
	private HashMap<String, User> userMap = AdminPanel.totalUserMap();
	private ArrayList<String> userFollowers;
	private ArrayList<String> userFollowings;
	private ArrayList<String> userFeed;
	private User lastUpdated;
	private Long creationTime, lastUpdateTime;

	public User(String twitterUser) {
		uniqueID = twitterUser;
		userFollowings = new ArrayList<String>();
		userFollowers = new ArrayList<String>();
		userFeed = new ArrayList<String>();
	}
	
	public User getUser() {
		return User.this;
	}
	
	public ArrayList<String> getUserFollowers() {
		return userFollowers;
	}

	public ArrayList<String> getUserFollowings() {
		return userFollowings;
	}

	public ArrayList<String> getUserFeed() {
		return userFeed;
	}

	// add methods - Composite pattern
	public void addFollowings(String following) {
		userFollowings.add(following);
	}

	// add methods - Composite pattern
	public void addFollowers(String follower) {
		userFollowers.add(follower);
	}
	
	public User getLastUser() {
		return lastUpdated;
	}
	
	public Long getTimeUpdate() {
		return lastUpdateTime;
	}
	
	@Override
	public void setCreationTime(Long updateTime) {
		creationTime = updateTime;
	}
	
	@Override
	public Long getCreationTime() {
		return creationTime;
	}

	// update followers with new tweets - Observer pattern
	@Override
	public void update(String tweet, Long tweetTime) {
		userFeed.add(tweet);
		lastUpdateTime = tweetTime;
	}

	@Override
	public String getUserID() {
		return uniqueID;
	}

	@Override
	public void attach(String userName) {
		getUser().addFollowings(userName);
		// grabbing the following and updating followers list
		userMap.get(userName).addFollowers(getUser().getUserID());
	}

	@Override
	public void notifyObservers(String userTweet) {
		int followerCount = getUser().getUserFollowers().size();
		for (int i = 0; i < followerCount; i++) {
			String twitterFollower = getUser().getUserFollowers().get(i);
			Long timeStamp = System.currentTimeMillis();
			// updates each follower with new tweet
			userMap.get(twitterFollower).update(userTweet, timeStamp);
		}
	}
	
}