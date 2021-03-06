package com.example.friends.repository;

import com.example.friends.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>{
    List<Person> findByName(String name);

    // assignment
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :month and person.birthday.dayOfBirthday = :day")
    List<Person> findByBirthday(@Param("month") int month, @Param("day") int day);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from Person person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}
