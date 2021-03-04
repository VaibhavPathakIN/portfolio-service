# Stock Price Service

###Project Description:

	This service has been created by using spring framework.
	To handle requested functionality, below endpoints are present.
		1. /get-resource-info	: To get specified stock price on particular date from client, if exists.

###Project Start Steps:

    Pre-requisites:
    1. Java version 1.8 must be installed.
    2. Install maven module (https://maven.apache.org/install.html). Apache Maven 3.6.3 is required.

    Steps:
    1. To run this application, do the following:
        1.a. Go to the project root directory.
        1.b. Run the following commands in the terminal/command line to build the app:
            - mvn clean install
        1.c. Run the following command(s) in the terminal/command line to run the app:
            - java -jar ./target/portfolio-service.jar

    2. Go to http://localhost:9090/ in your browser to view it.
    
###Endpoint curl scripts:

    Pre-requisites:
    1. Application should be in running state on embeded server.

    Commands:
    1. get stock info : /get-resource-info
	    ```
	   curl -X POST -H "Content-Type: application/json" -d "{\"stockName\": \"AAN\", \"date\": \"2021-03-01\"}" http://localhost:8080/api/get-resource-info
	   curl -X POST -H "Content-Type: application/json" -d "{\"stockName\": \"MFT\", \"date\": \"2021-03-01\"}" http://localhost:8080/api/get-resource-info
	    ```