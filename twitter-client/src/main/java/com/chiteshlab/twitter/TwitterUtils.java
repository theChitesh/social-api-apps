package com.chiteshlab.twitter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TwitterUtils {

    private static final String POSTED_BY = "ElsevierNews";

    private static final int NONCE_LENGTH =  6;

    private static final int NUMBER_OF_TWEETS = 100;

    private static final int INITIAL_ID = 0;

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789";

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "OAuth oauth_consumer_key=\"OSaEF8QoJE1Wx20W3xrbDaLH2\"," +
                "oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\""+getTimeStampForHeader()+"\"," +
                "oauth_nonce=\""+getNonceForHeader()+"\"," +
                "oauth_version=\"1.0\",oauth_signature=\"LIkt4cmvndvEhB9%2FlioFscCCi1M%3D\"");

        return headers;
    }

    public MultiValueMap<String, String> getFirstCallParameters() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.put("q", asList(POSTED_BY));
        queryParams.put("since_id", asList(string(INITIAL_ID)));
        queryParams.put("count", asList(string(NUMBER_OF_TWEETS)));
        queryParams.put("include_entities", asList(string(1)));
        return queryParams;
    }

    public String getNonceForHeader(){

         String nonce = new Random().ints(NONCE_LENGTH, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        return nonce;
    }


    public long getTimeStampForHeader(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setTime(new Date());
        return calendar.getTimeInMillis() / 1000L;
    }

    public static String string(int i) {
        return Integer.toString(i);
    }

    public static List<String> asList(String... args) {
        return Arrays.asList(args);
    }
}
