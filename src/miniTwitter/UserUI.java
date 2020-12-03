package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Client - runs Composite pattern components;
 * creates user GUI and UI buttons to update 
 * followers (of new tweets), post tweets, 
 * and display followings list
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserUI extends JFrame {
	
    private User currentUser;
    private HashMap<String, User> mapOfUsers = AdminPanel.totalUserMap();
    private JPanel contentPane;
    private JScrollPane followingScrollPane, newsfeedScrollPane;
    private JTextField followUserTextField, postTweetField;
    private JButton followUserButton;
    private JButton postTweetButton;
    private JLabel currentlyFollowingLabel, userFeedLabel, timeCreationLabel;
    private ArrayList<String> listOfFollowings;
    private DefaultListModel<String> followingListModel, userFeedListModel;
    private JList<String> followingList, userFeedList;
    private String userTweet, userToFollow;
    private List<String> userFeed;
	
    @SuppressWarnings("unchecked")
    public UserUI(User user) {		
		
	// initializes the User "leaf" - Composite Pattern
	currentUser = user.getUser();
		
	// generates User UI components
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(200, 200, 440, 493);

	setTitle("Welcome to @" + currentUser.getUserID() + "'s Twitter!");
	contentPane = new JPanel();
	contentPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.setBackground(Color.lightGray);
		
	userFeedListModel = new DefaultListModel<String>();
	followingListModel = new DefaultListModel<String>();
		
	followUserButton = new JButton("Follow User");
	followUserButton.setBounds(217, 12, 210, 23);
	followUserButton.addActionListener(new UserUIButtons());
	contentPane.add(followUserButton);
		
	currentlyFollowingLabel = new JLabel("Currently Following:");
	currentlyFollowingLabel.setBounds(10, 45, 175, 15);
	contentPane.add(currentlyFollowingLabel);
		
	followingList = new JList<String>(followingListModel);
	followingList.setBounds(10, 65, 415, 145);
	contentPane.add(followingList);
	listOfFollowings = (ArrayList<String>) currentUser.getUserFollowings().clone();
		
	followUserTextField = new JTextField();
	followUserTextField.setBounds(10, 13, 200, 18);
	contentPane.add(followUserTextField);
	followUserTextField.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

	postTweetField = new JTextField();
	postTweetField.setBounds(10, 235, 270, 20);
	contentPane.add(postTweetField);
	postTweetField.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
		
	postTweetButton = new JButton("Post Tweet");
	postTweetButton.setBounds(290, 234, 142, 25);
	postTweetButton.addActionListener(new TweetButtons());
	contentPane.add(postTweetButton);

	userFeedLabel = new JLabel("News Feed:");
	userFeedLabel.setBounds(10, 270, 210, 15);
	contentPane.add(userFeedLabel);
		
	// last updated user label
	userFeedLabel = new JLabel("Last Updated: " + currentUser.getTimeUpdate());
	userFeedLabel.setForeground(Color.BLUE);
	userFeedLabel.setBounds(230, 270, 210, 15);
	contentPane.add(userFeedLabel);
		
	// time creation label
	timeCreationLabel = new JLabel("User \"" + currentUser.getUserID() +  "\" Created At: " + currentUser.getCreationTime());
	timeCreationLabel.setForeground(Color.BLUE);
	timeCreationLabel.setBounds(10, 434, 350, 40);
	contentPane.add(timeCreationLabel);

	userFeedList = new JList<String>(userFeedListModel);
	userFeedList.setBounds(10, 290, 415, 150);
	userFeed = (ArrayList<String>) currentUser.getUserFeed().clone();
	contentPane.add(userFeedList);
		
	// scroll pane for userfeed and following list
	scrollRefresh();
		
	// add the user tweets to the userfeed
	int userTweets = userFeed.size();
	for (int i = 0; i < userTweets; i++) {
	    userFeedListModel.addElement(userFeed.get(i));
	}
		
	// add the user followings to the list
	int followingsList = listOfFollowings.size();
	for (int i = 0; i < followingsList; i++){
	    followingListModel.addElement(listOfFollowings.get(i));
	}
		
    }
	
    public void scrollRefresh() {
		
	followingScrollPane = new JScrollPane(followingList);
	followingScrollPane.setBounds(10, 68, 415, 150);
	followingScrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	contentPane.add(followingScrollPane);

	newsfeedScrollPane = new JScrollPane(userFeedList);
	newsfeedScrollPane.setBounds(10, 290, 415, 150);
	newsfeedScrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	contentPane.add(newsfeedScrollPane);
	    
    }
	
    // posts tweets (to user's feed and their followers' feeds)
    private class TweetButtons implements ActionListener {
				
	@Override
	public void actionPerformed(ActionEvent e) {	
			
	    // get the ID of the current user
	    String currentID = currentUser.getUserID();
			
	    userTweet = "-   " + currentID + ": " + postTweetField.getText();
			
	    if (postTweetField.getText().isEmpty() == true) {
		JOptionPane.showMessageDialog(null, "Please type in the text box to post a tweet!");
	    }
			
	    else {
				
		// updates time of new tweets
		Long timestamp = System.currentTimeMillis();
				
		// current user's tweets are updated to followers
		currentUser.notifyObservers(userTweet);
		currentUser.update(userTweet, timestamp);
		userFeedListModel.addElement(userTweet);;
					
		// visits LastUpdatedVisitor class
		LastUpdatedVisitor mostRecent = new LastUpdatedVisitor(currentID);
		mostRecent.accept(new DisplayVisitor());
				
		// visits TotalTweetsVisitor class 
		TotalTweetsVisitor tweetTotal = new TotalTweetsVisitor();
		tweetTotal.accept(new DisplayVisitor());
					
		// visits PositivePercentVisitor class
		PositivePercentVisitor positiveTweets = new PositivePercentVisitor();
		positiveTweets.accept(new DisplayVisitor());
		positiveTweets.countPositiveTweets(userTweet);
				
	    }
		
	}
	    
    }	
	
    // follows users (with edge cases)
    private class UserUIButtons implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent e) {	
			
	    // get the ID of the current user
	    String currentID = currentUser.getUserID();
			
	    userToFollow = followUserTextField.getText();
			
	    if (userToFollow.isEmpty() == true) {
		JOptionPane.showMessageDialog(null, "Please enter a user to follow!");
			
	    } else if (!mapOfUsers.containsKey(userToFollow)) {
		JOptionPane.showMessageDialog(null, "User \"" + userToFollow + "\" does not exist!");
				
	    } else if(currentUser.getUserFollowings().contains(userToFollow)) {
		JOptionPane.showMessageDialog(null, "You already follow the user \"" + userToFollow + "\"!");
				
	    } else if (userToFollow.equals(currentID)) {
		JOptionPane.showMessageDialog(null, "You cannot follow yourself!");
				
	    } else {
				
		// attach new follower to current user
		currentUser.attach(userToFollow);
		JOptionPane.showMessageDialog(null, "You are now following " + userToFollow + "!");
		followingListModel.addElement(userToFollow);
		    
	    }
		
	}
	    
    }
	
}
