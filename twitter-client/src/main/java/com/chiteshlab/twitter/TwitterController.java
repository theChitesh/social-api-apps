package com.chiteshlab.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * https://spring.io/guides/gs/messaging-stomp-websocket/
 * @author chitesh
 *
 */
@RestController
@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {

	public static final String TWITTER_BASE_URI = "lab/v1/tweets";
	
	@Autowired
	private Twitter twitter;
	
	@RequestMapping(value="{hashTag}" ,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Tweet> getTweets(@PathVariable final String hashTag){
//		Event
		
		System.out.println("Ready "+hashTag);
//		twitter.streamingOperations().
		List<Tweet> tweets =  twitter.searchOperations().search(hashTag, 20).getTweets();
		return tweets;
	}
}
