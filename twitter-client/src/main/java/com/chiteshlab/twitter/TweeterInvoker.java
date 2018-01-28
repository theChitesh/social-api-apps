package com.chiteshlab.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

/**
 * Class used to maked connection to twitter api and fetch the tweets.
 * @author chitesh
 *
 */
@Service
public class TweeterInvoker{
	
	
	private final int NUMBER_OF_TWEETS = 100;
	
	@Autowired
	private Twitter twitter;
	
	List<Tweet> tweets;
	
	
	/**
	 * Method retrieves last hundred tweets posted by ElsevierNews
	 * @return - list of Tweet
	 */
	public List<Tweet> getAllTweets(){
		
		final String postedBy = "ElsevierNews";
		return twitter.searchOperations().search(postedBy, NUMBER_OF_TWEETS).getTweets();

	}


}
