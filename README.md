# Portfolio Management Service

###Project Description:

	This service has been created by using spring framework.
	To handle requested functionality, below controllers and associated endpoints are present.
		1. Portfolio Controller:
			1.1. /portfolio/add : It is used to add a new portfolio in the system.
			1.2. /portfolio/update/{portfolio_id} : It is used to update an existing portfolio, if exist in the system.
			1.3. /portfolio/delete/{portfolio_id} : It is used to delete an existing portfolio, if exist in the system.
			1.4. /portfolio/getAll : It is used to fetch all portfolios in the system.
			1.5. /portfolio/getPrice : It is used to fetch the price of given stock on given date.
		
		2. Trade Controller:
			1.1. /trades/add : It is used to add a new trade in the system.
			1.2. /trades/update/{portfolio_id} : It is used to update an existing trade, if exist in the system.
			1.3. /trades/delete/{portfolio_id} : It is used to delete an existing trade, if exist in the system.
			1.4. /trades/getAll : It is used to fetch all trades in the system.

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
    1. All required curl commands have been kept in a file "ProtfolioService_cmd.txt" in the root folder of project.