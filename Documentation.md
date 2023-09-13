# Information System API

The Information System API is a RESTful web service that provides access to essential information about individuals. It allows users to perform CRUD (Create, Read, Update, Delete) operations on person records, including their names, ages, and contact information.

## Key Features

- **Create**: Create new person records by providing their first_name, last_name, age, gender, date_of_birth, phone_number and email address.
- **Read**: Retrieve information about individuals using their unique identifiers (IDs).
- **Update**: Modify existing person records to keep the data up-to-date.
- **Delete**: Remove person records from the system.
    

## API Endpoints

- **POST /api**: Create a new person record.
- **GET /api/{id}**: Retrieve information about an individual by their ID.
- **PUT /api/{id}**: Update an existing person's information.
- **DELETE /api/{id}**: Delete a person record.
    

## Error Handling

The API provides detailed error messages in case of invalid requests or server errors. Proper error codes and descriptions are included to aid developers in troubleshooting.

Note:

The First Name and Last Name accept only string you cannot use other data type

## TO RUN THIS APPLICATION ON LOCALHOST
## Running a Spring Boot Application with PostgreSQL

Follow these steps to run this Spring Boot application on your localhost with PostgreSQL as the database:

1. **Set Up PostgreSQL Database**:

   - Install PostgreSQL from [here](https://www.postgresql.org/download/) and follow the installation instructions for your operating system.
   - Create a PostgreSQL database for your Spring Boot application using `createdb` or a PostgreSQL client like pgAdmin.

2. **Spring Boot Application Configuration**:

   Open Spring Boot application's `application.properties` or `application.yml` file and configure the database connection properties:

   ```properties
   # PostgreSQL database configuration
   spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
   spring.datasource.username=your-username
   spring.datasource.password=your-password

   # Other Spring Boot configuration properties
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
Replace the following placeholders:

your-database-name: The name of the PostgreSQL database you created.
your-username: Your PostgreSQL database username.
your-password: Your PostgreSQL database password.
Customize other Spring Boot properties as needed.

Run the Spring Boot Application:

Start this Spring Boot application using your preferred development environment (IDE) or the command line. If you have the Maven Wrapper, you can use the ./mvnw spring-boot:run command.

Access the Application:

Open a web browser and navigate to http://localhost:8080/api (assuming your application runs on port 8080 by default). You can access specific endpoints defined in documentatio of this projectas well from [here](https://documenter.getpostman.com/view/18112964/2s9YC4UD7X#afdfb0c8-dff0-4148-a68f-6b814d3587b7).

Make sure to replace the placeholders in the application.properties file with your actual PostgreSQL database credentials and database name. 
This Spring Boot application should now be running locally with PostgreSQL as the database.


## Test Script Code for testing this API
#Create Test Script
// Check if the response status code is 201 Created
pm.test("Status code is 201 Created", function () {
    pm.response.to.have.status(201);
});

// Check if the response body contains the newly created person's first name
pm.test("Response body contains 'Mark'", function () {
    pm.expect(pm.response.text()).to.include("Mark");
});

// Check if the response body contains the newly created person's last name
pm.test("Response body contains 'Essein'", function () {
    pm.expect(pm.response.text()).to.include("Essien");
});

#Test Result
PASS
Status code is 201 Created
FAIL
Response body contains 'Mark' | AssertionError: expected '{"message":"Saved successfully","data…' to include 'Mark'
PASS
Response body contains 'Essein'

#Read Test Script
// Check if the response status code is 200 OK
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});

// Check if the response body is not empty
pm.test("Response body is not empty", function () {
    pm.expect(pm.response.text()).not.to.be.empty;
});

// Parse the response JSON
var responseBody = pm.response.json();

// Check if the response JSON contains the first name and last name
pm.test("Response contains first name 'Mark'", function () {
    pm.expect(responseBody.firstName).to.eql("Mark");
});

pm.test("Response contains last name 'Essien'", function () {
    pm.expect(responseBody.lastName).to.eql("Essien");
});

#Result 
PASS
Status code is 200 OK
PASS
Response body is not empty
PASS
Response contains first name 'Mark'
PASS
Response contains last name 'Essien'

# Update Test Script
// Check if the response status code is 200 OK 
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});

// Check if the response body is not empty
pm.test("Response body is not empty", function () {
    pm.expect(pm.response.text()).not.to.be.empty;
});

// Check if the response body contains a success message
pm.test("Response body contains 'Updated successfully'", function () {
    pm.expect(pm.response.text()).to.include("Updated successfully");
});

#Result
PASS
Status code is 200 OK
PASS
Response body is not empty
PASS
Response body contains 'Updated successfully'

# Delete Test Script
// Check if the response status code is 204 No Content
pm.test("Status code is 204 No Content", function () {
    pm.response.to.have.status(204);
});

// Check if the response body is empty
pm.test("Response body is empty", function () {
    pm.expect(pm.response.text()).to.be.empty;
});

#Result
PASS
Status code is 204 No Content
PASS
Response body is empty


## For Complete Documentation on Endpoint
[here](https://documenter.getpostman.com/view/18112964/2s9YC4UD7X#afdfb0c8-dff0-4148-a68f-6b814d3587b7)

