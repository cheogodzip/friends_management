package com.example.friends.service;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.exception.PersonNotFoundException;
import com.example.friends.exception.RenameIsNotPermittedException;
import com.example.friends.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public List<Person> getPeopleByName(String name){
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);
    }

    // assignment
    public List<Person> getBirthdayFriends() {
        List<Person> today = personRepository.findByBirthday(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        List<Person> tommorrow = personRepository.findByBirthday(LocalDate.now().plusDays(1).getMonthValue(), LocalDate.now().plusDays(1).getDayOfMonth());

        today.addAll(tommorrow);

        return today;
    }

    @Transactional
    public void put(PersonDto personDto){
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if (!person.getName().equals(personDto.getName())){
            throw new RenameIsNotPermittedException();
        }

        person.set(personDto);
        personRepository.save(person);
    }

    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        person.setName(name);

        personRepository.save(person);
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setDeleted(true);
        personRepository.save(person);
    }
}
