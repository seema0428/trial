TodoItem API Service:

This a simple REST service, It allows to perform CRUD actions on basic resources, like TodoItem entity, and retrieve them as JSON respond.

How to Build:
clone the repository $ git clone https://github.com/seema0428/trial.git
then project-maven-updateproject

$ mvn clean install

How to run?

clone the repository $ git clone https://github.com/seema0428/trial.git

run this class as java application

@SpringBootApplication
@EntityScan("com.todoitem.todoitem.model")
public class TodoitemApplication  {

	
	private static final Logger log = LoggerFactory.getLogger(TodoitemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TodoitemApplication.class, args);
	}
	
 Go to browser=:http://localhost:8080/api/todosItems

To run the testcases: $ mvn test


This class will run all the test cases unit and integration testing.


Documentation:
 The generation of API documentation springdoc-openapi with Open Api(3.0.1) implementation was used.

When the application is running, type in a browser:
http://localhost:8080/swagger-ui-custom.html

Technology stack:
Java 8
Spring Boot
Spring DataJpa
Swagger
Tomcat server (for local deployment)
JUnit
maven
H2 DB



