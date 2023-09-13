Information System API
The Information System API is a RESTful web service that provides access to essential information about individuals. It allows users to perform CRUD (Create, Read, Update, Delete) operations on person records, including their names, ages, and contact information.

Key Features
Create: Create new person records by providing their first_name, last_name, age, gender, date_of_birth, phone_number and email address.
Read: Retrieve information about individuals using their unique identifiers (IDs).
Update: Modify existing person records to keep the data up-to-date.
Delete: Remove person records from the system.
API Endpoints
POST /api: Create a new person record.
GET /api/{id}: Retrieve information about an individual by their ID.
PUT /api/{id}: Update an existing person's information.
DELETE /api/{id}: Delete a person record.
Error Handling
The API provides detailed error messages in case of invalid requests or server errors. Proper error codes and descriptions are included to aid developers in troubleshooting.

Note:

The First Name and Last Name accept only string you cannot use other data type

TO RUN THIS APPLICATION ON LOCALHOST
Running a Spring Boot Application with PostgreSQL
Follow these steps to run this Spring Boot application on your localhost with PostgreSQL as the database:

Set Up PostgreSQL Database:

Install PostgreSQL from here and follow the installation instructions for your operating system.
Create a PostgreSQL database for your Spring Boot application using createdb or a PostgreSQL client like pgAdmin.
Spring Boot Application Configuration:

Open Spring Boot application's application.properties or application.yml file and configure the database connection properties:

# PostgreSQL database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password

# Other Spring Boot configuration properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Replace the following placeholders:

your-database-name: The name of the PostgreSQL database you created. your-username: Your PostgreSQL database username. your-password: Your PostgreSQL database password. Customize other Spring Boot properties as needed.

Run the Spring Boot Application:

Start this Spring Boot application using your preferred development environment (IDE) or the command line. If you have the Maven Wrapper, you can use the ./mvnw spring-boot:run command.

Access the Application:

Open a web browser and navigate to http://localhost:8080/api (assuming your application runs on port 8080 by default). You can access specific endpoints defined in documentatio of this projectas well from here.

Make sure to replace the placeholders in the application.properties file with your actual PostgreSQL database credentials and database name. This Spring Boot application should now be running locally with PostgreSQL as the database.

FOR COMPLETE ENDPOINT DOCUMENTATION
here
