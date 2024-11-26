package com.colak.springtutorial.controller;

import com.colak.dto.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @PostMapping("/post")
    public ResponseEntity<String> uploadPerson(@RequestBody Person person) {
        // Process the person object as needed
        return ResponseEntity.ok("Received person: " + person.getName());
    }

    @GetMapping("/get")
    public ResponseEntity<Person> downloadPerson() {
        Person person = Person.newBuilder()
                .setName("John Doe")
                .setAge(30)
                .build();
        return ResponseEntity.ok(person);
    }
}