package ru.job4j.service;

import ru.job4j.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> save(Person user);

    Optional<Person> findById(int id);

    List<Person> findAll();

    boolean delete(Person person);
}