package com.example.smartagenda.repository;

import com.example.smartagenda.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private static List<Person> personList = new ArrayList<>();

    public PersonRepository() {
        Person p1 = new Person("p1 first name", "p1 last name", "0765434567", "test", 23);
        Person p2 = new Person("p2 first name", "p2 last name", "0765676667", "test", 34);
        Person p3 = new Person("p3 first name", "p3 last name", "0765670067", "test",12);

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
    }

    public List<Person> retrieveAllPerson() {
        return personList;
    }

    public String addPerson(Person person) {
        personList.add(person);
        return "A new person has been added with success!";
    }

    public String deletePerson(String firstName) {
        personList = personList.stream()
                .filter(person -> !person.getFirstName().equals(firstName))
                .collect(Collectors.toList());
        return "Person with name" + firstName + "has been deleted";
    }

}
