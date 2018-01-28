package com.chiteshlab.twitter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Class used to make connection to twitter api and fetch the tweets.
 * @author chitesh
 *
 */
@Service
public class TweeterInvoker{
	

//	@Autowired
//	private Twitter twitter;
	

	@Autowired
	private TwitterUtils utils;

	private final static String BASE_URL = "https://api.twitter.com/1.1/search/tweets.json";
	private final static ObjectMapper mapper = new ObjectMapper();
	private final static List<ResultDto> dataToBeDisplayed = new ArrayList<>();
	private static String nextResourceUrl = BASE_URL;
	private Predicate<MultiValueMap<String, String>> isNoParameters = queryParams -> queryParams == null || queryParams.size() == 0;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	/**
	 * Method schedules the pulling of tweets.
	 * @return - list of Tweet
	 */
	public List<ResultDto> getAllTweets(){

		try {
			startProcess();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataToBeDisplayed;

	}

	private void startProcess() throws IOException {
		performFirstCall(BASE_URL);
        scheduler.scheduleWithFixedDelay(() -> nextResourceTask(), 10, 20, TimeUnit.SECONDS);
//		System.out.println("Data to be displayed :" + dataToBeDisplayed.size());
	}

	private void nextResourceTask() {
		if (nextResourceUrl.equals(BASE_URL)) {
			System.out.println("First call not yet completed successfully. Waiting for first call to complete");
			return;
		}
		try {
			performOperation(nextResourceUrl, null);
		} catch (IOException e) {
			e.printStackTrace();
			//TODO : handle the failure
		}
	}

	private void performFirstCall(final String httpUrl) throws IOException {
		performOperation(httpUrl, utils.getFirstCallParameters());
	}


	private void performOperation(final String httpUrl,
										 final MultiValueMap<String, String> firstCallParameters) throws IOException {
		final UriComponentsBuilder builder = buildUrl(httpUrl, firstCallParameters);
		HttpHeaders headers = utils.getHttpHeaders();
		final HttpEntity<?> entity = new HttpEntity<>(headers);
		final RestTemplate template = new RestTemplate();

		try {
			final ResponseEntity<String> response = template.exchange(
					builder.build().toUri(),
					HttpMethod.GET,
					entity,
					String.class
			);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				final String data = response.getBody().toString();
				final StatusResults statusResults = mapper.readValue(data, StatusResults.class);
				if (null != statusResults.getData() && statusResults.getData().size() > 0) {
					final String nextUrl = mapper.readTree(data).get("search_metadata").get("next_results").textValue();
					nextResourceUrl = BASE_URL + nextUrl;
					dataToBeDisplayed.addAll(statusResults.getData());
				}

			}
		} catch (final HttpClientErrorException exp) {
			switch (exp.getStatusCode()) {
				case UNAUTHORIZED:
					System.out.println("Unauthorized");
					break;
				case FORBIDDEN:
					System.out.println("Invalid authorization");
			}
		} catch (final RestClientException exp) {
			exp.printStackTrace();
			//TODO : define what to if failure
		} catch (final Exception exp) {
			exp.printStackTrace();
		}
	}

	private UriComponentsBuilder buildUrl(String httpUrl, MultiValueMap<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl);
		return isNoParameters.test(queryParams) ? builder : builder.queryParams(queryParams);
	}

}
