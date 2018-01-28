## Twitter Client Project ##

Project is consuming twitter api using Spring Boot Social starter for twitter. 
The api-id and api-secret is obtained by registering one of the application on Twitter developer portal.

With the use of api-id and secret. Spring Twitter can eastablish connection to twitter server and using search operation, the application fetches the 
tweets posted by ElsevierNews.

Currently the tweets are limited to latest 100 tweets and using Spring- Thymeleaf, html5, bootstrap and jquery the tweets are displayed on dashboard. The
dashboard can be filtered with with a given input box. 

## To Run twitter client 

1) Start TwitterClientApplication as a Java project
2) hit url : http://localhost:8080/v1/tweets