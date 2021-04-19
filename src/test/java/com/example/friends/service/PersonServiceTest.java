package com.example.friends.service;

import com.example.friends.domain.Block;
import com.example.friends.domain.Person;
import com.example.friends.repository.BlockRepository;
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
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        List<Person> result = personService.getPeopleExcludeBlocks();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");
    }

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
        givenPerson("martin", "A");
        givenPerson("david", "B");
        givenBlockPerson("dennis", "O");
        givenBlockPerson("martin",  "AB");

    }

    private void givenBlockPerson(String name, String bloodType){
        Person blockPerson = new Person(name, bloodType);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }

    private void givenPerson(String name,  String bloodType) {
        personRepository.save(new Person(name, bloodType));
    }
}