package com.example.friends.service;

import com.example.friends.domain.Block;
import com.example.friends.domain.Person;
import com.example.friends.repository.BlockRepository;
import com.example.friends.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks(){
        return personRepository.findByBlockIsNull();
    }

    public List<Person> getPeopleByName(String name){
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).orElse(null);

        log.info("person : {}", person);

        return person;
    }
}
