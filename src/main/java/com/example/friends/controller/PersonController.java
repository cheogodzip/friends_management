package com.example.friends.controller;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.exception.PersonNotFoundException;
import com.example.friends.exception.RenameIsNotPermittedException;
import com.example.friends.exception.dto.ErrorResponse;
import com.example.friends.repository.PersonRepository;
import com.example.friends.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping(value = "/api/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonDto personDto){
        personService.put(personDto);
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id, personDto);
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id, name);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);
    }
}
