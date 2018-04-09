# Parser
Simple parser to search for given queries in a particular log file.

# Prerequisites
You need to create the necessary database and table before running the project. For that locate the createTable.sql under the src/main/resources directory. You need to have some MySql instance running in your machine.

# How to run the project
Check out the project and then run this mvn command.
mvn clean install.

It will download all the necessary jar files to your local repository.
Import the project to the eclipse IDE. 

Find the config.properties file under the src/main/resources where you need to give all the DB related properties such as connectionUrl, username and password. Change these properties according to your environment before running the application.

Then locate the main class which is org.wallet.App. Then run it as a main application. If you want to change the input values please feel free to do so.

The answers to the SQL queries can be found at the queries.sql file under the src/main.resources directory.