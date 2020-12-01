package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * This class is visited each time
 * a user total is incremented - Visitor pattern
 */

public class TotalUserVisitor implements Visitable {
	
    private static int userTotal;
	
    public TotalUserVisitor() {}
	
    // returns total number of users
    public int getUserTotal() {
	return userTotal;
    }

    // accepts a TotalUserVisitor visitor
    @Override
    public void accept(Visitor visit) {
	userTotal++;
	visit.visit(this);
		
    }

}
