package com.chiteshlab.twitter;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class TweeterInvoker implements Runnable{
	
	
	
	@Autowired
	private Twitter twitter;
	
	List<Tweet> tweets;
//	String tag = "@ElsevierNews";
	
	
	
	public List<Tweet> getAllTweets(){
		
//		 (new Thread(new TweeterInvoker())).start();
		 
		
		String tag = "ElsevierNews";
//		String tag = "ChiteshChandan";
		
		 List<StreamListener> listeners = new ArrayList<StreamListener>();

		 
		 
		 StreamListener stream = new TweetListner();
		 listeners.add(stream);
		 
		 SearchResults result = twitter.searchOperations().search(tag);
		 System.out.println(" since id"+result.getSearchMetadata().getSinceId());
		 
		 List<Tweet> search = result.getTweets();
		 
		 tweets = twitter.searchOperations().search(tag, 1000).getTweets();
		 
		twitter.streamingOperations().sample(listeners);
		
		System.out.println(listeners.size());
		
		System.out.println("chitesh twitts size 2: "+tweets.size());

/*		
		tweets.forEach((p) -> {
			System.out.println("Text: "+p.getText());
			System.out.println("Entities :"+p.getEntities());
			System.out.println("Source :" +p.getSource());
			System.out.println("Date :"+p.getCreatedAt());
		});*/
		
		
//		System.out.println("Text : "+tweet.getText());
//		twitter.timelineOperations()
		
		return tweets;
	}



	@Override
	public void run() {
		 System.out.println("Hello from a thread! 2");
		 /*SearchResults result = twitter.searchOperations().search(tag);
		 System.out.println(" since id"+result.getSearchMetadata().getSinceId());
		 List<Tweet> search = result.getTweets();
		 System.out.println("Search result "+search.size());*/
	}
/*	
	public void connection(HttpServletRequest request, HttpServletResponse response){
		String consumerKey = "OSaEF8QoJE1Wx20W3xrbDaLH2";
		String consumerSecret = "nX5HDPaJUMH7oh0eBmjRh9HdtCrGXFxtJ88aEJWay98936NmVU";
		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(consumerKey, consumerSecret);
		
		
		OAuth2Operations oauthOperations = (OAuth2Operations) connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("https://my-callback-url");
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
		response.sendRedirect(authorizeUrl);
		// upon receiving the callback from the provider:
		AccessGrant accessGrant = new AccessGrant(accessToken);
		Connection<Twitter> connection = connectionFactory.createConnection(accessGrant);

	}*/

}
