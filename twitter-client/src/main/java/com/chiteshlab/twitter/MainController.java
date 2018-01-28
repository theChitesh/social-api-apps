package com.chiteshlab.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller 
 * @author chitesh
 *
 */
@Controller
public class MainController {

	
	@Autowired
	private TweeterInvoker invoker;

	@GetMapping("/v1/tweets")
	public String getTest(ModelMap modelMap) {
		modelMap.put("obj", invoker.getAllTweets());
		return "twitter";
	}

}
