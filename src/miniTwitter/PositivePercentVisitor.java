package miniTwitter;

/*
 * Catherine Gronkiewicz
 * Professor Sun
 * CS 3560, Section 01
 * 12 November 2020
 * 
 * This class is visited each time
 * a positive word/tweet is incremented/found - Visitor pattern
 */

public class PositivePercentVisitor implements Visitable {
	
    // getting total tweets from TotalTweetsVisitor class
    private TotalTweetsVisitor ttv = new TotalTweetsVisitor();

    public double totalTweets;
    public static int posTotal;
    public double positiveTweets;
    double posPercent = 0.0;
	
    // positive words/tweets
    String[] positiveWords = {"woohoo", "congrats", "awesome", "cool", "nice", "love", "excited"};
	
    public PositivePercentVisitor() {}
	
    // counts total positive tweets
    public void countPositiveTweets(String tweets) {
	for (int i = 0; i < positiveWords.length; i++) {
	    if (tweets.contains(positiveWords[i])) {
		posTotal++;
	    }
	}
    }
	
    // returns number of positive tweets
    public int getPosTotal() {
	return posTotal;
    }
	
    // method to calculate positive tweets amongst total tweets
    public void calcPosPercent() {
	totalTweets = (double)ttv.getTotalTweets();
	positiveTweets = (double)getPosTotal();
	posPercent = (positiveTweets/totalTweets) * 100.0;
    }
	
    // returns percentage of positive tweets
    public double getPosPercent() {
	calcPosPercent();
	return posPercent;
    }
	
    // accepts a PositivePercentVisitor visitor
    @Override
    public void accept(Visitor visit) {
	visit.visit(this);		
    }

}
