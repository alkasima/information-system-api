package com.personmanagement.personmanagement.controller;

import com.personmanagement.personmanagement.entity.PersonEntity;
import com.personmanagement.personmanagement.exception.DataResponse;
import com.personmanagement.personmanagement.exception.DeleteResponse;
import com.personmanagement.personmanagement.exception.ErrorResponse;
import com.personmanagement.personmanagement.exception.ResourceException;
import com.personmanagement.personmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Optional<PersonEntity> person = personService.getPersonById(id);

        if (person.isPresent()) {
            // If a person with the given ID is found, return it
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        } else {
            // If no person with the given ID is found, return a "not found" response
            return new ResponseEntity<>("Person with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody @Valid PersonEntity person) {

        try {
            // Validate the firstName and lastName fields
            if (!person.getFirstName().matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("First name must be a string");
            }

            if (!person.getLastName().matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Last name must be a string");
            }

            // Validate the email field
            if (!person.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$")) {
                throw new IllegalArgumentException("Email address is invalid");
            }

            // Validate the phoneNumber field
            if (!person.getPhoneNumber().matches("^\\d{11}$")) {
                throw new IllegalArgumentException("Phone number must be 11 digits");
            }

            PersonEntity createdPerson = personService.createPerson(person);
            String successMessage = "Saved successfully";

            DataResponse<PersonEntity> response = new DataResponse<>(successMessage, createdPerson);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // If any validation fails, return a JSON response with the error message
            String errorMessage = e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions and return a JSON response with a generic error message
            ErrorResponse errorResponse = new ErrorResponse("An error occurred");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonEntity updatedPerson) {

        try {
        // Attempt to update the person with the given ID
        PersonEntity person = personService.updatePerson(id, updatedPerson);


        if (person == null) {
            // If the person with the given ID does not exist, throw an exception
            throw new ResourceException("Person with ID " + id + " not found");
        }

        String successMessage = "Updated successfully";

        DataResponse<PersonEntity> response = new DataResponse<>(successMessage, person);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // If any validation fails, return a JSON response with the error message
            String errorMessage = e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other exceptions and return a JSON response with a generic error message
            ErrorResponse errorResponse = new ErrorResponse("Person with ID " + id + " not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        try {
           personService.deletePerson(id);

            String successMessage = "Person with ID " + id + " deleted successfully";

            DeleteResponse response = new DeleteResponse(successMessage);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (ResourceException e) {
            // Handle the exception and return a custom JSON response
            ErrorResponse errorResponse = new ErrorResponse("Person with ID " + id + " not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
