package com.example.friends.repository;

import com.example.friends.domain.Person;
import com.example.friends.domain.dto.Birthday;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findByName(){
        List<Person> people = personRepository.findByName("tony");
        assertThat(people.size()).isEqualTo(1);

        Person person = people.get(0);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> org.assertj.core.api.Assertions.assertThat(person.getName()).isEqualTo("tony"),
                () -> org.assertj.core.api.Assertions.assertThat(person.getHobby()).isEqualTo("reading"),
                () -> org.assertj.core.api.Assertions.assertThat(person.getAddress()).isEqualTo("서울"),
                () -> org.assertj.core.api.Assertions.assertThat(person.getBirthday()).isEqualTo(Birthday.of(LocalDate.of(1991, 7, 10))),
                () -> org.assertj.core.api.Assertions.assertThat(person.getJob()).isEqualTo("officer"),
                () -> org.assertj.core.api.Assertions.assertThat(person.getPhoneNumber()).isEqualTo("010-2222-5555"),
                () -> org.assertj.core.api.Assertions.assertThat(person.isDeleted()).isEqualTo(false)
        );
    }

    @Test
    void findByNameIfDeleted(){
        List<Person> people = personRepository.findByName("andrew");

        assertThat(people.size()).isEqualTo(0);
    }

    @Test
    void findByMonthOfBirthday(){
        List<Person> people = personRepository.findByMonthOfBirthday(7);

        assertThat(people.size()).isEqualTo(2);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> org.assertj.core.api.Assertions.assertThat(people.get(0).getName()).isEqualTo("david"),
                () -> org.assertj.core.api.Assertions.assertThat(people.get(1).getName()).isEqualTo("tony")
        );
    }

    @Test
    void findPeopleDeleted(){
        List<Person> people = personRepository.findPeopleDeleted();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("andrew");
    }
}