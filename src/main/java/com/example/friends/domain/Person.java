package com.example.friends.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue()
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }
        Person person = (Person)object;

        if(!person.getName().equals(this.getName())){
            return false;
        }

        if (person.getAge() != (this.getAge())) {
            return false;
        }

        return true;
    }
}