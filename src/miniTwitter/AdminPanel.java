package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * Creates admin GUI and UI buttons to add users,
 * add groups, display total users, display
 * total groups, display total tweets, display
 * positive tweets percentage, display last 
 * updated user, and display the validation of 
 * both user and group IDs
 */

import javax.swing.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminPanel extends JFrame implements Visitable {
	
    // static instance of required Singleton pattern
    private static AdminPanel instance_object = null;
    private static HashMap<String, User> mapOfUsers = new HashMap<String, User>();
    private static HashMap<String, Group> mapOfGroup= new HashMap<String, Group>();
    private JPanel controlPanel;
    private String userID, groupID;
    private JTextField userTextBox, groupTextBox;
    private JButton userButton,		
		    groupButton,		
		    userViewButton,		
		    userTotalButton,		
		    groupTotalButton,		
		    totalTweetsButton,		
		    posPercentButton,
		    validateIDButton,
		    recentUserButton;
    private JTree tree;
    private TreeModel treeModel;
    private DefaultMutableTreeNode rootGroup, currentNode;	
    private JScrollPane treeScrollPane;
	
    // sets the Root Group for tree view
    // private constuctor - Singleton pattern
    private AdminPanel() {	
	rootGroup = new DefaultMutableTreeNode("Root");
	// calls the GUI components
	generateUIComponents();	
	// calls tree function
	manageTree();
    }
		
    // static getter of Singleton pattern
    public static AdminPanel getInstance() {
	if (instance_object == null) {
	    synchronized (AdminPanel.class) {
		if (instance_object == null) {
		    instance_object = new AdminPanel();
		}
	    }
        }
	return instance_object;
    }
	
    public void generateTextBox() {
		
	// generates GUI for user text box
	userTextBox = new JTextField(30);
	userTextBox.setBounds(185, 8, 220, 20);
	userTextBox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	controlPanel.add(userTextBox);
		
	// generates GUI for group text box
	groupTextBox = new JTextField(30);
	groupTextBox.setBounds(185, 40, 220, 20);
	groupTextBox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	controlPanel.add(groupTextBox);
	    
    }
	
    // generates buttons for panel
    public void generateButtons() {
		
	// generates user button
	userButton = new JButton("Add User");
	userButton.setBounds(417, 8, 218, 20);
	userButton.addActionListener(new UserButton());
	controlPanel.add(userButton);
		
	// generates group button
	groupButton = new JButton("Add Group");
	groupButton.setBounds(415, 40, 220, 20);		
	groupButton.addActionListener(new GroupButton());
	controlPanel.add(groupButton);
		
	// generates user total button
	userTotalButton = new JButton("Show User Total");
	userTotalButton.setBounds(185, 250, 220, 20);
	userTotalButton.addActionListener(new TotalUserButton());
	controlPanel.add(userTotalButton);
				
	// generates user view button
	userViewButton = new JButton("Open User View");
	userViewButton.setBounds(178, 75, 464, 27);
	userViewButton.addActionListener(new EnterUserButton());
	controlPanel.add(userViewButton);
		
	// generates group total button
	groupTotalButton = new JButton("Show Group Total");
	groupTotalButton.setBounds(415, 250, 220, 20);
	groupTotalButton.addActionListener(new TotalGroupButton());
	controlPanel.add(groupTotalButton);
		
	// generates total tweets button
	totalTweetsButton = new JButton("Show Tweet Total");
	totalTweetsButton.setBounds(185, 280, 220, 20);
	totalTweetsButton.addActionListener(new TotalTweetsButton());
	controlPanel.add(totalTweetsButton);
		
	// generates positive percentage button
	posPercentButton = new JButton("Show Positive Percentage");
        posPercentButton.setBounds(415, 280, 220, 20);
        posPercentButton.addActionListener(new TotalPosTweetsButton());
        controlPanel.add(posPercentButton);
        
        // generates user/group ID validation button
        validateIDButton = new JButton("All IDs Valid?");
        validateIDButton.setBounds(185, 220, 220, 20);
        validateIDButton.addActionListener(new ValidateIDButton());
        controlPanel.add(validateIDButton);
        
        // generates last updated/most recent user button
        recentUserButton = new JButton("Last Updated User");
        recentUserButton.setBounds(415, 220, 220, 20);
        recentUserButton.addActionListener(new LastUserButton());
        controlPanel.add(recentUserButton);
        
    }
	
    // initializes all GUI components
    public void generateUIComponents() {
		
	setTitle("Mini-Twitter");
		
	setBounds(100, 100, 644, 340);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	controlPanel = new JPanel();
	controlPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
	setContentPane(controlPanel);
	controlPanel.setLayout(null);
	controlPanel.setBackground(Color.lightGray);
		
	// method generates all text boxes
	generateTextBox();
		
	//method generates all buttons of control panel
	generateButtons();
	    
    }
	
    // builds tree of users and groups
    public void manageTree() {
		
	// generates tree for Root Group
        tree = new JTree(rootGroup);
        tree.setBounds(100, 100, 220, 300);
        controlPanel.add(tree);
        treeModel = tree.getModel();

        treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setBounds(10, 6, 165, 295);
        treeScrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        controlPanel.add(treeScrollPane);
	    
    }
	
    // returns the mapOfUsers
    public static HashMap<String, User> totalUserMap() {
	return mapOfUsers;
    }
	
    // adds users to either Group or Root (tree)
    private class UserButton implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent e){	
			
	    // handles user text box
	    userID = userTextBox.getText();
	    currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

	    if (userID.isEmpty() == true) {
		JOptionPane.showMessageDialog(null, "Please enter a user!");
				
	    } else if (mapOfUsers.containsKey(userID)) {
		JOptionPane.showMessageDialog(null, "The user \"" + userID + "\" already exists - please enter a new user!");
				
	    } else if (currentNode == null) {
		JOptionPane.showMessageDialog(null, "Sorry, you can only add to a Group or Root!");
				
	    } else if (mapOfUsers.containsKey(currentNode.toString())) {
		JOptionPane.showMessageDialog(null, "Sorry, users can only be created in Groups or Root!");
				
	    } else {
				
		// creates timestamp of when user was created
		Long timeStamp = System.currentTimeMillis();
		// creates a new User object
		User twitterUser = new User(userID);
		twitterUser.setCreationTime(timeStamp);
		mapOfUsers.put(userID, twitterUser);
				
		// visits TotalUserVisitor class
		TotalUserVisitor utv = new TotalUserVisitor();
		utv.accept(new DisplayVisitor());
				
		if (currentNode == null) {
		    rootGroup.add(new DefaultMutableTreeNode(userID));
					
		} else {
		    currentNode.add(new DefaultMutableTreeNode(userID));
			
		}	
		JOptionPane.showMessageDialog(null, userID + " has been added!");
		((DefaultTreeModel) treeModel).reload();
		    
	    }
		
	}
	    
    }

    // adds group to Root or other Group
    private class GroupButton implements ActionListener {
			
	@Override
	public void actionPerformed(ActionEvent e) {	
				
	    // handles group text box
	    groupID = groupTextBox.getText();
	    currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
	    if (groupID.isEmpty() == true) {
		JOptionPane.showMessageDialog(null, "Please enter a Group name!");
					
	    } else if (mapOfGroup.containsKey(groupID)) {
		JOptionPane.showMessageDialog(null, "The Group name \"" + groupID + "\" already exists - please enter a new Group name!");
				
	    } else if (currentNode == null) {
		JOptionPane.showMessageDialog(null, "Sorry, you can only add to a Group or Root!");
					
	    } else if (mapOfUsers.containsKey(currentNode.toString())) {
		JOptionPane.showMessageDialog(null, "Sorry, users can only be created in Groups or Root!");
					
	    } else {
				
		// creates timestamp of when group was created
		Long timeStamp = System.currentTimeMillis();
		// creates a new Group object	
		Group twitterGroup = new Group(groupID);
		twitterGroup.setCreationTime(timeStamp);
		System.out.println("Group \"" + groupID + "\" Created At: " + timeStamp);
		mapOfGroup.put(groupID, twitterGroup);
				
		// visits GroupTotalVisitor class
		GroupTotalVisitor gtv = new GroupTotalVisitor();
		gtv.accept(new DisplayVisitor());
					
		if (currentNode == null) {
		    rootGroup.add(new DefaultMutableTreeNode(groupID));
			
		} else {	
		    currentNode.add(new DefaultMutableTreeNode(groupID));
			
		} 
		JOptionPane.showMessageDialog(null, groupID + " has been added!");
		((DefaultTreeModel) treeModel).reload();
		    
	    }
		
	}
	    
    }
	
    // validates the user IDs and group IDs
    private class ValidateIDButton implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent e) {
			
	    // determine if all elements (user/group IDs) are valid
	    boolean allValid = true;
			
	    for (Iterator<Entry<String, Group>> iterator = mapOfGroup.entrySet().iterator(); iterator.hasNext() && allValid;) {
		Entry<String, Group> groupElement = iterator.next();
		String groupKey = (String)groupElement.getKey();
				
		// checks conditions if IDs are duplicates or contain spaces
		if(mapOfUsers.containsKey(groupKey) || groupKey.contains(" ")) {
		    allValid = false;
		}

	    }
			
	    for (Iterator<Entry<String, User>> iterator = mapOfUsers.entrySet().iterator(); iterator.hasNext() && allValid;) {
		Entry<String, User> userElement = iterator.next();
		String userKey = (String)userElement.getKey(); 
	            
		    // checks conditions if IDs are duplicates or contain spaces
	            if(mapOfGroup.containsKey(userKey) || userKey.contains(" ")) {
	            	allValid = false;
	            }    
	    }
			
	    if (mapOfGroup.isEmpty() == true && mapOfUsers.isEmpty() == true) {
		JOptionPane.showMessageDialog(null, "Sorry, there are no IDs to validate!");
	    }
			
	    else if (allValid) {
		JOptionPane.showMessageDialog(null, "Yes, all IDs are valid!");
	    }
			
	    else {
		JOptionPane.showMessageDialog(null, "No, not all IDs are valid!");
	    }
		
	}
	    
    }
	
    // gets the last user (most recent user) with updates
    private class LastUserButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
			
	    DisplayVisitor recentUser = new DisplayVisitor();
	    String lastUpdated = recentUser.getLastUser();
			
	    if (lastUpdated == null) {
	        JOptionPane.showMessageDialog(null, "No users have an update!");
	    }
			
	    else {
		JOptionPane.showMessageDialog(null, "Last Updated User: " + lastUpdated);
	    }
		
	}
	    
    }
		
    // displays UserUI button for user
    private class EnterUserButton implements ActionListener {
				
	@Override
	public void actionPerformed(ActionEvent e) {	
				
	    currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
	    if (currentNode == null) {
		JOptionPane.showMessageDialog(null, "Sorry, you need to select a user to view!");
		    	
            } else if (mapOfGroup.containsKey(currentNode.toString()) || currentNode.toString().equals("Root")) {
		JOptionPane.showMessageDialog(null, "Sorry, you cannot view a group - you need to select a user to view!");
		        
	    } else {   	
		User user = mapOfUsers.get(currentNode.toString());
		UserUI userUI = new UserUI(user);
		userUI.setVisible(true);
            }
		
	}
	    
    }
	
    // displays total number of users
    private class TotalUserButton implements ActionListener {
				
        @Override
	public void actionPerformed(ActionEvent e) {		

	    TotalUserVisitor userTotal = new TotalUserVisitor();
	    JOptionPane.showMessageDialog(null, "Total Number of Users: " + userTotal.getUserTotal());
			
	}
    }
	
    // displays total number of groups
    private class TotalGroupButton implements ActionListener {
					
	@Override
	public void actionPerformed(ActionEvent e) {
			
	    GroupTotalVisitor groupTotal = new GroupTotalVisitor();
	    JOptionPane.showMessageDialog(null, "Total Number of Groups: " + groupTotal.getGroupTotal());
		
	}
	    
    }
	
    // displays total number of tweets
    private class TotalTweetsButton implements ActionListener {
					
	@Override
	public void actionPerformed(ActionEvent e) {

	    TotalTweetsVisitor tweetTotal = new TotalTweetsVisitor();
	    JOptionPane.showMessageDialog(null, "Total Number of Tweets: " + tweetTotal.getTotalTweets());
		
	}
	    
    }

    // displays the positive percentage of tweets
    private class TotalPosTweetsButton implements ActionListener {
					
	@Override
	public void actionPerformed(ActionEvent e) {
			
	    PositivePercentVisitor posTotal = new PositivePercentVisitor();
	    double percentOfTweets = posTotal.getPosPercent();
	    // round to two decimal places
	    String twoDec = String.format("%.2f", percentOfTweets);
	    JOptionPane.showMessageDialog(null, "Percent Positive Tweets: " + twoDec + "%");
	
	}
	    
    }

    @Override
    public void accept(Visitor visit) {
    }
	
}
