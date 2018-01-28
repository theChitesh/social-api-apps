package com.chiteshlab.twitter;

import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

public class TweetListner implements StreamListener{

	@Override
	public void onTweet(Tweet tweet) {
		System.out.println("On Tweet");
		
	}

	@Override
	public void onDelete(StreamDeleteEvent deleteEvent) {
		System.out.println("on Delete");
		
	}

	@Override
	public void onLimit(int numberOfLimitedTweets) {
		System.out.println("on number of limited tweets");
		
	}

	@Override
	public void onWarning(StreamWarningEvent warningEvent) {
		System.out.println("on warning");
		
	}

}
