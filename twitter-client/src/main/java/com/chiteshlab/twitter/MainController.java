package com.chiteshlab.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Autowired
	private Twitter twitter;

	@Autowired
	private TweeterInvoker invoker;

	@GetMapping("/")
	public String home(ModelMap modelMap) {
		// Tweet tweet = new Tweet(1l, "1b", "Text", new Date(), "fromUser",
		// "profileImageUrl", 12l, 12l, "languageCode", "source");
		// Tweet tweet = new tw
		// System.out.println("Tweets : "+this.getTweets(null).size());
		modelMap.put("tweets", invoker.getAllTweets());
		modelMap.put("hello", "Chitesh");
		return "hello";
	}

	@GetMapping("/v1/tweets")
	public String getTest(ModelMap modelMap) {
		System.out.println("here in test 2 " + invoker.getAllTweets());

		modelMap.put("obj", invoker.getAllTweets());
		return "twitter";
	}

}
