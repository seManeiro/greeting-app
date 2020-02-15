# Telenor Greeting Test application:

###To build this application you will need to have installed localy:

- Java java 11.0.6 or later

- Apache Maven 3.3.9 or later


###Download the source code and cd into the project directory from your command line,

###run this commands:

- mvn compile

###This is just to verify everything is compiling correctly in your env.


- mvn package

###This command will create a .jar file to start the application.



###From the same directory you are standing run now this command:

- java -jar target/telenor-application-0.0.1-SNAPSHOT.jar

##The application should be up and running

###Testing examples:

##Run this Curl request from the command line:

##Personal test cases:

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"id": 123}' 'http://localhost:5000/greeting/personal/'

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"id": -123}' 'http://localhost:5000/greeting/personal/'

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"id": "error"}' 'http://localhost:5000/greeting/personal/'



##Business test cases:

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"type": "big"}' 'http://localhost:5000/greeting/business/'

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"type": "small"}' 'http://localhost:5000/greeting/business/'

curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"type": "error"}' 'http://localhost:5000/greeting/business/'



###You can also access Swagger to run  test request using Swagger:

http://localhost:5000/swagger-ui.html#
