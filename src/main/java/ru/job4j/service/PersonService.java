package ru.job4j.service;

import ru.job4j.model.Person;
import ru.job4j.model.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> save(Person user);

    Optional<Person> findById(int id);

    List<Person> findAll();

    boolean deleteById(int id);

    boolean update(Person person);

    boolean updatePersonPartially(PersonDTO personDTO);

    public Person findByLogin(String login);
}
