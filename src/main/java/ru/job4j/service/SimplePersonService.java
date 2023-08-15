package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(SimplePersonService.class.getName());

    private final PersonRepository personRepository;

    @Override
    public Optional<Person> save(Person user) {
        Optional<Person> result = Optional.empty();
        try {
            result = Optional.of(personRepository.save(user));
        } catch (Exception exception) {
            LOG.error("Error message: " + exception.getMessage(), exception);
        }
        return result;
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public boolean delete(Person person) {
        var result = true;
        try {
            personRepository.delete(person);
        } catch (Exception exception) {
            LOG.error("Error message: " + exception.getMessage(), exception);
            result = false;
        }
        return result;
    }
}
