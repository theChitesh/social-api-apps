## Twitter Client Project ##

Project is consuming twitter api using Spring Boot Social starter for twitter. 
The api-id and api-secret is obtained by registering one of the application on Twitter developer portal.

The application makes connection to twitter server with OAuth 1.0 security protocol. Without using spring boot twitter social implemetation.
Further the application maps the list of tweets to custom list of result dto. 

The application fetches the tweets posted by ElsevierNews.


Currently the tweets are limited to latest 100 tweets and using Spring- Thymeleaf, html5, bootstrap and jquery the tweets are displayed on dashboard. The
dashboard can be filtered with with a given input box. 

## To Run twitter client 

1) Start TwitterClientApplication as a Java project
2) hit url : http://localhost:8080/v1/tweets