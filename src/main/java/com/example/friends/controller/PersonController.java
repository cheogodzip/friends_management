package com.example.friends.controller;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.repository.PersonRepository;
import com.example.friends.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/api/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person){
        personService.put(person);
        log.info("person -> {} ", personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto persondto){
        personService.modify(id, persondto);
        log.info("person -> {} ", personRepository.findAll());
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id, name);
        log.info("person -> {} ", personRepository.findAll());
    }
}