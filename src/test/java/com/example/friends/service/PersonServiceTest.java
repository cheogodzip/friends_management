package com.example.friends.service;

import com.example.friends.domain.Person;
import com.example.friends.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void getPeopleByName(){
        List<Person> result = personService.getPeopleByName("martin");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }

    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);

        personRepository.findAll().forEach(System.out::println);

        assertThat(person.getName()).isEqualTo("dennis");
    }

    private void givenPeople() {
        givenPerson("martin");
        givenPerson("david");
        givenPerson("dennis");
        givenPerson("martin");

    }

    private void givenPerson(String name) {
        personRepository.save(new Person(name));
    }
}