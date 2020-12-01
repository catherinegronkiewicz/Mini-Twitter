package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * This class is visited each time
 * to find the last updated user - Visitor pattern
 */

public class LastUpdatedVisitor implements Visitable {
	
    private String userID;
	
    public LastUpdatedVisitor(String userName) {
	userID = userName;
    }

    // get ID of latest updated user
    public String getUserID() {
	return userID;
    }

    // accepts a LastUpdatedVisitor visitor
    @Override
    public void accept(Visitor visit) {
	visit.visit(this);
    }

}
