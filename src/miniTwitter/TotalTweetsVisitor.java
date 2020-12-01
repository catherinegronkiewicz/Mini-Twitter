package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 9 December 2020
 * 
 * This class is visited each time
 * a tweet total is incremented - Visitor pattern
 */

public class TotalTweetsVisitor implements Visitable {
	
    private static int tweetTotal;
	
    public TotalTweetsVisitor() {}
	
    // returns total number of tweets
    public int getTotalTweets() {
	return tweetTotal;
    }

    // accepts a TotalTweetsVisitor visitor
    @Override
    public void accept(Visitor visit) {
	tweetTotal++;
	visit.visit(this);	
    }
	
}
